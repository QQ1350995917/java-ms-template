package pwd.initializr.book.api.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import java.util.LinkedList;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * pwd.initializr.book.api.admin.vo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 22:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreateArticleInput extends CreateBookInput {

    @ApiModelProperty(name = "bookId", value = "0", required = false, example = "9787229124410")
    @NotNull(message = "0")
    private Long bookId;
    @ApiModelProperty(name = "paragraphs", value = "0", required = false, example = "[\"paragraph1\",\"paragraph2\",\"paragraph3\"]")
    @NotNull(message = "0")
    private LinkedList<String> paragraphs;
}
