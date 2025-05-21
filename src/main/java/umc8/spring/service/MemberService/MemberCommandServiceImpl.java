package umc8.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8.spring.domain.Member;
import umc8.spring.repository.MemberRepository;
import umc8.spring.web.dto.MemberRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    @Override
    public Member joinMember(MemberRequestDTO.joinDTO request) {

        return null;
    }
}
