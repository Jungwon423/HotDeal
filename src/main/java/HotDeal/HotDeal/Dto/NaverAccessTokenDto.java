package HotDeal.HotDeal.Dto;

import lombok.Data;

@Data
public class NaverAccessTokenDto {

    private String tokenType;
    private String accessToken;
    private String idToken;
    private int expiresIn;
    private String refreshToken;
    private int refreshTokenExpiresIn;
    private String scope;

}
