package goormton.tamrazu.server.repository.factory;

import static goormton.tamrazu.server.domain.QAlcohol.*;
import static goormton.tamrazu.server.domain.QFactory.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import goormton.tamrazu.server.domain.Factory;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FactoryRepositoryImpl implements FactoryCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Factory> getFactoryFetchJoinAlcohols(Long factoryId) {
		return queryFactory
			.select(factory)
			.from(factory)
			.leftJoin(factory.alcohols, alcohol)
			.fetchJoin()
			.distinct()
			.where(factory.id.eq(factoryId))
			.stream().findFirst();
	}
}
