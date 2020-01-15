package pwd.initializr.etl.business.admin;

import java.io.File;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * pwd.initializr.etl.business.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-15 16:16
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Getter
@Service
public class ReportTask {

  private static InputReport inputReport = new InputReport();

  @Scheduled(cron = "${etl.report.input.cron}")
  private void inputReport() {
    System.out.println("ReportTask" + new Date());
  }

  private void scanInput() {
    String filePath = null;
    inputReport.setTransferring(8);
    inputReport.setTransferred(50);
    inputReport.setProcessing(5);
    inputReport.setData(63);

  }
}

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
class InputReport {

  // 输入区路径
  private String filePath;
  // 输入区域中正在输入的文件总数
  private Integer transferring;
  // 输入区域中已经输入完成的文件总数
  private Integer transferred;
  // 输入区域中正在处理的文件总数
  private Integer Processing;
  // 输入区域中数据文件总数
  private Integer data;
}
