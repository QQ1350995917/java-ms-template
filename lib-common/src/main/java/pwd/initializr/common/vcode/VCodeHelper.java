package pwd.initializr.common.vcode;

import io.swagger.models.auth.In;
import java.awt.image.BufferedImage;

/**
 * pwd.initializr.common.vcode@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-18 18:54
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface VCodeHelper {

  String productMessage();

  String productMessage(Integer length);

  BufferedImage productImage();

  BufferedImage productImage(String codeMessage);
}
