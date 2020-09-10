package pwd.initializr.generator.business.mysql.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.generator.business.mysql.database@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-10 10:34
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TableColumnBO {

    private String name;
    private String type;
    private String comment;
    private Boolean key;

    public TableColumnBO(String name, String type, String comment, Boolean key) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.key = key;
    }
}
