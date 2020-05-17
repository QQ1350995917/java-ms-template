package pwd.initializr.book.api.user.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.book.rpc.SearchInput;

/**
 * pwd.initializr.book.api.user.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-17 16:34
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "bookVO", description = "图书VO属性")
public class SearchInputVO extends SearchInput {

}
