package chatapp.backEndApp.dao;

public interface MessageProcessingDao {
    void insertMessageProcessing(Long chatId, Long messageId);
}
