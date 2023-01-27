package HotDeal.HotDeal.Util;

import HotDeal.HotDeal.Domain.KakaoAccessTokenDto;
import HotDeal.HotDeal.Domain.KakaoUserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class KakaoAuthUtils {
    public static KakaoUserDto getUserInfoByCode(String code) {
        return getUserInfoByAccessToken(getAccessTokenByCode(code));
    }

    private static String getAccessTokenByCode(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", "fc823add0f27c72bac64296b34151966");
        params.add("redirect_url", "https://localhost:8080/login/kakao");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        String kakaoAuthEndpoint = "https://kauth.kakao.com/oauth/token";

        RestTemplate restTemplate = new RestTemplate();
        // access token 받아오기
        ResponseEntity<String> response = restTemplate.postForEntity(kakaoAuthEndpoint, request, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        try {
            return objectMapper.readValue(response.getBody(), KakaoAccessTokenDto.class).getAccessToken();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static KakaoUserDto getUserInfoByAccessToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String kakaoUserInfoEndpoint = "https://kapi.kakao.com/v2/user/me";
        RestTemplate restTemplate = new RestTemplate();
        // access token으로 유저정보 받아오기
        String userInfoString = restTemplate.postForObject(kakaoUserInfoEndpoint, request, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // id와 nickname은 필수, email은 선택
            JsonNode jsonNode = objectMapper.readTree(userInfoString);
            Long id = jsonNode.get("id").asLong();
            String nickname = jsonNode.get("kakao_account").get("profile").get("nickname").asText();
            JsonNode emailNode = jsonNode.get("kakao_account").get("email");
            String email = null;
            if (emailNode != null) {
                email = emailNode.asText();
            }
            JsonNode phoneNumberNode = jsonNode.get("kakao_account").get("phone_number");
            String phoneNumber = null;
            if (phoneNumberNode != null) {
                phoneNumber = phoneNumberNode.asText();
            }
            return new KakaoUserDto(id, nickname, email, phoneNumber);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
