package pwd.initializr.edu.test.business;

import javax.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.edu.business.EduTermCourseService;
import pwd.initializr.edu.business.EduTermCourseTextbookService;
import pwd.initializr.edu.business.EduTermService;
import pwd.initializr.edu.business.bo.EduTermCourseTextbookBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-03-08 16:44
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EduTermCourseTextbookServiceTest {

    @Resource
    private EduTermService eduTermService;

    @Resource
    private EduTermCourseService eduTermCourseService;

    @Resource
    private EduTermCourseTextbookService eduTermCourseTextbookService;


    public void initEduTermCourseTable(Long termId,Long courseId) {

        EduTermCourseTextbookBO eduTermCourseTextbookBO = new EduTermCourseTextbookBO();
        eduTermCourseTextbookBO.setId(null);
        eduTermCourseTextbookBO.setCid(courseId);
        eduTermCourseTextbookBO.setTid(termId);
//        eduTermCourseTextbookBO.setName(courseId);
//        eduTermCourseTextbookBO.setPublisher(courseId);
//        eduTermCourseTextbookBO.setYear(courseId);
//        eduTermCourseTextbookBO.setIsbn(courseId);
//        eduTermCourseTextbookBO.setVersion(courseId);
//        eduTermCourseTextbookBO.setSummary(courseId);
        Long id = eduTermCourseTextbookService.insert(eduTermCourseTextbookBO);

    }

}
