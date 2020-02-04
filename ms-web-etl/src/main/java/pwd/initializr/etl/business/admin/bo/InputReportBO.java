package pwd.initializr.etl.business.admin.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.etl.business.admin.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-15 21:18
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class InputReportBO {
  private String filePath;
  // 输入区域中正在输入的文件总数
  private Integer transferring;
  // 输入区域中已经输入完成的文件总数
  private Integer transferred;
  // 输入区域中正在处理的文件总数
  private Integer Processing;
  // 输入区域中数据文件总数
  private Integer total;
}
