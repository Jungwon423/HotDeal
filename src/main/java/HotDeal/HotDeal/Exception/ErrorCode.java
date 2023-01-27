package HotDeal.HotDeal.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),
    DUPLICATE_ID(400, "중복된 ID 값 입니다."),
    DUPLICATE_NICKNAME(400, "중복된 닉네임입니다."),

    //404 NOT_FOUND 잘못된 리소스 접근
    LIST_IS_EMPTY(404, "값이 비어있습니다."),
    PRODUCT_NOT_FOUND(404, "존재하지 않는 제품입니다"),
    CATEGORY_NOT_FOUND(404, "존재하지 않는 카테고리입니다."),

    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_PRODUCT(409, "이미 저장한 제품입니다."),
    ALREADY_SAVED_CATEGORY(409, "이미 저장한 카테고리입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!");

    private final int status;
    private final String message;
}
