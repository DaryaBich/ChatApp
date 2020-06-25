package chatApp.backEndApp.dao;

import chatApp.backEndApp.entities.Chat;

import java.util.List;

public interface ChatDao {
    List<Chat> searchChatsByUserId(Long userId);
}
