package chatapp.backEndApp.entities;
public class MessageProcessing {
    private long id;
    private long idChat;
    private long idMessage;

    public MessageProcessing(long id, long idChat, long idMessage) {
        this.id = id;
        this.idChat = idChat;
        this.idMessage = idMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdChat() {
        return idChat;
    }

    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }

    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }
}
