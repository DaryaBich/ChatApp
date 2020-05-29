package example.web.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

@Controller
@RequestMapping(value = "/chatapp")
public class AuthenticationController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/authentication")
    public ModelAndView logInToAccount(ModelAndView modelAndView) {
        modelAndView.setViewName("/jsp/authentication");
        return modelAndView;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkUser(@RequestParam(value = "login", defaultValue = "", required = false) String login,
                            @RequestParam(value = "password", defaultValue = "", required = false) String password,
                            ModelAndView modelAndView, HttpServletRequest request) throws SQLException{
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where login = ? " +
                "and password=?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet searchedUsers = preparedStatement.executeQuery();
        if (searchedUsers.next()) {
            long userId = searchedUsers.getLong("ID");
            preparedStatement.close();
            request.getSession().setAttribute("userId", userId);
            return "redirect:/user/chatapp/openchats";
        } else {
            preparedStatement.close();
            return "redirect:/chatapp/authentication";
        }
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
