package HotDeal.HotDeal.Dto;

import HotDeal.HotDeal.Domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class WishListDto {
    @Id
    @Size(min = 2, max = 20, message = "길이는 2~20로")
    private String name;
    @PositiveOrZero
    private Double price;
    private String imageUrl;
    private String marketName;
    private int clickCount;
    private Double naverPrice;
    private Double rating;
    private int commentCount;

    public static WishListDto from(Product product) {
        return WishListDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .marketName(product.getMarketName())
                .clickCount(product.getClickCount())
                .naverPrice(product.getNaverPrice())
                .rating(product.getRating())
                .commentCount(product.getComments().size())
                .build();
    }
}
