package goormton.tamrazu.server.repository.factory;

import org.springframework.data.jpa.repository.JpaRepository;

import goormton.tamrazu.server.domain.Factory;

public interface FactoryRepository extends JpaRepository<Factory, Long>, FactoryCustomRepository {
}
