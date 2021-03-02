package pwd.initializr.edu.test.business;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-02 23:50
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class Test {


//  public static void main(String[] args) {
//    String json = "{\"cn\":\"[00:02.00]Module 7 第七模块\n"
//        + "[00:03.58]Unit 1 It's Children's Day today. 今天是儿童节。\n"
//        + "[00:08.45]1 Listen and chant. 1 听一听，唱一唱。\n"
//        + "[00:18.26]Children's day, Children's Day. null\n"
//        + "[00:21.38]We're very happy today. null\n"
//        + "[00:23.77]Chidren's Day, Children's Day. null\n"
//        + "[00:26.69]Let's come and play. null\n"
//        + "[00:35.81]2 Listen, point and find \"-ing\". null\n"
//        + "[00:43.19]Good morning, boys and girls. 早上好，小朋友们。\n"
//        + "[00:45.70]I'm Xiaohu from School TV. 我是来自学校电视台的小虎。\n"
//        + "[00:49.45]It's Children's Day today. 今天是儿童节。\n"
//        + "[00:51.97]Look! The children are very happy. 看！孩子们非常开心。\n"
//        + "[00:56.04]Daming is doing a play with some children. 大明正在和一些孩子们进行戏剧演出。\n"
//        + "[01:03.84]Lingling is singing a song. 玲玲正在唱一首歌。\n"
//        + "[01:11.74]Sam is saying a poem. 山姆正在朗诵一首诗。\n"
//        + "[01:21.17]Amy is dancing. 艾米正在跳舞。\n"
//        + "[01:31.44]Now, look! 现在，看！\n"
//        + "[01:32.94]Some children are doing a dragon dance. 一些孩子们正在舞龙。\n"
//        + "[01:44.29]3 Listen and say. 3 听一听，说一说。\n"
//        + "[01:48.66]Daming is doing a play with some children. 大明正在和一些孩子们进行戏剧演出。\n"
//        + "[01:53.22]Lingling is singing a song. 玲玲正在唱一首歌。\n"
//        + "\",\"online_audio\":\"/tpl/Web/audio/sentenceVolice/13版外研社版小学英语(二下)_Module07_Unit1.mp3\",\"remark\":null}";
//
//    Map map = JSON.parseObject(json, Map.class);
//    System.out.println(map);
//  }

  public static void main(String[] args) {
    String name = "六年级上";
    String pattern = ".*上.*";
    boolean isMatch = Pattern.matches(pattern, name);
    System.out.println(isMatch);
  }
}
