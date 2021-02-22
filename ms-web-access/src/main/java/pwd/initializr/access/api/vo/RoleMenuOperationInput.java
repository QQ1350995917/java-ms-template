package pwd.initializr.access.api.vo;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * pwd-initializr-access@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-22 22:48
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "RoleMenuOperationInput", description = "RoleMenuOperation请求参数")
public class RoleMenuOperationInput implements Serializable {

}
