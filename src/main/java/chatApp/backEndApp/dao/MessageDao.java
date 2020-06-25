package chatApp.backEndApp.dao;

import chatApp.backEndApp.entities.Message;

import java.util.List;

public interface MessageDao {
    List<Message> searchMessageByChatId(String chatId);
    void insertMessage(Long userId, String text);
    Long selectLastMessage(Long userId, String text);
}
