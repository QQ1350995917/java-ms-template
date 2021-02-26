package pwd.initializr.search.business.bo;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwd.initializr.search.rpc.RPCDocument;

/**
 * pwd.initializr.search.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-06-14 23:19
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
public class DocumentBO extends RPCDocument {

  private String index;

  public DocumentBO(String id, String able, String title, String source,
      Set<String> tags, List<String> contents, String link, String time) {
    super(id, able, title, source, tags, contents, link, time);
  }
}
