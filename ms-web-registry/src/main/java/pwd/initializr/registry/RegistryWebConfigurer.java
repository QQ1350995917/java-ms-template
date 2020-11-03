//package pwd.initializr.registry;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * pwd.initializr.registry@ms-web-initializr
// *
// * <h1>TODO what you want to do?</h1>
// *
// * date 2020-07-06 21:17
// *
// * @author DingPengwei[www.dingpengwei@foxmail.com]
// * @version 1.0.0
// * @since DistributionVersion
// */
//@Component
//@EnableWebMvc
//public class RegistryWebConfigurer implements WebMvcConfigurer {
//
//  @Override
//  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//    FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
//    FastJsonConfig config = new FastJsonConfig();
//    config.setCharset(StandardCharsets.UTF_8);
//    //禁止循环引用
//    config.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
//    httpMessageConverter.setFastJsonConfig(config);
//    //所有序列化组件均保存在converters集合中
//    //添加扩展的组件到索引0的位置，便于Spring首先尝试
//    converters.add(0, httpMessageConverter);
//  }
//}
