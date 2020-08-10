package pwd.initializr.common.web.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * pwd.initializr.common.web.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-08-10 17:47
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@ToString
public enum EntitySort {
    DESC("DESC", "DESC", "降序"),
    ASC("ASC", "ASC", "升序");

    private String sort;
    private String enus;
    private String zhcn;

    EntitySort(String sort, String enus, String zhcn) {
        this.sort = sort;
        this.enus = enus;
        this.zhcn = zhcn;
    }

}
