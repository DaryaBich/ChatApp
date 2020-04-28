package example.backEndApp.dao;

import example.backEndApp.entities.Chat;

public interface ChatDao {
    // создание чата
    boolean addChat(Chat chat);

    // изменение имени чата
    boolean updateChatName(Chat chat);

    // получение максимального id (для создания нового чата)
    long getMaxChatId(Chat chat);
}
