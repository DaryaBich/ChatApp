package example.backEndApp.dao;

import example.backEndApp.entities.Chat;
import example.backEndApp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserInChatDaoImpl implements UserInChatDao{
    @Override
    public void addUserToChat(Chat chat, User user) {

    }

    @Override
    public boolean removeUserFromChat(Chat chat, User user) {
        return false;
    }

    @Override
    public List<User> getUsersFromChat(Chat chat) {
        return null;
    }

    @Override
    public List<Chat> getUserChats(User user) {
        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat(1, "first"));
        return chats;
    }
}
