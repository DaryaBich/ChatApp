package example.web.controllers;

import example.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping(value = "/chatapp")
public class SendMessageController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    public void sendMessage(@RequestParam(value = "messageText", defaultValue = "", required = false) String text,
                              ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId");
        long chatId = (long) session.getAttribute("chatId");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet maxMessageId = statement.executeQuery("select max(id) from message");
        long messageId;
        if (maxMessageId.next()) {
            messageId = maxMessageId.getLong("max(id)") + 1;
        } else {
            messageId = 1;
        }
        ResultSet maxMessageProcessingId = statement.executeQuery("select max(id) from MessageProcessing");
        long messageProcessingId;
        if (maxMessageProcessingId.next()) {
            messageProcessingId = maxMessageProcessingId.getLong("max(id)") + 1;
        } else {
            messageProcessingId = 1;
        }
        statement.executeUpdate("INSERT INTO MESSAGE VALUES(" + messageId + ", " + userId + ", '" + text +
                "', GETDATE())");
        statement.executeUpdate("INSERT INTO MESSAGEPROCESSING VALUES(" + messageProcessingId + ", " +
                chatId + ", " + messageId + ")");
        statement.close();
        response.sendRedirect("/chatapp/adaptchat");
    }
}
