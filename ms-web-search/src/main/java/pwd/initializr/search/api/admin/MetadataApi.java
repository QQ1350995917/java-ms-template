package pwd.initializr.search.api.admin;

import io.swagger.annotations.Api;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pwd.initializr.search.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-16 14:22
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Api(
    tags = "元数据管理",
    value = "metadataApi",
    description = "元数据API"
)
@RestController(value = "metadataApi")
@RequestMapping(value = "/api/admin/metadata")
public interface MetadataApi {

    @GetMapping(value = {"/indices","/indices/{indexName}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void listIndices(@PathVariable(value = "indexName",required = false) String indexName);

    @PostMapping(value = {"/index"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createIndex(@RequestBody @Valid @NotNull(message = "参数不能为空") String indexName);

    @DeleteMapping(value = {"/index/{indexName}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void deleteIndices(@PathVariable(value = "indexName",required = true) @Valid @NotNull(message = "参数不能为空") String indexName);
}
