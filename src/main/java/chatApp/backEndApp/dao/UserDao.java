package chatApp.backEndApp.dao;

import chatApp.backEndApp.entities.User;
import java.util.List;

public interface UserDao {
    boolean addUser(String name, String login, String password);
    boolean searchUserByLogin(String login);
    List<User> searchUserByLoginAndPassword(String login, String password);
    List<User> searchUserById(Long id);
    List<User> searchUserByChatId(String chatId);
}
