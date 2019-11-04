package pwd.initializr.account.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pwd.initializr.account.persistence.dao.AccountEntity;

/**
 * pwd.initializr.account.persistence.mapper@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 10:52
 *
 * @author DingPengwei[dingpengwei@eversec.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Component
@Mapper
public interface AccountMapper {

  void insertAccount(AccountEntity accountEntity);

  List<AccountEntity> findByUserId(Long userId);

  AccountEntity findByIdentifyAndPassword(@Param("identify") String identify,@Param("password") String password);

  AccountEntity findByAccountId(Long accountId);

}
