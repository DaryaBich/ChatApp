package chatApp.web.controllers;

import chatApp.backEndApp.dao.MessageDao;
import chatApp.backEndApp.dao.MessageProcessingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping(value = "/user/chatapp")
public class SendMessageController {
    @Autowired
    MessageDao messageDao;

    @Autowired
    MessageProcessingDao messageProcessingDao;

    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    public void sendMessage(@RequestParam(value = "messageText", defaultValue = "", required = false) String text,
                              ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId");
        long chatId = (long) session.getAttribute("chatId");
        messageDao.insertMessage(userId, text);
        Long messageId = messageDao.selectLastMessage(userId, text);
        messageProcessingDao.insertMessageProcessing(chatId, messageId);
        response.sendRedirect("/user/chatapp/adaptchat");
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
