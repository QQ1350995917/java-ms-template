package pwd.initializr.monitor.api.admin;

import io.swagger.annotations.Api;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pwd.initializr.common.web.api.admin.AdminController;
import pwd.initializr.monitor.api.admin.vo.OperatingSystemOutput;

/**
 * pwd.initializr.monitor.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-19 16:46
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "服务器信息",
    value = "服务器信息",
    description = "[OS，CPU，MEMORY，SWAP，WHO，DISK，NETWORK，ETHERNET]"
)
@RestController(value = "admin")
@RequestMapping(value = "/api/admin/server")
@Slf4j
public class SystemController extends AdminController implements SystemApi {

    private static final OperatingSystem OS = OperatingSystem.getInstance();
    private static final Sigar SIGAR = new Sigar();

    @Override
    public void os() {
        OperatingSystemOutput operatingSystemVO = new OperatingSystemOutput();
        operatingSystemVO.setName(OS.getName());
        operatingSystemVO.setVersion(OS.getVersion());
        operatingSystemVO.setArch(OS.getArch());
        operatingSystemVO.setDescription(OS.getDescription());
        operatingSystemVO.setMachine(OS.getMachine());
        operatingSystemVO.setPatchLevel(OS.getPatchLevel());
        operatingSystemVO.setCpuEndian(OS.getCpuEndian());
        operatingSystemVO.setDataModel(OS.getDataModel());
        operatingSystemVO.setVendor(OS.getVendor());
        operatingSystemVO.setVendorCodeName(OS.getVendorCodeName());
        operatingSystemVO.setVendorName(OS.getVendorName());
        operatingSystemVO.setVendorVersion(OS.getVendorVersion());
        this.outputData(operatingSystemVO);
    }

    @Override
    public void cpu() {
        try {
            CpuInfo infos[] = SIGAR.getCpuInfoList();
            System.out.println("cpu总量参数情况 "+ SIGAR.getCpu());
            System.out.println("cpu总百分比情况 "+ SIGAR.getCpuPerc());
            CpuPerc cpuList[] = SIGAR.getCpuPercList();
            for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
                CpuInfo info = infos[i];
                System.out.println("第" + (i + 1) + "块CPU信息");
                System.out.println("CPU的总量MHz:    " + info.getMhz());// CPU的总量MHz
                System.out.println("CPU生产商:    " + info.getVendor());// 获得CPU的卖主，如：Intel
                System.out.println("CPU类别:    " + info.getModel());// 获得CPU的类别，如：Celeron
                System.out.println("CPU缓存数量:    " + info.getCacheSize());// 缓冲存储器数量
                CpuPerc cpu = cpuList[i];
                System.out.println("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));// 用户使用率
                System.out.println("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));// 系统使用率
                System.out.println("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));// 当前等待率
                System.out.println("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));//
                System.out.println("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
                System.out.println("CPU总的使用率:    " + CpuPerc.format(cpu.getCombined()));// 总的使用率
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }

        this.outputData(200);
    }

    public static void main(String[] args) {
        SystemController systemController = new SystemController();
        systemController.cpu();
    }

    @Override
    public void memory() {

        try {
            Mem mem = SIGAR.getMem();
            // 内存总量
            System.out.println("内存总量:    " + mem.getTotal() / 1024L + "K av");
            // 当前内存使用量
            System.out.println("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
            // 当前内存剩余量
            System.out.println("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void swap() {

        try {
            Swap swap = SIGAR.getSwap();
            // 交换区总量
            System.out.println("交换区总量:    " + swap.getTotal() / 1024L + "K av");
            // 当前交换区使用量
            System.out.println("当前交换区使用量:    " + swap.getUsed() / 1024L + "K used");
            // 当前交换区剩余量
            System.out.println("当前交换区剩余量:    " + swap.getFree() / 1024L + "K free");
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void who() {
        try {
            Who who[] = SIGAR.getWhoList();
            if (who != null && who.length > 0) {
                for (int i = 0; i < who.length; i++) {
                    // System.out.println("当前系统进程表中的用户名" + String.valueOf(i));
                    Who _who = who[i];
                    System.out.println("用户控制台:    " + _who.getDevice());
                    System.out.println("用户host:    " + _who.getHost());
                    // System.out.println("getTime():    " + _who.getTime());
                    // 当前系统进程表中的用户名
                    System.out.println("当前系统进程表中的用户名:    " + _who.getUser());
                }
                Map<String, String> map = System.getenv();
                String userName = map.get("USERNAME");// 获取用户名
                String computerName = map.get("COMPUTERNAME");// 获取计算机名
                String userDomain = map.get("USERDOMAIN");// 获取计算机域名
                System.out.println("用户名:    " + userName);
                System.out.println("计算机名:    " + computerName);
                System.out.println("计算机域名:    " + userDomain);

            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disk() {
        try {
            FileSystem fslist[] = SIGAR.getFileSystemList();
            for (int i = 0; i < fslist.length; i++) {
                System.out.println("分区的盘符名称" + i);
                FileSystem fs = fslist[i];
                // 分区的盘符名称
                System.out.println("盘符名称:    " + fs.getDevName());
                // 分区的盘符名称
                System.out.println("盘符路径:    " + fs.getDirName());
                System.out.println("盘符标志:    " + fs.getFlags());//
                // 文件系统类型，比如 FAT32、NTFS
                System.out.println("盘符类型:    " + fs.getSysTypeName());
                // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
                System.out.println("盘符类型名:    " + fs.getTypeName());
                // 文件系统类型
                System.out.println("盘符文件系统类型:    " + fs.getType());
                FileSystemUsage usage = null;
                usage = SIGAR.getFileSystemUsage(fs.getDirName());
                switch (fs.getType()) {
                    case 0: // TYPE_UNKNOWN ：未知
                        break;
                    case 1: // TYPE_NONE
                        break;
                    case 2: // TYPE_LOCAL_DISK : 本地硬盘
                        // 文件系统总大小
                        System.out.println(fs.getDevName() + "总大小:    "
                            + usage.getTotal() + "KB");
                        // 文件系统剩余大小
                        System.out.println(fs.getDevName() + "剩余大小:    "
                            + usage.getFree() + "KB");
                        // 文件系统可用大小
                        System.out.println(fs.getDevName() + "可用大小:    "
                            + usage.getAvail() + "KB");
                        // 文件系统已经使用量
                        System.out.println(fs.getDevName() + "已经使用量:    "
                            + usage.getUsed() + "KB");
                        double usePercent = usage.getUsePercent() * 100D;
                        // 文件系统资源的利用率
                        System.out.println(fs.getDevName() + "资源的利用率:    "
                            + usePercent + "%");
                        break;
                    case 3:// TYPE_NETWORK ：网络
                        break;
                    case 4:// TYPE_RAM_DISK ：闪存
                        break;
                    case 5:// TYPE_CDROM ：光驱
                        break;
                    case 6:// TYPE_SWAP ：页面交换
                        break;
                }
                System.out.println(fs.getDevName() + "读出：    "
                    + usage.getDiskReads());
                System.out.println(fs.getDevName() + "写入：    "
                    + usage.getDiskWrites());
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void network() {
        try {
            String ifNames[] = SIGAR.getNetInterfaceList();
            for (int i = 0; i < ifNames.length; i++) {
                String name = ifNames[i];
                NetInterfaceConfig ifconfig = SIGAR.getNetInterfaceConfig(name);
                System.out.println("网络设备名:    " + name);// 网络设备名
                System.out.println("IP地址:    " + ifconfig.getAddress());// IP地址
                System.out.println("子网掩码:    " + ifconfig.getNetmask());// 子网掩码
                if ((ifconfig.getFlags() & 1L) <= 0L) {
                    System.out.println("!IFF_UP...skipping getNetInterfaceStat");
                    continue;
                }
                NetInterfaceStat ifstat = SIGAR.getNetInterfaceStat(name);
                System.out.println(name + "接收的总包裹数:" + ifstat.getRxPackets());// 接收的总包裹数
                System.out.println(name + "发送的总包裹数:" + ifstat.getTxPackets());// 发送的总包裹数
                System.out.println(name + "接收到的总字节数:" + ifstat.getRxBytes());// 接收到的总字节数
                System.out.println(name + "发送的总字节数:" + ifstat.getTxBytes());// 发送的总字节数
                System.out.println(name + "接收到的错误包数:" + ifstat.getRxErrors());// 接收到的错误包数
                System.out.println(name + "发送数据包时的错误数:" + ifstat.getTxErrors());// 发送数据包时的错误数
                System.out.println(name + "接收时丢弃的包数:" + ifstat.getRxDropped());// 接收时丢弃的包数
                System.out.println(name + "发送时丢弃的包数:" + ifstat.getTxDropped());// 发送时丢弃的包数
            }

            try {
                InetAddress addr = InetAddress.getLocalHost();
                String ip = addr.getHostAddress();

                System.out.println("本地ip地址:    " + ip);
                //
                System.out.println("本地主机名:    " + addr.getHostName());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            //
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ethernet() {
        try {
            String[] ifaces = SIGAR.getNetInterfaceList();
            for (int i = 0; i < ifaces.length; i++) {
                NetInterfaceConfig cfg = SIGAR.getNetInterfaceConfig(ifaces[i]);
                if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
                    || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                    continue;
                }
                System.out.println(cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
                System.out.println(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
                System.out.println(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
                System.out.println(cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
                System.out.println(cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
                System.out.println(cfg.getName() + "网卡类型" + cfg.getType());//
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }
}
