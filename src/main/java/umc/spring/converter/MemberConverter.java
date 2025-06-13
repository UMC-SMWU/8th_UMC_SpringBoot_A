package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.token.TokenDto;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO (Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember (MemberRequestDTO.JoinDto request) {
        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .build();
    }


    public static MemberResponseDTO.MemberInfoDTO toMemberInfoDTO(Member member){
        return MemberResponseDTO.MemberInfoDTO.builder()
                .email(member.getEmail())
                .name(member.getName())
                .gender(member.getGender().name())
                .build();
    }

    public static Member toMemberFromGoogle(MemberResponseDTO.GoogleMemberInfoDto requestDto){
        return Member.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .socialType(SocialType.GOOGLE)
                .build();
    }

    public static Member toMemberFromKakao(MemberResponseDTO.KakaoMemberInfoDto requestDto){
        return Member.builder()
                .email(requestDto.getKakaoAccount().getEmail())
                .socialType(SocialType.KAKAO)
                .build();
    }

}
