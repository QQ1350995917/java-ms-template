package pwd.initializr.search.business.admin.bo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.search.business.admin.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-16 15:13
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
public class IndexBO {

    private String name;
    private String uuid;
    private String version;
    private String replicas;
    private String shards;
    private String date;
    private List<MappingBO> properties;
}
