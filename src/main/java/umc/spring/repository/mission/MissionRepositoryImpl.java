package umc.spring.repository.mission;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMember;
import umc.spring.domain.QMission;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QMember member = QMember.member;
    QMemberMission memberMission = QMemberMission.memberMission;
    QMission mission = QMission.mission;
    QStore store = QStore.store;
    QRegion region = QRegion.region;

    @Override
    public List<MissionResponseDto> findOngoingMissions(Long memberId, Long cursor) {

        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionResponseDto.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        memberMission.status
                ))
                .from(member)
                .join(memberMission).on(member.id.eq(memberMission.member.id))
                .join(mission).on(mission.id.eq(memberMission.mission.id))
                .join(store).on(mission.store.id.eq(store.id))
                .where(
                        member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.valueOf("진행중")),
                        mission.id.lt(cursor)
                )
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public List<MissionResponseDto> findCompletedMissions(Long memberId, Long cursor) {

        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionResponseDto.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        memberMission.status
                ))
                .from(member)
                .join(memberMission).on(member.id.eq(memberMission.member.id))
                .join(mission).on(mission.id.eq(memberMission.mission.id))
                .join(store).on(mission.store.id.eq(store.id))
                .where(
                        member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.valueOf("진행완료")),
                        mission.id.lt(cursor)
                )
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }

    @Override
    public List<MissionRequestDto> findAvailableMissionsByRegion(String regionName, Long memberId, Long cursor) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionRequestDto.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        Expressions.numberTemplate(Long.class, "datediff({0}, current_timestamp)", mission.deadline)
                ))
                .from(mission)
                .join(mission.store, store)
                .join(store.region, region)
                .where(
                        region.name.eq(regionName),
                        mission.deadline.gt(LocalDate.from(LocalDateTime.now())),
                        mission.id.lt(cursor),
                        mission.id.notIn(
                                JPAExpressions
                                        .select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(memberId))
                        )
                )
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }
}
