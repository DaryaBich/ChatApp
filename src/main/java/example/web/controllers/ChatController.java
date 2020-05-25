package example.web.controllers;

import example.backEndApp.entities.Chat;
import example.backEndApp.entities.Message;
import example.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
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
import java.util.*;

@Controller
@RequestMapping(value = "/chatapp")
public class ChatController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public ModelAndView openChat(@RequestParam(value = "click", defaultValue = "", required = false) String chatId,
                                 ModelAndView modelAndView, HttpServletRequest request) throws SQLException {
        User user = null;
        List messages = new ArrayList<>();
        String userWith = null;
        long userId = (long) request.getSession().getAttribute("userId");
        Map model = modelAndView.getModel();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet searchedUsers = statement.executeQuery("select * from users where id=" + userId);
        if (searchedUsers.next()) {
            user = new User(searchedUsers.getInt("ID"), searchedUsers.getString("name"),
                    searchedUsers.getString("login"),
                    searchedUsers.getString("password").toCharArray());
        }
        String messageQuery = "select * from message where id in "+
                "(select idMessage from messageProcessing where idChat = " + chatId + ")";
        ResultSet searchedMessages = statement.executeQuery(messageQuery);
        while (searchedMessages.next()) {
            messages.add(new Message(searchedMessages.getInt("ID"),
                    searchedMessages.getInt("sentByUserWithId"),
                    searchedMessages.getString("text"),
                    searchedMessages.getTimestamp("sendingDate")));
        }
        String selectUserWithWhomDialog = "select * from users where id in "+
                "(select userId from userInChat where chatId = " + chatId+")";
        ResultSet searchedUser = statement.executeQuery(selectUserWithWhomDialog);
        while (searchedUser.next()) {
            if (searchedUser.getLong("id") != userId) {
                userWith = searchedUser.getString("name");
            }
        }
        statement.close();
        Comparator<Message> comparator = Comparator.comparing(message -> message.getSendingDate());
        Collections.sort(messages, comparator);
        Collections.reverse(messages);
        model.put("user", user);
        model.put("userId", userId);
        model.put("chatId", Integer.valueOf(chatId));
        model.put("messages", messages);
        model.put("userWith", userWith);
        modelAndView.setViewName("/jsp/chat");
        return modelAndView;
    }
}
