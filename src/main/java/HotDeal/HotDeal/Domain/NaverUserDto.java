package HotDeal.HotDeal.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NaverUserDto {
    private String accountId;
    private String nickname;
    private String email;
    private String phoneNumber;
    //private String name; 받을 수 있음.
}
