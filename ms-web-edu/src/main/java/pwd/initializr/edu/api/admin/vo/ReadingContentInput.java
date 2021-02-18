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
 * pwd-initializr-edu-20210218125038934@ms-web-initializr
 *
 * <h1>请求参数封装</h1>
 *
 * date 2021-02-18 12:50
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
@ApiModel(value = "ReadingContentInput", description = "ReadingContent请求参数")
public class ReadingContentInput implements Serializable {

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
   @ApiModelProperty(name = "readingName", value = "标签名称", required = true, example = "")
   @NotBlank(message = "readingName不能为空")
   private String readingName;

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
   @ApiModelProperty(name = "sysOrgCode", value = "", required = false, example = "")
   private String sysOrgCode;

   /**
    *
    *
    */
   @ApiModelProperty(name = "sysCompanyCode", value = "", required = false, example = "")
   private String sysCompanyCode;

   /**
    *
    *
    */
   @ApiModelProperty(name = "sort", value = "", required = false, example = "")
   private String sort;

   /**
    *
    *
    */
   @ApiModelProperty(name = "zuixiao", value = "", required = false, example = "")
   private String zuixiao;

   /**
    *
    *
    */
   @ApiModelProperty(name = "zuida", value = "", required = false, example = "")
   private String zuida;

   /**
    *
    *
    */
   @ApiModelProperty(name = "qusCount", value = "", required = false, example = "")
   private String qusCount;

   /**
    *
    *
    */
   @ApiModelProperty(name = "score", value = "", required = false, example = "")
   private String score;

   /**
    *
    *
    */
   @ApiModelProperty(name = "open", value = "", required = false, example = "")
   private String open;

   /**
    *
    *
    */
   @ApiModelProperty(name = "content", value = "", required = false, example = "")
   private String content;

   /**
    *
    * 叶子节点，0：非叶子节点；1：叶子节点
    */
   @ApiModelProperty(name = "leaf", value = "叶子节点，0：非叶子节点；1：叶子节点", required = true, example = "")
   @Digits(integer = 10, fraction = 0, message = "leaf须为整数")
   private Integer leaf;

}
