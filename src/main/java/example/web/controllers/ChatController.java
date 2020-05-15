package example.web.controllers;

import example.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@Controller
@RequestMapping(value = "/chatapp")
public class ChatController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public ModelAndView openChat(@RequestParam(value = "click", defaultValue = "", required = false) String chatId,
                                 ModelAndView modelAndView, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId");
        Map model = modelAndView.getModel();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet searchedUsers = statement.executeQuery("select * from users where id=" + userId);
        User user = null;
        if (searchedUsers.next()) {
            user = new User(searchedUsers.getInt("ID"), searchedUsers.getString("name"),
                    searchedUsers.getString("login"),
                    searchedUsers.getString("password").toCharArray());
        }
        statement.close();
        model.put("user", user);
        model.put("chatId", Integer.valueOf(chatId));
        modelAndView.setViewName("/jsp/chat");
        return modelAndView;
    }
}
