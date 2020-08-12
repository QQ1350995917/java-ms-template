package pwd.initializr.account.test.api.admin;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pwd.initializr.account.api.admin.vo.AdminAccountInput;
import pwd.initializr.account.api.admin.vo.AdminUserInput;
import pwd.initializr.account.api.admin.vo.CreateAdminInput;

/**
 * pwd.initializr.account.test.api.admin@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-08-09 20:50
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class AdminApiTest {

  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testCreate() throws Exception {
    CreateAdminInput caocaoInput = new CreateAdminInput();

    AdminUserInput caocaoAdminUserInput = new AdminUserInput();
    caocaoAdminUserInput.setName("曹操");
    caocaoAdminUserInput.setGender("1");

    AdminAccountInput caocaoAdminAccountInput = new AdminAccountInput();
    caocaoAdminAccountInput.setLoginName("caocao");
    caocaoAdminAccountInput.setLoginPwd("caocao");

    caocaoInput.setAccount(caocaoAdminAccountInput);
    caocaoInput.setUser(caocaoAdminUserInput);

    MvcResult mvcResult = mockMvc.perform(
        MockMvcRequestBuilders.post("/api/admin/admin")
            .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(caocaoInput))
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
    String response = mvcResult.getResponse().getContentAsString();

    log.info(response);

  }


}
