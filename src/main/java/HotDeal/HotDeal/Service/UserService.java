package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Repository.UserRepository;
import HotDeal.HotDeal.Util.DuplicateIdException;
import HotDeal.HotDeal.Util.DuplicateNicknameException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger2 = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public ResponseEntity<Map<String,Object>> userRegister(User user){
        Map<String, Object> responseJson = new HashMap<>();
        validateDuplicate(user.getId(),user.getNickname());
        userRepository.save(user);
        responseJson.put("result",user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    private void validateDuplicate(String id, String nickname){
        if(userRepository.existsById(id)){
            throw new DuplicateIdException();
        }
        if(userRepository.existsByNickname(nickname)){
            throw new DuplicateNicknameException();
        }
    }
    public ResponseEntity<Map<String,Object>> userLogin(User user){
        Map<String, Object> responseJson = new HashMap<>();
        User tempUser = userRepository.findById(user.getId())
                .orElseThrow(IllegalArgumentException::new);    //ID 없으면 일단 IllegalArgumentException임.
        if (tempUser.getPassword().equals(user.getPassword())){
            responseJson.put("result",tempUser);
            logger2.info("비밀번호는 " + tempUser.getPassword());
        }
        else{
            responseJson.put("result","패스워드가 다릅니다.");
        }
        responseJson.put("Result",user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
}
