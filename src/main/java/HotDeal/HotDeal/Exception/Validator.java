package HotDeal.HotDeal.Exception;

import HotDeal.HotDeal.Repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
    public static void checkIfLogin(String userId){
        if (userId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_LOGIN);
        }
    }
    public static void ListIsEmpty(List<?> givenList) {
        /*
        Map<String, Object> responseJson = new HashMap<>();
        if (givenList.isEmpty()) {
            responseJson.put("errorMesssage", ErrorCode.LIST_IS_EMPTY.getMessage());
            //responseJson.put("errorMessage","네이버 쇼핑 검색 결과가 없습니다");
        }  */
        if (givenList.isEmpty()) {
            throw new IllegalArgumentException(ErrorCode.LIST_IS_EMPTY.getMessage());
        }
        //throw new CustomException(ALREADY_SAVED_DISPLAY)
    }
}
