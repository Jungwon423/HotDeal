package HotDeal.HotDeal.Exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponseEntity {
    private int status;
    private String errorName;
    private String message;
    private String customString;

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponseEntity.builder()
                        .status(e.getStatus())
                        .errorName(e.name())
                        .message(e.getMessage())
                        .build()
                );
    }
    public static ResponseEntity<ErrorResponseEntity> toResponseEntity2(ErrorCode e, String customString){
        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponseEntity.builder()
                        .status(e.getStatus())
                        .errorName(e.name())
                        .message(customString + e.getMessage())
                        .build()
                );
    }
}
