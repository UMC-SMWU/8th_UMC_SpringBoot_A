package umc8.spring.repository.MissionRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import umc8.spring.domain.QMission;
import umc8.spring.domain.QStore;
import umc8.spring.domain.enums.MissionStatus;
import umc8.spring.domain.mapping.QMemberMission;
import umc8.spring.web.dto.MemberMissionDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final Querydsl querydsl;

    @Override
    public Page<MemberMissionDto> findMyMissions(Long memberId, Pageable pageable) {
        QMemberMission mm = QMemberMission.memberMission;
        QMission m = QMission.mission;
        QStore s = QStore.store;

        JPQLQuery<MemberMissionDto> query = jpaQueryFactory
                .select(Projections.constructor(
                        MemberMissionDto.class,
                        mm.id,
                        m.missionSpec,
                        m.reward,
                        s.name,
                        mm.status,
                        mm.updatedAt
                ))
                .from(mm)
                .join(mm.mission, m)
                .join(m.store, s)
                .where(
                        mm.member.id.eq(memberId),
                        mm.status.in(MissionStatus.CHALLENGING, MissionStatus.COMPLETED)
                ).orderBy(mm.updatedAt.desc());
        List<MemberMissionDto> results = querydsl.applyPagination(pageable, query).fetch();
        return new PageImpl<>(results, pageable, query.fetchCount());
    }
}
