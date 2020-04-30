package example.backEndApp.entities;

import java.util.Date;

public class Message {
    long id;
    long sentByUserWithId;
    String text;
    Date sendingDate;

    public Message(long id, long sentByUserWithId, String text, Date sendingDate) {
        this.id = id;
        this.sentByUserWithId = sentByUserWithId;
        this.text = text;
        this.sendingDate = sendingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSentByUserWithId() {
        return sentByUserWithId;
    }

    public void setSentByUserWithId(long sentByUserWithId) {
        this.sentByUserWithId = sentByUserWithId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }
}
