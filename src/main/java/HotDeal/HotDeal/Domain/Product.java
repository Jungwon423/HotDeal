package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    @Size(min = 2, max = 20, message = "길이는 2~20로")
    private String name;
    @PositiveOrZero
    private Double price;
    private String currency;
    @PositiveOrZero
    private Double discountRate;
    private String imageUrl;
    private String categoryName;
    private String marketName;
    private String link;
    private Double tax;
    private Double shippingFee;
    private int clickCount;
}
