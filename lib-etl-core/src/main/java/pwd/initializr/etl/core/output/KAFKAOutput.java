package pwd.initializr.etl.core.output;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * pwd.initializr.etl.core.output@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-03 16:42
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class KAFKAOutput extends DefaultOutput {

  @Override
  public void run() {
    Properties properties = new Properties();
    properties.put("bootstrap.servers", getConfig().getString("bootstrapServers"));
    properties.put("acks", getConfig().getString("acks"));
    properties.put("retries", getConfig().getInteger("retries"));
    properties.put("compression.type", getConfig().getString("compressionType"));
    properties.put("batch.size", getConfig().getInteger("batchSize"));
    properties.put("linger.ms", getConfig().getInteger("lingerMs"));
    properties.put("buffer.memory", getConfig().getInteger("bufferMemory"));
    properties.put("max.in.flight.requests.per.connection",
        getConfig().getInteger("maxInFlightRequestsPerConnection"));
    properties.put("key.serializer", getConfig().getString("keySerializer"));
    properties.put("value.serializer", getConfig().getString("valueSerializer"));
    String topic = getConfig().getString("topic");

    while (true) {
      Producer<String, String> producer = new KafkaProducer<>(properties);
      while (true) {
        try {
          Map<String, Object> take = this.getOutBlockingQueue().take();
          String message = JSON.toJSONString(take);
          producer.send(new ProducerRecord<>(topic, message));
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
