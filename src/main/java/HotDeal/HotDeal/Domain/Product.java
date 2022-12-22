package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String name;
    private Double price;
    private Double discountRate;
    private String imageUrl;
    private String categoryName;
    private String link;
    private int click;
}
