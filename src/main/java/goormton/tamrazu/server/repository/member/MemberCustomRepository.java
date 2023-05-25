package goormton.tamrazu.server.repository.member;

import java.util.Optional;

import goormton.tamrazu.server.domain.Member;

public interface MemberCustomRepository {
	Optional<Member> getMemberFetchJoinAlcohols(Long memberId);
}
