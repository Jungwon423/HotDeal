package HotDeal.HotDeal.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
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
        return "IllegalArgumentException Occurred!!!!!!!!!!!!!";
    }
}
