//package pwd.initializr.common.web.api;
//
//import java.net.BindException;
//import java.util.Set;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import pwd.initializr.common.web.api.vo.Meta;
//import pwd.initializr.common.web.api.vo.Output;
//
///**
// * pwd.initializr.common.web.api@ms-web-initializr
// *
// * <h1>TODO what you want to do?</h1>
// *
// * date 2020-07-31 11:56
// *
// * @author DingPengwei[dingpengwei@foxmail.com]
// * @version 1.0.0
// * @since DistributionVersion
// */
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(value = BindException.class)
//    public Output violationException(BindException exception) {
//        // 不带任何参数访问接口,会抛出 BindException
//        // 因此，我们只需捕获这个异常，并返回我们设置的 message 即可
////        String message = exception().getAllErrors().get(0).getDefaultMessage();
//        return new Output();
//    }
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    public Output violationException(Exception exception) {
//        //使用关键字instanceof 判断 ConstraintViolationException 是否为 Exception 直接或间接子类
//        if (exception instanceof ConstraintViolationException) {
//            //调用下面方法，返回结果
//            return constraintViolationException((ConstraintViolationException) exception);
//        }
//        return new Output(500);
//    }
//
//    // 当我们没有此方法，空参访问localhost:8080/login 会抛出ConstraintViolationException 异常
//    public Output constraintViolationException(ConstraintViolationException ex) {
//        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//        if (!CollectionUtils.isEmpty(constraintViolations)) { //判断是否为空
//            StringBuilder sb = new StringBuilder();
//            for (ConstraintViolation constraintViolation : constraintViolations) { //遍历 ConstraintViolation
//                sb.append(constraintViolation.getMessage()).append(","); // 吧错误信息循环放到sb中, 并以逗号隔开
//            }
//            String errorMessage = sb.toString(); // 获得异常信息字符串
//            return new Output(new Meta(400, errorMessage));
//        }
//        return new Output(new Meta(500, "server error"));
//    }
//}
