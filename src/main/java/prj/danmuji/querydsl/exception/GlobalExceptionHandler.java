package prj.danmuji.querydsl.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import prj.danmuji.querydsl.model.response.ErrorRestResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            NoUserException.class,
            RuntimeException.class
    })
    public ResponseEntity<ErrorRestResult> error(NoUserException noUserException) {
        ErrorRestResult errorRestResult = ErrorRestResult.builder()
                .errMsg(noUserException.getMessage())
                .errCode(noUserException.getErrCode())
                .build();
        return new ResponseEntity<>(errorRestResult, noUserException.getStatus());
    }
}
