package indi.jcl.magicblog.handler;

import indi.jcl.magicblog.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response handleException(Exception e) {
        String expInfo =  getExceptionInfo(e);
        StringBuilder logInfo = new StringBuilder();
        logInfo.append("------------------------------------------------GlobalExceptionHandler start------------------------------------------------").append("\r\n");
        logInfo.append(expInfo).append("\r\n");
        logInfo.append("------------------------------------------------GlobalExceptionHandler end--------------------------------------------------").append("\r\n");
        logger.warn(logInfo.toString());
        return Response.EXCEPTION().setResult(expInfo);
    }

    private String getExceptionInfo(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

}
