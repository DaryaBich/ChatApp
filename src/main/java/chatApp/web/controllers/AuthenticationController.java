package chatApp.web.controllers;

import chatApp.backEndApp.dao.UserDao;
import chatApp.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;

@Controller
@RequestMapping(value = "/chatapp")
public class AuthenticationController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/authentication")
    public ModelAndView logInToAccount(ModelAndView modelAndView) {
        modelAndView.setViewName("/jsp/authentication");
        return modelAndView;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkUser(@RequestParam(value = "login", defaultValue = "", required = false) String login,
                            @RequestParam(value = "password", defaultValue = "", required = false) String password,
                            ModelAndView modelAndView, HttpServletRequest request) throws SQLException {
        List<User> users = userDao.searchUserByLoginAndPassword(login, password);
        if (users.size() == 1) {
            request.getSession().setAttribute("userId", users.get(0).getId());
            return "redirect:/user/chatapp/openchats";
        } else {
            return "redirect:/chatapp/authentication";
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
