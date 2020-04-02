package pwd.initializr.word.html;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * pwd.initializr.word.html@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-31 17:50
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Slf4j
public class WordToPdfByLiboffice {
//https://www.cnblogs.com/qlqwjy/p/9846904.html
  public static void main(String[] args) {
    convertOffice2PDF("C:\\Users\\Administrator\\Desktop\\自动报告\\template.docx","C:\\Users\\Administrator\\Desktop\\自动报告");
  }

  public static boolean convertOffice2PDF(String inputFile, String pdfFile){
    long start = System.currentTimeMillis();
    String command;
    boolean flag;
    String osName = System.getProperty("os.name");
    if (osName.contains("Windows")) {
      command = "cmd /c soffice --headless --invisible --convert-to pdf:writer_pdf_Export " + inputFile + " --outdir " + pdfFile;
    }else {
      command = "libreoffice --headless --invisible --convert-to pdf:writer_pdf_Export " + inputFile + " --outdir " + pdfFile;
    }
    flag = executeLibreOfficeCommand(command);
    long end = System.currentTimeMillis();
    log.debug("用时:{} ms", end - start);
    return flag;
  }

  public static boolean executeLibreOfficeCommand(String command) {
    log.info("开始进行转化.......");
    Process process;// Process可以控制该子进程的执行或获取该子进程的信息
    try {
      log.debug("convertOffice2PDF cmd : {}", command);
      process = Runtime.getRuntime().exec(command);// exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
      // 下面两个可以获取输入输出流
//            InputStream errorStream = process.getErrorStream();
//            InputStream inputStream = process.getInputStream();
    } catch (IOException e) {
      log.error(" convertOffice2PDF {} error", command, e);
      return false;
    }
    int exitStatus = 0;
    try {
      exitStatus = process.waitFor();// 等待子进程完成再往下执行，返回值是子线程执行完毕的返回值,返回0表示正常结束
      // 第二种接受返回值的方法
      int i = process.exitValue(); // 接收执行完毕的返回值
      log.debug("i----" + i);
    } catch (InterruptedException e) {
      log.error("InterruptedException  convertOffice2PDF {}", command, e);
      return false;
    }
    if (exitStatus != 0) {
      log.error("convertOffice2PDF cmd exitStatus {}", exitStatus);
    } else {
      log.debug("convertOffice2PDF cmd exitStatus {}", exitStatus);
    }
    process.destroy(); // 销毁子进程
    log.info("转化结束.......");
    return true;
  }
}
