package pwd.initializr.typeface.business;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-01 22:49
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class StorageServiceFormEncoder extends FormEncoder {

  public StorageServiceFormEncoder() {
    this(new Default());
  }

  public StorageServiceFormEncoder(Encoder delegate) {
    super(delegate);

    MultipartFormContentProcessor processor = (MultipartFormContentProcessor) getContentProcessor(
        ContentType.MULTIPART);
    processor.addWriter(new SpringSingleMultipartFileWriter());
    processor.addWriter(new SpringManyMultipartFilesWriter());
  }

  @Override
  public void encode(Object object, Type bodyType, RequestTemplate template)
      throws EncodeException {
    if (bodyType.equals(MultipartFile.class)) {
      MultipartFile file = (MultipartFile) object;
      Map data = Collections.singletonMap(file.getName(), object);
      super.encode(data, MAP_STRING_WILDCARD, template);
      return;
    } else if (bodyType.equals(MultipartFile[].class)) {
      MultipartFile[] file = (MultipartFile[]) object;
      if (file != null) {
        Map data = Collections.singletonMap(file.length == 0 ? "" : file[0].getName(), object);
        super.encode(data, MAP_STRING_WILDCARD, template);
        return;
      }
    }
    super.encode(object, bodyType, template);
  }
}
