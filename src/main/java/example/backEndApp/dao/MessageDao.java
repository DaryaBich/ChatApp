package example.backEndApp.dao;

import example.backEndApp.entities.Message;

public interface MessageDao {
    // добавление сообщения
    boolean addMessage(Message message);

    // получение макс id сообщения
    long getMaxMessageId();
}
