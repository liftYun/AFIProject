package personal.afiproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
} //Bear 인증방식 사용
//Access Token을 HTTP 요청의 Authorization 헤더에 포함하여 전송 방식
