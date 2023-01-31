package HotDeal.HotDeal.Domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Good {
    @Id
    @Size(min = 2, max = 20, message = "길이는 2~20로")
    private String name;
    @PositiveOrZero
    private Double price;
    private String imageUrl;
    private String categoryName;
    private String marketName;
    private String link = "link";
    private int clickCount;
    private Double naverPrice;
    private int goodCount;
    private int badCount;
    private int rating;
    private int commentCount;

    public static Good from(Product product) {
        return Good.builder()
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .marketName(product.getMarketName())
                .clickCount(product.getClickCount())
                .naverPrice(product.getNaverPrice())
                .rating(product.getRating())
                //.goodCount(product.getGood().size())
                //.badCount(product.getBad().size())
                .goodCount(0)
                .badCount(0)
                .commentCount(product.getComments().size())
                .build();
    }
}
