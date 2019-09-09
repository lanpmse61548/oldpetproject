package pet.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.http.HttpStatus;
@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleError404(HttpServletRequest request, Exception e) {
        return "404";
    }

    @ExceptionHandler({ HttpClientErrorException.class,Throwable.class  })
    @ResponseStatus
    public String handleError4xx(HttpServletRequest request, Exception e) {
        return "errors4";
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus
    public String handleError5xx(HttpServletRequest request, Exception e) {
        return "errors5";
    }

}