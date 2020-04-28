package example.backEndApp.dao;

import example.backEndApp.entities.Chat;
import example.backEndApp.entities.User;

import java.util.List;

public interface UserInChatDao {
    // добавление пользователя в чат
    void addUserToChat(Chat chat, User user);

    // удаление пользователя из чата
    boolean removeUserFromChat(Chat chat, User user);

    // получение списка пользователей в чате
    List<User> getUsersFromChat(Chat chat);

    // получение списка чатов пользователя
    List<Chat> getUserChats(User user);
}
