package chatapp.web.controllers;

import chatapp.backEndApp.dao.ChatDao;
import chatapp.backEndApp.dao.UserDao;
import chatapp.backEndApp.entities.Chat;
import chatapp.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user/chatapp")
public class ChatsController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ChatDao chatDao;

    @RequestMapping(value = "/openchats", method = RequestMethod.GET)
    public ModelAndView openChats(ModelAndView modelAndView, HttpServletRequest request)
            throws SQLException {

        long userId = (long) request.getSession().getAttribute("userId");
        User user = userDao.searchUserById(userId).get(0);
        List<Chat> chats = chatDao.searchChatsByUserId(userId);
        modelAndView.setViewName("/jsp/chats");
        Map model = modelAndView.getModel();
        model.put("user", user);
        model.put("chats", chats);
        return modelAndView;
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
