package pwd.initializr.book.persistence.entity;

import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * pwd.initializr.book.persistence.entity@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-12-14 20:39
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
@Document(collection = "book")
public class BookEntity {

  private static Segment SHORTEST_SEGMENT = new DijkstraSegment().enableCustomDictionary(false)
      .enablePlaceRecognize(true).enableOrganizationRecognize(true);

  @Id
  @AutoIncKey
  @Field("id")
  private Long id;
  @Field("uid")
  private Long uid;
  @Field("isbn")
  private String isbn;
  @Field("title")
  private String title;
  @Field("sub_title")
  private String subTitle;
  @Field("author_id")
  private String authorId;
  @Field("author_name")
  private String authorName;
  @Field("summary")
  private String summary;
  @Field("thumbs")
  private Set<String> thumbs;
  @Field("labels")
  private Set<String> labels;
  @Field("publisher")
  private String publisher;
  @Field("publication_time")
  private String publicationTime;
  @Field("status")
  private Integer status;
  @Field("create_time")
  private String createTime;
  @Field("update_time")
  private String updateTime;
  @Field("words")
  private Set<String> words;

  public Set<String> createWords() {
    HashSet<String> words = new HashSet<>();

    if (StringUtils.isNotEmpty(isbn)) {
      List<Term> isbnSeg = SHORTEST_SEGMENT.seg(isbn);
      isbnSeg.forEach(obj -> words.add(obj.word));
    }
    if (StringUtils.isNotEmpty(title)) {
      List<Term> titleSeg = SHORTEST_SEGMENT.seg(title);
      titleSeg.forEach(obj -> words.add(obj.word));
      words.add(title);
    }

    if (StringUtils.isNotEmpty(subTitle)) {
      List<Term> subTitleSeg = SHORTEST_SEGMENT.seg(subTitle);
      subTitleSeg.forEach(obj -> words.add(obj.word));
      words.add(subTitle);
    }

    if (StringUtils.isNotEmpty(authorName)) {
      List<Term> authorNameSeg = SHORTEST_SEGMENT.seg(authorName);
      authorNameSeg.forEach(obj -> words.add(obj.word));
      words.add(authorName);
    }

    if (StringUtils.isNotEmpty(summary)) {
      List<Term> summarySeg = SHORTEST_SEGMENT.seg(summary);
      summarySeg.forEach(obj -> words.add(obj.word));
    }

    if (StringUtils.isNotEmpty(publisher)) {
      List<Term> publisherSeg = SHORTEST_SEGMENT.seg(publisher);
      publisherSeg.forEach(obj -> words.add(obj.word));
      words.add(publisher);
    }

    if (labels != null) {
      labels.forEach(label -> {
        SHORTEST_SEGMENT.seg(label).forEach(item -> words.add(item.word));
        words.add(label);
      });
    }

    if (StringUtils.isNotEmpty(publicationTime)) {
      List<Term> publicationTimeSeg = SHORTEST_SEGMENT.seg(publicationTime);
      publicationTimeSeg.forEach(obj -> words.add(obj.word));
    }

    return words;
  }
}
