package chatapp.backEndApp.dao;

import chatapp.backEndApp.entities.User;
import java.util.List;

public interface UserDao {
    boolean addUser(String name, String login, String password);
    boolean searchUserByName(String name);
    List<User> searchUserByLoginAndPassword(String login, String password);
    List<User> searchUserById(Long id);
    List<User> searchUserByChatId(String chatId);
}
