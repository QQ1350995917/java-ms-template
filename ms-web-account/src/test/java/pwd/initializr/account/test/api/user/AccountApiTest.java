package pwd.initializr.account.test.api.user;

import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
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
import pwd.initializr.account.api.user.vo.SignUpByNamePwdInput;

/**
 * pwd.initializr.account.test.api.user@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-09-04 11:21
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class AccountApiTest {
    static HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();

    static {
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }


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
            LinkedHashMap<String,Integer> linkedHashMap = new LinkedHashMap();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().equals("魏") || line.trim().equals("蜀") || line.trim().equals("吴")) {
                    continue;
                }
                String[] s = line.split(" ");
                String userName = s[0];
                String accountName = getAccountName(userName);
                if (linkedHashMap.containsKey(accountName)){
                    Integer integer = linkedHashMap.get(accountName);
                    integer = integer + 1;
                    linkedHashMap.put(accountName,integer);
                    accountName = accountName + integer;
                } else {
                    linkedHashMap.put(accountName,0);
                }
                String accountPwd = "123456";
                StringBuilder summaryBuilder = new StringBuilder();
                for (int i = 4; i < s.length; i++) {
                    summaryBuilder.append(s[i] + " ");
                }

                SignUpByNamePwdInput signUpByNamePwdInput = new SignUpByNamePwdInput();
                signUpByNamePwdInput.setLoginName(accountName);
                signUpByNamePwdInput.setLoginPwd(accountPwd);
                signUpByNamePwdInput.setCaptcha("123456");

                MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/api/account/primeval")
                        .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(signUpByNamePwdInput))
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

    private static String getAccountName(String userName) {
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < userName.length(); i++) {
                String[] pinyin = PinyinHelper
                    .toHanyuPinyinStringArray(userName.charAt(i), hanyuPinyinOutputFormat);
                result.append(pinyin[0]);
            }
            return result.toString();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            return null;
        }
    }
}
