package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Exception.Validator;
import HotDeal.HotDeal.Service.AdminService;
import HotDeal.HotDeal.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/{userId}/detail")
    public ResponseEntity<Map<String, Object>> getUserDetail(HttpServletRequest request, @PathVariable("userId") String userId) {
        //String userId = (String) request.getAttribute("userId");
        String id="test";
        Validator.checkIfLogin(id);
        return adminService.getUserDetail(userId);
    }

    @GetMapping("/checkAdmin")
    public ResponseEntity<Map<String, Object>> CheckAdminUser(HttpServletRequest request) {
        //String userId = (String) request.getAttribute("userId");
        String userId="test";
        Validator.checkIfLogin(userId);
        return adminService.handleAdminUserCheckRequest(userId);
    }
}
