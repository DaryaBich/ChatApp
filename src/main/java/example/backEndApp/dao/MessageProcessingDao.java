package example.backEndApp.dao;

import example.backEndApp.entities.Chat;
import example.backEndApp.entities.Message;

import java.util.List;

public interface MessageProcessingDao {
    // отправка сообщения в чат
    void sendMessage(Message message, Chat chat);

    // список сообщений в чате
    List<Message> getMessagesFromChat(Chat chat);
}
