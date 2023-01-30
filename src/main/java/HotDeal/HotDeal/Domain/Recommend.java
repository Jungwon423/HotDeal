package HotDeal.HotDeal.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
@Data
@Document(collection = "recommend")
public class Recommend {
    @Id
    @Size(min = 2, max = 20, message = "길이는 2~20로")
    private String name = "좋은 상품";         //테스트를 위한 초기값
    @PositiveOrZero
    private Double price = 104d;
    private String imageUrl;
    private String categoryName = "생활/건강";
    private String marketName = "아마존";
    private String link = "link";
    private int clickCount;
    private Double naverPrice;
    private int goodCount;
    private int badCount;
    private int rating;
    private int commentCount;
}
