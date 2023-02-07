package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "product")
public class Product {

    @Id
    private String name;
    @PositiveOrZero
    private Double price;
    private String currency;
    private String imageUrl;
    private String categoryName;
    private String categoryName2;
    private String marketName;
    private String link;
    private Double tax;
    private Double shippingFee;
    private int clickCount;
    private String locale;
    private Double naverPrice;
    private List<String> good = new ArrayList<>();   //리스트의 경우 선언 안해주면 null값이 참조됨.
    private List<String> bad = new ArrayList<>();
    private Double rating;
    private List<String> wishUserList = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<String> subImageUrl = new ArrayList<>();
}
