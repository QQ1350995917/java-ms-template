package pwd.initializr.etl.business.admin;

import com.alibaba.fastjson.JSON;
import java.io.File;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pwd.initializr.etl.ETLApplication;
import pwd.initializr.etl.api.admin.MonitorWebService;
import pwd.initializr.etl.business.admin.bo.InputReportBO;

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
@Service
public class ReportTask {

  private static InputReportBO inputReportBO = new InputReportBO();

  @Scheduled(cron = "${etl.report.input.cron}")
  private void inputReport() {
    scanInput();
  }

  private void scanInput() {
    String filePath = ETLApplication.inputDir;
    File[] files = new File(filePath).listFiles();
    inputReportBO.setFilePath(filePath);
    inputReportBO.setTotal(files.length);
    int transferring = 0;
    for (File file : files) {
      String name = file.getName();
    }
    inputReportBO.setTransferring(8);
    inputReportBO.setTransferred(50);
    inputReportBO.setProcessing(5);


    MonitorWebService.sendInfo(JSON.toJSONString(inputReportBO),null);
  }

  public InputReportBO getInputReport() {
    return inputReportBO;
  }
}
