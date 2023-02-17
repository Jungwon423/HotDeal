package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "brand")
public class Brand {
    @Id
    private String id;
    private int type;
    private String brandImage;
    private String content;
    private String link;
}
