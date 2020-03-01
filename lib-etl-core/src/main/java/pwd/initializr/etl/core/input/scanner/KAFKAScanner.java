package pwd.initializr.etl.core.input.scanner;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import pwd.initializr.etl.core.input.processor.Processor;

/**
 * pwd.initializr.etl.core.input.scanner@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-01 16:53
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class KAFKAScanner extends DefaultScanner implements Runnable {

  @Override
  public void run() {
    Properties properties = new Properties();
    properties.put("bootstrap.servers", getConfig().getString("bootstrapServers"));
    properties.put("group.id", getConfig().getString("groupId"));
    properties.put("enable.auto.commit", getConfig().getBoolean("enableAutoCommit"));
    properties.put("auto.commit.interval.ms", getConfig().getInteger("autoCommitIntervalMs"));
    properties.put("auto.offset.reset", getConfig().getString("autoOffsetReset"));
    properties.put("session.timeout.ms", getConfig().getInteger("sessionTimeoutMs"));
    properties.put("key.deserializer", getConfig().getString("keyDeserializer"));
    properties.put("value.deserializer", getConfig().getString("valueDeserializer"));
    properties.put("metrics.recording.level", "INFO");

    String topic = getConfig().getString("topic");
    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
    kafkaConsumer.subscribe(Arrays.asList(topic));
    while (true) {
      ConsumerRecords<String, String> records = kafkaConsumer
          .poll(Duration.ofMillis(Long.MAX_VALUE));
      for (ConsumerRecord<String, String> record : records) {
        long offset = record.offset();
        String value = record.value();
        Processor processor = getProcessor();
        Map<String, Object> map = new HashMap<>(2);
        map.put("_offset", offset);
        map.put("_value", value);
        processor.process(map);
      }
    }
  }
}
