package pwd.initializr.edu.test.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pwd.initializr.common.http.HttpXByHttpClient;
import pwd.initializr.common.http.HttpXConfig;

/**
 * pwd.initializr.edu.test.business@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-18 10:49
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ReadingTableSpider {

    @org.junit.Test
    public void table0 () {
        StringBuilder lines = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
            new FileReader(new File("E:\\workspace\\github\\ms-web-initializr\\ms-web-edu\\src\\test\\java\\pwd\\initializr\\edu\\test\\temp.html")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String html = lines.toString();

        Document document = Jsoup.parse(html);
        Element body = document.body();
        Element rootElement = body.getElementsByClass("tree tree-bbox").get(0);
        Element rootUL = rootElement.getElementsByTag("ul").get(0);
        Elements itemLevel = rootUL.children();
        for (Element item : itemLevel) {
            String level = item.getElementsByTag("div").get(0).getElementsByTag("a").get(0).text();
            String parentId;
            if ("小学".equals(level)) {
                parentId = "4028b4816460a6da016460b2548e0004";
            } else if ("初中".equals(level)) {
                parentId = "4028b4816460a6da016460b186140003";
            } else if ("高中".equals(level)) {
                parentId = "402880e66507589b0165076d65780005";
            }
            System.out.println(level);
            Element ul = item.getElementsByTag("ul").get(0);
            Elements li = ul.getElementsByTag("li");
            for (Element element : li) {
                String id = element.getElementsByTag("div").get(0).attr("data-id");
                String title = element.getElementsByTag("div").get(0).getElementsByTag("a").get(0)
                    .text();
                System.out.println("\t" + title + ":" + id);

            }
        }
    }

    @org.junit.Test
    public void table1 () {
        HttpXByHttpClient httpXByHttpClient = new HttpXByHttpClient(new HttpXConfig());
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "en-US,en;q=0.5");
        header.put("Connection", "keep-alive");
        header.put("Cookie", SpriderConfig.cookie);
        header.put("Host", "47.92.155.170");
        header.put("Referer",
            "http://47.92.155.170/Web/AiReading/index.html");
        header.put("User-Agent", SpriderConfig.UserAgent);
        header.put("X-Requested-With", "XMLHttpRequest");

        String content = httpXByHttpClient
            .get("http://47.92.155.170/Web/AiReading/ajaxGetBooks.html?aj_levelId=31", header, null);


        System.out.println(content);
    }
}

//
//<!DOCTYPE html>
//<html lang="en">
//
//<head>
//<meta charset="UTF-8">
//<title>智能阅读-爱闻思智能英语学习系统</title>
//
//<!--meta-->
//<meta name="Keywords" content="智能阅读-爱闻思智能英语学习系统"/>
//<meta name="Description" content="智能阅读-爱闻思智能英语学习系统"/>
//<meta http-equiv="Pragma" content="no-cache"/>
//<meta name="format-detection" content="telephone=no"/>
//<meta name="viewport"
//    content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
//<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
//<meta http-equiv="X-UA-Compatible" content="IE=edge">
//<!--<meta http-equiv="Expires" content="0">
//<meta http-equiv="Pragma" content="no-cache">
//<meta http-equiv="Cache-control" content="no-cache">
//<meta http-equiv="Cache" content="no-cache">-->
//<meta name="renderer" content="webkit">
//
//
//<link rel="stylesheet" type="text/css" href="/tpl/Web/zwplus/BOOTSTRAP/css/bootstrap.min.css"/>
//<link rel="stylesheet" type="text/css"
//    href="/tpl/Web/zwplus/font-awesome/css/font-awesome.min.css"/>
//<link rel="stylesheet" type="text/css" href="/tpl/Web/css/animate.min.css"/>
//<link rel="stylesheet" type="text/css"
//    href="/tpl/Web/css/zw_animation.css?v=2019年12月14日15:46:68"/>
//<link rel="stylesheet" href="/tpl/Web/zwplus/jqueryloading22_files/fakeLoader.css">
//<!--步骤教学-->
//<link rel="stylesheet" href="/tpl/Web/zwplus/pagewalkthrough/css/jquery.pagewalkthrough.css">
//<link rel="stylesheet" type="text/css" href="/tpl/Web/zwplus/hover/css/hover-min.css"/>
//<link rel="stylesheet" type="text/css" href="/tpl/Web/css/zwcom.css?v=2019年12月14日15:46:68"/>
//<link rel="stylesheet" type="text/css" href="/tpl/Web/css/zw.css?v=2019年12月14日15:46:68"/>
//<link rel="stylesheet" type="text/css" href="/tpl/Web/css/v1style.css?v=2019年12月14日15:46:68"/>
//<!--<script src="/tpl/Web/js/menuTop.js" type="text/javascript" charset="utf-8"></script>-->
//<!--机器人相关-->
//<!--<link rel="stylesheet" type="text/css" href="/tpl/Web/css/robot.css?v=2019年12月14日15:46:68" />-->
//
//<!--消息通知-->
//<link rel="stylesheet" href="/tpl/Web/zwplus/xiaoxitongzhi/css/notification.css">
//
//<!--其他的一些插件-->
//<link rel="stylesheet" type="text/css" href="/tpl/Web/css/other.css?v=2019年12月14日15:46:68"/>
//
//<!--[if IE]> 所有的IE可识别<![endif]-->
//<script src="/tpl/Web/js/jquery-1.11.2.min.js" type="text/javascript" charset="utf-8"></script>
//<script src="/tpl/Web/zwplus/layer/layer/layer.js" type="text/javascript"
//    charset="utf-8"></script>
//<script src="/tpl/Web/zwplus/BOOTSTRAP/js/bootstrap.min.js" type="text/javascript"
//    charset="utf-8"></script>
//
//<!--返回顶部-->
//<link href="css/style.css" rel="stylesheet" type="text/css"/>
//<link href="/tpl/Web/zwplus/fanhuidingbu/css/style.css" rel="stylesheet" type="text/css"/>
//
//
//<script src="/tpl/Web/zwplus/fanhuidingbu/js/script.js" type="text/javascript"></script>
//<!--消息通知-->
//
//<script src="/tpl/Web/zwplus/xiaoxitongzhi/js/notification.js"></script>
//
//<script src="/tpl/Web/zwplus/sweetalert2/sweetalert.min.js"></script>
//<link rel="stylesheet" type="text/css" href="/tpl/Web/zwplus/sweetalert2/sweetalert.css">
//<script src="/tpl/Web/zwplus/wow/js/wow.min.js" type="text/javascript" charset="utf-8"></script>
//<script src="/tpl/Web/js/Gold.js?v=2019年12月14日15:46:68" type="text/javascript"
//    charset="utf-8"></script>
//<script src="/tpl/Web/js/utils.js?v=2019年12月14日15:46:68" type="text/javascript"
//    charset="utf-8"></script>
//<script type="text/javascript">
//    /*浏览器判断开始 start*/
//    //			var g_layerLoad = layer.load(2, {
//    //				time: 10 * 1000
//    //			}); //0代表加载的风格，支持0-2
//    var u = navigator.userAgent,
//    app = navigator.appVersion;
//    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //g
//    var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
//
//    function is_weixin() {
//    var ua = window.navigator.userAgent.toLowerCase();
//    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
//    return true;
//    } else {
//    return false;
//    }
//    }
//
//    //			if(isIOS || isAndroid) {
//    //				window.location = "/mobile/index/index.html"
//    //
//    //			}
//
//    var myDate = new Date();
//    var riqi_yue = myDate.getMonth() + 1;
//    var riqi_ri = myDate.getDate();
//    //var aa = $('#show_riqi').html();
//
//    setTimeout(function () {
//    $("#show_riqi").text(riqi_yue + '月' + riqi_ri + '日');
//    }, 600);
//</script>
//
//
//<script type="text/javascript">
//    window.site_config = {
//    site: "/Web/index/index.html",
//    root_url: "/tpl/Web/",
//    welcome_url: "/Web/index/welcome.html",
//    fangsong_url: "/Web/AiWord/fangsong.html",
//    AiWord_url: "/Web/AiWord/index.html",
//    AiWord_jyzz_url: "/Web/AiWord/jyzz.html",
//    AiWord_znmx_url: "/Web/AiWord/znmx.html",
//    AiWord_zntx_url: "/Web/AiWord/zntx.html",
//    AiWord_find_url: "/Web/AiWord/findNeedReviewWord.html",
//    AiWord_znjyznfy_review_url: "/Web/AiWord/cyjy/study_type/review.html", //记忆追踪智能复习
//    AiWord_znjyznfy_reStudy_url: "/Web/AiWord/cyjy/study_type/reStudy.html", //重学
//    AiWord_znjy_review_url: "/Web/AiWord/cyjy/study_type/review/mode/1.html", //记忆追踪智能复习
//    AiWord_zntx_review_url: "/Web/AiWord/zntx/study_type/review.html", //记忆追踪智能复习
//    AiWord_zntx_reStudy_url: "/Web/AiWord/zntx/study_type/reStudy.html", //重学
//    AiWord_znmx_review_url: "/Web/AiWord/znmx/study_type/review.html", //记忆追踪智能复习
//    AiWord_znmx_reStudy_url: "/Web/AiWord/znmx/study_type/reStudy.html", //重学
//    AiWord_getReviewAllByBook_url: "/Web/AiWord/getReviewAllByBook.html",
//    AiReading_index_url: "/Web/AiReading/index.html",
//    aciton_name: "index",
//    robot_pic: "http://houtai.i-wins.com/upload/files/20180709173310rxi0Beta.png",
//    java_web: "http://houtai.i-wins.com/",
//    lang_likefuxi: "黄金记忆点已到，为了牢记请立刻复习",
//    lang_julihjjyd: "距离下次黄金记忆点还有",
//    lang_nonotice: "关闭后,本次登陆时间内不再提醒新消息",
//    lang_shareGiveGold: "30",
//    lang_momodi: "需要测试先来摸摸底吧", //摸底
//    lang_xuehouceshi: "恭喜你已经完成 全部单元学习 现在要进行一个学后测试 ！",
//    lang_xuyaowanchengcaineng: "需要完成学后测验 才可以继续学习本单元",
//    lang_xuehouchongxue: '学后测试通过,重新学习这个单元',
//    uid: "000000006cedb98f016cf100606f004e",
//    session_user_account: "WS244023",
//    session_user_special: "",
//    define_time: 'znyy_session_onlineTimev2' + "000000006cedb98f016cf100606f004e",
//    define_validTime: 'znyy_session_validTimev2' + "000000006cedb98f016cf100606f004e",
//    ajax_updateOnlineTime_url: "/Web/My/ajaxUpdateOnlineTime.html",
//    AiWord_exam_url: "/Web/AiWord/exam.html",
//    audio_kaoshikaishi: "/tpl/Web/audio/xianzaikaoshikaishi.mp3",
//    audio_kaoshijieshu: "/tpl/Web/audio/kaoshijieshu.mp3",
//    audio_cuowutishi: "/tpl/Web/audio/cuowu.mp3",
//    audio_daduitishi: "/tpl/Web/audio/dadui.wav",
//    audio_xinxiaoxitishi: "http://document.i-wins.com/audio/xinxiaoxitishi.wav",
//    lang_score_60: "本课程有点难，建议你换个低一级别的课程！",
//    lang_score_60_80: "本课程比较适合",
//    lang_score_80: "本课程没有压力",
//    config_maxIdelTime: "200",
//    mode: "",
//    unitid: '',
//    zhangwei: "",
//    username: "",
//    ajax_feedback_url: "/Web/index/ajaxFeedBack.html",
//    img_huodejinbi5: "/tpl/Web/images/main/modal_huodejinbi.png",
//    img_shiqujinbi5: "/tpl/Web/images/main/modal_shiqujinbi.png",
//    img_jinbi: "/tpl/Web/images/jb.png",
//    img_robot_xiaolian: "/tpl/Web/images/main/robotxiaolian.png",
//    img_robot_qiguai: "/tpl/Web/images/main/robotqiguai.png",
//    img_robot_kulian: "/tpl/Web/images/main/robotkulian.png",
//    img_robot_yiwen: "/tpl/Web/images/main/robotyiwen.png",
//    img_xiaoxi: "/tpl/Web/images/main/xiaoxiimm.png?v=2",
//    site_version: "2",
//    ajax_ajaxGetGold_url: "/Web/index/ajaxGetGold.html",
//    img_laba: "/tpl/Web/images/lanba.png",
//    img_laba: "/tpl/Web/images/zndc_cstx_30.png",
//    index_goldShop_url: "/Web/index/goldshop.html",
//    ajax_addGold_url: "/Web/index/ajaxAddGold.html",
//    is_local: "0",
//    is_demo: "",
//    admin: "",
//    jiaocaiid: "",
//    study_type: "",
//    ajax_updateGold_url: "/Web/user/updateGold.html",
//    ajax_ajaxGetPeopleMessage_url: "/Web/my/ajaxGetPeopleMessage.html", //获得消息
//    ajax_isDayFirstSign: "/Web/index/ajaxIsFirstSign.html", //当天第一次登陆
//    ajax_ajaxBuy_url: "/Web/index/ajaxBuy.html",
//    correct_url: "/Web/my/correct.html",
//    ajaxGetCurrentGold_url: "/Web/user/ajaxGetCurrentGold.html",
//    ajax_ajaxUpdateMessageRead_url: "/Web/my/ajaxUpdateMessageRead.html",
//    iip: "",
//    audio_root: '/tpl/Web/audio',
//    video_root: '/tpl/Web/video',
//    module: '',
//    g_wrong_audio_will_compound: '',
//    aiSpeechType: '',
//    back: "/Web/index/index.html",
//    page_from: "",
//    ajaxDeleteMessage: "/Web/My/ajaxDeleteMessage.html",
//    baidu_compound_token: "24.e970c9d3adb24a28ae771a8b079e664c.2592000.1614518877.282335-14754235",
//    url_feedback: "/Web/index/feedback.html",
//    ls_no_notice: "ls_no_notice000000006cedb98f016cf100606f004e",
//    keyichongxue_account: "iwens1300#zhangwei#zw805000#iwens1300#zw855000#zw805000#test785000#test839000#test164000",
//    will_kantu_class: '',
//    aispeech_ajaxsubmitexam_url: "/Web/AiSpeech/ajaxSubmitExam.html",
//    ajax_voice_recognition_url: "/Web/My/ajax_voice_recognition.html"
//    };
//
//    var lang_AiSpeech = 'AiSpeech';
//    </script>
//
//
//    </head>
//    <!--背景特效 start-->
//    <canvas id="c" style="position: fixed;z-index: -1;text-align: center; "></canvas>
//    <!--背景特效 end-->
//    <audio src="" id="bofangqi"></audio>
//    <!--背景特效 end-->
//    <!-- MP3 swf Div -->
//    <div id="jquery_jplayer" style="width: 0px; height: 0px;"><img id="jp_poster_0"
//    style="width: 0px; height: 0px; display: none;">
//    <audio id="jp_audio_0" preload="auto" src=""></audio>
//    </div>
//    <!-- jplayer div -->
//
//    <!--张伟自定义弹窗 start-->
//    <div id="zw-modal-bbox">
//
//    <div id="zw-modal1">
//    <img class="zw-modal-img666"/>
//    <div class="zw-modal-title">
//    信息
//    </div>
//    <div class="zw-modal-content">
//
//    </div>
//
//    <div class="zw-modal-button-caozuos">
//    <button class="js-queren-zwmodal zw-modal-button-zuo hvr-grow">确定</button>
//    <button class=" js-close-zwmodal zw-modal-button-you hvr-grow">取消</button>
//    </div>
//
//    </div>
//    <div id="zw-modal1-bg">
//
//    </div>
//    </div>
//    <!--张伟自定义弹窗 end-->
//
//    <body topmargin="0" ondragstart="return false" onselectstart="return false"
//    onselect="document.selection.empty()" oncopy="document.selection.empty()"
//    onbeforecopy="return false" id="znyy-body" class="pagemode
//    ">
//    <div style="display:none;" id="rocket-to-top">
//
//    <div style="opacity:0;display:block;" class="level-2"></div>
//
//    <div class="level-3"></div>
//
//    </div>
//    <!--登记学生信息 start-->
//    <div id="g-student-info-bbox">
//    <div class="student-info5">
//    <span class="student-label5"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="student-span2"
//    id="show_riqi"></span>
//    </div>
//
//    <div class="student-info5">
//    <span class="student-label5">在读:</span><span class="student-span2"
//    style="text-decoration: underline;"><a
//    style="color: white;" href="/Web/user/mycenter.html">请填写年级</a></span>
//
//    </div>
//    <div class="student-info5">
//    <span class="student-label5">在线:</span><span class="student-span2 vue-jinrionline"></span>
//    </div>
//    </div>
//    <!--登记学生信息 end-->
//    <canvas id="canvas"></canvas>
//    <div class="canvasBgImage"></div>
//    <a id="logo-center-a" href="/Web/index/welcome.html">
//
//    <div id="logo-center-bbox" class="center "></div>
//    </a>
//    <!-- 顶部背景-->
//    <div class="header main-header-v1">
//    <div class="left"></div>
//
//    <div class="right"></div>
//    </div>
//    <!-- 顶部背景 end-->
//    <div class="fakeloader">
//
//    </div>
//    <img class="jinbi-img556651252   jinbidonghua" src="/tpl/Web/images/main/huodejinbi.png"/>
//
//    <!-- <script type="text/javascript" src="/tpl/Web/zwplus/jqueryCanvas/canvas_bg.js"></script> -->
//
//    <div class="container big-container 	">
//    <link rel="stylesheet" type="text/css" href="/tpl/Web/zwplus/youxiang/css/styles.css">
//
//    <!--获得金币 start-->
//    <div id="layer-kantu-bbox" style="display: none;">
//    <img id="layer-kantu-img11233" src="" style="width: 100%;"/>
//    </div>
//    <!-- 趣味练习  start -->
//    <div id="modal-tblx-bbox" class="hidden">
//    <div class="modal-tblx-box">
//    <div class="daohangunit zw-shenlan-btn ml20    hvr-float margin-bottom-20">
//    <a href="/Web/AiClassroom/tblx/id/xdc.html" class="js-tblx-a  " data-id="0">
//    学单词
//    </a>
//    </div>
//    <div class="daohangunit zw-shenlan-btn ml20   hvr-float margin-bottom-20">
//    <a href="/Web/AiClassroom/tblx/id/fsyx.html" class="js-tblx-a  " data-id="1">
//    开心一刻
//    </a>
//    </div>
//    <div class="daohangunit zw-shenlan-btn   ml20  hvr-float margin-bottom-20">
//    <a href="/Web/AiClassroom/tblx/id/xgs.html" class="js-tblx-a  " data-id="2">
//    故事会
//    </a>
//    </div>
//
//    <hr/>
//    <div class="robot-jieshuo-bbox">
//    <img src="/tpl/Web/images/main/jiesuojiqiren.png"
//    class="robot-jieshuo-img modal-tblx-robot-img5"/>
//
//    <div class="robot-jieshuo-div">
//    同学，在上侧选择适合你的课程 进行学习吧！
//    </div>
//    <div class="robot-jieshuo-remark">
//
//    如果学习得法,便可事半功倍,要达到这样的目的,趣味性学习可以说是一种行之有效的方法。如何激发学生的兴趣并保持它,使之成为他们学习的动力
//    </div>
//
//    </div>
//
//    </div>
//
//    </div>
//    <!-- 趣味练习  end -->
//
//    <div id="layer-modal-getGold">
//
//    <div id="modal-getGold-bbox">
//    <div class="getgold-right-close-bbox js-getgold-close">
//    <span class="getgold-guanbi-x cursor-point">X</span>
//    </div>
//    <img src="/tpl/Web/images/main/getgoldgongxinin.png"
//    class="getgold-gongxi-img yihangongxi-title"/>
//    <img src="/tpl/Web/images/main/getgoldyihan.png" class="getgold-yihan-img yihangongxi-title"/>
//    <!--<img class="getGold-bbox-bg-img" src="/tpl/Web/images/main/modal_huodejinbi.png" />-->
//    <!--获得金币中部内容 start-->
//    <div class="getgold-content-bbox zw-clear">
//    <div class="getgold-content-left">
//    <img class="getgold-gongxi-img gongxiyihan-left-img5    "
//    src="/tpl/Web/images/main/modal_getgold_jinbibox.png"/>
//    <img class="getgold-yihan-img gongxiyihan-left-img5    "
//    src="/tpl/Web/images/main/modal_getgold_jinbihezi.png"/>
//    </div>
//
//    <div class="getgold-content-right color-qianhui">
//    <div data-wow-delay="0.5s" class="getgold-content-right-info row">
//    <span class="gcr-info-span col-xs-3">金币</span>
//    <span class="gcr-info-span col-xs-3 vue-gold-num">18</span>
//    <div class="gcr-info-span gcr-bianhua-bbox col-xs-3">
//
//    <div class="bianhua-wenzi-box">
//    <span class="getgold-bianhua-wenzi vue-getgold-gold">+15</span><img
//    class="grc-getgold-bianhua-img" src="/tpl/Web/images/main/jinbiicon.png"/>
//    </div>
//
//    <img data-wow-delay="0.5s" src="/tpl/Web/images/main/getgoldjiantou.png"
//    class="gcr-bianhua-jiantou-img"/>
//
//    </div>
//    <span data-wow-delay="0.9s"
//    class="gcr-info-span col-xs-3 vue-bianhua-after-gold">188</span>
//    </div>
//    <div style="display: none;" data-wow-delay="0.5s"
//    class="getgold-content-right-info row hidden">
//    <span class="gcr-info-span col-xs-3">成长值</span>
//    <span class="gcr-info-span col-xs-3 vue-grow-num">0</span>
//    <div class="gcr-info-span gcr-bianhua-bbox col-xs-3">
//
//    <div class="bianhua-wenzi-box">
//    <span class="getgold-bianhua-wenzi vue-getgold-grow">+15</span><img
//    class="grc-getgold-bianhua-img"
//    src="/tpl/Web/images/main/getgoldchengzhangzhi.png"/>
//    </div>
//
//    <img data-wow-delay="0.5s" src="/tpl/Web/images/main/getgoldjiantou.png"
//    class="gcr-bianhua-jiantou-img"/>
//
//    </div>
//    <span data-wow-delay="0.9s"
//    class="gcr-info-span vue-bianhua-after col-xs-3 vue-bianhua-after-grow">166</span>
//    </div>
//
//    <div data-wow-delay="0.8s" class="getgold-content-right-info vue-getgold-title55">
//    获得4枚金币，分享即可获 得更多。
//    </div>
//
//    </div>
//
//    </div>
//    <!--获得金币中部内容 end-->
//    <!--获得金币操作组 start-->
//    <div class="getgold-caozuos-bbox">
//    <div class="js-getgold-caozuo-left hvr-float-shadow cursor-point">
//    <span class="fa fa-check"></span>
//
//    <span class="vue-querenanniu">分享</span>
//
//    </div>
//
//    <div class="js-getgold-caozuo-right hvr-float-shadow  cursor-point">
//    <span class="fa fa-circle-o"></span>
//    <span class="vue-quxiaoanniu">主页</span>
//
//    </div>
//
//    </div>
//
//    </div>
//
//    </div>
//
//    <!--获得金币 end-->
//
//    <!--效能管家 start-->
//    <div class="xiaonengguanjia-bbox" id="xiaonengguanjia-bbox">
//
//    <div class="xiaonengyuanquan-bbox">
//
//    <div class="xiaonengyuanquan-text">
//    效能管家<br/>02.18
//    </div>
//    </div>
//    <!--弹出-->
//    <div class="xngj-info-bbox">
//    <div class="xngj-info-innerbbox">
//    <div class="xngj-info">
//
//    <div class="xngj-info-top">
//    今日学习效率
//    </div>
//
//    <div class="xngj-info-bottom">
//    <span class="xngj-info-big-text vue-xuexixiaolv"></span>
//    </div>
//    <span class="xngj-info-xiobian"></span>
//
//    </div>
//    <div class="xngj-info">
//
//    <div class="xngj-info-top">
//    有效学习时长
//    </div>
//
//    <div class="xngj-info-bottom">
//
//    <span class="xngj-info-big-text vue-jinriyouxiao"></span>
//    </div>
//
//    <span class="xngj-info-xiobian"></span>
//    </div>
//    <div class="xngj-info">
//
//    <div class="xngj-info-top">
//    今日在线时长
//    </div>
//
//    <div class="xngj-info-bottom">
//    <span class="xngj-info-big-text vue-jinrionline"></span>
//    </div>
//
//    </div>
//    </div>
//
//    </div>
//
//    </div>
//    <!--效能管家 end-->
//
//    <!--消息盒子 start-->
//
//    <div class="g-xiaoxi-bbox">
//
//    <div class='container_ui'>
//    <div class='container_ui__heading'>
//    <div class='header_icon' style="width: 50px;">
//    <span class="glyphicon glyphicon-envelope xiaoxi-icon661521"></span>
//    </div>
//    <h1>
//    我的消息 （<span class="vue-xiaoxi-num">0</span>）
//    </h1>
//    <div class='menu_icon'>
//    <div class='div'></div>
//    <div class='div'></div>
//    <div class='div'></div>
//    </div>
//    <span class="fa fa-times js-guanbixiaoxi"></span>
//    </div>
//    <div id="message-bbox55">
//
//    </div>
//    </div>
//    </div>
//    <!--消息盒子 end-->
//    <!--通用modal弹窗-->
//
//    <div class="modal fade" id="commonModal" tabindex="-1" role="dialog"
//    aria-labelledby="myModalLabel">
//    <div class="modal-dialog modal-lg" role="document">
//    <div class="modal-content">
//    <div class="modal-header">
//    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
//    aria-hidden="true">&times;</span></button>
//    <h4 class="modal-title modal-title615" id="myModalLabel">全自动导航学习──根据你的情况，你需要进行以下任务：</h4>
//    </div>
//    <div class="modal-body " id="commonModal-body">
//
//    </div>
//    <!--<div class="modal-footer xuanzudan-model-footer">
//    <div class="xzdymodel-footer-neirong">
//    您有<span class="bencefuxicount"></span>个单词需要复习,
//    <a class="js-jyzz-a">去复习</a>
//    </div>
//
//    </div>-->
//    </div>
//    </div>
//    </div>
//    <!--获胜弹窗 start-->
//    <div id="huoshengtanchuang-bbox">
//    <div class="zw-zhezhao">
//
//    </div>
//    <img src="/tpl/Web/images/main/huosheng.png" class="huosheng-img"/>
//    <img src="/tpl/Web/images/main/huoshengguanghuan.png" class="huoshengguanghuan-img   "/>
//    <div id="huoshenginfo-bbox">
//    <div class="color-lanse tisheng51521 text-center">提升</div>
//    <div class="tishengshuju15621 color-fense-shadow ">
//    <span class="tishenglabel-span color-qianlan">金币:</span> <span
//    class="color-white"></span><img
//    src="/tpl/Web/images/main/youjiantou.png" class="tishengyoujiantou-img"/><span
//    class="color-green vue-huodexinjibi">65</span>
//    </div>
//    <div class="tishengshuju15621 color-fense-shadow ">
//    <span class="tishenglabel-span color-qianlan">经验:</span> <span
//    class="color-white"></span><img
//    src="/tpl/Web/images/main/youjiantou.png" class="tishengyoujiantou-img"/><span
//    class="color-green vue-huodexinexp">65</span>
//    </div>
//    </div>
//    <div class="huoshengcaozuos-bbox">
//
//    <div onclick="history.go(-1)" id="huoshengjixu-btn" class="zw-shenlan-btn">
//    <a href="/Web/index/index.html">继续</a>
//    </div>
//
//    </div>
//
//    </div>
//    <!--获胜弹窗 end-->
//    <!-- 活动列表-->
//    <div id="modal-huodong" class="hide">
//
//    <div class="   huoDongDiv  ">
//    <div class="title">
//    活动列表
//    </div>
//    <div class="content">
//    <div class="clear"></div>
//    <div class="left">
//
//    <a href="/Web/index/dazhuanpan.html">
//    <img src="/tpl/Web/images/huodong1.jpg"/>
//    </a>
//
//    </div>
//    <div class="right">
//
//    <a href="/Web/index/game_gold_egg.html">
//    <img src="/tpl/Web/images/huodong2.jpg"/>
//    </a>
//
//    </div>
//    </div>
//    </div>
//    </div>
//    <!--消息查看弹窗-->
//    <div style="display: none;" id="layer-feddback-detail-bbox">
//
//    <table border="" class="zw-table2  color-qianlan leave-detail-table text-left" cellspacing=""
//    cellpadding="">
//    <tr>
//    <th>发件人：<span class="color-lanse vue-fajianren"></span></th>
//    </tr>
//    <tr>
//    <th>收件人：<span class="color-lanse vue-shoujianren"></span></th>
//    </tr>
//    <tr>
//    <th>标题： <span class="color-lanse vue-zhuti"></span></th>
//    </tr>
//    <tr>
//    <th>发送时间： <span class="color-lanse vue-riqi">2019年4月28日11:01:12</span></th>
//    </tr>
//    <tr>
//    <th class="leave-detail-bbox  ">
//
//    <textarea readonly="readonly" class="vue-leave-detail" value="" name="" rows=""
//    cols=""></textarea>
//
//    </th>
//    </tr>
//
//    </table>
//    <div class="feedback-caozuos-bbox">
//
//    <button onclick="reply_message(this)"
//    class="btn btn-info  btn-md feedback-huifu-btn pull-right">回复
//    </button>
//    <button onclick="message_delete(this)"
//    class="btn btn-danger mr10  btn-md feedback-huifu-btn pull-right">删除本消息
//    </button>
//
//    </div>
//
//    </div>
//
//    <!--新消息回复窗口-->
//    <div id="layer-newmsgreply-bbox" style="display: none;">
//    <form class="form-horizontal newmsgreplay-form v1-form">
//    <input type="hidden" name="type" id="messageType" value="0">
//    <input type="hidden" name="type" id="teacherId" value="">
//
//    <div class="form-group v1-form-group">
//    <label for="inputEmail3" class="col-sm-2 control-label">消息标题：</label>
//    <div class="col-sm-10">
//    <input type="text" class="form-control" id="title" value="" placeholder="请输入消息标题"/>
//
//    </div>
//    </div>
//    <div class="form-group v1-form-group">
//    <label for="inputPassword3" class="col-sm-2 control-label">内容：</label>
//    <div class="col-sm-10">
//    <textarea class="form-control" placeholder="请在此输入正文内容！" id="message"></textarea>
//
//    </div>
//    </div>
//
//    <div class="form-group">
//    <div class="col-sm-offset-2 col-sm-10 text-right margin-top-20">
//    <button id="js-tijiaoreply" type="button" class="btn btn-default">立即发送</button>
//    </div>
//    </div>
//    </form>
//    </div>
//    <!--获得金币弹窗-->
//    <div class="layer-huodejinbi-bbox">
//    <div style="background-image: url(/tpl/Web/images/main/huodejinbi.png);"
//    class="huodejinbi-bbox">
//    <div class="huodejinbi-text">
//    <span class="huodejinbi-title">此次</span>
//    <span class="huodejinbi-gold">0</span>
//    </div>
//
//    </div>
//
//    </div>
//
//
//    <script type="text/javascript">
//    $(function () {
//
//    })
//    </script>
//    <div class="zwwww-nav5  ">
//
//
//    </div>
//    <!-- 顶部菜单-->
//    <div class="menu_top">
//
//    <ul class="left">
//
//    <li class=''>
//    <a href="/Web/index/index.html"><img
//    src="/tpl/Web/images/index_icon_t_01_a.png"/><br/><span>首页</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/user/mycenter.html"><img
//    src="/tpl/Web/images/index_icon_t_02.png"/><br/><span>个人中心</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/index/reason.html"><img src="/tpl/Web/images/index_icon_t_03.png"/><br/><span>人工智能</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/index/qiandao.html"><img
//    src="/tpl/Web/images/index_icon_t_04.png"/><br/><span>签到</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/index/rank.html"><img
//    src="/tpl/Web/images/index_icon_t_05.png"/><br/><span>榜单</span></a>
//    </li>
//    </ul>
//    <ul class="right">
//    <li>
//    <a href="/Web/User/logout.html"><img src="/tpl/Web/images/index_icon_t_10.png"/><br/><span>退出</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/index/goldshop.html"><img
//    src="/tpl/Web/images/index_icon_t_09.png"/><br/><span>金币商城</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/index/share.html"><img src="/tpl/Web/images/index_icon_t_08.png"/><br/><span>分享有礼</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/index/feedback.html"><img
//    src="/tpl/Web/images/index_icon_t_07.png"/><br/><span>师生互动</span></a>
//    </li>
//    <li class=''>
//    <a href="/Web/AiClassroom/tblx.html"><img
//    src="/tpl/Web/images/index_icon_t_11.png"/><br/><span>趣味练习</span></a>
//    </li>
//    </ul>
//
//    </div><!--顶部菜单 end-->
//
//    <!--main start-->
//    <!--树形-->
//    <link rel="stylesheet" href="/tpl/Web/zwplus/jqueryTreescroll/css/tree.css"/>
//    <link rel="stylesheet" type="text/css" href="/tpl/Web/zwplus/wow/css/animate.min.css"/>
//    <link rel="stylesheet" type="text/css" href="/tpl/Web/css/reading.css?v=2019年12月14日15:46:68"/>
//    <link rel="stylesheet" type="text/css" href="/tpl/Web/css/v1.css?v=2019年12月14日15:46:68"/>
//
//    <script type="text/javascript">
//    site_config.ajax_getBooks_url = "/Web/AiReading/ajaxGetBooks.html"
//    site_config.readingexam_url = "/Web/AiReading/readingexam.html"
//    site_config.keben_img = "/tpl/Web/images/zncg_02.png"
//    site_config.shangsuo_keben_img = "/tpl/Web/images/zncg_01.png"
//    site_config.star_img = "/tpl/Web/images/reading/star1.png"
//    site_config.suotou_img = "/tpl/Web/images/reading/suotou.png"
//    site_config.moren = 3; //默认展示第几个
//    site_config.readingbook_url = "/Web/AiReading/readingbook.html"
//    </script>
//    <!-- Modal -->
//    <div class="modal fade" id="xiugaiModal" tabindex="-1" role="dialog"
//    aria-labelledby="myModalLabel">
//    <div class="modal-dialog" role="document">
//    <div class="modal-content">
//    <div class="modal-header">
//    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
//    aria-hidden="true">&times;</span></button>
//    <h4 class="modal-title" id="myModalLabel">设置基础阅读速度</h4>
//    </div>
//
//    <div class="modal-body">
//    <div class="xugaisudu-content">
//    提示：正常(50词/分钟) 高速(100词/分钟) 飞速(200词/分钟) 句读训练是以整句突出显示的方式，引导读者培养整句阅读习惯。通过这种方式还能有效避免不好的阅读习惯。
//    文中的句子按照一定的速度自动逐句闪现，闪现速度是根据您的基础阅读速度计算得出。
//    闪现过程中你要尽量读懂文章的含义，句子闪现完成后，系统要求进行效果检测，没有通过将要进行生词筛选和学习，然后再进行句读训练。
//
//    </div>
//
//    <div class="xiugaisudu-caozuos">
//
//    速度： <label class="my_radio">
//    <input class="my_radio_old" type="radio" name="a" onchange="setReadingSpeed(50)"
//    checked="checked">
//    <span class="my_radio_new"></span>正常
//    </label>
//    <label class="my_radio">
//    <input class="my_radio_old" type="radio" onchange="setReadingSpeed(100)" name="a">
//    <span class="my_radio_new"></span>较快
//    </label>
//    <label class="my_radio">
//    <input class="my_radio_old" type="radio" onchange="setReadingSpeed(200)" name="a">
//    <span class="my_radio_new"></span>最快
//    </label>
//
//    </div>
//
//    </div>
//
//    </div>
//    </div>
//    </div>
//
//    <!--移动端选择目录 start-->
//    <div class="modal fade" id="m-xuanzedanyuan-Modal" tabindex="-1" role="dialog"
//    aria-labelledby="myModalLabel">
//    <div class="modal-dialog" role="document">
//    <div class="modal-content">
//    <div class="modal-header">
//    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
//    aria-hidden="true">&times;</span></button>
//    <h4 class="modal-title" id="m-xuanzedanyuanModalLabel">请选择适合的语法进行学习吧！</h4>
//    </div>
//
//    <div class="modal-body">
//    <div class="g-kewenleft-bbox col-md-3 auto-height">
//    <div class="treebox  my_scroll my_scroll_no_radius   auto-height">
//    <div class="tree tree-bbox">
//    <ul>
//    <!--一级菜单栏-->
//    <li>
//    <div class="close_menu"><span></span>
//    <a title="一级菜单">小学</a>
//    </div>
//    <ul>
//
//    <li>
//    <div data-id="31" class="close_menu js-level">
//    <a title="小学提高版(易)">小学提高版(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="32" class="close_menu js-level">
//    <a title="小学提高版(中)">小学提高版(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="33" class="close_menu js-level">
//    <a title="小学提高版(难)">小学提高版(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="1" class="close_menu js-level">
//    <a title="三年级(易)">三年级(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="2" class="close_menu js-level">
//    <a title="三年级(中)">三年级(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="3" class="close_menu js-level">
//    <a title="三年级(难)">三年级(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="4" class="close_menu js-level">
//    <a title="四年级(易)">四年级(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="5" class="close_menu js-level">
//    <a title="四年级(中)">四年级(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="6" class="close_menu js-level">
//    <a title="四年级(难)">四年级(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="7" class="close_menu js-level">
//    <a title="五年级(易)">五年级(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="52" class="close_menu js-level">
//    <a title="五年级(中)">五年级(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="8" class="close_menu js-level">
//    <a title="五年级(难)">五年级(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="9" class="close_menu js-level">
//    <a title="六年级易">六年级易</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="10" class="close_menu js-level">
//    <a title="六年级中">六年级中</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="11" class="close_menu js-level">
//    <a title="六年级难">六年级难</a>
//    </div>
//
//    </li>
//    </ul>
//    </li>
//    <!--一级菜单栏end-->
//    <!--一级菜单栏-->
//    <li>
//    <div class="close_menu"><span></span>
//    <a title="初中">初中</a>
//    </div>
//    <ul>
//
//    <li>
//    <div data-id="12" class="close_menu js-level">
//    <a title="初一(易)">初一(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="13" class="close_menu js-level">
//    <a title="初一(中)">初一(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="14" class="close_menu js-level">
//    <a title="初一(难)">初一(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="15" class="close_menu js-level">
//    <a title="初二(易)">初二(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="16" class="close_menu js-level">
//    <a title="初二(中)">初二(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="17" class="close_menu js-level">
//    <a title="初二(难)">初二(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="18" class="close_menu js-level">
//    <a title="初三(易)">初三(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="19" class="close_menu js-level">
//    <a title="初三(中)">初三(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="20" class="close_menu js-level">
//    <a title="初三(难)">初三(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="21" class="close_menu js-level">
//    <a title="初中真题一">初中真题一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="22" class="close_menu js-level">
//    <a title="初中真题二">初中真题二</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="23" class="close_menu js-level">
//    <a title="初中真题三">初中真题三</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="24" class="close_menu js-level">
//    <a title="初中真题四">初中真题四</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="25" class="close_menu js-level">
//    <a title="初中真题五">初中真题五</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="26" class="close_menu js-level">
//    <a title="初中真题六">初中真题六</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="27" class="close_menu js-level">
//    <a title="初中真题七">初中真题七</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="28" class="close_menu js-level">
//    <a title="初中模拟一">初中模拟一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="29" class="close_menu js-level">
//    <a title="初中模拟二">初中模拟二</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="30" class="close_menu js-level">
//    <a title="初中模拟三">初中模拟三</a>
//    </div>
//
//    </li>
//    </ul>
//    </li>
//    <li>
//    <div class="close_menu"><span></span>
//    <a title="高中">高中</a>
//    </div>
//    <ul>
//
//    <li>
//    <div data-id="40" class="close_menu js-level">
//    <a title="高中基础训练一">高中基础训练一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="41" class="close_menu js-level">
//    <a title="高中基础训练二">高中基础训练二</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="42" class="close_menu js-level">
//    <a title="高中必修一（同步）">高中必修一（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="43" class="close_menu js-level">
//    <a title="高中必修一（提高）">高中必修一（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="44" class="close_menu js-level">
//    <a title="高中必修二（同步）">高中必修二（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="53" class="close_menu js-level">
//    <a title="高中必修二（提高）">高中必修二（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="45" class="close_menu js-level">
//    <a title="高中必修三（同步）">高中必修三（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="54" class="close_menu js-level">
//    <a title="高中必修三（提高）">高中必修三（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="46" class="close_menu js-level">
//    <a title="高中必修四（同步）">高中必修四（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="47" class="close_menu js-level">
//    <a title="高中必修四（提高）">高中必修四（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="48" class="close_menu js-level">
//    <a title="高中必修五（同步）">高中必修五（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="49" class="close_menu js-level">
//    <a title="高中必修五（提高">高中必修五（提高</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="50" class="close_menu js-level">
//    <a title="高中拓展训练一">高中拓展训练一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="51" class="close_menu js-level">
//    <a title="高中拓展训练二">高中拓展训练二</a>
//    </div>
//
//    </li>
//    </ul>
//    </li>
//    <!--一级菜单栏end-->
//    </ul>
//    </div>
//    </div>
//    </div>
//
//    </div>
//    <div class="modal-footer">
//    <button type="button" class="btn btn-default" data-dismiss="modal"> 取消</button>
//    </div>
//    </div>
//    </div>
//    </div>
//
//    <div class="container study-type ispxmx page-mode">
//    <div class="main">
//    <div class="top777">
//
//    <div class="huanying-bbox zw-shenlan">
//    Welcome ！
//    </div>
//    <div class="mainpage-title-v1   ">
//    智能阅读
//    </div>
//    <div class="yonghuzhinan-bbox">
//    <a href="" class="yonghuzhinan-a hidden">用户指南</a>
//    <a class="fanhuiba" href="/Web/index/index.html"><img
//    class="zw-boxshadow-white tuichuanniu555"
//    src="/tpl/Web/images/main/tuichuanniu55.png"/></a>
//
//    </div>
//    </div>
//
//    <div id="main-reading" class=" row auto-height">
//
//    <div class="qingxuanzeyf5 color-lanse">
//    请选择适合的语法进行学习吧！
//    </div>
//    <div class="g-kewenleft-bbox col-md-3 auto-height">
//    <div class="treebox  my_scroll my_scroll_no_radius   auto-height">
//    <div class="tree tree-bbox">
//    <ul>
//    <!--一级菜单栏-->
//    <li>
//    <div class="close_menu"><span></span>
//    <a title="一级菜单">小学</a>
//    </div>
//    <ul>
//
//    <li>
//    <div data-id="31" class="close_menu js-level">
//    <a title="小学提高版(易)">小学提高版(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="32" class="close_menu js-level">
//    <a title="小学提高版(中)">小学提高版(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="33" class="close_menu js-level">
//    <a title="小学提高版(难)">小学提高版(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="1" class="close_menu js-level">
//    <a title="三年级(易)">三年级(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="2" class="close_menu js-level">
//    <a title="三年级(中)">三年级(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="3" class="close_menu js-level">
//    <a title="三年级(难)">三年级(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="4" class="close_menu js-level">
//    <a title="四年级(易)">四年级(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="5" class="close_menu js-level">
//    <a title="四年级(中)">四年级(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="6" class="close_menu js-level">
//    <a title="四年级(难)">四年级(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="7" class="close_menu js-level">
//    <a title="五年级(易)">五年级(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="52" class="close_menu js-level">
//    <a title="五年级(中)">五年级(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="8" class="close_menu js-level">
//    <a title="五年级(难)">五年级(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="9" class="close_menu js-level">
//    <a title="六年级易">六年级易</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="10" class="close_menu js-level">
//    <a title="六年级中">六年级中</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="11" class="close_menu js-level">
//    <a title="六年级难">六年级难</a>
//    </div>
//
//    </li>
//    </ul>
//    </li>
//    <!--一级菜单栏end-->
//    <!--一级菜单栏-->
//    <li>
//    <div class="close_menu"><span></span>
//    <a title="初中">初中</a>
//    </div>
//    <ul>
//
//    <li>
//    <div data-id="12" class="close_menu js-level">
//    <a title="初一(易)">初一(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="13" class="close_menu js-level">
//    <a title="初一(中)">初一(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="14" class="close_menu js-level">
//    <a title="初一(难)">初一(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="15" class="close_menu js-level">
//    <a title="初二(易)">初二(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="16" class="close_menu js-level">
//    <a title="初二(中)">初二(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="17" class="close_menu js-level">
//    <a title="初二(难)">初二(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="18" class="close_menu js-level">
//    <a title="初三(易)">初三(易)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="19" class="close_menu js-level">
//    <a title="初三(中)">初三(中)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="20" class="close_menu js-level">
//    <a title="初三(难)">初三(难)</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="21" class="close_menu js-level">
//    <a title="初中真题一">初中真题一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="22" class="close_menu js-level">
//    <a title="初中真题二">初中真题二</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="23" class="close_menu js-level">
//    <a title="初中真题三">初中真题三</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="24" class="close_menu js-level">
//    <a title="初中真题四">初中真题四</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="25" class="close_menu js-level">
//    <a title="初中真题五">初中真题五</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="26" class="close_menu js-level">
//    <a title="初中真题六">初中真题六</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="27" class="close_menu js-level">
//    <a title="初中真题七">初中真题七</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="28" class="close_menu js-level">
//    <a title="初中模拟一">初中模拟一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="29" class="close_menu js-level">
//    <a title="初中模拟二">初中模拟二</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="30" class="close_menu js-level">
//    <a title="初中模拟三">初中模拟三</a>
//    </div>
//
//    </li>
//    </ul>
//    </li>
//    <li>
//    <div class="close_menu"><span></span>
//    <a title="高中">高中</a>
//    </div>
//    <ul>
//
//    <li>
//    <div data-id="40" class="close_menu js-level">
//    <a title="高中基础训练一">高中基础训练一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="41" class="close_menu js-level">
//    <a title="高中基础训练二">高中基础训练二</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="42" class="close_menu js-level">
//    <a title="高中必修一（同步）">高中必修一（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="43" class="close_menu js-level">
//    <a title="高中必修一（提高）">高中必修一（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="44" class="close_menu js-level">
//    <a title="高中必修二（同步）">高中必修二（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="53" class="close_menu js-level">
//    <a title="高中必修二（提高）">高中必修二（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="45" class="close_menu js-level">
//    <a title="高中必修三（同步）">高中必修三（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="54" class="close_menu js-level">
//    <a title="高中必修三（提高）">高中必修三（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="46" class="close_menu js-level">
//    <a title="高中必修四（同步）">高中必修四（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="47" class="close_menu js-level">
//    <a title="高中必修四（提高）">高中必修四（提高）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="48" class="close_menu js-level">
//    <a title="高中必修五（同步）">高中必修五（同步）</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="49" class="close_menu js-level">
//    <a title="高中必修五（提高">高中必修五（提高</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="50" class="close_menu js-level">
//    <a title="高中拓展训练一">高中拓展训练一</a>
//    </div>
//
//    </li>
//    <li>
//    <div data-id="51" class="close_menu js-level">
//    <a title="高中拓展训练二">高中拓展训练二</a>
//    </div>
//
//    </li>
//    </ul>
//    </li>
//    <!--一级菜单栏end-->
//    </ul>
//    </div>
//    </div>
//    </div>      <!--正式内容 start-->
//    <div class="g-right-bbox  col-md-9">
//    <div class="g-top-bbox">
//
//    <span class="sudu-span">阅读基础速度：</span> <span class="sudu"><span class="vue_speed_set">100</span>词/分钟</span>
//    <span class="xiugaisudu" data-toggle="modal" data-target="#xiugaiModal">【修改速度】</span>
//    <a class="yannaoxunlian-a hvr-float-shadow"
//    href="/tpl/Web/page/yanbulianxi.html">眼脑训练</a>
//
//    <a class="yannaoxunlian-a hvr-float-shadow shengciben-a"
//    href="/Web/AiReading/readingnewword.html">生词本</a>
//
//    </div>
//
//    <div id="g-keben-bbox" class="my_scroll my_scroll_no_radius g-keben-bbox zw-clear">
//    <!--每个课本 start-->
//    <div class="keben-box shangsuo hidden">
//    <img src="/tpl/Web/images/reading/suotou.png" class="suotou-img"/>
//    <a href="">
//    <div class="keben-img-box">
//    <img class="keben-img" src="/tpl/Web/images/zncg_01.png"/>
//    <div class="xingxing-bbox">
//    <img src="/tpl/Web/images/reading/star1.png" class="xingxing-img "/>
//    </div>
//    </div>
//
//    <div class="keben-name">
//    第一篇
//    </div>
//    </a>
//    <div class="guanqiahengxian">
//
//    </div>
//
//    </div>
//    <!--每个课本 end-->
//
//    </div>
//
//    </div>
//
//    </div>
//
//    </div>
//    </div>
//    <!--底部内容 start-->
//
//    <footer>
//
//
//    </footer>
//    <!--底部内容 end-->
//
//
//    <script type="text/javascript">
//    var jsLevel = localStorage.getItem('js-level')
//
//    if (jsLevel) {
//    $('.js-level[data-id="' + jsLevel + '"]').find('a').addClass('ontree').trigger('click')
//    } else {
//    $('.js-level').eq(site_config.moren).find('a').addClass('ontree')
//
//    }
//    </script>
//    <script type="text/javascript"
//    src="/tpl/Web/zwplus/jqueryTreescroll/js/treescroll.min.js"></script>
//
//    <script src="/tpl/Web/js/reading.js?v=2019年12月14日15:46:68" type="text/javascript"
//    charset="utf-8"></script>
//    <script src="/tpl/Web/zwplus/wow/js/wow.min.js" type="text/javascript" charset="utf-8"></script>
//
//    <script type="text/javascript">
//    $(function () {
//    l_reading.m_index();
//    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
//    new WOW().init();
//    }
//    ;
//    })
//    </script>
//    <!--main end-->
//
//    </div>
//
//
//    <script src="/tpl/Web/js/zw.js?v=2019年12月14日15:46:68" type="text/javascript"
//    charset="utf-8"></script>
//
//    <!--<script src="/tpl/Web/zwplus/wow/js/wow.min.js" type="text/javascript" charset="utf-8"></script>-->
//    <script src="/tpl/Web/zwplus/jqueryloading22_files/fakeLoader.min.js"></script>
//    <script src="/tpl/Web/js/star.js" type="text/javascript" charset="utf-8"></script>
//
//    <script src="/tpl/Web/js/Tdrag.js" type="text/javascript" charset="utf-8"></script>
//    <!--机器人 Start-->
//    <div class="jiqiren-bbox" id="jiqiren-bbox">
//    <div id="robot-prompt-bbox" class="index-23">
//    <div class="qiandaoshouri">
//    <p id="vue-robot-prompt">提示：今日还未签到，请签到！</p>
//    <div id="js-robot-prompt-fun" class="qdBtn   an-yaohuang">签到</div>
//    </div>
//    </div>
//    <button class="js-robot-button zw-shenlan-btn hvr-float-shadow color-fense-shadow">返回</button>
//
//    <div style="background-image: url(/tpl/Web/images/keyidong.png);background-size: 100% 100%;"
//    class="jiqiren-keyidong-img">
//    </div>
//
//    <div style="background-image: url(/tpl/Web/images/faguang.png);background-size: 100% 100%;"
//    class="jiqirenfaguang-img">
//
//    </div>
//
//    <div id="robot-property-bbox">
//
//    <a href="javascript:;" class="correct-btn" id="robot-correct"><img
//    src="/tpl/Web/images/cyjy/jiucuoimg.png" class="jiucuo-img"/></a>
//    <div class="robot-property-title">
//    robot小爱 <span class="jiqiren-chengzhangzhi"></span>
//    </div>
//    <a href="/Web/user/gold_record.html">
//    <div class="robot-property hidden">
//    <img src="/tpl/Web/images/main/getgoldchengzhangzhi.png"
//    class="jiqiren-chengzhangzhi-img"/></span> 成长 <span
//    class="pull-right vue-grow-num">0</span>
//    </div>
//    </a>
//    <a href="/Web/user/gold_record.html">
//    <div class="robot-property">
//    <span class="fa fa-diamond"></span> 金币 <span class="pull-right vue-gold-num">18</span>
//    </div>
//    </a>
//    <div class="robot-property js-xiaoxi cursor-point">
//    <span class="fa fa-comment-o"></span> 消息 <span class="pull-right vue-xiaoxi-num">0</span>
//    </div>
//
//    <div class="robot-xiaonengbbox">
//    <span class="fa fa-area-chart"></span>效能管家
//    <div class="switch demo3 kaiguanxiaonengguanjia">
//    <input type="checkbox" id="openxiaonengguanjia" checked="checked">
//    <label><i></i></label>
//    </div>
//    </div>
//
//    <div class="robot-property">
//    <span class="fa fa-envelope-o"></span> 任务 <span class="pull-right">0</span>
//    </div>
//
//    <div class="robot-property js-zuixinhuodong">
//    <span class="fa fa-envelope-o"></span> 最新活动 <span class="pull-right">2</span>
//    </div>
//    <a href="/Web/index/version.html">
//    <div class="robot-property">
//
//    <span class="fa fa-envelope-o"></span> 版本日志 <span
//    class="pull-right vue-banben-xinxi"></span>
//
//    </div>
//    </a>
//    </div>
//
//    </div>
//
//    <!--机器人 end-->
//
//    <!--开关按钮-->
//    <script src="/tpl/Web/js/robot.js?v=2019年12月14日15:46:68" type="text/javascript"
//    charset="utf-8"></script>
//
//    <!--步骤教学-->
//
//    <script type="text/javascript"
//    src="/tpl/Web/zwplus/pagewalkthrough/jquery.pagewalkthrough.min.js"></script>
//    <script type="text/javascript">
//    $(function () {
//    $('#jiqiren-bbox').css('backgroundImage', 'url(/tpl/Web/images/main/robothuaixiao.png)');
//
//    })
//    </script>
//    <script type="text/javascript">
//    //content 高度自适应
//    f_zw_loading()
//    //content 高度自适应
//
//    $('.main-main').height($(window).height() - 280)
//    //统计有效在线时间
//
//    //			window.setInterval(function() {
//    //				var define_time = site_config.define_time
//    //				var onlineTime = localStorage.getItem(define_time);
//    //				if(typeof(onlineTime) == "undefined" || !onlineTime) {
//    //					localStorage.setItem(define_time, 10);
//    //				} else {
//    //					var newTime = parseInt(onlineTime) + 10
//    //					localStorage.setItem(define_time, newTime);
//    //
//    //				}
//    //
//    //			}, 10000)
//    $(function () {
//
//    l_zw.init();
//    if (isMobile()) {
//
//    l_m_zw.init();
//    }
//
//    f_zw_loading_close()
//    //				layer.close(g_layerLoad); //关闭加载曾
//
//    })
//    </script>
//
//    </body>
//
//    </html>
