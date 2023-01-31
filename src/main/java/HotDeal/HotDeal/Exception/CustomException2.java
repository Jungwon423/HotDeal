package HotDeal.HotDeal.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException2 extends RuntimeException{
    ErrorCode errorCode;
    String customString;
    /*CustomException(String customMessage) {
        super(customMessage); // RuntimeException 클래스의 생성자를 호출합니다.
    }*/
}
