package pwd.initializr.search.test.business;

import java.util.Date;
import java.util.LinkedHashSet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.search.persistence.dao.ArticleRepository;
import pwd.initializr.search.persistence.dao.BookRepository;
import pwd.initializr.search.persistence.entity.ArticleDocument;
import pwd.initializr.search.persistence.entity.BookDocument;

/**
 * pwd.initializr.search.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-05-12 16:38
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DocumentTest {

  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private ArticleRepository articleRepository;

  @Test
  public void saveArticle() {
    ArticleDocument articleDocument = new ArticleDocument();
    articleDocument.setId(1L);
    articleDocument.setBookId(0L);
    articleDocument.setTitle("春江花月夜");
    articleDocument.setSubTitle("孤偏盖全唐");
    articleDocument.setAuthorId(0L);
    articleDocument.setAuthorName("张若虚");
    LinkedHashSet<String> thumbs = new LinkedHashSet<>();
    thumbs.add("http://pic.pwd.com");
    articleDocument.setThumbs(thumbs);
    LinkedHashSet<String> paragraphs = new LinkedHashSet<>();
    paragraphs.add("春江潮水连海平，海上明月共潮生。");
    paragraphs.add("滟滟随波千万里，何处春江无月明！");
    paragraphs.add("江流宛转绕芳甸，月照花林皆似霰；");
    paragraphs.add("空里流霜不觉飞，汀上白沙看不见。");
    paragraphs.add("江天一色无纤尘，皎皎空中孤月轮。");
    paragraphs.add("江畔何人初见月？江月何年初照人？");
    paragraphs.add("人生代代无穷已，江月年年望相似。");
    paragraphs.add("不知江月待何人，但见长江送流水。");
    paragraphs.add("白云一片去悠悠，青枫浦上不胜愁。");
    paragraphs.add("谁家今夜扁舟子？何处相思明月楼？");
    paragraphs.add("可怜楼上月徘徊，应照离人妆镜台。");
    paragraphs.add("玉户帘中卷不去，捣衣砧上拂还来。");
    paragraphs.add("此时相望不相闻，愿逐月华流照君。");
    paragraphs.add("鸿雁长飞光不度，鱼龙潜跃水成文。");
    paragraphs.add("昨夜闲潭梦落花，可怜春半不还家。");
    paragraphs.add("江水流春去欲尽，江潭落月复西斜。");
    paragraphs.add("斜月沉沉藏海雾，碣石潇湘无限路。");
    paragraphs.add("不知乘月几人归，落月摇情满江树。");
    articleDocument.setParagraphs(paragraphs);
    LinkedHashSet<String> labels = new LinkedHashSet<>();
    labels.add("电子书");
    labels.add("唐诗");
    articleDocument.setLabels(labels);
    articleDocument.setStatus(0);
    articleDocument.setCreateTime(new Date());
    articleDocument.setUpdateTime(new Date());
    ArticleDocument save = articleRepository.save(articleDocument);
    Assert.assertNotNull(save);
  }

  @Test
  public void saveBook() {
    BookDocument bookDocument = new BookDocument();
    bookDocument.setId(0L);
    bookDocument.setIsbn("9787229124410");
    bookDocument.setTitle("三体");
    bookDocument.setSubTitle("三体");
    bookDocument.setAuthorId(0L);
    bookDocument.setAuthorName("刘慈欣");
    bookDocument.setSummary("邪乎到家必有鬼");
    LinkedHashSet<String> thumbs = new LinkedHashSet<>();
    thumbs.add(
        "https://img11.360buyimg.com/n1/jfs/t7084/309/1723776720/329560/b5511e85/598d1fe1N95cad7bc.jpg");
    bookDocument.setThumbs(thumbs);
    LinkedHashSet<String> labels = new LinkedHashSet<>();
    labels.add("实体书");
    labels.add("科幻");
    labels.add("胶版纸");
    labels.add("精装");
    labels.add("盒装");
    labels.add("16开");
    bookDocument.setLabels(labels);
    bookDocument.setPublisher("重庆出版社");
    bookDocument.setPublicationTime(new Date());
    bookDocument.setStatus(0);
    bookDocument.setCreateTime(new Date());
    bookDocument.setUpdateTime(new Date());
    BookDocument save = bookRepository.save(bookDocument);
    Assert.assertNotNull(save);
  }

}
