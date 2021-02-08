package pwd.initializr.common.web.api.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.LinkedHashSet;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import pwd.initializr.common.web.business.bo.SortBO;

/**
 * pwd.initializr.common.web.api.vo@ms-web-initializr
 *
 * <h1>API统一排序查询输入结构声明</h1>
 *
 * date 2020-07-27 16:31
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Slf4j
public class SortInput implements Serializable {

    @ApiModelProperty(name = "fieldName", value = "指定排序字段", required = false, example = "id")
    private String fieldName;
    @ApiModelProperty(name = "value", value = "[desc|asc]", required = false, example = "desc")
    private String sort;

    public static LinkedHashSet<SortBO> parse(String sortsJson) {
        LinkedHashSet<SortInput> sortInputs = new LinkedHashSet<>();
        if (StringUtils.isNotBlank(sortsJson)) {
            try {
                sortInputs = JSON
                    .parseObject(sortsJson, new TypeReference<LinkedHashSet<SortInput>>() {
                    });
            } catch (Exception e) {
                log.error("格式化" + sortsJson + "JSON" + "发生异常", e);
                throw new RuntimeException(e);
            }
        }
        LinkedHashSet<SortBO> sortBOS = new LinkedHashSet<>();
        for (SortInput sortInput : sortInputs) {
            SortBO sortBO = new SortBO();
            BeanUtils.copyProperties(sortInput, sortBO);
            sortBOS.add(sortBO);
        }
        return sortBOS;
    }

    @Override
    public int hashCode() {
        return fieldName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SortInput)) {
            return false;
        }

        SortInput sortInput = (SortInput) obj;
        if (sortInput.getFieldName() == null) {
            return false;
        }
        if (!sortInput.getSort().equals(fieldName)) {
            return false;
        }

        return true;
    }

    public String getFieldName() {
        return StringUtils.isBlank(fieldName) ? "id" : fieldName;
    }

    public String getSort() {
        return StringUtils.isBlank(sort) ? "desc" : sort;
    }

}
