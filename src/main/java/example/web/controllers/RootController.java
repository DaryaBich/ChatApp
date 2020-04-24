package example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

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
}
