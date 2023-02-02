package HotDeal.HotDeal.Dto;

import HotDeal.HotDeal.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class UserDetailDto {
    @Id
    private String id;
    private Map<String, Integer> categoryCount;
    private Map<String, Integer> productCount;

    public static UserDetailDto from(User user) {
        return UserDetailDto.builder()
                .id(user.getId())
                .categoryCount(user.getCategoryCount())
                .productCount(user.getProductCount())
                .build();
    }
}