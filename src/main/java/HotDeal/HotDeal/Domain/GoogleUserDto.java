package HotDeal.HotDeal.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleUserDto {
    private String accountId;
    private String email;
    private String imageURl;
}
