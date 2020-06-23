package chatapp.backEndApp.dao;

import chatapp.backEndApp.entities.Chat;
import chatapp.backEndApp.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    private RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
        @Override
        public Message mapRow(ResultSet rS, int i) throws SQLException {
            Message message = new Message(rS.getLong("id"), rS.getLong("sentByUserWithId"),
                    rS.getString("text"), rS.getTimestamp("sendingDate"));
            return message;
        }
    };

    @Override
    public List<Message> searchMessageByChatId(String chatId) {
        List<Message> messages = jdbcTemplate.query("select * from message where id in (select idMessage from " +
                "messageProcessing where idChat = ?)", new String[]{chatId}, messageRowMapper);
        return messages;
    }

    @Override
    public void insertMessage(Long userId, String text) {
        jdbcTemplate.update("INSERT INTO MESSAGE(sentByUserWithId, text, sendingDate) VALUES(?, ?, NOW())",
                userId, text);
    }

    @Override
    public Long selectLastMessage(Long userId, String text) {
        Long result = jdbcTemplate.queryForObject("select max(id) from message where sentByUserWithId = ? and text = ?",
                new Object[]{userId, text}, Long.class);
        System.out.println(result);
        return result;
    }
}
