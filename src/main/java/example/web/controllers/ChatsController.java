package example.web.controllers;

import example.backEndApp.entities.Chat;
import example.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/chatapp")
public class ChatsController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/openchats", method = RequestMethod.GET)
    public ModelAndView openChats(ModelAndView modelAndView, HttpServletRequest request)
            throws SQLException {
        User user = null;
        long userId = (long) request.getSession().getAttribute("userId");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet searchedUsers = statement.executeQuery("select * from users where id=" + userId);
        if (searchedUsers.next()) {
            user = new User(searchedUsers.getInt("ID"), searchedUsers.getString("name"),
                    searchedUsers.getString("login"),
                    searchedUsers.getString("password").toCharArray());
        }
        statement.close();
        statement = connection.createStatement();
        String chatsQuery = "select * from chat where id in (select chatId from UserInChat where userId=" + userId + ")";
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
        return modelAndView;
    }
}
