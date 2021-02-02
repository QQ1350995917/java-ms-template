package pwd.initializr.edu.api.vo;

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
 * project-generator-test-20210202144421995@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-02 14:44
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
@ApiModel(value = "ArticleContentInput", description = "ArticleContent请求参数")
public class ArticleContentInput implements Serializable {

   /**
    * 
    * 上级ID
    */
   @ApiModelProperty(name = "pid", value = "上级ID", required = true, example = "")
   @Digits(integer = 19, fraction = 0, message = "pid须为整数")
   private Long pid;

   /**
    * 
    * 标签名称
    */
   @ApiModelProperty(name = "name", value = "标签名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
   private String name;

   /**
    * 
    * 叶子节点，0：非叶子节点；1：叶子节点
    */
   @ApiModelProperty(name = "leaf", value = "叶子节点，0：非叶子节点；1：叶子节点", required = true, example = "")
   @Digits(integer = 10, fraction = 0, message = "leaf须为整数")
   private Integer leaf;

}
