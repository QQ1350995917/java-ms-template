package pwd.initializr.etl.business.admin;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pwd.initializr.etl.ETLApplication;
import pwd.initializr.etl.business.admin.bo.InputReportBO;
import pwd.initializr.etl.util.FileReportUtils;

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

  @Autowired
  private MonitorService monitorService;

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

  private void scanInput() {
    String filePath = ETLApplication.inputDir;
    Map<String, Integer> stringIntegerMap = FileReportUtils.countFile(filePath);
    inputReportBO.setTransferring(stringIntegerMap.get("transferring"));
    inputReportBO.setTransferred(stringIntegerMap.get("transferred"));
    inputReportBO.setProcessing(stringIntegerMap.get("processing"));
    inputReportBO.setFilePath(filePath);
    monitorService.sendInfo(JSON.toJSONString(inputReportBO), null);
  }

  public InputReportBO getInputReport() {
    return inputReportBO;
  }
}
