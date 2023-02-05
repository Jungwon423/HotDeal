package HotDeal.HotDeal.Exception;

import HotDeal.HotDeal.Domain.User;

import java.util.List;
import java.util.Set;

public class Validator {
    public static void validatePassword(User user, User tempUser) {
        if (!tempUser.getPassword().equals(user.getPassword())) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }
    }
    public static void checkIfLogin(String userId){
        if (userId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_LOGIN);
        }
    }

    public static void validateProductByCategoryId(boolean check){
        if(check){
            throw new CustomException(ErrorCode.CATEGORY_NOT_FOUND);
        }
    }

    public static void validateNullList(List<?> exList) {
        if (exList == null) {
            throw new CustomException(ErrorCode.LIST_IS_NULL);
        }
    }
    public static void validateEmptyList(List<?> exList) {
        if (exList.isEmpty()) {
            throw new CustomException(ErrorCode.LIST_IS_EMPTY);
        }
    }
    public static void validateNullEmptyList(List<?> exList) {
        if (exList == null) {
            throw new CustomException(ErrorCode.LIST_IS_NULL);
        }
        if (exList.isEmpty()) {
            throw new CustomException(ErrorCode.LIST_IS_EMPTY);
        }
    }
    public static void validateNullObject(Object exObject){
        if(exObject == null){
            throw new CustomException(ErrorCode.OBJECT_IS_NULL);
        }
    }
    public static void validateSet(Set<?> exSet){
        if(exSet == null){
            throw new CustomException(ErrorCode.SET_IS_NULL);
        }
        if(exSet.isEmpty()){
            throw new CustomException(ErrorCode.SET_IS_EMPTY);
        }
    }
}
