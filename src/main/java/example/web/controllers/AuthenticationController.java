package example.web.controllers;

import example.backEndApp.entities.Chat;
import example.backEndApp.entities.User;
import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.swing.text.View;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
@RequestMapping(value = "/chatapp")
public class AuthenticationController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/authentication")
    public ModelAndView logInToAccount(ModelAndView modelAndView) {
        modelAndView.setViewName("/jsp/authentication");
        return modelAndView;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkUser(@RequestParam(value = "login", defaultValue = "", required = false) String login,
                            @RequestParam(value = "password", defaultValue = "", required = false) String password,
                            ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String userQuery = "select * from users where login='" + login + "' and password='" + password + "'";
        ResultSet searchedUsers = statement.executeQuery(userQuery);
        if (searchedUsers.next()) {
            long userId = searchedUsers.getLong("ID");
            statement.close();
            request.getSession().setAttribute("userId", userId);
            return "redirect:/chatapp/openchats";
        } else {
            statement.close();
            return "redirect:/chatapp/authentication";
        }
    }
}
