package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.GoogleService;
import HotDeal.HotDeal.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final GoogleService googleService;

    @GetMapping("kakao")
    public Map<String, Object> receiveKakaoAuthRequest(@RequestParam String code) {
        return loginService.loginWithKakao(code);
    }

    @GetMapping("naver")
    public Map<String, Object> receiveNaverAuthRequest(@RequestParam String code) {
        return loginService.loginWithNaver(code);
    }
    @GetMapping("google")
    public String receiveGoogleAuthRequest(@RequestParam String code) {
        return googleService.getUserInfo(code);
    }
}
