package pwd.initializr.storage.api.robot;

import io.swagger.annotations.Api;
import java.io.InputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pwd.initializr.common.web.api.robot.RobotController;
import pwd.initializr.storage.business.StorageService;
import pwd.initializr.storage.business.bo.StorageBO;
import pwd.initializr.storage.rpc.RPCUploadOutput;

/**
 * pwd.initializr.storage.api.robot@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-03-30 23:03
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件",
    value = "文件Api",
    description = "文件API"
)
@RestController(value = "fileApiByRobot")
@RequestMapping(value = "/api/robot/file")
public class FileController extends RobotController implements FileApi {

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/{appName}/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void upload(
        @PathVariable("appName") String appName,
        @PathVariable("bucketName") String bucketName,
        @RequestPart("file") MultipartFile file) {
        try {
            RPCUploadOutput rpcUploadOutput = upload0(appName, bucketName, file);
            outputData(rpcUploadOutput);
        } catch (Exception e) {
            outputException(500);
        }
    }

//    @PostMapping("/{appName}/{bucketName}/batch")
//    public void upload(
//        @PathVariable("appName") String appName,
//        @PathVariable("bucketName") String bucketName,
//        HttpServletRequest request) {
//
//        HashMap result = new HashMap<>(2);
//        List<RPCUploadOutput> successResult = new LinkedList<>();
//        List<RPCUploadErrorOutput> failResult = new LinkedList<>();
//        result.put("success",successResult);
//        result.put("fail",failResult);
//
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file;
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            String originalFilename = file.getOriginalFilename();
//            if (!file.isEmpty()) {
//                try {
//                    RPCUploadOutput output = upload0(appName, bucketName, file);
//                    successResult.add(output);
//                } catch (Exception e) {
//                    failResult.add(new RPCUploadErrorOutput(e.getMessage(), i, originalFilename));
//                }
//            } else {
//                failResult.add(new RPCUploadErrorOutput("file is empty", i, originalFilename));
//            }
//        }
//        outputData(result);
//    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public void download(@PathVariable("id") String id) {
        StorageBO oneByUrl = storageService.findOneById(id);
        String contentType = oneByUrl.getFileType();
        String filename = oneByUrl.getFileName();
        String suffix = oneByUrl.getFileSuffix();
        try (InputStream inputStream = storageService.getObject(oneByUrl)) {
            this.outputAttachmentFile(inputStream, contentType, filename, suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RPCUploadOutput upload0(String appName, String bucketName, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String contentType = file.getContentType();
        try (InputStream inputStream = file.getInputStream()) {
            StorageBO storageBO = new StorageBO();
            storageBO.setApp(appName);
            storageBO.setBucketName(bucketName);
            storageBO.setFileName(fileName);
            storageBO.setFileSuffix(suffix);
            storageBO.setFileType(contentType);
            StorageBO result = storageService.uploadFile(storageBO, inputStream);
            RPCUploadOutput uploadOutput = new RPCUploadOutput();
            BeanUtils.copyProperties(result, uploadOutput);
            return uploadOutput;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
