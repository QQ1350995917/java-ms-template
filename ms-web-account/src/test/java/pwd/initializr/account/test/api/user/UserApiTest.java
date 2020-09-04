package pwd.initializr.account.test.api.user;

import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
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
import pwd.initializr.account.api.user.vo.UserUpdateInput;

/**
 * pwd.initializr.account.test.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-04 15:09
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserApiTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreate() throws Exception {
        String file = "E:\\workspace\\github\\ms-web-initializr\\ms-web-account\\src\\test\\java\\pwd\\initializr\\account\\test\\test\\list.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap();
            int uid = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().equals("魏") || line.trim().equals("蜀") || line.trim().equals("吴")) {
                    continue;
                }
                String[] s = line.split(" ");
                String userName = s[0];
                StringBuilder summaryBuilder = new StringBuilder();
                for (int i = 4; i < s.length; i++) {
                    summaryBuilder.append(s[i] + " ");
                }

                UserUpdateInput userUpdateInput = new UserUpdateInput();
                userUpdateInput.setName(userName);
                userUpdateInput.setGender("1");
                userUpdateInput.setSummary(summaryBuilder.toString());
                MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.put("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(userUpdateInput)).header("x-uid", ++uid)
                )
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
                String response = mvcResult.getResponse().getContentAsString();

                log.info(response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/user")
                .contentType(MediaType.APPLICATION_JSON).header("x-uid", 1)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
    }
}
