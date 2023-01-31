package HotDeal.HotDeal.Exception;

import java.util.List;

public class Validator {
    public static void checkIfLogin(String userId){
        if (userId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_LOGIN);
        }
    }
    public static void ListIsEmpty(List<?> givenList) {
        //Map<String, Object> responseJson = new HashMap<>();
        if (givenList.isEmpty()) {
            throw new CustomException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
