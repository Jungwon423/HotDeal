package HotDeal.HotDeal.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),
    WRONG_PASSWORD(400, "패스워드가 틀렸습니다."),

    //401 UNAUTHORIZED 권한없음
    UNAUTHORIZED_LOGIN(401,"로그인되지 않은 유저입니다."),

    //404 NOT_FOUND 잘못된 리소스 접근
    ID_NOT_FOUND(404, "존재하지 않는 ID입니다"),
    LIST_IS_EMPTY(404, "리스트 값이 비어있습니다."),
    LIST_IS_NULL(404, "리스트가 null값 입니다.(어떤 주소값도 참조 X)"),

    PRODUCT_NOT_FOUND(404, "존재하지 않는 제품입니다"),
    CATEGORY_IS_NULL(404, "카테고리가 null값 입니다. (Category Map에 없음.)"),
    CATEGORY_NOT_FOUND(404, "존재하지 않는 카테고리입니다."),
    OBJECT_IS_NULL(404, "해당 객체가 null값을 가집니다."),

    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_Category(409, "중복된 카테고리 입니다."),
    DUPLICATE_ID(409, "중복된 ID 값 입니다."),
    DUPLICATE_NICKNAME(409, "중복된 닉네임입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!");

    private final int status;
    private final String message;

    /*  4.1. 200 OK
        4.2. 201 Created
        4.3. 202 Accepted
        4.4. 204 No Content

        4XX Client errors
        5.1. 400 Bad Request
        5.2. 401 Unauthorized
        5.3. 403 Forbidden
        5.4. 404 Not Found
        5.5. 405 Method Not Allowed
        5.6. 409 Conflict
        5.7. 429 Too many Requests
    */
}
