package pwd.initializr.typeface.business;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;
import pwd.initializr.storage.rpc.RPCUploadOutput;

/**
 * pwd.initializr.typeface.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-04-04 22:48
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class StorageServiceFallbackImpl implements StorageService {

  @Value("${typeface.micro.service.deploy}")
  private Boolean microServiceDeploy;
  @Value("${typeface.micro.service.deploy.flase.storage.dir}")
  private String microServiceDeployFalseStorageDir;
  @Value("${typeface.micro.service.deploy.flase.url.prefix}")
  private String microServiceDeployFalseUrlPrefix;

  @Override
  public String upload(String appName, String bucketName, String fileName, MultipartFile file) {
    if (microServiceDeploy) {
      return JSON.toJSONString(new Output(new Meta(504)));
    } else {
      String originalFilename = file.getOriginalFilename();
      String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
      String contentType = file.getContentType();

      Output<RPCUploadOutput> output = new Output<>();
      try (InputStream inputStream = file.getInputStream()) {
        String filePath = microServiceDeployFalseStorageDir + File.separator + fileName;
        FileUtils.writeByteArrayToFile(new File(filePath), file.getBytes());
        String url = microServiceDeployFalseUrlPrefix + "/" + fileName;
        RPCUploadOutput rpcUploadOutput = new RPCUploadOutput(UUID.randomUUID().toString(), url,System.currentTimeMillis());
        output = new Output(new Meta(200),rpcUploadOutput);
      } catch (Exception e) {
        output = new Output(new Meta(500));
        throw new RuntimeException(e);
      }
      return JSON.toJSONString(output);
    }
  }
}
