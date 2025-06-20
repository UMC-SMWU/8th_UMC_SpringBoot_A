package umc8.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// yml 설정 파일 값을 불러와서 코드 상의 조작을 해야할 때 설정값을 가져오는 방식
@Component
@Getter
@Setter
@ConfigurationProperties("jwt.token")
public class JwtProperties {

    private String secretKey="";
    private Expiration expiration;

    @Getter
    @Setter
    public static class Expiration {
        private Long access;
        // TODO: refreshToken
    }
}
