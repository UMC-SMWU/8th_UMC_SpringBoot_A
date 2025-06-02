package umc8.spring.converter;

import umc8.spring.domain.Member;
import umc8.spring.domain.enums.Gender;
import umc8.spring.web.dto.request.MemberRequestDTO;
import umc8.spring.web.dto.response.MemberResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponse.JoinResultDto toJoinResultDTO(Member member) {
        return MemberResponse.JoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {

        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> null;
        };

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
