package pwd.initializr.etl.business.admin;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.Random;
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
  boolean transferringFlag = true;
  int transferring = 0;
  boolean transferredFlag = true;
  int transferred = 0;
  int processing = 0;

  @Scheduled(cron = "${etl.report.input.cron}")
  private void inputReport() {
    scanInput();
  }
  Random random = new Random(10);
  private void scanInput() {
    String filePath = ETLApplication.inputDir;
//    File[] files = new File(filePath).listFiles();
    inputReportBO.setFilePath(filePath);
//    inputReportBO.setTotal(files.length);

//    for (File file : files) {
//      String name = file.getName();
//    }

    if (transferringFlag && transferring <= 100) {
      transferring += 8;
    } else {
      transferringFlag = false;
    }

    if (transferredFlag && transferred <= 100){
      transferred += 3;
      transferring -= 3;
      if (transferring < 5) {
        transferringFlag = true;
      }
    } else {
      transferring -= 3;
      if (transferring < 5) {
        transferringFlag = true;
      }
      transferredFlag = false;
    }

    processing ++;
    transferred --;
    if (transferred < 1) {
      transferredFlag = true;
    }

    if (processing > 200) {
      processing = 10;
    }

    inputReportBO.setTransferring(transferring);
    inputReportBO.setTransferred(transferred);
    inputReportBO.setProcessing(processing);

    MonitorWebService.sendInfo(JSON.toJSONString(inputReportBO), null);
  }

  public InputReportBO getInputReport() {
    return inputReportBO;
  }
}
