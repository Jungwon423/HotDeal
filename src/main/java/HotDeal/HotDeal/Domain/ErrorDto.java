package HotDeal.HotDeal.Domain;

import lombok.Getter;

@Getter
public class ErrorDto {

    private int status;
    private String message;

    public ErrorDto(int status, String message) {
    }
}