package umc.spring.repository.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMember;
import umc.spring.web.dto.member.MemberInfoDto;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QMember member = QMember.member;

    @Override
    public MemberInfoDto findMemberInfoById(Long memberId) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        MemberInfoDto.class,
                        member.id,
                        member.name,
                        member.email,
                        member.phoneNum,
                        member.point
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
