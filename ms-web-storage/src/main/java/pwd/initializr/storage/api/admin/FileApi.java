package pwd.initializr.storage.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * pwd.initializr.storage.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-08 11:16
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "文件",
    value = "文件Api",
    description = "文件API"
)
@Controller(value = "fileApiByAdmin")
@RequestMapping(value = "/api/admin/file")
public interface FileApi {

    @ApiOperation(value = "文件列表")
    @GetMapping(value = {"/{index}/{size}"})
    void list(@PathVariable("index") int pageIndex, @PathVariable("size") Integer pageSize);

    @PostMapping(value = "/{appName}/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void upload(
        @PathVariable("appName") String appName,
        @PathVariable("bucketName") String bucketName,
        @RequestPart("file") MultipartFile file);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void download(@PathVariable("id") String id);

}
