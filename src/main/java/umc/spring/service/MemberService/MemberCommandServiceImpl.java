package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.config.security.jwt.TokenType;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.converter.TokenConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.token.TokenDto;

import java.util.Collections;
import java.util.List;

import static umc.spring.apiPayload.code.status.ErrorStatus.FOOD_CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        List<FoodCategory> foodCategories = request.getPreferCategory().stream()
                .map(category ->
                    foodCategoryRepository.findById(category)
                            .orElseThrow(() -> new FoodCategoryHandler(FOOD_CATEGORY_NOT_FOUND))
                ).toList();

        List<MemberPrefer> memberPrefers = MemberPreferConverter.toMemberPreferList(foodCategories);
        newMember.addMemberPrefers(memberPrefers);
        return memberRepository.save(newMember);
    }

    @Override
    public TokenDto.TokenResponseDto loginMember(MemberRequestDTO.LoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication, TokenType.ACCESS);
        String refreshToken = jwtTokenProvider.generateToken(authentication, TokenType.REFRESH);

        member.getRefreshToken().setRefreshValue(refreshToken);

        return TokenConverter.toTokenResultDTO(
                member.getId(),
                accessToken,
                refreshToken
        );
    }

    // Refresh Token을 가지고 Access Token 발급
    @Transactional(readOnly = true)
    @Override
    public TokenDto.AccessTokenDto reissueAccessToken(String refreshToken){
        if (!jwtTokenProvider.validateToken(refreshToken, TokenType.REFRESH)){
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }

        String email = jwtTokenProvider.parseClaims(refreshToken).getSubject();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if (!isEqualWithSavedToken(member, refreshToken)){
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);

        return TokenConverter.toAccessTokenDto(jwtTokenProvider.generateToken(authentication, TokenType.ACCESS));
    }

    private boolean isEqualWithSavedToken(Member member, String refreshTokenValue){
        String actualValue = member.getRefreshToken()
                                .getRefreshValue();
        return refreshTokenValue.equals(actualValue);
    }
}
