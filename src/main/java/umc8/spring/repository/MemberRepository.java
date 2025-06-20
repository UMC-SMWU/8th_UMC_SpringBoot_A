package umc8.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8.spring.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
