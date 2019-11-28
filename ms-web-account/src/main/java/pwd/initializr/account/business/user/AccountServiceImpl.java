package pwd.initializr.account.business.user;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwd.initializr.account.business.user.bo.Account;
import pwd.initializr.account.persistence.dao.AccountEntity;
import pwd.initializr.account.persistence.mapper.AccountMapper;
import pwd.initializr.common.web.business.bo.ObjectList;

/**
 * pwd.initializr.account.business.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-21 14:08
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountMapper accountMapper;

  @Override
  public Account insertAccount(Account account) {
    AccountEntity accountEntity = new AccountEntity();
    BeanUtils.copyProperties(account,accountEntity);
    accountMapper.insertAccount(accountEntity);
    account.setId(accountEntity.getId());
    return account;
  }

  @Override
  public ObjectList<Account> findByUserId(Long userId) {
    List<AccountEntity> accountEntities = accountMapper.findByUserId(userId);
    ObjectList<Account> result = new ObjectList<>();
    for (AccountEntity accountEntity : accountEntities) {
      Account account = new Account();
      BeanUtils.copyProperties(accountEntity,account);
      result.getElements().add(account);
    }
    return result;
  }

  @Override
  public Account findByAccountId(Long accountId) {
    AccountEntity accountEntity = accountMapper.findByAccountId(accountId);
    Account account = new Account();
    BeanUtils.copyProperties(accountEntity,account);
    return account;
  }
}
