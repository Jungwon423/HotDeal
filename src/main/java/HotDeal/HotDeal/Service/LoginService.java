package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Dto.GoogleUserDto;
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
        String jwtToken = JwtUtils.generateJwtToken(user);
        responseJson.put("token", jwtToken);
        responseJson.put("resultMessage", resultMessage);
        return responseJson;
    }

    private User joinWithNaver(NaverUserDto naverUser) { // TODO
        User user = new User();
        user.setId("Naver_" + naverUser.getAccountId());
        if (naverUser.getNickname()==null){
            user.setNickname(generateRandomNickname()); //랜덤 닉네임
        }
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
        String jwtToken = JwtUtils.generateJwtToken(user);
        responseJson.put("token", jwtToken);
        responseJson.put("resultMessage", resultMessage);
        return responseJson;
    }

    private User joinWithGoogle(GoogleUserDto googleUser) { // TODO
        User user = new User();
        user.setId("Google_" + googleUser.getAccountId());
        user.setNickname(generateRandomNickname()); //랜덤 닉네임
        user.setEmail(googleUser.getEmail());
        user.setPassword("*");
        user.setPhoneNumber("*");
        //user.setImageUrl(googleUser.getImageURl());
        userRepository.save(user);
//        alimTalkService.sendWelcomeMessage(user.getId(), user.getNickname());
        return user;
    }

    private String generateRandomNickname() {
        List<String> prefixPool = Arrays.asList("배고픈", "누추한", "냄새나는", "배부른", "심심한", "대담한", "멋있는", "격렬한", "운좋은", "단단한", "부드러운", "무심한", "아차싶은", "잘생긴","재밌는");
        List<String> suffixPool = Arrays.asList("발바닥", "손바닥", "방바닥", "길바닥", "고양이", "강아지", "하마", "사다리", "오징어", "학교", "배꼽", "얼굴", "언덕", "섬유유연제", "장학금","자명종","시계추");

        Random random = new Random();
        String nickname = prefixPool.get(random.nextInt(prefixPool.size()))
                + " " + suffixPool.get(random.nextInt(suffixPool.size()));
        return nickname;

    }
}
