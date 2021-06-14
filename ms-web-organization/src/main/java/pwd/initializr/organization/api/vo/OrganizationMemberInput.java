package pwd.initializr.organization.api.vo;

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
 * pwd-initializr-organization@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-22 21:33
 *
 * @author Automatic[www.dingpengwei@foxmail.com]
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(value = "OrganizationMemberInput", description = "OrganizationMember请求参数")
public class OrganizationMemberInput implements Serializable {

   /**
    * 
    * 排序
    */
   @ApiModelProperty(name = "sort", value = "排序", required = false, example = "0")
   @Digits(integer = 10, fraction = 0, message = "sort须为整数")
   private Integer sort = 0;

}
