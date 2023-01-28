package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.GoogleUserDto;
import HotDeal.HotDeal.Dto.KakaoUserDto;
import HotDeal.HotDeal.Dto.NaverUserDto;
import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Repository.UserRepository;
import HotDeal.HotDeal.Util.GoogleAuthUtils;
import HotDeal.HotDeal.Util.JwtUtils;
import HotDeal.HotDeal.Util.KakaoAuthUtils;
import HotDeal.HotDeal.Util.NaverAuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public Map<String, Object> loginWithKakao(String code) {
        Map<String, Object> responseJson = new HashMap<>();

        KakaoUserDto userInfo = KakaoAuthUtils.getUserInfoByCode(code);
        User user;

        String resultMessage = "";

        if (userRepository.findById("kakao_" + userInfo.getAccountId()).isEmpty()) {
            user = joinWithKakao(userInfo);
            resultMessage += String.format("User with id '%s' joined with kakao authentication code.\n", user.getId());
            responseJson.put("isNewUser", true);
        } else {
            user = userRepository.findById("kakao_" + userInfo.getAccountId()).get();
            responseJson.put("isNewUser", false);
        }

        updateLoginInfo(user);
//        resultMessage += String.format("User with id '%s' logged in at timestamp %tF. continuous attendance count: %d\n",
//                user.getId(), user.getLastLoginDate(), user.getContinuousAttendanceCount());

        String jwtToken = JwtUtils.generateJwtToken(user);
        responseJson.put("token", jwtToken);
        responseJson.put("resultMessage", resultMessage);

        return responseJson;
    }

    private User joinWithKakao(KakaoUserDto kakaoUser) { // TODO
        User user = new User();
        user.setId("kakao_" + kakaoUser.getAccountId());
        user.setNickname(kakaoUser.getNickname());
        user.setEmail(kakaoUser.getEmail());
        user.setPassword("*");
        user.setPhoneNumber(kakaoUser.getPhoneNumber());
        userRepository.save(user);
//        alimTalkService.sendWelcomeMessage(user.getId(), user.getNickname());
        return user;
    }

    private void updateLoginInfo(User user) {
        Date currentTimestamp = new Date();
//        user.setLastLoginDate(currentTimestamp);
        userRepository.save(user);
    }

    public Map<String, Object> loginWithNaver(String code) {
        Map<String, Object> responseJson = new HashMap<>();
        NaverUserDto userInfo = NaverAuthUtils.getUserInfoByAccessToken(code);

        User user;
        String resultMessage = "";

        if (userRepository.findById("Naver_" + userInfo.getAccountId()).isEmpty()) {
            user = joinWithNaver(userInfo);
            resultMessage += String.format("User with id '%s' joined with naver authentication code.\n", user.getId());
            responseJson.put("isNewUser", true);
        } else {
            user = userRepository.findById("Naver_" + userInfo.getAccountId()).get();
            responseJson.put("isNewUser", false);
        }

//        updateLoginInfo(user);
//        resultMessage += String.format("User with id '%s' logged in at timestamp %tF. continuous attendance count: %d\n",
//                user.getId(), user.getLastLoginDate(), user.getContinuousAttendanceCount());
        responseJson.put("resultMessage", resultMessage);
        responseJson.put("result", user);

        return responseJson;
    }

    private User joinWithNaver(NaverUserDto naverUser) { // TODO
        User user = new User();
        user.setId("Naver_" + naverUser.getAccountId());
        user.setNickname(naverUser.getNickname());
        user.setEmail(naverUser.getEmail());
        user.setPassword("*");
        user.setPhoneNumber(naverUser.getPhoneNumber());
        userRepository.save(user);
//        alimTalkService.sendWelcomeMessage(user.getId(), user.getNickname());
        return user;
    }

    public Map<String, Object> loginWithGoogle(String code) {
        Map<String, Object> responseJson = new HashMap<>();
        GoogleUserDto userInfo = GoogleAuthUtils.getUserInfoByAccessToken(code);

        User user;
        String resultMessage = "";

        if (userRepository.findById("Google_" + userInfo.getAccountId()).isEmpty()) {
            user = joinWithGoogle(userInfo);
            resultMessage += String.format("User with id '%s' joined with google authentication code.\n", user.getId());
            responseJson.put("isNewUser", true);
        } else {
            user = userRepository.findById("Google_" + userInfo.getAccountId()).get();
            responseJson.put("isNewUser", false);
        }

//        updateLoginInfo(user);
//        resultMessage += String.format("User with id '%s' logged in at timestamp %tF. continuous attendance count: %d\n",
//                user.getId(), user.getLastLoginDate(), user.getContinuousAttendanceCount());
        responseJson.put("resultMessage", resultMessage);
        responseJson.put("result", user);

        return responseJson;
    }

    private User joinWithGoogle(GoogleUserDto googleUser) { // TODO
        User user = new User();
        user.setId("Google_" + googleUser.getAccountId());
        user.setNickname("*");
        user.setEmail(googleUser.getEmail());
        user.setPassword("*");
        user.setPhoneNumber("*");
        //user.setImageUrl(googleUser.getImageURl());
        userRepository.save(user);
//        alimTalkService.sendWelcomeMessage(user.getId(), user.getNickname());
        return user;
    }
}
