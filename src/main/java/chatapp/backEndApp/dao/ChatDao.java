package chatapp.backEndApp.dao;

import chatapp.backEndApp.entities.Chat;

import java.util.List;

public interface ChatDao {
    List<Chat> searchChatsByUserId(Long userId);
}
