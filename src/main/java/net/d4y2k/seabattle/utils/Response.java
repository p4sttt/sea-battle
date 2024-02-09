package net.d4y2k.seabattle.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class Response {

    private String path;

    private HttpStatus httpStatus;

    private String message;

    private Object data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public Response(String path, HttpStatus httpStatus, String message, Object data) {
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public Response(String path, HttpStatus httpStatus, String message) {
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public Response(String path, HttpStatus httpStatus, Object data) {
        this.path = path;
        this.httpStatus = httpStatus;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
