package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MemberStatus;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    List<Member> findByNameAndStatus(String name, MemberStatus status);
    @Query("select m from Member m where m.name = :name AND m.status = :status")
    List<Member> findByNameAndStatus(String name, MemberStatus status);

    Optional<Member> findByEmail(String email);
}
