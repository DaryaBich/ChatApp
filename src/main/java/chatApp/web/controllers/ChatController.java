package chatApp.web.controllers;

import chatApp.backEndApp.dao.MessageDao;
import chatApp.backEndApp.dao.UserDao;
import chatApp.backEndApp.entities.Message;
import chatApp.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;

@Controller
@RequestMapping(value = "/user/chatapp")
public class ChatController {
    @Autowired
    UserDao userDao;
    @Autowired
    MessageDao messageDao;

    @RequestMapping(value = "/adaptchat", method = RequestMethod.GET)
    public ModelAndView adapter(ModelAndView modelAndView, HttpServletRequest request) throws SQLException {
        long  chatId = (long)  request.getSession().getAttribute("chatId");
        return openChat(String.valueOf(chatId), modelAndView, request);
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public ModelAndView openChat(@RequestParam(value = "click", defaultValue = "", required = false) String chatId,
                                 ModelAndView modelAndView, HttpServletRequest request){
        User user = null;
        User userInterlocutor = null;
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId");
        session.setAttribute("chatId", Long.valueOf(chatId));
        user = userDao.searchUserById(userId).get(0);
        List<Message> messages = messageDao.searchMessageByChatId(chatId);
        List<User> users = userDao.searchUserByChatId(chatId);
        userInterlocutor = users.get(0).getId() == userId? users.get(1) : users.get(0);
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
//    @ExceptionHandler(Exception.class)
//    public ModelAndView except(Exception exception, HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/jsp/error");
//        return modelAndView;
//    }
}
