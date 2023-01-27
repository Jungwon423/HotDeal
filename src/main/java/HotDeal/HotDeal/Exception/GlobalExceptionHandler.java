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
    @ExceptionHandler(DuplicateIdException.class)
    public JSONObject duplicateIdException(){
        HashMap<String, Object> ErrorMap = new HashMap<>();
        ErrorMap.put("errorCode", ErrorCode.DUPLICATE_ID.getStatus());
        ErrorMap.put("errorMessage", ErrorCode.DUPLICATE_ID.getMessage());
        return new JSONObject(ErrorMap);
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public JSONObject duplicateNicknameException(){
        HashMap<String, Object> ErrorMap = new HashMap<>();
        ErrorMap.put("errorCode", ErrorCode.DUPLICATE_NICKNAME.getStatus());
        ErrorMap.put("errorMessage", ErrorCode.DUPLICATE_NICKNAME.getMessage());
        return new JSONObject(ErrorMap);
    }

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<Map<String,Object>> handleCustomException(CustomException ex) {
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("result",ex.getErrorCode().getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getErrorCode().getStatus());
        //쉬발!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
        return ResponseEntity.status(httpStatus).body(responseJson);
    }
/*
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity handleServerException(Exception ex) {
        return new ResponseEntity(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

 */
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        return "ID 존재하지 않음, IllegalArgumentException Occurred!!!!!!!!!!!!!";
    }
}
