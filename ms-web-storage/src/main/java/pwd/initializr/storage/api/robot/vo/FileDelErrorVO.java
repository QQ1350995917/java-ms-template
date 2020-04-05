package pwd.initializr.storage.api.robot.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.storage.api.robot.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-05 11:30
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class FileDelErrorVO {
  protected String code;
  protected String message;
  protected String bucketName;
  protected String objectName;
  protected String resource;
  protected String requestId;
  protected String hostId;
  protected String errorCode;
}
