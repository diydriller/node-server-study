package spring.jpa.dto;

import lombok.Getter;

public class RegisterDto {

    @Getter
    public static class RequestDto {
        String username;
        Integer age;
    }
}
