package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Exception.IdNotFoundException;
import HotDeal.HotDeal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public ResponseEntity<Map<String, Object>> handleAdminUserCheckRequest(String userId) {
        Map<String, Object> responseJson = new HashMap<>();
        User user = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        boolean isAdminUser = checkAdminUser(user);

        responseJson.put("isAdminUser", isAdminUser);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public boolean checkAdminUser(User user) {
        List<String> adminUserEmails = Arrays.asList("auskevin@naver.com", "jungwon423@naver.com", "seastark87@gmail.com", "eoruqnfj@naver.com", "sooregi@kakao.com", "duswns094@naver.com", "robo_tronic@kakao.com");
        List<String> adminUserIds = Arrays.asList("test", "kakao_2639335658", "Naver_W138dOP75RAfUcatq8GXSy1wsipgo4AfL9vehSnuLNM", "Google_113720332858503328418", "Google_108382021164431770971", "kakao_2643524224", "Naver_87Os8jX8qWP46YZAdP5z5THotylr2do4W1Z2NrMOWis");
        //return adminUserEmails.contains(user.getEmail());
        return adminUserIds.contains(user.getId());
    }

    public ResponseEntity<Map<String, Object>> getUserDetail(String userId){
        Map<String, Object> responseJson = new HashMap<>();
        User user = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        responseJson.put("categoryCount",user.getCategoryCount());
        responseJson.put("productCount", user.getProductCount());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
}
