package pwd.initializr.monitor.rpc;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.monitor.rpc@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-10-21 16:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RPCHostDisk implements Serializable {

    @ApiModelProperty(value = "主机名称")
    private String id;
    @ApiModelProperty(value = "磁盘挂载路径")
    private String dirName;
    @ApiModelProperty(value = "磁盘名称")
    private String devName;
    @ApiModelProperty(value = "本地硬盘、光驱、网络文件系统等")
    private String typeName;
    @ApiModelProperty(value = "FAT32、NTFS")
    private String sysTypeName;
    @ApiModelProperty(value = "磁盘选项")
    private String options;
    @ApiModelProperty(value = "磁盘类型，0：UNKNOWN；1：NONE；2：LOCAL_DISK；3：NETWORK；4：RAM_DISK；5：CDROM；6：SWAP；")
    private Integer type;
    @ApiModelProperty(value = "磁盘标记")
    private Long flags;

}
