package pwd.initializr.monitor.rpc;

import io.swagger.annotations.ApiModelProperty;
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
public class RPCHostDisk {
    @ApiModelProperty(value = "主机名称")
    private String id;
    private String dirName = null;
    private String devName = null;
    @ApiModelProperty(value = "本地硬盘、光驱、网络文件系统等")
    private String typeName = null;
    @ApiModelProperty(value = "FAT32、NTFS")
    private String sysTypeName = null;
    private String options = null;
    private Integer type = 0;
    private Long flags = 0L;
}
