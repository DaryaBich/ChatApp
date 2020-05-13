package example.web.controllers;
import example.backEndApp.entities.Chat;
import example.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/chatapp", method = RequestMethod.GET)
public class AuthenticationController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/authentication")
    public ModelAndView logInToAccount(ModelAndView modelAndView) {
        modelAndView.setViewName("/jsp/authentication");
        return modelAndView;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView checkUser(@RequestParam(value = "login", defaultValue = "", required = false) String login,
                                  @RequestParam(value = "password", defaultValue = "", required = false) String password,
                                  ModelAndView modelAndView) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        User user = null;
        String userQuery = "select * from users where login='" + login + "' and password='" + password + "'";
        ResultSet searchedUsers = statement.executeQuery(userQuery);
        while (searchedUsers.next()) {
            user = new User(searchedUsers.getInt("ID"), searchedUsers.getString("name"),
                    searchedUsers.getString("login"),
                    searchedUsers.getString("password").toCharArray());
        }
        statement.close();
        if (user != null) {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String chatsQuery = "select * from chat where id in (select chatId from UserInChat where userId=" +
                    user.getId() + ")";
            ResultSet searchedChats = statement.executeQuery(chatsQuery);
            List<Chat> chats = new ArrayList<>();
            while (searchedChats.next()) {
                chats.add(new Chat(searchedChats.getInt("ID"), searchedChats.getString("name")));
            }
            statement.close();
            modelAndView.setViewName("/jsp/chats");
            Map model = modelAndView.getModel();
            model.put("user", user);
            model.put("chats", chats);
            List<String> chatName = new ArrayList<>();
            chats.forEach(c -> chatName.add(c.getName()));
            model.put("chatName", chatName);
            model.put("userName", user.getName());
        } else {
            modelAndView.setViewName("/jsp/authentication");
        }
        return modelAndView;
    }
}
