package co.offcampusjobs.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
    private LocalDateTime dateTime;
    private String message;
}
