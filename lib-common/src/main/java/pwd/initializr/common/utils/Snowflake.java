package pwd.initializr.common.utils;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>https://github.com/souyunku/SnowFlake</h1>
 *
 * date 2020-11-30 22:57
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Snowflake {

  //数据中心占用的位数
  private final static long DATA_CENTER_BIT = 5;
  //机器标识占用的位数
  private final static long MACHINE_BIT = 5;
  /**
   * 每一部分的最大值
   */
  private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
  private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
  /**
   * 每一部分占用的位数
   */
  //序列号占用的位数
  private final static long SEQUENCE_BIT = 12;
  private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
  /**
   * 每一部分向左的位移
   */
  private final static long MACHINE_LEFT = SEQUENCE_BIT;
  private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
  /**
   * 起始的时间戳
   */
  private final static long START_STAMP = 1480166465631L;
  private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

  //数据中心
  private long dataCenterId;
  //机器标识
  private long machineId;
  //序列号
  private long sequence = 0L;
  //上一次时间戳
  private long lastStamp = -1L;

  public Snowflake(long dataCenterId, long machineId) {
    if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
      throw new IllegalArgumentException(
          "dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
    }
    if (machineId > MAX_MACHINE_NUM || machineId < 0) {
      throw new IllegalArgumentException(
          "machineId can't be greater than MAX_MACHINE_NUM or less than 0");
    }
    this.dataCenterId = dataCenterId;
    this.machineId = machineId;
  }

  public Snowflake(){
    this(2,3);
  }

  public static void main(String[] args) {
    Snowflake snowflake = new Snowflake(2, 3);

    long start = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) {
      System.out.println(snowflake.nextId());
    }

    System.out.println(System.currentTimeMillis() - start);


  }

  /**
   * 产生下一个ID
   */
  public synchronized Long nextId() {
    long currStamp = getNewStamp();
    if (currStamp < lastStamp) {
      throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
    }

    if (currStamp == lastStamp) {
      //相同毫秒内，序列号自增
      sequence = (sequence + 1) & MAX_SEQUENCE;
      //同一毫秒的序列数已经达到最大
      if (sequence == 0L) {
        currStamp = getNextMill();
      }
    } else {
      //不同毫秒内，序列号置为0
      sequence = 0L;
    }

    lastStamp = currStamp;

    return (currStamp - START_STAMP)
        //时间戳部分
        << TIMESTAMP_LEFT
        //数据中心部分
        | dataCenterId << DATA_CENTER_LEFT
        //机器标识部分
        | machineId << MACHINE_LEFT
        //序列号部分
        | sequence;
  }

  private long getNewStamp() {
    return System.currentTimeMillis();
  }

  private long getNextMill() {
    long mill = getNewStamp();
    while (mill <= lastStamp) {
      mill = getNewStamp();
    }
    return mill;
  }
}
