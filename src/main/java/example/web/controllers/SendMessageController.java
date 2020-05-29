package example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/user/chatapp")
public class SendMessageController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    public void sendMessage(@RequestParam(value = "messageText", defaultValue = "", required = false) String text,
                              ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId");
        long chatId = (long) session.getAttribute("chatId");
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select max(id) from message");
        ResultSet maxMessageId = preparedStatement.executeQuery();
        long messageId;
        if (maxMessageId.next()) {
            messageId = maxMessageId.getLong("max(id)") + 1;
        } else {
            messageId = 1;
        }
        preparedStatement = connection.prepareStatement("select max(id) from MessageProcessing");
        ResultSet maxMessageProcessingId = preparedStatement.executeQuery();
        long messageProcessingId;
        if (maxMessageProcessingId.next()) {
            messageProcessingId = maxMessageProcessingId.getLong("max(id)") + 1;
        } else {
            messageProcessingId = 1;
        }
        preparedStatement = connection.prepareStatement("INSERT INTO MESSAGE VALUES( ?, ?, '?', NOW())");
        preparedStatement.setLong(1, messageId);
        preparedStatement.setLong(2, userId);
        preparedStatement.setString(3, text);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("INSERT INTO MESSAGEPROCESSING VALUES(?, ?, ? )");
        preparedStatement.setLong(1, messageProcessingId);
        preparedStatement.setLong(2, chatId);
        preparedStatement.setLong(3, messageId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        response.sendRedirect("/user/chatapp/adaptchat");
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
