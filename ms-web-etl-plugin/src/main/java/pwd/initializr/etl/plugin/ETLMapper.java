package pwd.initializr.etl.plugin;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

/**
 * pwd.initializr.etl.plugin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-01-14 17:13
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Mapper
public interface ETLMapper {

  List<Map<String,String>> selectRegisterEnterprise();

}
