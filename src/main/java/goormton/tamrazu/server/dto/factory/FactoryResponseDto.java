package goormton.tamrazu.server.dto.factory;

import goormton.tamrazu.server.domain.Factory;

public record FactoryResponseDto(
	Long factoryId,
	double latitude,
	double longitude,
	String address,
	boolean hasAte) {
}
