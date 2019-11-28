package pwd.initializr.logger.test.business;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

/**
 * pwd.initializr.logger.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 12:54
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class MongoDBClient {

  public static MongoClient getMongoClient() {
    /**MongoClient 是线程安全的，可以在多个线程中共享同一个实例
     * 一个 MongoClient 相当于一个客户端，一个客户端可以有多个连接
     */
    MongoClient mongoClient = null;
    try {
      /** new MongoClient 创建客户端的时候，可以传入 MongoClientOptions 客户端配置选项
       * 所以可以将设置全部事先设置好
       */
      MongoClientOptions.Builder build = new MongoClientOptions.Builder();
      /**与目标数据库能够建立的最大连接数为50*/
      build.connectionsPerHost(50);

      /**如果当前所有的连接都在使用中，则每个连接上可以有50个线程排队等待*/
      build.threadsAllowedToBlockForConnectionMultiplier(50);

      /**一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为，此处为 2分钟
       * 如果超过 maxWaitTime 都没有获取到连接的话，该线程就会抛出 Exception
       * */
      build.maxWaitTime(1000 * 60 * 2);

      /**设置与数据库建立连接时最长时间为1分钟*/
      build.connectTimeout(1000 * 60 * 1);
      MongoClientOptions mongoClientOptions = build.build();

      /** 将 MongoDB 服务器的 ip 与端口先封装好
       * 连接 MongoDB 服务端地址，实际项目中应该放到配置文件进行配置
       * */
      ServerAddress serverAddress = new ServerAddress("localhost", 27017);

      /**
       * 通过 ServerAddress 与 MongoClientOptions 创建连接到 MongoDB 的数据库实例
       * MongoClient(String host, int port)：
       *      1）host：MongoDB 服务端 IP
       *      2）port：MongoDB 服务端 端口，默认为 27017
       *      3）即使 MongoDB 服务端关闭，此时也不会抛出异常，只有到真正调用方法是才会
       *      4）连接 MongoDB 服务端地址，实际项目中应该放到配置文件进行配置
       * MongoClient(final ServerAddress addr, final MongoClientOptions options)
       * 重载了很多构造方法，这只是其中两个常用的
       *      */
//      MongoCredential credential = MongoCredential.createCredential(
//          "root", "admin", "root".toCharArray());
//      mongoClient = new MongoClient(serverAddress,credential, mongoClientOptions);

      mongoClient = new MongoClient(serverAddress, mongoClientOptions);
    } catch (MongoException e) {
      e.printStackTrace();
    }
    return mongoClient;
  }

}
