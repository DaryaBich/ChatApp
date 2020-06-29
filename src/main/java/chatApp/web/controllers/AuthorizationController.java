package chatApp.web.controllers;

import chatApp.backEndApp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/chatapp")
public class AuthorizationController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/authorization")
    public ModelAndView openAuthorizationPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/jsp/authorization");
        return modelAndView;
    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public ModelAndView createNewAccount(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                         @RequestParam(value = "login", defaultValue = "", required = false) String login,
                                         @RequestParam(value = "password1", defaultValue = "", required = false)
                                                 String password1,
                                         @RequestParam(value = "password2", defaultValue = "", required = false)
                                                 String password2,
                                         ModelAndView modelAndView, HttpServletRequest request) throws SQLException {
        modelAndView.setViewName("/jsp/authorization");
        if (password1.equals(password2) && password1.length() > 0) {
            if (!userDao.searchUserByLogin(name)){
                if (userDao.addUser(name, login, password1)){
                    modelAndView.setViewName("/jsp/authentication");
                }
            }
        }
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
