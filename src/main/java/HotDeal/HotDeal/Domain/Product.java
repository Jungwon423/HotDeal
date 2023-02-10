package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.PositiveOrZero;
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
    private int clickCount = 0;
    private String locale;
    private Double naverPrice;
    private Double rating;
    private Double direct_tax = -1d;
    private Double direct_shippingFee = -1d;
    private Double discountRate;
    private Double indirect_tax = -1d;
    private Double indirect_shippingFee = -1d;
    private int review;
    private List<String> good = new ArrayList<>();   //리스트의 경우 선언 안해주면 null값이 참조됨.
    private List<String> bad = new ArrayList<>();
    private List<String> wishUserList = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<String> subImageUrl = new ArrayList<>();
}
