package HotDeal.HotDeal.Exception;

import org.json.simple.JSONObject;
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
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Map<String,Object>> IdNotFoundException() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("errorMessage",ErrorCode.ID_NOT_FOUND.getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(ErrorCode.ID_NOT_FOUND.getStatus());
        return ResponseEntity.status(httpStatus).body(errorMap);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        return "IllegalArgumentException Occurred";
    }

    @ExceptionHandler(NotFoundException.class)
    public String NotFoundExceptionAdvice(NotFoundException e) {
        return "NotFoundArgumentException Occurred";
    }
}
