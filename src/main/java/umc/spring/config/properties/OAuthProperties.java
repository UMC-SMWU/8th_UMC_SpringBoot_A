package umc.spring.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@ConfigurationProperties("keys")
public class OAuthProperties {
    private Google google;
//    private Kakao kakao;

    @Getter
    public static class Google {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }

//    @Getter
//    public static class Kakao {
//        private String restApiKey;
//        private String clientSecret;
//        private String redirectUri;
//    }

}
