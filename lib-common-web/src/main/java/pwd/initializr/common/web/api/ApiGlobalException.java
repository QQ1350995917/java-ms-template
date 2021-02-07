package pwd.initializr.common.web.api;

import java.util.Set;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pwd.initializr.common.web.api.vo.Meta;
import pwd.initializr.common.web.api.vo.Output;

/**
 * pwd.initializr.common.web.api@ms-web-initializr
 *
 * <h1>异常全局处理器</h1>
 *
 * date 2020-08-02 11:17
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
@RestControllerAdvice
@Slf4j
public class ApiGlobalException {

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Output handle(Exception exception) {
    int code = 400;
    String message = null;
    if (exception instanceof ConstraintViolationException) {
      ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
      Set<ConstraintViolation<?>> violations = constraintViolationException
          .getConstraintViolations();
      for (ConstraintViolation<?> violation : violations) {
        message = violation.getMessage();
        break;
      }
    } else if (exception instanceof ConstraintDeclarationException) {
      ConstraintDeclarationException constraintDeclarationException = (ConstraintDeclarationException) exception;
      message = constraintDeclarationException.getMessage();
    } else if (exception instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
      BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        String field = fieldError.getField();
        String msg = fieldError.getDefaultMessage();
        message = String.format("%s:%s", field, msg);
        break;
      }
    } else if (exception instanceof ApiBaseException){
      ApiBaseException apiBaseException = (ApiBaseException) exception;
      code = apiBaseException.getStatusCode();
      message = apiBaseException.getMessage();
    } else {
      message = exception.getMessage();
    }
    log.error(exception.getClass().getCanonicalName());
    StackTraceElement[] stackTrace = exception.getStackTrace();
    if (stackTrace != null) {
      StringBuilder stackTraceText = new StringBuilder();
      for (StackTraceElement stackTraceElement : stackTrace) {
        stackTraceText.append(stackTraceElement.toString()).append("\r\n");
      }
      log.error(stackTraceText.toString());
    }
    return new Output(new Meta(code, message));
  }
}
