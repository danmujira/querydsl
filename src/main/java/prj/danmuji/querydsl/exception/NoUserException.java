package prj.danmuji.querydsl.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Nullable;

/**
 * 유저 없음 에러 처리
 */
@Getter
public class NoUserException extends ResponseStatusException {
    private final String errCode;

    public NoUserException(HttpStatus status, @Nullable String errMsg) {
        super(status, errMsg);
        this.errCode = null;
    }

    public NoUserException(HttpStatus status, @Nullable String errCode, @Nullable String errMsg) {
        super(status, errMsg);
        this.errCode = errCode;
    }
}
