package example.backEndApp.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    long id;
    long sentByUserWithId;
    String text;
    Date sendingDate;
}
