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
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcCredName;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.ProcTime;
import org.hyperic.sigar.ProcUtil;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;
import org.hyperic.sigar.cmd.Ps;
import pwd.initializr.monitor.rpc.RPCHostCpu;
import pwd.initializr.monitor.rpc.RPCHostCpuCore;
import pwd.initializr.monitor.rpc.RPCHostCpuCoreUsage;
import pwd.initializr.monitor.rpc.RPCHostDisk;
import pwd.initializr.monitor.rpc.RPCHostDiskUsage;
import pwd.initializr.monitor.rpc.RPCHostEthernet;
import pwd.initializr.monitor.rpc.RPCHostEthernetStat;
import pwd.initializr.monitor.rpc.RPCHostMemory;
import pwd.initializr.monitor.rpc.RPCHostMemorySwap;
import pwd.initializr.monitor.rpc.RPCHostOS;
import pwd.initializr.monitor.rpc.RPCHostProcess;
import pwd.initializr.monitor.rpc.RPCHostProcessCpu;
import pwd.initializr.monitor.rpc.RPCHostProcessCredName;
import pwd.initializr.monitor.rpc.RPCHostProcessMemory;
import pwd.initializr.monitor.rpc.RPCHostProcessState;
import pwd.initializr.monitor.rpc.RPCHostProcessTime;
import pwd.initializr.monitor.rpc.RPCHostWho;

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
public class Host {

    private static final OperatingSystem OS = OperatingSystem.getInstance();
    private static final Sigar SIGAR = new Sigar();

    private static String id;

    public static String getId() {
        if (id == null) {
            id = server();
        }
        return id;
    }

    private static String server() {
        Map<String, String> map = System.getenv();
        return map.get("COMPUTERNAME");
    }

    private static List<RPCHostWho> who() {
        List<RPCHostWho> rpcHostWhos = new LinkedList<>();
        try {
            Who whos[] = SIGAR.getWhoList();
            if (whos != null && whos.length > 0) {
                for (int i = 0; i < whos.length; i++) {
                    Who who = whos[i];
                    RPCHostWho rpcHostWho = new RPCHostWho();
                    rpcHostWho.setId(getId());
                    rpcHostWho.setUser(who.getUser());
                    rpcHostWho.setHost(who.getHost());
                    rpcHostWho.setDevice(who.getDevice());
                    rpcHostWho.setTime(who.getTime());
                    rpcHostWhos.add(rpcHostWho);
                }
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostWhos;
    }

    public static RPCHostOS os() {
        RPCHostOS rpcHostOS = new RPCHostOS();
        rpcHostOS.setId(getId());
        rpcHostOS.setName(OS.getName());
        rpcHostOS.setVersion(OS.getVersion());
        rpcHostOS.setArch(OS.getArch());
        rpcHostOS.setDescription(OS.getDescription());
        rpcHostOS.setMachine(OS.getMachine());
        rpcHostOS.setPatchLevel(OS.getPatchLevel());
        rpcHostOS.setCpuEndian(OS.getCpuEndian());
        rpcHostOS.setDataModel(OS.getDataModel());
        rpcHostOS.setVendor(OS.getVendor());
        rpcHostOS.setVendorCodeName(OS.getVendorCodeName());
        rpcHostOS.setVendorName(OS.getVendorName());
        rpcHostOS.setVendorVersion(OS.getVendorVersion());
        return rpcHostOS;
    }

    public static RPCHostCpu cpu() {
        RPCHostCpu rpcHostCpu = new RPCHostCpu();
            rpcHostCpu.setId(getId());
        new LinkedList<>();
        try {
            Cpu cpu = SIGAR.getCpu();
            rpcHostCpu.setUser(cpu.getUser());
            rpcHostCpu.setSys(cpu.getSys());
            rpcHostCpu.setNice(cpu.getNice());
            rpcHostCpu.setIdle(cpu.getIdle());
            rpcHostCpu.setWait(cpu.getWait());
            rpcHostCpu.setIrq(cpu.getIrq());
            rpcHostCpu.setSoftIrq(cpu.getSoftIrq());
            rpcHostCpu.setStolen(cpu.getStolen());
            rpcHostCpu.setTotal(cpu.getTotal());
            //System.out.println("cpu总百分比情况 " + SIGAR.getCpuPerc());
            LinkedList<RPCHostCpuCore> rpcHostCpuCores = cpuCore();
            rpcHostCpu.setCore(rpcHostCpuCores);
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostCpu;
    }

    public static LinkedList<RPCHostCpuCore> cpuCore() {
        RPCHostCpu rpcHostCpu = new RPCHostCpu();
        LinkedList<RPCHostCpuCore> rpcHostCpuCores = new LinkedList<>();
        try {
            CpuInfo[] cpuInfoList = SIGAR.getCpuInfoList();
            for (int i = 0; i < cpuInfoList.length; i++) {
                CpuInfo info = cpuInfoList[i];
                RPCHostCpuCore rpcHostCpuCore = new RPCHostCpuCore();
                rpcHostCpuCore.setId(getId());
                rpcHostCpuCore.setIndex(String.valueOf(i));
                rpcHostCpuCore.setVendor(info.getVendor());
                rpcHostCpuCore.setModel(info.getModel());
                rpcHostCpuCore.setCacheSize(info.getCacheSize());
                rpcHostCpuCore.setMhz(info.getMhz());
                rpcHostCpuCore.setTotalCores(info.getTotalCores());
                rpcHostCpuCore.setTotalSockets(info.getTotalSockets());
                rpcHostCpuCore.setCoresPerSocket(info.getCoresPerSocket());
                rpcHostCpuCores.add(rpcHostCpuCore);
            }
            rpcHostCpu.setCore(rpcHostCpuCores);
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostCpuCores;
    }

    public static LinkedList<RPCHostCpuCoreUsage> cpuCoreUsage() {
        LinkedList<RPCHostCpuCoreUsage> rpcHostCpuCoreUsages = new LinkedList<>();
        try {
            CpuPerc[] cpuList = SIGAR.getCpuPercList();
            for (int i = 0; i < cpuList.length; i++) {
                CpuPerc cpuPerc = cpuList[i];
                RPCHostCpuCoreUsage rpcHostCpuCoreUsage = new RPCHostCpuCoreUsage();
                rpcHostCpuCoreUsage.setId(getId());
                rpcHostCpuCoreUsage.setIndex(String.valueOf(i));
                rpcHostCpuCoreUsage.setUser(CpuPerc.format(cpuPerc.getUser()));
                rpcHostCpuCoreUsage.setSys(CpuPerc.format(cpuPerc.getSys()));
                rpcHostCpuCoreUsage.setNice(CpuPerc.format(cpuPerc.getNice()));
                rpcHostCpuCoreUsage.setIdle(CpuPerc.format(cpuPerc.getIdle()));
                rpcHostCpuCoreUsage.setWait(CpuPerc.format(cpuPerc.getWait()));
                rpcHostCpuCoreUsage.setIrq(CpuPerc.format(cpuPerc.getIrq()));
                rpcHostCpuCoreUsage.setSoftIrq(CpuPerc.format(cpuPerc.getSoftIrq()));
                rpcHostCpuCoreUsage.setStolen(CpuPerc.format(cpuPerc.getStolen()));
                rpcHostCpuCoreUsage.setCombined(CpuPerc.format(cpuPerc.getCombined()));
                rpcHostCpuCoreUsages.add(rpcHostCpuCoreUsage);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostCpuCoreUsages;
    }

    public static RPCHostMemory memory() {
        RPCHostMemory rpcHostMemory = new RPCHostMemory();
            rpcHostMemory.setId(getId());
        try {
            Mem mem = SIGAR.getMem();
            rpcHostMemory.setTotal(mem.getTotal());
            rpcHostMemory.setRam(mem.getRam());
            rpcHostMemory.setUsed(mem.getUsed());
            rpcHostMemory.setFree(mem.getFree());
            rpcHostMemory.setActualUsed(mem.getActualUsed());
            rpcHostMemory.setActualFree(mem.getActualFree());
            rpcHostMemory.setUsedPercent(mem.getUsedPercent());
            rpcHostMemory.setFreePercent(mem.getFreePercent());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostMemory;
    }

    public static RPCHostMemorySwap swap() {
        RPCHostMemorySwap rpcHostMemorySwap = new RPCHostMemorySwap();
            rpcHostMemorySwap.setId(getId());
        try {
            Swap swap = SIGAR.getSwap();
            rpcHostMemorySwap.setTotal(swap.getTotal());
            rpcHostMemorySwap.setUsed(swap.getUsed());
            rpcHostMemorySwap.setFree(swap.getFree());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostMemorySwap;
    }

    public static List<RPCHostDisk> disk() {
        List<RPCHostDisk> rpcHostDisks = new LinkedList<>();
        try {
            FileSystem[] fileSystemList = SIGAR.getFileSystemList();
            for (int i = 0; i < fileSystemList.length; i++) {
                RPCHostDisk rpcHostDisk = new RPCHostDisk();
                FileSystem fs = fileSystemList[i];
                rpcHostDisk.setId(getId());
                rpcHostDisk.setDevName(fs.getDevName());
                rpcHostDisk.setDirName(fs.getDirName());
                rpcHostDisk.setFlags(fs.getFlags());
                rpcHostDisk.setSysTypeName(fs.getSysTypeName());
                rpcHostDisk.setTypeName(fs.getTypeName());
                rpcHostDisk.setType(fs.getType());
                rpcHostDisk.setOptions(fs.getOptions());
                rpcHostDisks.add(rpcHostDisk);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostDisks;
    }

    public static List<RPCHostDiskUsage> diskUsage() {
        List<RPCHostDiskUsage> rpcServerDisks = new LinkedList<>();
        try {
            FileSystem[] fileSystemList = SIGAR.getFileSystemList();
            for (int i = 0; i < fileSystemList.length; i++) {
                FileSystem fs = fileSystemList[i];
                RPCHostDiskUsage rpcHostDiskUsage = diskUsage(fs.getDevName());
                rpcServerDisks.add(rpcHostDiskUsage);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcServerDisks;
    }

    public static RPCHostDiskUsage diskUsage(String devName) {
        RPCHostDiskUsage rpcHostDiskUsage = new RPCHostDiskUsage();
        try {
            FileSystemUsage fileSystemUsage = SIGAR.getFileSystemUsage(devName);
            rpcHostDiskUsage.setId(getId());
            rpcHostDiskUsage.setDevName(devName);
            rpcHostDiskUsage.setTotal(fileSystemUsage.getTotal());
            rpcHostDiskUsage.setFree(fileSystemUsage.getFree());
            rpcHostDiskUsage.setUsed(fileSystemUsage.getUsed());
            rpcHostDiskUsage.setAvail(fileSystemUsage.getAvail());
            rpcHostDiskUsage.setFiles(fileSystemUsage.getFiles());
            rpcHostDiskUsage.setFreeFiles(fileSystemUsage.getFreeFiles());
            rpcHostDiskUsage.setDiskReads(fileSystemUsage.getDiskReads());
            rpcHostDiskUsage.setDiskWrites(fileSystemUsage.getDiskWrites());
            rpcHostDiskUsage.setDiskReadBytes(fileSystemUsage.getDiskReadBytes());
            rpcHostDiskUsage.setDiskWriteBytes(fileSystemUsage.getDiskWriteBytes());
            rpcHostDiskUsage.setDiskQueue(fileSystemUsage.getDiskQueue());
            rpcHostDiskUsage.setDiskServiceTime(fileSystemUsage.getDiskServiceTime());
            rpcHostDiskUsage.setUsePercent(fileSystemUsage.getUsePercent());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostDiskUsage;
    }

    public static List<RPCHostEthernet> ethernet() {
        List<RPCHostEthernet> rpcHostEthernets = new LinkedList<>();
        try {
            String[] netInterfaceList = SIGAR.getNetInterfaceList();
            for (int i = 0; i < netInterfaceList.length; i++) {
                RPCHostEthernet rpcHostEthernet = new RPCHostEthernet();
                String name = netInterfaceList[i];
                NetInterfaceConfig cfg = SIGAR.getNetInterfaceConfig(name);
                if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
                    || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                    continue;
                }
                rpcHostEthernet.setId(getId());
                rpcHostEthernet.setName(cfg.getName());
                rpcHostEthernet.setHwaddr(cfg.getHwaddr());
                rpcHostEthernet.setType(cfg.getType());
                rpcHostEthernet.setDescription(cfg.getDescription());
                rpcHostEthernet.setAddress(cfg.getAddress());
                rpcHostEthernet.setDescription(cfg.getDescription());
                rpcHostEthernet.setBroadcast(cfg.getBroadcast());
                rpcHostEthernet.setNetmask(cfg.getNetmask());
                rpcHostEthernet.setMtu(cfg.getMtu());
                rpcHostEthernet.setMetric(cfg.getMetric());
                rpcHostEthernets.add(rpcHostEthernet);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostEthernets;
    }

    public static List<RPCHostEthernetStat> ethernetStat() {
        List<RPCHostEthernetStat> rpcHostEthernetStats = new LinkedList<>();
        try {
            String[] netInterfaceList = SIGAR.getNetInterfaceList();
            for (int i = 0; i < netInterfaceList.length; i++) {
                String name = netInterfaceList[i];
                RPCHostEthernetStat rpcHostEthernetStat = ethernetStat(name);
                rpcHostEthernetStats.add(rpcHostEthernetStat);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostEthernetStats;
    }

    public static RPCHostEthernetStat ethernetStat(String name) {
        RPCHostEthernetStat rpcHostEthernetStat = new RPCHostEthernetStat();
        try {
            NetInterfaceConfig cfg = SIGAR.getNetInterfaceConfig(name);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
                || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                return rpcHostEthernetStat;
            }
            NetInterfaceStat netInterfaceStat = SIGAR.getNetInterfaceStat(name);
            rpcHostEthernetStat.setId(getId());
            rpcHostEthernetStat.setHwaddr(cfg.getHwaddr());
            rpcHostEthernetStat.setRxBytes(netInterfaceStat.getRxBytes());
            rpcHostEthernetStat.setRxPackets(netInterfaceStat.getRxPackets());
            rpcHostEthernetStat.setRxErrors(netInterfaceStat.getRxErrors());
            rpcHostEthernetStat.setRxDropped(netInterfaceStat.getRxDropped());
            rpcHostEthernetStat.setRxOverruns(netInterfaceStat.getRxOverruns());
            rpcHostEthernetStat.setRxFrame(netInterfaceStat.getRxFrame());
            rpcHostEthernetStat.setTxBytes(netInterfaceStat.getTxBytes());
            rpcHostEthernetStat.setTxPackets(netInterfaceStat.getTxPackets());
            rpcHostEthernetStat.setTxErrors(netInterfaceStat.getTxErrors());
            rpcHostEthernetStat.setTxDropped(netInterfaceStat.getTxDropped());
            rpcHostEthernetStat.setTxOverruns(netInterfaceStat.getTxOverruns());
            rpcHostEthernetStat.setTxCollisions(netInterfaceStat.getTxCollisions());
            rpcHostEthernetStat.setTxCarrier(netInterfaceStat.getTxCarrier());
            rpcHostEthernetStat.setSpeed(netInterfaceStat.getSpeed());
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return rpcHostEthernetStat;
    }

    public static List<RPCHostProcess> process() {
        List<RPCHostProcess> rpcHostProcesses = new LinkedList<>();
        try {
            long[] procList = SIGAR.getProcList();

            for (Long pid : procList) {
                RPCHostProcess rpcHostProcess = new RPCHostProcess();
                rpcHostProcess.setId(getId());
                rpcHostProcess.setPid(pid.toString());
                rpcHostProcess.setName(ProcUtil.getDescription(SIGAR, pid));

                try {
                    ProcCredName cred = SIGAR.getProcCredName(pid);
                    rpcHostProcess.setCredName(new RPCHostProcessCredName(cred.getUser(),cred.getGroup()));
                } catch (SigarException e) {
                    e.printStackTrace();
                }
                try {
                    ProcTime time = SIGAR.getProcTime(pid);
                    rpcHostProcess.setTime(new RPCHostProcessTime(time.getStartTime(),time.getUser(),time.getSys(),time.getTotal()));
                } catch (SigarException e) {
                    e.printStackTrace();
                }
                try {
                    ProcMem mem = SIGAR.getProcMem(pid);
                    rpcHostProcess.setMemory(new RPCHostProcessMemory(mem.getSize(),mem.getResident(),mem.getShare(),mem.getMinorFaults(),mem.getMajorFaults(),mem.getPageFaults()));
                } catch (SigarException e) {
                    e.printStackTrace();
                }
                try {
                    ProcCpu cpu = SIGAR.getProcCpu(pid);
                    rpcHostProcess.setCpu(new RPCHostProcessCpu(cpu.getPercent(),cpu.getLastTime(),cpu.getStartTime(),cpu.getUser(),cpu.getSys(),cpu.getTotal()));
                } catch (SigarException e) {
                    e.printStackTrace();
                }
                try {
                    ProcState state = SIGAR.getProcState(pid);
                    rpcHostProcess.setState(new RPCHostProcessState(state.getState(),state.getName(),state.getPpid(),state.getTty(),state.getNice(),state.getPriority(),state.getThreads(),state.getProcessor()));
                } catch (SigarException e) {
                    e.printStackTrace();
                }

                rpcHostProcesses.add(rpcHostProcess);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }

        return rpcHostProcesses;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String id = getId();
//        RPCHostOS os = Host.os();
//        List<RPCHostWho> who = Host.who();
//        RPCHostCpu cpu = Host.cpu();
//        LinkedList<RPCHostCpuCore> rpcHostCpuCores = Host.cpuCore();
//        LinkedList<RPCHostCpuCoreUsage> rpcHostCpuCoreUsages = Host.cpuCoreUsage();
        RPCHostMemory memory = Host.memory();
//        RPCHostMemorySwap swap = Host.swap();
//        List<RPCHostDisk> disk = Host.disk();
//        List<RPCHostDiskUsage> rpcHostDiskUsages = Host.diskUsage();
//        List<RPCHostEthernet> ethernet = Host.ethernet();
//        List<RPCHostEthernetStat> rpcHostEthernetStats = Host.ethernetStat();
//        List<RPCHostProcess> process = Host.process();
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }


}
