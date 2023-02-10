package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Exception.Validator;
import HotDeal.HotDeal.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/{userId}/detail")
    public ResponseEntity<Map<String, Object>> getUserDetail(HttpServletRequest request, @PathVariable("userId") String userId) {
        String loginId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(loginId);
        return adminService.getUserDetail(userId);
    }

    @GetMapping("/checkAdmin")
    public ResponseEntity<Map<String, Object>> CheckAdminUser(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return adminService.handleAdminUserCheckRequest(userId);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getUsers() {
        //public ResponseEntity<Map<String, Object>> getUsers(HttpServletRequest request) {
        //String loginId = (String) request.getAttribute("userId");
        //Validator.checkIfLogin(loginId);
        return adminService.getUsers();
    }
}
