package example.backEndApp.entities;

import lombok.Data;

@Data
public class UserInChat {
    long id;
    long userId;
    long chatId;
}
