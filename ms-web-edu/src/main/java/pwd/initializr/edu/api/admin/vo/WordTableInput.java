package pwd.initializr.edu.api.admin.vo;

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
 * pwd-initializr-app-20210216152305571@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-16 15:23
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
@ApiModel(value = "WordTableInput", description = "WordTable请求参数")
public class WordTableInput implements Serializable {

   /**
    * 
    * 上级ID
    */
   @ApiModelProperty(name = "pid", value = "上级ID", required = true, example = "")
   @NotBlank(message = "pid不能为空")
   private String pid;

   /**
    * 
    * 标签名称
    */
   @ApiModelProperty(name = "name", value = "标签名称", required = true, example = "")
   @NotBlank(message = "name不能为空")
   private String name;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "sysOrgCode", value = "", required = false, example = "")
   private String sysOrgCode;

   /**
    * 
    * 原始数据标识
    */
   @ApiModelProperty(name = "sysCompanyCode", value = "原始数据标识", required = false, example = "")
   private String sysCompanyCode;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "bpmStatus", value = "", required = false, example = "")
   private String bpmStatus;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "zindex", value = "", required = false, example = "")
   private String zindex;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "wordAmount", value = "", required = false, example = "")
   private String wordAmount;

   /**
    * 
    * 
    */
   @ApiModelProperty(name = "sort", value = "", required = false, example = "")
   private String sort;

   /**
    * 
    * 叶子节点，0：非叶子节点；1：叶子节点
    */
   @ApiModelProperty(name = "leaf", value = "叶子节点，0：非叶子节点；1：叶子节点", required = true, example = "")
   @Digits(integer = 10, fraction = 0, message = "leaf须为整数")
   private Integer leaf;

}
