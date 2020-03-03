package pwd.initializr.etl.core.test.input;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import pwd.initializr.etl.core.ETLDriver;

/**
 * pwd.initializr.etl.core.test.input@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-02-29 23:12
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class KAFKAInputTest {

  public static void main(String[] args) {
    String jsonFilePath = "/Users/pwd/workspace/dingpw/ms-web-initializr/lib-etl-core/src/test/resources/config-instance-mac.json";
    ETLDriver etlDriver = new ETLDriver().start(jsonFilePath,null);
    BlockingQueue<Map<String, Object>> blockingQueue = etlDriver.getInputBlockingQueue();
    new Thread(new Consume(blockingQueue)).start();
//    new Thread(new KAFKAInputTest().new Produce());
  }

  class Produce implements Runnable {

    Properties kafkaProps = new Properties();

    Produce() {
      kafkaProps.put("acks", "all");
      kafkaProps.put("retries", 0);
      kafkaProps.put("compression.type", "snappy");
      kafkaProps.put("batch.size", 100);
      kafkaProps.put("linger.ms", 1);
      kafkaProps.put("buffer.memory", 33554432);
      kafkaProps.put("max.in.flight.requests.per.connection", 1);
      kafkaProps.put("bootstrap.servers", "127.0.0.1:9092");
      kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
      kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    @Override
    public void run() {
      Producer<String, String> producer = new KafkaProducer<>(kafkaProps);
      while (true) {
        try {
          String msg = "我|和|我|的|祖|国|a" + System.currentTimeMillis();
          producer.send(new ProducerRecord<>("etl-test", msg));
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
//      producer.close();

    }
  }
}


