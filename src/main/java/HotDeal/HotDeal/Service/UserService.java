package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Comment;
import HotDeal.HotDeal.Dto.GoodDto;
import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Dto.WishListDto;
import HotDeal.HotDeal.Exception.*;
import HotDeal.HotDeal.Repository.UserRepository;
import HotDeal.HotDeal.Util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger2 = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public ResponseEntity<Map<String, Object>> userRegister(User user) {
        Map<String, Object> responseJson = new HashMap<>();
        validateDuplicate(user.getId(), user.getNickname());
        userRepository.save(user);
        responseJson.put("result", user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> userLogin(User user) {  //user에는 id,password 밖에 없다.
        Map<String, Object> responseJson = new HashMap<>();
        User savedUser = userRepository.findById(user.getId())
                .orElseThrow(IdNotFoundException::new);
        validatePassword(user, savedUser);

        String jwtToken = JwtUtils.generateJwtToken(user);
        responseJson.put("token", jwtToken);
        String resultMessage = String.format("User with id '%s' joined with default login\n", savedUser.getId());
        responseJson.put("resultMessage", resultMessage);
        responseJson.put("객체", savedUser);

        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getUserProfileById(String userId) {
        //test를 위해 임의로 객체 값들을 임시로 넣어놓은 상태이다.
        Map<String, Object> userProfile = new HashMap<>();

        User tempUser = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        userProfile.put("nickname", tempUser.getNickname());
        userProfile.put("email", tempUser.getEmail());

        List<Comment> comments = tempUser.getComments();
        List<GoodDto> goods = tempUser.getGoods();

        userProfile.put("recommends", goods);
        userProfile.put("comments", comments);
        return ResponseEntity.status(HttpStatus.OK).body(userProfile);
    }

    public ResponseEntity<Map<String, Object>> getUserWishlistsById(String userId) {
        //test를 위해 임의로 객체 값들을 임시로 넣어놓은 상태이다.
        Map<String, Object> userWishlists = new HashMap<>();

        User tempUser = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        List<WishListDto> wishLists = tempUser.getWishLists();
        userWishlists.put("찜목록", wishLists);

        return ResponseEntity.status(HttpStatus.OK).body(userWishlists);
    }

    private void validateDuplicate(String id, String nickname) {
        if (userRepository.existsById(id)) {
            throw new CustomException(ErrorCode.DUPLICATE_ID);
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }

    private void validatePassword(User user, User tempUser) {
        if (!tempUser.getPassword().equals(user.getPassword())) {
            logger2.info("비밀번호는 " + tempUser.getPassword());
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }
    }
}
