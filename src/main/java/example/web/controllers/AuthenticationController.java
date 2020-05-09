package example.web.controllers;

import example.backEndApp.entities.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.sql.DataSource;
import java.util.Map;

@Controller
@RequestMapping(value = "/chatapp", method = RequestMethod.GET)
public class AuthenticationController {
    @Autowired
    DataSource dataSource;
    @RequestMapping(value = "/authentication")
    public ModelAndView logInToAccount(ModelAndView modelAndView){
        modelAndView.setViewName("/jsp/authentication");
        return modelAndView;
    }
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView checkUser(@RequestParam(value = "login",defaultValue = "",required = false)String login,
                                  @RequestParam(value = "password",defaultValue = "",required = false)String password,
                                  ModelAndView modelAndView){

        modelAndView.setViewName("/jsp/authentication");
        return modelAndView;
    }
}
