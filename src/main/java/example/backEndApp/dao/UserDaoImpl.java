package example.backEndApp.dao;

import example.backEndApp.entities.User;

public class UserDaoImpl implements UserDao{
    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User checkUser(String login, String password) {
        char[] arr = {'a'};
        return new User(1,"name", "password", arr);
    }
}
