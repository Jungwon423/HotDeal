package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String nickname;
    private String email;
    private String password;
}
