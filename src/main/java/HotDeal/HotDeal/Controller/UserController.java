package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user){
        return userService.userRegister(user);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
        return userService.userLogin(user);
    }
}
