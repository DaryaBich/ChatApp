package chatapp.backEndApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MessageProcessingDaoImpl implements MessageProcessingDao {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void insertMessageProcessing(Long chatId, Long messageId) {
        jdbcTemplate.update("INSERT INTO MESSAGEPROCESSING(idchat, idmessage) VALUES(?, ?)", chatId, messageId);
    }
}
