package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.common.BaseEntity;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reward;

    private LocalDate deadLine;

    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @Setter
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionsList = new ArrayList<>();


    @Builder
    private Mission(Integer reward, LocalDate deadLine, String missionSpec){
        this.reward = reward;
        this.deadLine = deadLine;
        this.missionSpec = missionSpec;
    }

    public void addMemberMission(MemberMission memberMission){
        memberMissionsList.add(memberMission);
        memberMission.setMission(this);
    }

}
