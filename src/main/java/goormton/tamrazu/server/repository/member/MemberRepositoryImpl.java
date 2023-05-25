package goormton.tamrazu.server.repository.member;

import static goormton.tamrazu.server.domain.QAlcohol.*;
import static goormton.tamrazu.server.domain.QHistory.*;
import static goormton.tamrazu.server.domain.QMember.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import goormton.tamrazu.server.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Member> getMemberFetchJoinAlcohols(Long memberId) {
		return queryFactory
			.select(member)
			.from(member)
			.leftJoin(member.histories, history)
			.fetchJoin()
			.leftJoin(history.alcohol, alcohol)
			.fetchJoin()
			.distinct()
			.where(member.id.eq(memberId))
			.stream().findFirst();
	}
}
