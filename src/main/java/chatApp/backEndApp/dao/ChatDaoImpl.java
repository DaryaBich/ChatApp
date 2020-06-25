package chatApp.backEndApp.dao;

import chatApp.backEndApp.entities.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ChatDaoImpl implements ChatDao {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    private RowMapper<Chat> chatRowMapper = new RowMapper<Chat>() {
        @Override
        public Chat mapRow(ResultSet rS, int i) throws SQLException {
            Chat chat = new Chat(rS.getLong("id"), rS.getString("name"));
            return chat;
        }
    };
    @Override
    public List<Chat> searchChatsByUserId(Long userId) {
        List<Chat> chats = jdbcTemplate.query("select * from chat where id in (select chatId from UserInChat " +
                        "where userId = ?)", new Long[]{userId}, chatRowMapper);
        return chats;
    }
}
