package HotDeal.HotDeal.Domain;

import HotDeal.HotDeal.Dto.GoodDto;
import HotDeal.HotDeal.Dto.WishListDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "user")
@NotNull
public class User {
    @Id
    private String id;
    private String nickname;
    private String email;
    private String password;
    private String phoneNumber;
    private List<Comment> comments = new ArrayList<>();
    private List<GoodDto> goods = new ArrayList<>();
    private List<WishListDto> wishLists = new ArrayList<>();
}
