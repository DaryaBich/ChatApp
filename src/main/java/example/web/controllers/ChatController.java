package example.web.controllers;

import example.backEndApp.entities.Message;
import example.backEndApp.entities.User;
import org.h2.engine.Session;
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
import java.util.*;

@Controller
@RequestMapping(value = "/chatapp")
public class ChatController {
    @Autowired
    DataSource dataSource;
    @RequestMapping(value = "/adaptchat", method = RequestMethod.GET)
    public ModelAndView adapter(ModelAndView modelAndView, HttpServletRequest request) throws SQLException {
        long  chatId = (long)  request.getSession().getAttribute("chatId");
        return openChat(String.valueOf(chatId), modelAndView, request);
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public ModelAndView openChat(@RequestParam(value = "click", defaultValue = "", required = false) String chatId,
                                 ModelAndView modelAndView, HttpServletRequest request) throws SQLException {

        User user = null;
        User userInterlocutor = null;
        List messages = new ArrayList<>();
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId");
        session.setAttribute("chatId", Long.valueOf(chatId));
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet searchedUsers = statement.executeQuery("select * from users where id=" + userId);
        searchedUsers.next();
        user = new User(searchedUsers.getInt("ID"), searchedUsers.getString("name"),
                searchedUsers.getString("login"),
                searchedUsers.getString("password").toCharArray());
        ResultSet searchedMessages = statement.executeQuery("select * from message where id in " +
                "(select idMessage from messageProcessing where idChat = " + chatId + ")");
        while (searchedMessages.next()) {
            messages.add(new Message(searchedMessages.getInt("ID"),
                    searchedMessages.getInt("sentByUserWithId"),
                    searchedMessages.getString("text"),
                    searchedMessages.getTimestamp("sendingDate")));
        }
        ResultSet searchedInterlocutor = statement.executeQuery("select * from users where id in " +
                "(select userId from userInChat where chatId = " + chatId + ")");
        while (searchedInterlocutor.next()) {
            if (searchedInterlocutor.getLong("id") != userId) {
                userInterlocutor = new User(searchedInterlocutor.getInt("ID"),
                        searchedInterlocutor.getString("name"),
                        searchedInterlocutor.getString("login"),
                        searchedInterlocutor.getString("password").toCharArray());
            }
        }
        statement.close();
        Comparator<Message> comparator = Comparator.comparing(message -> message.getSendingDate());
        Collections.sort(messages, comparator);
        Collections.reverse(messages);
        Map model = modelAndView.getModel();
        model.put("user", user);
        model.put("userId", userId);
        model.put("chatId", Long.valueOf(chatId));
        model.put("messages", messages);
        model.put("userInterlocutor", userInterlocutor);
        modelAndView.setViewName("/jsp/chat");
        return modelAndView;
    }
}
