package chatApp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class RootController {

    WebApplicationContext webContext;

    /**
     * Welcome page mapping
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping
    public ModelAndView welcomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("/index");
        return modelAndView;
    }
    /**
     * Welcome source page mapping
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView indexPage(ModelAndView modelAndView) {
               return modelAndView;
    }
    @RequestMapping(value = "/chatApp") String appCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        return userId == null ? "redirect:chatapp/welcomepage":"redirect:chatapp/openchats";
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
