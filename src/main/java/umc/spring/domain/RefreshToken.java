package umc.spring.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id @GeneratedValue
    private Long id;

    @Setter
    private String refreshToken;

    @Builder
    public RefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
