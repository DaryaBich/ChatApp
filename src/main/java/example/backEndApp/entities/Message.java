package example.backEndApp.entities;

import java.sql.Timestamp;

public class Message {
    long id;
    long sentByUserWithId;
    String text;
    Timestamp sendingDate;

    public Message(long id, long sentByUserWithId, String text, Timestamp sendingDate) {
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

    public Timestamp getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Timestamp sendingDate) {
        this.sendingDate = sendingDate;
    }
}
