package pwd.initializr.common.mw.monitor.index;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;
import pwd.initializr.monitor.rpc.RPCServer;
import pwd.initializr.monitor.rpc.RPCServerCpu;
import pwd.initializr.monitor.rpc.RPCServerCpuCore;
import pwd.initializr.monitor.rpc.RPCServerCpuCoreUsage;
import pwd.initializr.monitor.rpc.RPCServerDisk;
import pwd.initializr.monitor.rpc.RPCServerDiskUsage;
import pwd.initializr.monitor.rpc.RPCServerEthernet;
import pwd.initializr.monitor.rpc.RPCServerEthernetStat;
import pwd.initializr.monitor.rpc.RPCServerMemory;
import pwd.initializr.monitor.rpc.RPCServerMemorySwap;
import pwd.initializr.monitor.rpc.RPCServerOS;
import pwd.initializr.monitor.rpc.RPCServerWho;

/**
 * pwd.initializr.common.mw.monitor.index@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-20 21:29
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Server {

    private static final OperatingSystem OS = OperatingSystem.getInstance();
    private static final Sigar SIGAR = new Sigar();

    public static RPCServer server() {
        Map<String, String> map = System.getenv();
        RPCServer rpcServer = new RPCServer();
        rpcServer.setComputerName(map.get("COMPUTERNAME"));
        rpcServer.setUserDomain(map.get("USERDOMAIN"));
        rpcServer.setUserName(map.get("USERNAME"));
        return rpcServer;
    }

    private static List<RPCServerWho> who() {
        List<RPCServerWho> rpcServerWhos = new LinkedList<>();
        try {
            Who whos[] = SIGAR.getWhoList();
            if (whos != null && whos.length > 0) {
                for (int i = 0; i < whos.length; i++) {
                    Who who = whos[i];
                    RPCServerWho rpcServerWho = new RPCServerWho();
                    rpcServerWho.setUser(who.getUser());
                    rpcServerWho.setHost(who.getHost());
                    rpcServerWho.setDevice(who.getDevice());
                    rpcServerWho.setTime(who.getTime());
                    rpcServerWhos.add(rpcServerWho);
                }
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerWhos;
    }

    public static RPCServerOS os() {
        RPCServerOS rpcServerOS = new RPCServerOS();
        rpcServerOS.setName(OS.getName());
        rpcServerOS.setVersion(OS.getVersion());
        rpcServerOS.setArch(OS.getArch());
        rpcServerOS.setDescription(OS.getDescription());
        rpcServerOS.setMachine(OS.getMachine());
        rpcServerOS.setPatchLevel(OS.getPatchLevel());
        rpcServerOS.setCpuEndian(OS.getCpuEndian());
        rpcServerOS.setDataModel(OS.getDataModel());
        rpcServerOS.setVendor(OS.getVendor());
        rpcServerOS.setVendorCodeName(OS.getVendorCodeName());
        rpcServerOS.setVendorName(OS.getVendorName());
        rpcServerOS.setVendorVersion(OS.getVendorVersion());
        return rpcServerOS;
    }

    public static RPCServerCpu cpu() {
        RPCServerCpu rpcServerCpu = new RPCServerCpu();
        new LinkedList<>();
        try {
            Cpu cpu = SIGAR.getCpu();
            rpcServerCpu.setUser(cpu.getUser());
            rpcServerCpu.setSys(cpu.getSys());
            rpcServerCpu.setNice(cpu.getNice());
            rpcServerCpu.setIdle(cpu.getIdle());
            rpcServerCpu.setWait(cpu.getWait());
            rpcServerCpu.setIrq(cpu.getIrq());
            rpcServerCpu.setSoftIrq(cpu.getSoftIrq());
            rpcServerCpu.setStolen(cpu.getStolen());
            rpcServerCpu.setTotal(cpu.getTotal());
            //System.out.println("cpu总百分比情况 " + SIGAR.getCpuPerc());
            LinkedList<RPCServerCpuCore> rpcServerCpuCores = cpuCore();
            rpcServerCpu.setCore(rpcServerCpuCores);
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerCpu;
    }

    public static LinkedList<RPCServerCpuCore> cpuCore() {
        RPCServerCpu rpcServerCpu = new RPCServerCpu();
        LinkedList<RPCServerCpuCore> rpcServerCpuCores = new LinkedList<>();
        try {
            CpuInfo[] cpuInfoList = SIGAR.getCpuInfoList();
            for (int i = 0; i < cpuInfoList.length; i++) {
                CpuInfo info = cpuInfoList[i];
                RPCServerCpuCore rpcServerCpuCore = new RPCServerCpuCore();
                rpcServerCpuCore.setVendor(info.getVendor());
                rpcServerCpuCore.setModel(info.getModel());
                rpcServerCpuCore.setCacheSize(info.getCacheSize());
                rpcServerCpuCore.setMhz(info.getMhz());
                rpcServerCpuCore.setTotalCores(info.getTotalCores());
                rpcServerCpuCore.setTotalSockets(info.getTotalSockets());
                rpcServerCpuCore.setCoresPerSocket(info.getCoresPerSocket());
                rpcServerCpuCores.add(rpcServerCpuCore);
            }
            rpcServerCpu.setCore(rpcServerCpuCores);
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerCpuCores;
    }

    public static LinkedList<RPCServerCpuCoreUsage> cpuCoreUsage() {
        LinkedList<RPCServerCpuCoreUsage> rpcServerCpuCoreUsages = new LinkedList<>();
        try {
            CpuPerc[] cpuList = SIGAR.getCpuPercList();
            for (int i = 0; i < cpuList.length; i++) {
                RPCServerCpuCoreUsage rpcServerCpuCoreUsage = new RPCServerCpuCoreUsage();
                CpuPerc cpuPerc = cpuList[i];
                rpcServerCpuCoreUsage.setUser(CpuPerc.format(cpuPerc.getUser()));
                rpcServerCpuCoreUsage.setSys(CpuPerc.format(cpuPerc.getSys()));
                rpcServerCpuCoreUsage.setNice(CpuPerc.format(cpuPerc.getNice()));
                rpcServerCpuCoreUsage.setIdle(CpuPerc.format(cpuPerc.getIdle()));
                rpcServerCpuCoreUsage.setWait(CpuPerc.format(cpuPerc.getWait()));
                rpcServerCpuCoreUsage.setIrq(CpuPerc.format(cpuPerc.getIrq()));
                rpcServerCpuCoreUsage.setSoftIrq(CpuPerc.format(cpuPerc.getSoftIrq()));
                rpcServerCpuCoreUsage.setStolen(CpuPerc.format(cpuPerc.getStolen()));
                rpcServerCpuCoreUsage.setCombined(CpuPerc.format(cpuPerc.getCombined()));
                rpcServerCpuCoreUsages.add(rpcServerCpuCoreUsage);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerCpuCoreUsages;
    }

    public static RPCServerMemory memory() {
        RPCServerMemory rpcServerMemory = new RPCServerMemory();
        try {
            Mem mem = SIGAR.getMem();
            rpcServerMemory.setTotal(mem.getTotal());
            rpcServerMemory.setRam(mem.getRam());
            rpcServerMemory.setUsed(mem.getUsed());
            rpcServerMemory.setFree(mem.getFree());
            rpcServerMemory.setActualUsed(mem.getActualUsed());
            rpcServerMemory.setActualFree(mem.getActualFree());
            rpcServerMemory.setUsedPercent(mem.getUsedPercent());
            rpcServerMemory.setFreePercent(mem.getFreePercent());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerMemory;
    }

    public static RPCServerMemorySwap swap() {
        RPCServerMemorySwap rpcServerMemorySwap = new RPCServerMemorySwap();
        try {
            Swap swap = SIGAR.getSwap();
            rpcServerMemorySwap.setTotal(swap.getTotal());
            rpcServerMemorySwap.setUsed(swap.getUsed());
            rpcServerMemorySwap.setFree(swap.getFree());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerMemorySwap;
    }

    public static List<RPCServerDisk> disk() {
        List<RPCServerDisk> rpcServerDisks = new LinkedList<>();
        try {
            FileSystem[] fileSystemList = SIGAR.getFileSystemList();
            for (int i = 0; i < fileSystemList.length; i++) {
                RPCServerDisk rpcServerDisk = new RPCServerDisk();
                FileSystem fs = fileSystemList[i];
                rpcServerDisk.setDevName(fs.getDevName());
                rpcServerDisk.setDirName(fs.getDirName());
                rpcServerDisk.setFlags(fs.getFlags());
                rpcServerDisk.setSysTypeName(fs.getSysTypeName());
                rpcServerDisk.setTypeName(fs.getTypeName());
                rpcServerDisk.setType(fs.getType());
                rpcServerDisk.setOptions(fs.getOptions());
                rpcServerDisks.add(rpcServerDisk);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerDisks;
    }

    public static List<RPCServerDiskUsage> diskUsage() {
        List<RPCServerDiskUsage> rpcServerDisks = new LinkedList<>();
        try {
            FileSystem[] fileSystemList = SIGAR.getFileSystemList();
            for (int i = 0; i < fileSystemList.length; i++) {
                FileSystem fs = fileSystemList[i];
                RPCServerDiskUsage rpcServerDiskUsage = diskUsage(fs.getDirName());
                rpcServerDisks.add(rpcServerDiskUsage);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerDisks;
    }

    public static RPCServerDiskUsage diskUsage(String dirName) {
        RPCServerDiskUsage rpcServerDiskUsage = new RPCServerDiskUsage();
        try {
            FileSystemUsage fileSystemUsage = SIGAR.getFileSystemUsage(dirName);
            rpcServerDiskUsage.setTotal(fileSystemUsage.getTotal());
            rpcServerDiskUsage.setFree(fileSystemUsage.getFree());
            rpcServerDiskUsage.setUsed(fileSystemUsage.getUsed());
            rpcServerDiskUsage.setAvail(fileSystemUsage.getAvail());
            rpcServerDiskUsage.setFiles(fileSystemUsage.getFiles());
            rpcServerDiskUsage.setFreeFiles(fileSystemUsage.getFreeFiles());
            rpcServerDiskUsage.setDiskReads(fileSystemUsage.getDiskReads());
            rpcServerDiskUsage.setDiskWrites(fileSystemUsage.getDiskWrites());
            rpcServerDiskUsage.setDiskReadBytes(fileSystemUsage.getDiskReadBytes());
            rpcServerDiskUsage.setDiskWriteBytes(fileSystemUsage.getDiskWriteBytes());
            rpcServerDiskUsage.setDiskQueue(fileSystemUsage.getDiskQueue());
            rpcServerDiskUsage.setDiskServiceTime(fileSystemUsage.getDiskServiceTime());
            rpcServerDiskUsage.setUsePercent(fileSystemUsage.getUsePercent());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerDiskUsage;
    }

    public static List<RPCServerEthernet> ethernet() {
        List<RPCServerEthernet> rpcServerEthernets = new LinkedList<>();
        try {
            String[] netInterfaceList = SIGAR.getNetInterfaceList();
            for (int i = 0; i < netInterfaceList.length; i++) {
                RPCServerEthernet rpcServerEthernet = new RPCServerEthernet();
                String name = netInterfaceList[i];
                NetInterfaceConfig cfg = SIGAR.getNetInterfaceConfig(name);
                if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
                    || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                    continue;
                }
                rpcServerEthernet.setName(cfg.getName());
                rpcServerEthernet.setHwaddr(cfg.getHwaddr());
                rpcServerEthernet.setType(cfg.getType());
                rpcServerEthernet.setDescription(cfg.getDescription());
                rpcServerEthernet.setAddress(cfg.getAddress());
                rpcServerEthernet.setDescription(cfg.getDescription());
                rpcServerEthernet.setBroadcast(cfg.getBroadcast());
                rpcServerEthernet.setNetmask(cfg.getNetmask());
                rpcServerEthernet.setMtu(cfg.getMtu());
                rpcServerEthernet.setMetric(cfg.getMetric());
                rpcServerEthernets.add(rpcServerEthernet);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerEthernets;
    }

    public static List<RPCServerEthernetStat> ethernetStat() {
        List<RPCServerEthernetStat> rpcServerEthernetStats = new LinkedList<>();
        try {
            String[] netInterfaceList = SIGAR.getNetInterfaceList();
            for (int i = 0; i < netInterfaceList.length; i++) {
                String name = netInterfaceList[i];
                RPCServerEthernetStat rpcServerEthernetStat = ethernetStat(name);
                rpcServerEthernetStats.add(rpcServerEthernetStat);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerEthernetStats;
    }

    public static RPCServerEthernetStat ethernetStat(String name) {
        RPCServerEthernetStat rpcServerEthernetStat = new RPCServerEthernetStat();
        try {
            NetInterfaceConfig cfg = SIGAR.getNetInterfaceConfig(name);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
                || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                return rpcServerEthernetStat;
            }

            NetInterfaceStat netInterfaceStat = SIGAR.getNetInterfaceStat(name);
            rpcServerEthernetStat.setRxBytes(netInterfaceStat.getRxBytes());
            rpcServerEthernetStat.setRxPackets(netInterfaceStat.getRxPackets());
            rpcServerEthernetStat.setRxErrors(netInterfaceStat.getRxErrors());
            rpcServerEthernetStat.setRxDropped(netInterfaceStat.getRxDropped());
            rpcServerEthernetStat.setRxOverruns(netInterfaceStat.getRxOverruns());
            rpcServerEthernetStat.setRxFrame(netInterfaceStat.getRxFrame());
            rpcServerEthernetStat.setTxBytes(netInterfaceStat.getTxBytes());
            rpcServerEthernetStat.setTxPackets(netInterfaceStat.getTxPackets());
            rpcServerEthernetStat.setTxErrors(netInterfaceStat.getTxErrors());
            rpcServerEthernetStat.setTxDropped(netInterfaceStat.getTxDropped());
            rpcServerEthernetStat.setTxOverruns(netInterfaceStat.getTxOverruns());
            rpcServerEthernetStat.setTxCollisions(netInterfaceStat.getTxCollisions());
            rpcServerEthernetStat.setTxCarrier(netInterfaceStat.getTxCarrier());
            rpcServerEthernetStat.setSpeed(netInterfaceStat.getSpeed());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerEthernetStat;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        RPCServer server = Server.server();
        RPCServerOS os = Server.os();
        List<RPCServerWho> who = Server.who();
        RPCServerCpu cpu = Server.cpu();
        LinkedList<RPCServerCpuCore> rpcServerCpuCores = Server.cpuCore();
        LinkedList<RPCServerCpuCoreUsage> rpcServerCpuCoreUsages = Server.cpuCoreUsage();
        RPCServerMemory memory = Server.memory();
        RPCServerMemorySwap swap = Server.swap();
        List<RPCServerDisk> disk = Server.disk();
        List<RPCServerDiskUsage> rpcServerDiskUsages = Server.diskUsage();
        List<RPCServerEthernet> ethernet = Server.ethernet();
        List<RPCServerEthernetStat> rpcServerEthernetStats = Server.ethernetStat();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}
