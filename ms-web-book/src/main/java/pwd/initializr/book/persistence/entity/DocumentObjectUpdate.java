package pwd.initializr.book.persistence.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Update;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-06 21:21
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class DocumentObjectUpdate {

  public static Update getUpdate(Object object) {
    Class<?> aClass = object.getClass();
    Class<?> aSuperClass = aClass.getSuperclass();
    Document documentAnnotation = aClass.getAnnotation(Document.class);
    if (documentAnnotation == null) {
      throw new RuntimeException("not a mongodb document object");
    }
    Update update = new Update();
    if (aSuperClass != null) {
      Field[] declaredFields = aSuperClass.getDeclaredFields();
      Map<String, Object> updateMap = getUpdateMap(object, declaredFields);
      Optional.ofNullable(updateMap).ifPresent(map -> map.forEach((key, value) -> update.set(key, value)));
    }
    Field[] declaredFields = aClass.getDeclaredFields();
    Map<String, Object> updateMap = getUpdateMap(object, declaredFields);
    Optional.ofNullable(updateMap).ifPresent(map -> map.forEach((key, value) -> update.set(key, value)));

    return update;
  }

  private static Map<String, Object> getUpdateMap(Object object, Field[] declaredFields) {
    if (declaredFields == null) {
      return null;
    }
    Map<String, Object> result = new HashMap<>();
    for (Field declaredField : declaredFields) {
      declaredField.setAccessible(true);
      org.springframework.data.mongodb.core.mapping.Field fieldAnnotation = declaredField
          .getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
      if (fieldAnnotation != null) {
        String key = fieldAnnotation.value();
        try {
          Object value = declaredField.get(object);
          if (value != null) {
            result.put(key, value);
          }
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e.getMessage());
        }
      }
    }
    if (result.size() > 0) {
      return result;
    } else {
      return null;
    }
  }
}
