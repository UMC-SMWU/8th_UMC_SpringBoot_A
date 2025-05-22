package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;

//    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgrees = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPrefer> memberPrefers = new ArrayList<>();

    @Builder
    private Member(String name, String address, String specAddress, Gender gender, String socialType, String status, LocalDate inactiveDate,
                   String email, Integer point){
        this.name = name;
        this.address = address;
        this.specAddress = specAddress;
        this.gender = gender;
//        this.socialType = SocialType.valueOf(socialType);
//        this.status = MemberStatus.valueOf(status);
        this.inactiveDate = inactiveDate;
        this.email = email;
        this.point = point;
    }

    public void addMemberPrefer(MemberPrefer memberPrefer){
        memberPrefer.setMember(this);
        this.memberPrefers.add(memberPrefer);
    }

    public void addMemberPrefers(List<MemberPrefer> memberPrefers){
        for (MemberPrefer memberPrefer : memberPrefers) {
            memberPrefer.setMember(this);
        }
        this.memberPrefers.addAll(memberPrefers);
    }

    public void removeMemberPrefer(MemberPrefer memberPrefer){
        memberPrefer.setMember(null);
        this.memberPrefers.remove(memberPrefer);
    }

    public void addMemberMission(MemberMission memberMission){
        memberMissions.add(memberMission);
        memberMission.setMember(this);
    }

}
