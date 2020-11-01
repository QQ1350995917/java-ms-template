package pwd.initializr.common.mw.monitor.index;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import pwd.initializr.common.shell.ShellOnLinux;
import pwd.initializr.monitor.rpc.IHost;
import pwd.initializr.monitor.rpc.IHostCpuCore;
import pwd.initializr.monitor.rpc.IHostCpuCoreStat;
import pwd.initializr.monitor.rpc.IHostDiskStat;
import pwd.initializr.monitor.rpc.IHostEthernetStat;
import pwd.initializr.monitor.rpc.IHostLoadStat;
import pwd.initializr.monitor.rpc.IHostLoggedStat;
import pwd.initializr.monitor.rpc.IHostMemoryStat;
import pwd.initializr.monitor.rpc.RPCHost;
import pwd.initializr.monitor.rpc.RPCHostCpuCore;
import pwd.initializr.monitor.rpc.RPCHostCpuCoreStat;
import pwd.initializr.monitor.rpc.RPCHostDiskStat;
import pwd.initializr.monitor.rpc.RPCHostEthernetStat;
import pwd.initializr.monitor.rpc.RPCHostLoadStat;
import pwd.initializr.monitor.rpc.RPCHostLoggedStat;
import pwd.initializr.monitor.rpc.RPCHostMemoryStat;

/**
 * pwd.initializr.common.mw.monitor.index@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-27 20:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class MonitorByShellOnLinux extends ShellOnLinux implements Index {

  public static void main(String[] args) {
    MonitorByShellOnLinux monitorByShellOnLinux = new MonitorByShellOnLinux();
    IHost host = monitorByShellOnLinux.getHost();
//    IHostLoadStat loadStat = monitorByShellOnLinux.getLoadStat();
//    List<IHostLoggedStat> loggedStat = monitorByShellOnLinux.getLoggedStat();
//    List<IHostCpuCore> cpuCore = monitorByShellOnLinux.getCpuCore();
//    List<IHostCpuCoreStat> cpuCoreStat = monitorByShellOnLinux.getCpuCoreStat();
//    List<IHostDiskStat> diskStat = monitorByShellOnLinux.getDiskStat();
//    List<IHostEthernetStat> ethernetStat = monitorByShellOnLinux.getEthernetStat();
//    IHostMemoryStat memoryStat = monitorByShellOnLinux.getMemoryStat();
    System.out.println();
  }

  static RPCHost rpcHost;

  @Override
  public IHost getHost() {
    if (rpcHost == null) {
      synchronized (MonitorByShellOnLinux.class) {
        if (rpcHost == null) {

          ShellResult groupNameShellResult = this
              .execForResult(
                  getCommandForResultArray(new String[]{"cat /etc/groupname"}));
          ShellResult nodeNameShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -n"}));
          ShellResult distributeShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"cat /etc/os-release"}));
          ShellResult operatingSystemShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -o"}));
          ShellResult hardwarePlatformShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -i"}));
          ShellResult systemUpSinceShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uptime -s"}));
          ShellResult kernelNameShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -s"}));
          ShellResult kernelVersionShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -v"}));
          ShellResult kernelReleaseShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -r"}));
          ShellResult machineShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -m"}));
          ShellResult processorShellResult = this
              .execForResult(getCommandForResultArray(new String[]{"uname -p"}));

          rpcHost = new RPCHost();
          rpcHost.setGroupName(
              groupNameShellResult.getLines().size() > 0 ? groupNameShellResult.getLines()
                  .get(0) : "unnamed");
          rpcHost.setNodeName(
              nodeNameShellResult.getLines().size() > 0 ? nodeNameShellResult.getLines()
                  .get(0) : "unnamed");

          List<String> lines = distributeShellResult.getLines();
          /**
           * NAME="Ubuntu"
           * VERSION="18.04.4 LTS (Bionic Beaver)"
           * ID=ubuntu
           * ID_LIKE=debian
           * PRETTY_NAME="Ubuntu 18.04.4 LTS"
           * VERSION_ID="18.04"
           * HOME_URL="https://www.ubuntu.com/"
           * SUPPORT_URL="https://help.ubuntu.com/"
           * BUG_REPORT_URL="https://bugs.launchpad.net/ubuntu/"
           * PRIVACY_POLICY_URL="https://www.ubuntu.com/legal/terms-and-policies/privacy-policy"
           * VERSION_CODENAME=bionic
           * UBUNTU_CODENAME=bionic
           */
          for (String line : lines) {
            String[] split = line.split("=");
            String key = split[0];
            String value = split[1];
            if ("ID".equalsIgnoreCase(key)) {
              rpcHost.setDistributeId(value);
            } else if ("ID_LIKE".equalsIgnoreCase(key)) {
              rpcHost.setDistributeIdLike(value);
            } else if ("NAME".equalsIgnoreCase(key)) {
              rpcHost.setDistributeName(value.replace("\"",""));
            } else if ("VERSION_ID".equalsIgnoreCase(key)) {
              rpcHost.setDistributeVersion(value.replace("\"",""));
            } else if ("VERSION_CODENAME".equalsIgnoreCase(key)) {
              rpcHost.setDistributeCodeName(value);
            } else if ("VERSION".equalsIgnoreCase(key)) {
              rpcHost.setDistributeDescription(value.replace("\"",""));
            }
          }

          rpcHost.setOperatingSystem(operatingSystemShellResult.getLines().get(0));
          rpcHost.setHardwarePlatform(hardwarePlatformShellResult.getLines().get(0));
          rpcHost.setSystemUpSince(systemUpSinceShellResult.getLines().get(0));
          rpcHost.setKernelName(kernelNameShellResult.getLines().get(0));
          rpcHost.setKernelVersion(kernelVersionShellResult.getLines().get(0));
          rpcHost.setKernelRelease(kernelReleaseShellResult.getLines().get(0));
          rpcHost.setMachine(machineShellResult.getLines().get(0));
          rpcHost.setProcessor(processorShellResult.getLines().get(0));
        }
      }

    }
    return rpcHost;
  }

  @Override
  public IHostLoadStat getLoadStat() {
    ShellResult loadavgShellResult = this
        .execForResult(getCommandForResultArray(new String[]{"cat /proc/loadavg"}));
    /**
     * 0.42 0.37 0.31 1/2663 8452
     */
    String loadavg = loadavgShellResult.getLines().get(0);
    String[] load = loadavg.replaceAll("\\s{2,}", " ").split(" ");
    RPCHostLoadStat rpcLoad = new RPCHostLoadStat();
    rpcLoad.setGroupName(getHost().getGroupName());
    rpcLoad.setNodeName(getHost().getNodeName());
    rpcLoad.setLoadIn1m(load[0]);
    rpcLoad.setLoadIn5m(load[1]);
    rpcLoad.setLoadIn15m(load[2]);
    rpcLoad.setProcessRate(load[3]);
    rpcLoad.setLastProcessId(load[4]);
    return rpcLoad;
  }

  @Override
  public List<IHostLoggedStat> getLoggedStat() {
    ShellResult loadShellResult = this
        .execForResult(getCommandForResultArray(new String[]{"w -h"}));
    /**
     * USER     TTY      FROM             LOGIN@   IDLE   JCPU   PCPU WHAT
     * root     pts/0    192.168.47.39    10:43    6:59m  7:45   0.03s -bash
     * root     pts/1    192.168.47.39    10:43    6:06m  0.03s  0.03s -bash
     * root     pts/2    192.168.50.237   16:48    3:19   0.27s  0.27s -bash
     * root     pts/3    192.168.50.29    Mon18    7.00s 56.02s  0.91s -bash
     * root     pts/4    192.168.50.230   10:40    3:13m  4:24   0.34s -bash
     * root     pts/5    192.168.50.237   17:37    5:59   0.04s  0.04s -bash
     */
    List<String> lines = loadShellResult.getLines();
    List<IHostLoggedStat> loggedStats = new LinkedList<>();
    for (String line : lines) {
      String[] s = line.trim().replaceAll("\\s{2,}", " ").split(" ");
      RPCHostLoggedStat rpcLoggedStat = new RPCHostLoggedStat();
      rpcLoggedStat.setGroupName(getHost().getGroupName());
      rpcLoggedStat.setNodeName(getHost().getNodeName());
      rpcLoggedStat.setUser(s[0]);
      rpcLoggedStat.setTty(s[1]);
      rpcLoggedStat.setFrom(s[2]);
      rpcLoggedStat.setLogin(s[3]);
      rpcLoggedStat.setIdle(s[4]);
      rpcLoggedStat.setJcpu(s[5]);
      rpcLoggedStat.setPcpu(s[6]);
      rpcLoggedStat.setWhat(s[7]);
      loggedStats.add(rpcLoggedStat);
    }
    return loggedStats;
  }

  @Override
  public List<IHostCpuCore> getCpuCore() {
    ShellResult cpuinfoShellResult = this
        .execForResult(getCommandForResultArray(new String[]{"cat /proc/cpuinfo"}));
    /**
     * processor	: 0
     * vendor_id	: GenuineIntel
     * cpu family	: 6
     * model		: 58
     * model name	: Intel(R) Xeon(R) CPU E3-1230 V2 @ 3.30GHz
     * stepping	: 9
     * microcode	: 0x12
     * cpu MHz		: 1631.179
     * cache size	: 8192 KB
     * physical id	: 0
     * siblings	: 8
     * core id		: 0
     * cpu cores	: 4
     * apicid		: 0
     * initial apicid	: 0
     * fpu		: yes
     * fpu_exception	: yes
     * cpuid level	: 13
     * wp		: yes
     * flags		: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx smx est tm2 ssse3 cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm epb tpr_shadow vnmi flexpriority ept vpid fsgsbase smep erms xsaveopt dtherm ida arat pln pts
     * bogomips	: 6600.25
     * clflush size	: 64
     * cache_alignment	: 64
     * address sizes	: 36 bits physical, 48 bits virtual
     * power management:
     *
     * processor	: 1
     * vendor_id	: GenuineIntel
     * cpu family	: 6
     * model		: 58
     * model name	: Intel(R) Xeon(R) CPU E3-1230 V2 @ 3.30GHz
     * stepping	: 9
     * microcode	: 0x12
     * cpu MHz		: 1667.660
     * cache size	: 8192 KB
     * physical id	: 0
     * siblings	: 8
     * core id		: 1
     * cpu cores	: 4
     * apicid		: 2
     * initial apicid	: 2
     * fpu		: yes
     * fpu_exception	: yes
     * cpuid level	: 13
     * wp		: yes
     * flags		: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx rdtscp lm constant_tsc arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx smx est tm2 ssse3 cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm epb tpr_shadow vnmi flexpriority ept vpid fsgsbase smep erms xsaveopt dtherm ida arat pln pts
     * bogomips	: 6600.25
     * clflush size	: 64
     * cache_alignment	: 64
     * address sizes	: 36 bits physical, 48 bits virtual
     * power management:
     *
     */
    List<String> lines = cpuinfoShellResult.getLines();
    List<IHostCpuCore> cpuCores = new LinkedList<>();
    RPCHostCpuCore rpcCpuCore = new RPCHostCpuCore();
    rpcCpuCore.setGroupName(getHost().getGroupName());
    rpcCpuCore.setNodeName(getHost().getNodeName());
    int index = 0;
    for (String line : lines) {
      String[] split = line.split(":");
      if (split.length > 1) {
        String value = split[1].trim();
        if (index == 0) {
          rpcCpuCore.setProcessor(value);
        } else if (index == 1) {
          rpcCpuCore.setVendorId(value);
        } else if (index == 2) {
          rpcCpuCore.setCpuFamily(value);
        } else if (index == 3) {
          rpcCpuCore.setModel(value);
        } else if (index == 4) {
          rpcCpuCore.setModelName(value);
        } else if (index == 5) {
          rpcCpuCore.setStepping(value);
        } else if (index == 6) {
          rpcCpuCore.setMicrocode(value);
        } else if (index == 7) {
          rpcCpuCore.setCpuMHz(value);
        } else if (index == 8) {
          rpcCpuCore.setCacheSize(value);
        } else if (index == 9) {
          rpcCpuCore.setPhysicalId(value);
        } else if (index == 10) {
          rpcCpuCore.setSiblings(value);
        } else if (index == 11) {
          rpcCpuCore.setCoreId(value);
        } else if (index == 12) {
          rpcCpuCore.setCpuCores(value);
        } else if (index == 13) {
          rpcCpuCore.setApicid(value);
        } else if (index == 14) {
          rpcCpuCore.setInitialApicid(value);
        } else if (index == 15) {
          rpcCpuCore.setFpu(value);
        } else if (index == 16) {
          rpcCpuCore.setFpuException(value);
        } else if (index == 17) {
          rpcCpuCore.setCpuidLevel(value);
        } else if (index == 18) {
          rpcCpuCore.setWp(value);
        } else if (index == 19) {
          rpcCpuCore.setFlags(value);
        } else if (index == 20) {
          rpcCpuCore.setBogomips(value);
        } else if (index == 21) {
          rpcCpuCore.setClflushSize(value);
        } else if (index == 23) {
          rpcCpuCore.setCacheAlignment(value);
        } else if (index == 24) {
          rpcCpuCore.setAddressSizes(value);
        } else if (index == 25) {
          rpcCpuCore.setPowerManagement(value);
        }
      }

      index++;
      if (StringUtils.isBlank(line)) {
        cpuCores.add(rpcCpuCore);
        rpcCpuCore = new RPCHostCpuCore();
        rpcCpuCore.setGroupName(getHost().getGroupName());
        rpcCpuCore.setNodeName(getHost().getNodeName());
        index = 0;
      }
    }
    return cpuCores;
  }

  @Override
  public List<IHostCpuCoreStat> getCpuCoreStat() {
    ShellResult cpuStatShellResult = this
        .execForResult(getCommandForResultArray(new String[]{"cat /proc/stat"}));
    /**
     * cpu  44770555 640 15844690 2752674416 4186605 0 188012 0 0 0
     * cpu0 6678535 71 2225148 342394287 314029 0 25595 0 0 0
     * cpu1 6572392 23 2229068 342509488 168061 0 68434 0 0 0
     * cpu2 7661454 296 2332731 338568802 2983234 0 36651 0 0 0
     * cpu3 7338745 119 2305697 341543739 523880 0 24292 0 0 0
     * cpu4 4293156 47 1681730 346730858 38839 0 8558 0 0 0
     * cpu5 4582362 32 1673331 346445232 33611 0 8214 0 0 0
     * cpu6 3076578 37 1681462 347962204 79064 0 7969 0 0 0
     * cpu7 4567330 12 1715521 346519804 45884 0 8296 0 0 0
     * intr 24546620586 34 3 0 0 0 0 0 0 1 0 0 0 4 0 0 0 29 0 0 0 0 0 0 29 0 0 59055239 57001268 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * ctxt 35316682512
     * btime 1600328619
     * processes 5165836
     * procs_running 1
     * procs_blocked 0
     * softirq 2914150691 4 1323483050 712067 203001674 56992397 0 748102 794580919 0 534632478
     */

    LinkedList<IHostCpuCoreStat> cpuCoreStats = new LinkedList<>();
    List<String> lines = cpuStatShellResult.getLines();
    for (String line : lines) {
      if (line.startsWith("cpu")) {
        String[] split = line.trim().replaceAll("\\s{2,}", " ").split(" ");
        RPCHostCpuCoreStat rpcCpuCoreStat = new RPCHostCpuCoreStat();
        rpcCpuCoreStat.setGroupName(getHost().getGroupName());
        rpcCpuCoreStat.setNodeName(getHost().getNodeName());
        rpcCpuCoreStat.setCpuCoreName(split[0]);
        rpcCpuCoreStat.setUser(Long.parseLong(split[1]));
        rpcCpuCoreStat.setNice(Long.parseLong(split[2]));
        rpcCpuCoreStat.setSystem(Long.parseLong(split[3]));
        rpcCpuCoreStat.setIdle(Long.parseLong(split[4]));
        rpcCpuCoreStat.setIowait(Long.parseLong(split[5]));
        rpcCpuCoreStat.setIrq(Long.parseLong(split[6]));
        rpcCpuCoreStat.setSoftirq(Long.parseLong(split[7]));
        rpcCpuCoreStat.setSteal(Long.parseLong(split[8]));
        rpcCpuCoreStat.setGuest(Long.parseLong(split[9]));
        rpcCpuCoreStat.setGuestNice(Long.parseLong(split[10]));
        cpuCoreStats.add(rpcCpuCoreStat);
      }
    }
    return cpuCoreStats;
  }

  @Override
  public IHostMemoryStat getMemoryStat() {
    ShellResult meminfoShellResult = this
        .execForResult(getCommandForResultArray(new String[]{"cat /proc/meminfo"}));
    /**
     * MemTotal:       32754328 kB
     * MemFree:          227844 kB
     * MemAvailable:    5713652 kB
     * Buffers:               0 kB
     * Cached:          3966648 kB
     * SwapCached:        85288 kB
     * Active:         24916468 kB
     * Inactive:        4922196 kB
     * Active(anon):   22702692 kB
     * Inactive(anon):  3292592 kB
     * Active(file):    2213776 kB
     * Inactive(file):  1629604 kB
     * Unevictable:           0 kB
     * Mlocked:               0 kB
     * SwapTotal:      16515068 kB
     * SwapFree:       15892912 kB
     * Dirty:               636 kB
     * Writeback:             0 kB
     * AnonPages:      25821460 kB
     * Mapped:           155320 kB
     * Shmem:            123268 kB
     * Slab:            2143632 kB
     * SReclaimable:    2043256 kB
     * SUnreclaim:       100376 kB
     * KernelStack:       42704 kB
     * PageTables:        80812 kB
     * NFS_Unstable:          0 kB
     * Bounce:                0 kB
     * WritebackTmp:          0 kB
     * CommitLimit:    32892232 kB
     * Committed_AS:   42043560 kB
     * VmallocTotal:   34359738367 kB
     * VmallocUsed:      136740 kB
     * VmallocChunk:   34359537660 kB
     * HardwareCorrupted:     0 kB
     * AnonHugePages:   7026688 kB
     * HugePages_Total:       0
     * HugePages_Free:        0
     * HugePages_Rsvd:        0
     * HugePages_Surp:        0
     * Hugepagesize:       2048 kB
     * DirectMap4k:      144772 kB
     * DirectMap2M:    33382400 kB
     */
    List<String> lines = meminfoShellResult.getLines();
    RPCHostMemoryStat rpcMemory = new RPCHostMemoryStat();
    rpcMemory.setGroupName(getHost().getGroupName());
    rpcMemory.setNodeName(getHost().getNodeName());
    int index = 0;
    for (String line : lines) {
      String[] split = line.trim().split(":");
      String stringValue = split[1].replace("kB", "").trim();
      long longValue = Long.parseLong(stringValue);
      if (index == 0) {
        rpcMemory.setMemTotal(longValue);
      } else if (index == 1) {
        rpcMemory.setMemFree(longValue);
      } else if (index == 2) {
        rpcMemory.setMemAvailable(longValue);
      } else if (index == 3) {
        rpcMemory.setBuffers(longValue);
      } else if (index == 4) {
        rpcMemory.setCached(longValue);
      } else if (index == 5) {
        rpcMemory.setSwapCached(longValue);
      } else if (index == 6) {
        rpcMemory.setActive(longValue);
      } else if (index == 7) {
        rpcMemory.setInactive(longValue);
      } else if (index == 8) {
        rpcMemory.setActiveAnon(longValue);
      } else if (index == 9) {
        rpcMemory.setInactiveAnon(longValue);
      } else if (index == 10) {
        rpcMemory.setActiveFile(longValue);
      } else if (index == 11) {
        rpcMemory.setInactiveFile(longValue);
      } else if (index == 12) {
        rpcMemory.setUnevictable(longValue);
      } else if (index == 13) {
        rpcMemory.setMlocked(longValue);
      } else if (index == 14) {
        rpcMemory.setSwapTotal(longValue);
      } else if (index == 15) {
        rpcMemory.setSwapFree(longValue);
      } else if (index == 16) {
        rpcMemory.setDirty(longValue);
      } else if (index == 17) {
        rpcMemory.setWriteback(longValue);
      } else if (index == 18) {
        rpcMemory.setAnonPages(longValue);
      } else if (index == 19) {
        rpcMemory.setMapped(longValue);
      } else if (index == 20) {
        rpcMemory.setShmem(longValue);
      } else if (index == 21) {
        rpcMemory.setSlab(longValue);
      } else if (index == 22) {
        rpcMemory.setSReclaimable(longValue);
      } else if (index == 23) {
        rpcMemory.setSUnreclaim(longValue);
      } else if (index == 24) {
        rpcMemory.setKernelStack(longValue);
      } else if (index == 25) {
        rpcMemory.setPageTables(longValue);
      } else if (index == 26) {
        rpcMemory.setNFSUnstable(longValue);
      } else if (index == 27) {
        rpcMemory.setBounce(longValue);
      } else if (index == 28) {
        rpcMemory.setWritebackTmp(longValue);
      } else if (index == 29) {
        rpcMemory.setCommitLimit(longValue);
      } else if (index == 30) {
        rpcMemory.setCommittedAS(longValue);
      } else if (index == 31) {
        rpcMemory.setVmallocTotal(longValue);
      } else if (index == 32) {
        rpcMemory.setVmallocUsed(longValue);
      } else if (index == 33) {
        rpcMemory.setVmallocChunk(longValue);
      } else if (index == 34) {
        rpcMemory.setHardwareCorrupted(longValue);
      } else if (index == 35) {
        rpcMemory.setAnonHugePages(longValue);
      } else if (index == 36) {
        rpcMemory.setHugePagesTotal(longValue);
      } else if (index == 37) {
        rpcMemory.setHugePagesFree(longValue);
      } else if (index == 38) {
        rpcMemory.setHugePagesRsvd(longValue);
      } else if (index == 39) {
        rpcMemory.setHugePagesSurp(longValue);
      } else if (index == 40) {
        rpcMemory.setHugePagesize(longValue);
      } else if (index == 41) {
        rpcMemory.setDirectMap4k(longValue);
      } else if (index == 42) {
        rpcMemory.setDirectMap2M(longValue);
      }
      index++;
    }
    return rpcMemory;
  }

  @Override
  public List<IHostDiskStat> getDiskStat() {
    ShellResult diskstatsShellResult = this
        .execForResult(getCommandForResultArray(new String[]{"cat /proc/diskstats"}));
    /**
     *    8       0 sda 17901464 2276295 4360396473 31628468 33779408 2746750 1439523404 630945667 0 216680376 662544172
     *    8       1 sda1 1180 3 58995 2160 13 0 4236 1219 0 2050 3373
     *    8       2 sda2 17900206 2276292 4360332974 31625254 27470316 2746750 1439519168 522045179 0 112901128 553645563
     *  253       0 dm-0 276687 0 17791429 1429802 7116169 0 100734372 57089180 0 8004310 58519217
     *  253       1 dm-1 2514529 0 20119936 12335880 2344065 0 18752520 61180729 0 2203454 73517061
     *  253       2 dm-2 17387670 0 4322420593 31444783 21604537 0 1320032276 613350422 0 208845612 644801680
     */
    List<String> lines = diskstatsShellResult.getLines();
    LinkedList<IHostDiskStat> iHostDiskStats = new LinkedList<>();
    for (String line : lines) {
      RPCHostDiskStat rpcDiskStat = new RPCHostDiskStat();
      rpcDiskStat.setGroupName(getHost().getGroupName());
      rpcDiskStat.setNodeName(getHost().getNodeName());
      String[] split = line.trim().replaceAll("\\s{2,}", " ").split(" ");
      rpcDiskStat.setMajorDeviceNumber(Long.parseLong(split[0]));
      rpcDiskStat.setMinorDeviceNumber(Long.parseLong(split[1]));
      rpcDiskStat.setDeviceName(split[2]);
      rpcDiskStat.setRead(Long.parseLong(split[3]));
      rpcDiskStat.setReadMerge(Long.parseLong(split[4]));
      rpcDiskStat.setReadSector(Long.parseLong(split[5]));
      rpcDiskStat.setReadSpentMilliseconds(Long.parseLong(split[6]));
      rpcDiskStat.setWrite(Long.parseLong(split[7]));
      rpcDiskStat.setWriteMerge(Long.parseLong(split[8]));
      rpcDiskStat.setWriteSector(Long.parseLong(split[9]));
      rpcDiskStat.setWriteSpentMilliseconds(Long.parseLong(split[10]));
      rpcDiskStat.setIoRequest(Long.parseLong(split[11]));
      rpcDiskStat.setIoSpentMilliseconds(Long.parseLong(split[12]));
      rpcDiskStat.setIoSpentAllMilliseconds(Long.parseLong(split[13]));
      iHostDiskStats.add(rpcDiskStat);
    }
    return iHostDiskStats;
  }

  @Override
  public List<IHostEthernetStat> getEthernetStat() {
    ShellResult ethernetStatShellResult = this
        .execForResult(getCommandForResultArray(new String[]{"cat /proc/net/dev"}));
    /**
     * Inter-|   Receive                                                |  Transmit
     *  face |bytes    packets errs drop fifo frame compressed multicast|bytes    packets errs drop fifo colls carrier compressed
     * enp2s0f0:       0       0    0    0    0     0          0         0        0       0    0    0    0     0       0          0
     * enp2s0f1:       0       0    0    0    0     0          0         0        0       0    0    0    0     0       0          0
     * vethf07e130:       0       0    0    0    0     0          0         0 15919464  379020    0    0    0     0       0          0
     *   eno1: 22661033212 69729184    0  183    0     0          0       149 8460858070 68091662    0    0    0     0       0          0
     *     lo: 1892617635657 132094238    0    0    0     0          0         0 1892617635657 132094238    0    0    0     0       0          0
     * enp5s0:       0       0    0    0    0     0          0         0        0       0    0    0    0     0       0          0
     * veth1b928cf: 192670594 1473136    0    0    0     0          0         0 178832986 1292736    0    0    0     0       0          0
     * docker_gwbridge:       0       0    0    0    0     0          0         0 15918816  379012    0    0    0     0       0          0
     * docker0: 2317138351 19909357    0    0    0     0          0         0 2393929244 17465644    0    0    0     0       0          0
     */
    List<IHostEthernetStat> ethernetStats = new LinkedList<>();
    List<String> lines = ethernetStatShellResult.getLines();
    int index = 0;
    for (String line : lines) {
      if (index < 2) {
        index ++;
        continue;
      }
      String[] split = line.trim().replaceAll("\\s{2,}", " ").split(" ");
      RPCHostEthernetStat rpcEthernetStat = new RPCHostEthernetStat();
      rpcEthernetStat.setGroupName(getHost().getGroupName());
      rpcEthernetStat.setNodeName(getHost().getNodeName());
      rpcEthernetStat.setInterFace(split[0].replace(":", ""));
      rpcEthernetStat.setReceiveBytes(Long.parseLong(split[1]));
      rpcEthernetStat.setReceivePackets(Long.parseLong(split[2]));
      rpcEthernetStat.setReceiveErrs(Long.parseLong(split[3]));
      rpcEthernetStat.setReceiveDrop(Long.parseLong(split[4]));
      rpcEthernetStat.setReceiveFifo(Long.parseLong(split[5]));
      rpcEthernetStat.setReceiveFrame(Long.parseLong(split[6]));
      rpcEthernetStat.setReceiveCompressed(Long.parseLong(split[7]));
      rpcEthernetStat.setReceiveMulticast(Long.parseLong(split[8]));
      rpcEthernetStat.setTransmitBytes(Long.parseLong(split[9]));
      rpcEthernetStat.setTransmitPackets(Long.parseLong(split[10]));
      rpcEthernetStat.setTransmitErrs(Long.parseLong(split[11]));
      rpcEthernetStat.setTransmitDrop(Long.parseLong(split[12]));
      rpcEthernetStat.setTransmitFifo(Long.parseLong(split[13]));
      rpcEthernetStat.setTransmitColls(Long.parseLong(split[14]));
      rpcEthernetStat.setTransmitCarrier(Long.parseLong(split[15]));
      rpcEthernetStat.setTransmitCompressed(Long.parseLong(split[16]));
      ethernetStats.add(rpcEthernetStat);
      index++;
    }
    return ethernetStats;
  }
}
