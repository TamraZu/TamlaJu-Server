package goormton.tamrazu.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.History;
import goormton.tamrazu.server.domain.Member;

public interface HistoryRepository extends JpaRepository<History, Long> {
	boolean existsByMemberAndAlcohol(Member member, Alcohol alcohol);
	void deleteByMemberAndAlcohol(Member member, Alcohol alcohol);
	Optional<History> findByMemberAndAlcohol(Member member, Alcohol alcohol);
}
