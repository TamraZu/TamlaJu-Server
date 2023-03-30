package goormton.tamrazu.server.dto.factory;

import goormton.tamrazu.server.domain.Factory;

public record FactoryResponseDto(
	Long factoryId,
	float latitude,
	float longitude,
	String address,
	boolean hasAte) {
}
