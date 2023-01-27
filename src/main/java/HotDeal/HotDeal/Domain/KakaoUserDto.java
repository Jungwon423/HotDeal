package HotDeal.HotDeal.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoUserDto {

    private Long accountId;
    private String nickname;
    private String email;
    private String phoneNumber;

}