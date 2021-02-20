package pwd.initializr.edu.api.admin.vo;

 import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * pwd-initializr-app-20210216215106880@ms-web-initializr
 *
 * <h1>响应参数封装</h1>
 *
 * date 2021-02-16 21:51
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
@ApiModel(value = "WordCollectionInput", description = "WordCollection响应参数")
public class WordCollectionOutput implements Serializable {

  /**
   * 主键
   * 
   */
  @ApiModelProperty(name = "id", value = "", required = true, example = "")
   @NotBlank(message = "id不能为空")
  private Long id;
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
  @ApiModelProperty(name = "bpmStatus", value = "", required = false, example = "")
  private String bpmStatus;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "bookId", value = "", required = false, example = "")
  private String bookId;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "enWord", value = "", required = false, example = "")
  private String enWord;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "chWord", value = "", required = false, example = "")
  private String chWord;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "enRadio", value = "", required = false, example = "")
  private String enRadio;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "enRadioPath", value = "", required = false, example = "")
  private String enRadioPath;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "detail", value = "", required = false, example = "")
  private String detail;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "enExample", value = "", required = false, example = "")
  private String enExample;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "chExample", value = "", required = false, example = "")
  private String chExample;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "pronounce", value = "", required = false, example = "")
  private String pronounce;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "unitId", value = "", required = false, example = "")
  private String unitId;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "oldChWord", value = "", required = false, example = "")
  private String oldChWord;
  /**
   * 
   * 
   */
  @ApiModelProperty(name = "paixu", value = "", required = false, example = "")
  private String paixu;
  /**
   * 
   * 可用性状态，0：不可用；1：可用；
   */
  @ApiModelProperty(name = "able", value = "可用性状态，0：不可用；1：可用；", required = true, example = "")
   @NotBlank(message = "able不能为空")
  private Integer able;
  /**
   * 
   * 删除状态，0：未删除，1：已删除；
   */
  @ApiModelProperty(name = "del", value = "删除状态，0：未删除，1：已删除；", required = true, example = "")
   @NotBlank(message = "del不能为空")
  private Integer del;
  /**
   * 
   * 创建时间
   */
  @ApiModelProperty(name = "createTime", value = "创建时间", required = true, example = "")
   @NotBlank(message = "createTime不能为空")
  private Date createTime;
  /**
   * 
   * 修改时间
   */
  @ApiModelProperty(name = "updateTime", value = "修改时间", required = true, example = "")
   @NotBlank(message = "updateTime不能为空")
  private Date updateTime;
}