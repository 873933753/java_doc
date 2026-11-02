package com.tang.missu.core;

import com.tang.missu.core.configuration.ExceptionCodeConfiguration;
import com.tang.missu.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

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
}
