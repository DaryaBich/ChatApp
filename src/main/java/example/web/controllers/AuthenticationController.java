package example.web.controllers;

import example.backEndApp.dao.UserDao;
import example.backEndApp.dao.UserInChatDao;
import example.backEndApp.entities.Authentication;
import example.backEndApp.entities.Chat;
import example.backEndApp.entities.User;
import example.backEndApp.entities.UserInChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/chatapp", method = RequestMethod.GET)
public class AuthenticationController {
    @Autowired
    DataSource dataSource;
    @RequestMapping(value = "/authentication")
    public ModelAndView logInToAccount(ModelAndView modelAndView){
        modelAndView.setViewName("/jsp/authentication");
        return modelAndView;
    }
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView checkUser(@RequestParam(value = "login",defaultValue = "",required = false)String login,
                                  @RequestParam(value = "password",defaultValue = "",required = false)String password,
                                  ModelAndView modelAndView){
        // где проверять наличие пользователя
//        User user = userDao.checkUser(login, password);
//        if (user != null) {
//            modelAndView.setViewName("/jsp/chats");
//            List<Chat> chats = userInChatDao.getUserChats(user);
//            Map model = modelAndView.getModel();
//            model.put("chats", chats);
//        }
        List<Chat> chats = new ArrayList<>();
        List<String> chatName = new ArrayList<>();
        chats.add(new Chat(0,"first"));
        chatName.add("first");
        chats.add(new Chat(1, "second"));
        chatName.add("second");
        chatName.add("secondarythirdly");
        // установить длину строк дял отображения
        modelAndView.setViewName("/jsp/chats");
        Map model = modelAndView.getModel();
        model.put("chats", chats);
        model.put("chatName", chatName);
        return modelAndView;
    }
}
