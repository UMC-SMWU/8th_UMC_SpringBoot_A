package umc.spring.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNum;
    private Integer point;
}