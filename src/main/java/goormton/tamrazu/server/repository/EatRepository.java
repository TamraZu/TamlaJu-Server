package goormton.tamrazu.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Eat;
import goormton.tamrazu.server.domain.Member;

public interface EatRepository extends JpaRepository<Eat, Long> {
	boolean existsByMemberAndAlcohol(Member member, Alcohol alcohol);
	void deleteByMemberAndAlcohol(Member member, Alcohol alcohol);
	Optional<Eat> findByMemberAndAlcohol(Member member, Alcohol alcohol);
}
