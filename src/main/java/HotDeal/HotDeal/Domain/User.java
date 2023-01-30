package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String nickname;
    private String email;
    private String password;
    private String phoneNumber;
    private List<Comment> comments;
    private List<String> goods;
    private List<WishList> wishLists;
}
