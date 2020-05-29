package example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

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
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MESSAGE(sentByUserWithId, " +
                "text, sendingDate) VALUES(?, ?, NOW())");
        preparedStatement.setLong(1, userId);
        preparedStatement.setString(2, text);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("select max(id) from message where sentByUserWithId = ? " +
                "and text = ?");
        preparedStatement.setLong(1, userId);
        preparedStatement.setString(2, text);
        ResultSet lastMessageId = preparedStatement.executeQuery();
        long messageId;
        if (lastMessageId.next()) {
            messageId = lastMessageId.getLong("max(id)");
        } else {
            messageId = 1;
        }
        preparedStatement = connection.prepareStatement("INSERT INTO MESSAGEPROCESSING(idchat, idmessage) " +
                "VALUES(?, ?)");
        preparedStatement.setLong(1, chatId);
        preparedStatement.setLong(2, messageId);
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
