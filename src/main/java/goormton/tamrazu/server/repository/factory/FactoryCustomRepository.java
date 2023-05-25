package goormton.tamrazu.server.repository.factory;

import java.util.Optional;

import goormton.tamrazu.server.domain.Factory;

public interface FactoryCustomRepository {
	Optional<Factory> getFactoryFetchJoinAlcohols(Long factoryId);
}
