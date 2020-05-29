package example.web.controllers;

import example.backEndApp.entities.Message;
import example.backEndApp.entities.User;
import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

@Controller
@RequestMapping(value = "/user/chatapp")
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
        List<Message> messages = new ArrayList<>();
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId");
        session.setAttribute("chatId", Long.valueOf(chatId));
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setLong(1, userId);
        ResultSet searchedUsers = preparedStatement.executeQuery();
        searchedUsers.next();
        user = new User(searchedUsers.getInt("ID"), searchedUsers.getString("name"),
                searchedUsers.getString("login"),
                searchedUsers.getString("password").toCharArray());
        preparedStatement = connection.prepareStatement("select * from message where id in " +
                        "(select idMessage from messageProcessing where idChat = ?)");
        preparedStatement.setString(1, chatId);
        ResultSet searchedMessages = preparedStatement.executeQuery();
        while (searchedMessages.next()) {
            messages.add(new Message(searchedMessages.getInt("ID"),
                    searchedMessages.getInt("sentByUserWithId"),
                    searchedMessages.getString("text"),
                    searchedMessages.getTimestamp("sendingDate")));
        }
        preparedStatement = connection.prepareStatement("select * from users where id in " +
                "(select userId from userInChat where chatId = ?)");
        preparedStatement.setString(1, chatId);
        ResultSet searchedInterlocutor = preparedStatement.executeQuery();
        while (searchedInterlocutor.next()) {
            if (searchedInterlocutor.getLong("id") != userId) {
                userInterlocutor = new User(searchedInterlocutor.getInt("ID"),
                        searchedInterlocutor.getString("name"),
                        searchedInterlocutor.getString("login"),
                        searchedInterlocutor.getString("password").toCharArray());
            }
        }
        preparedStatement.close();
        Comparator<Message> comparator = Comparator.comparing(Message::getSendingDate);
        messages.sort(comparator);
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
    @ExceptionHandler(Exception.class)
    ModelAndView except(Exception exception){
        return new ModelAndView(new View() {
            @Override
            public String getContentType() {
                return "web.xml";
            }

            @Override
            public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write("test exception");
            }
        });
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
