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
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return userService.getUserProfileById(userId);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<Map<String, Object>> getUserWishlist(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return userService.getUserWishlistsById(userId);
    }

    @PutMapping("/nickname")
    public ResponseEntity<Map<String, Object>> changeNickname(HttpServletRequest request, @RequestBody Map<String, String> nickname) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return userService.changeNickname(userId, nickname);
    }
}
