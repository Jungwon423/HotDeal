package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@Document(collection = "wishlist")
public class WishList {
    @Id
    @Size(min = 2, max = 20, message = "길이는 2~20로")
    private String name = "찜한 상품";         //테스트를 위한 초기값
    @PositiveOrZero
    private Double price = 87000d;
    private String imageUrl;
    private String marketName = "아마존";
    private int clickCount;
    private Double naverPrice;
    private int rating;
    private int commentCount;

}
