package chatApp.backEndApp.dao;

import chatApp.backEndApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rS, int i) throws SQLException {
            User user = new User(rS.getLong("id"), rS.getString("name"),
                    rS.getString("login"), rS.getString("password").toCharArray());
            return user;
        }
    };

    @Override
    public boolean addUser(String name, String login, String password) {
        return jdbcTemplate.update("insert into users(name, login, password) values(?, ?, ?)", name, login, password)
                == 1;
    }

    @Override
    public boolean searchUserByLogin(String login) {
        return jdbcTemplate.queryForObject("select count(*) from users where login = ?", new Object[]{login},
                Integer.class) > 0;
    }

    @Override
    public List<User> searchUserByLoginAndPassword(String login, String password) {
        List<User> users = jdbcTemplate.query("select * from users where login = ? and password=?",
                new Object[]{login, password}, userRowMapper);
        return users;
    }

    @Override
    public List<User> searchUserById(Long id) {
        List<User> users = jdbcTemplate.query("select * from users where id = ?", new Long[]{id}, userRowMapper);
        return users;
    }

    @Override
    public List<User> searchUserByChatId(String chatId) {
        List<User> users = jdbcTemplate.query("select * from users where id in (select userId from userInChat " +
                "where chatId = ?)", new String[]{chatId}, userRowMapper);
        return users;
    }

}
