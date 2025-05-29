package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByMemberAndStatus(Member member, String missionStatus, PageRequest pageRequest);

    @Query("select mM from MemberMission mM " +
            "where mM.member = :member" +
            " and mM.mission = :mission" +
            " and mM.status = 'CHALLENGING'")
    Page<MemberMission> findChallengingMissions(@Param("member") Member member,
                                        @Param("mission") Mission mission,
                                        PageRequest pageRequest);
}
