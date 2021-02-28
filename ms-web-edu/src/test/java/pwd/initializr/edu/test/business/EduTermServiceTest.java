package pwd.initializr.edu.test.business;

import java.util.LinkedList;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pwd.initializr.edu.business.EduTermService;
import pwd.initializr.edu.business.bo.EduTermBO;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-28 22:54
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EduTermServiceTest {
  @Resource
  private EduTermService eduTermService;

  @Test
  public void initEduTermTable() {
    EduTermBO eduTermBO00 = new EduTermBO(null,0L,"幼儿园","幼儿园",0,0,0);
    EduTermBO eduTermBO0000 = new EduTermBO(null,null,"小班","小班",0,0,0);
    EduTermBO eduTermBO0001 = new EduTermBO(null,null,"中班","中班",0,1,0);
    EduTermBO eduTermBO0002 = new EduTermBO(null,null,"大班","大班",0,2,0);
    EduTermBO eduTermBO1 = new EduTermBO(null,0L,"幼小衔接","幼小衔接",0,1,1);
    EduTermBO eduTermBO2 = new EduTermBO(null,0L,"小学","小学",1,2,0);
    EduTermBO eduTermBO200 = new EduTermBO(null,null,"一年级","一年级",1,0,0);
    EduTermBO eduTermBO201 = new EduTermBO(null,null,"二年级","二年级",1,1,0);
    EduTermBO eduTermBO202 = new EduTermBO(null,null,"三年级","三年级",1,2,0);
    EduTermBO eduTermBO203 = new EduTermBO(null,null,"四年级","四年级",1,3,0);
    EduTermBO eduTermBO204 = new EduTermBO(null,null,"五年级","五年级",1,4,0);
    EduTermBO eduTermBO205 = new EduTermBO(null,null,"六年级","六年级",1,5,0);
    EduTermBO eduTermBO3 = new EduTermBO(null,0L,"小升初","小升初",0,3,1);
    EduTermBO eduTermBO4 = new EduTermBO(null,0L,"初中","初中",1,4,0);
    EduTermBO eduTermBO400 = new EduTermBO(null,null,"初一","初一",1,0,0);
    EduTermBO eduTermBO401 = new EduTermBO(null,null,"初二","初二",1,1,0);
    EduTermBO eduTermBO402 = new EduTermBO(null,null,"初三","初三",1,2,0);
    EduTermBO eduTermBO5 = new EduTermBO(null,0L,"初升高","初升高",0,5,1);
    EduTermBO eduTermBO6 = new EduTermBO(null,0L,"高中","高中",1,6,0);
    EduTermBO eduTermBO600 = new EduTermBO(null,null,"高一","高一",1,0,0);
    EduTermBO eduTermBO601 = new EduTermBO(null,null,"高二","高二",1,1,0);
    EduTermBO eduTermBO602 = new EduTermBO(null,null,"高三","高三",1,2,0);
    EduTermBO eduTermBO7 = new EduTermBO(null,0L,"考研","考研",0,7,1);

    LinkedList<EduTermBO> eduTermBOS = new LinkedList<>();
    eduTermBOS.add(eduTermBO00);
    eduTermBOS.add(eduTermBO0000);
    eduTermBOS.add(eduTermBO0001);
    eduTermBOS.add(eduTermBO0002);

    eduTermBOS.add(eduTermBO1);

    eduTermBOS.add(eduTermBO2);
    eduTermBOS.add(eduTermBO200);
    eduTermBOS.add(eduTermBO201);
    eduTermBOS.add(eduTermBO202);
    eduTermBOS.add(eduTermBO203);
    eduTermBOS.add(eduTermBO204);
    eduTermBOS.add(eduTermBO205);

    eduTermBOS.add(eduTermBO3);

    eduTermBOS.add(eduTermBO4);
    eduTermBOS.add(eduTermBO400);
    eduTermBOS.add(eduTermBO401);
    eduTermBOS.add(eduTermBO402);

    eduTermBOS.add(eduTermBO5);

    eduTermBOS.add(eduTermBO6);
    eduTermBOS.add(eduTermBO600);
    eduTermBOS.add(eduTermBO601);
    eduTermBOS.add(eduTermBO602);

    eduTermBOS.add(eduTermBO7);

    Long rootLevelId = null;
    for (EduTermBO eduTermBO : eduTermBOS) {
      if (eduTermBO.getPid() != null) {
         rootLevelId = eduTermService.insert(eduTermBO);
      } else {
        eduTermBO.setPid(rootLevelId);
        Long gradeId = eduTermService.insert(eduTermBO);
        if (eduTermBO.getLeaf() == 0) {
          EduTermBO term0 = new EduTermBO(null, gradeId, "上学期", "上学期",
              eduTermBO.getScholastic(), 0, 1);
          EduTermBO term1 = new EduTermBO(null, gradeId, "下学期", "下学期",
              eduTermBO.getScholastic(), 1, 1);
          eduTermService.insert(term0);
          eduTermService.insert(term1);
        }
      }
    }
  }

}
