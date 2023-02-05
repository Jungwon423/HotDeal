package HotDeal.HotDeal.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e) {
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(CustomException2.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException2 e, String customString) {
        return ErrorResponseEntity.toResponseEntity2(e.getErrorCode(),customString);
    }
/*
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity handleServerException(Exception ex) {
        return new ResponseEntity(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
 */
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Map<String,Object>> ProductNotFoundException() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorMessage",ErrorCode.PRODUCT_NOT_FOUND.getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(ErrorCode.PRODUCT_NOT_FOUND.getStatus());
        return ResponseEntity.status(httpStatus).body(errorMap);
    }
    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<Map<String,Object>> CategoryNotFoundException() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorMessage",ErrorCode.CATEGORY_NOT_FOUND.getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(ErrorCode.CATEGORY_NOT_FOUND.getStatus());
        return ResponseEntity.status(httpStatus).body(errorMap);
    }
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String,Object>> UserNotFoundException() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorMessage",ErrorCode.USER_NOT_FOUND.getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(ErrorCode.USER_NOT_FOUND.getStatus());
        return ResponseEntity.status(httpStatus).body(errorMap);
    }
    @ExceptionHandler(CommentNotFound.class)
    public ResponseEntity<Map<String,Object>> CommentNotFoundException() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorMessage",ErrorCode.COMMENT_NOT_FOUND.getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(ErrorCode.COMMENT_NOT_FOUND.getStatus());
        return ResponseEntity.status(httpStatus).body(errorMap);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        return "IllegalArgumentException Occurred";
    }
}
