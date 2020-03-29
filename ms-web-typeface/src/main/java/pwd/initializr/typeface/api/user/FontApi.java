package pwd.initializr.typeface.api.user;

import pwd.initializr.typeface.api.user.vo.FontListInput;

/**
 * pwd.initializr.typeface.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-29 20:42
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public interface FontApi {

  void listFont(FontListInput input);

  void findFont(Long id);

}
