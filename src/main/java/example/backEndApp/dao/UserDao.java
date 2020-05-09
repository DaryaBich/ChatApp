package example.backEndApp.dao;

import example.backEndApp.entities.User;

public interface UserDao {
    // добавление пользователя
    boolean addUser(User user);
    // аутентификация пользователя
    User checkUser(String login, String password);
}
