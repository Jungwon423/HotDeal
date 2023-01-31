package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Exception.Validator;
import HotDeal.HotDeal.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        return userService.userLogin(user);
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getUserProfile(HttpServletRequest request) {
        //String userId = (String) request.getAttribute("userId");   
        String userId = "test";    //테스트용

        Validator.checkIfLogin(userId);
        return userService.getUserProfileById(userId);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<Map<String, Object>> getUserWishlist(HttpServletRequest request) {
        //String userId = (String) request.getAttribute("userId");
        String userId="test";
        //String userId = "kakao_2639335658"; //테스트용2
        Validator.checkIfLogin(userId);
        return userService.getUserWishlistsById(userId);
    }
}
