package edu.nju.se.yrd.iotconnmgmt.controller;

import edu.nju.se.yrd.iotconnmgmt.vo.BasicResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public BasicResponse illegalArgumentException(Exception e) {
        return BasicResponse.error().message(e.getMessage());
    }
}
