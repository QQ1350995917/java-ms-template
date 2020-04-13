package pwd.initializr.article.persistence.dao;

import java.lang.reflect.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * pwd.initializr.logger.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-19 14:15
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
public class AutoIncKeyEventListener extends AbstractMongoEventListener {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void onBeforeConvert(BeforeConvertEvent event) {
    Object source = event.getSource();
    if (source != null) {
      ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
          ReflectionUtils.makeAccessible(field);
          // 如果字段添加了我们自定义的AutoIncKey注解
          if (field.isAnnotationPresent(AutoIncKey.class)) {
            // 设置自增ID
            field.set(source, getNextId(source.getClass().getSimpleName()));
          }
        }
      });
    }
  }


  private Long getNextId(String collName) {
    Query query = new Query(Criteria.where("collName").is(collName));
    Update update = new Update();
    update.inc("seqId", 1);
    FindAndModifyOptions options = new FindAndModifyOptions();
    options.upsert(true);
    options.returnNew(true);
    AutoIncInfo seq = mongoTemplate.findAndModify(query, update, options, AutoIncInfo.class);
    return seq.getSeqId();
  }
}
