package chatApp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/chatApp")
public class WelcomePageController {
    @RequestMapping(value = "/welcomepage")
    public ModelAndView openWelcomePage(ModelAndView modelAndView){
        modelAndView.setViewName("/jsp/welcome");
        return modelAndView;
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}

