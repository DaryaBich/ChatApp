package example.backEndApp.dao;

import example.backEndApp.entities.User;

public interface UserDao {
    // добавление пользователя
    boolean addUser(User user);

    // получение максимального id (для создания нового пользователя)
    long getMaxUserId();
}
