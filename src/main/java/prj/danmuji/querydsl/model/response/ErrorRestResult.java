package prj.danmuji.querydsl.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Rest 에러 데이터 항목
 */
@Data
public class ErrorRestResult {
    @JsonProperty("err_msg")
    private String errMsg;

    @JsonProperty("err_code")
    private String errCode;

    @Builder
    public ErrorRestResult(String errMsg, String errCode) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }
}
