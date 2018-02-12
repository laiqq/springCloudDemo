package com.jony.base;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {


    @ExceptionHandler(value = Exception.class)
    public void exception(Exception exception, WebRequest request){
        System.out.println("test exception");
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","test @ModelAttribute");
    }

}
