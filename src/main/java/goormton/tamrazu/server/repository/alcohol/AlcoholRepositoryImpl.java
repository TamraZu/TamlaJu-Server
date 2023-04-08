package goormton.tamrazu.server.repository.alcohol;

import static goormton.tamrazu.server.domain.QAlcohol.*;
import static goormton.tamrazu.server.domain.QHistory.*;
import static java.util.Objects.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AlcoholRepositoryImpl implements AlcoholCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Alcohol> getAlcoholsMemberHistory(Member member) {
		return queryFactory
			.select(alcohol)
			.from(alcohol)
			.leftJoin(alcohol.histories, history)
			.where(history.member.eq(member))
			.fetch();
	}

	@Override
	public List<Alcohol> getAllByCategory(Category category) {
		return queryFactory
			.selectFrom(alcohol)
			.where(categoryEq(category))
			.fetch();
	}

	private BooleanExpression categoryEq(Category category) {
		return nonNull(category) ? alcohol.category.eq(category) : null;
	}
}
