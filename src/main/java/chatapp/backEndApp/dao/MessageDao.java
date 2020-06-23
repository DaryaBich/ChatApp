package chatapp.backEndApp.dao;

import chatapp.backEndApp.entities.Message;

import java.util.List;

public interface MessageDao {
    List<Message> searchMessageByChatId(String chatId);
    void insertMessage(Long userId, String text);
    Long selectLastMessage(Long userId, String text);
}
