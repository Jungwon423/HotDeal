package HotDeal.HotDeal.Controller;

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

    @GetMapping("kakao")
    public Map<String, Object> receiveKakaoAuthRequest(@RequestParam String code) {
        return loginService.loginWithKakao(code);
    }
}
