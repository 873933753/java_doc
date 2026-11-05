package com.tang.missu.core;

import com.tang.missu.core.configuration.ExceptionCodeConfiguration;
import com.tang.missu.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {

    /* 处理未知异常  */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public UnifyResponse hanldeException(HttpServletRequest req,Exception e) {
        String requestUrl = req.getRequestURI(); // 获取请求接口名
        String method = req.getMethod(); // 获取请求类型
        System.out.println(e); // 开发时通过打印e查看异常原因
        UnifyResponse message = new UnifyResponse(9999,"出错了",method + " " +requestUrl);
        return message;
    }


    /* 注入异常配置类 */
    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;

    /* 处理已知异常 - 代码里抛出来的已知异常 */
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException e) {
        // code和httpStatus都从类中获取不再写死
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String msg = codeConfiguration.getMessage(e.getCode());
        UnifyResponse message = new UnifyResponse(e.getCode(),msg,method+" " + requestUrl);

        // RequestEntity里接收三个参数，message，httpStatus和header
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // 设置响应头
        ResponseEntity<UnifyResponse> r = new ResponseEntity<>(message,headers,httpStatus);
        return r;
    }


    /* 处理已知异常 - 参数问题body中的参数 */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) // 400
    public UnifyResponse hanldeArgmentException(HttpServletRequest req, MethodArgumentNotValidException e) {
        String requestUrl = req.getRequestURI(); // 获取请求接口名
        String method = req.getMethod(); // 获取请求类型
        System.out.println("xxx:"+e); // 开发时通过打印e查看异常原因
        // 有可能是多个错误
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);
        UnifyResponse UniMessage = new UnifyResponse(10001,message,method + " " +requestUrl);
        return UniMessage;
    }
    // 自定义方法处理错误,将多个错误拼接在一起
    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error -> errorMsg.append(error.getDefaultMessage()).append(';'));
        return errorMsg.toString();
    }
    // url上的参数异常- ConstraintViolationException
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifyResponse handleConstrainException(HttpServletRequest req,ConstraintViolationException e){
        String requestUrl = req.getRequestURI(); // 获取请求接口名
        String method = req.getMethod(); // 获取请求类型
        String message = e.getMessage();

        UnifyResponse UniMessage = new UnifyResponse(10001,message,method + " " +requestUrl);
        return UniMessage;
    }
}
