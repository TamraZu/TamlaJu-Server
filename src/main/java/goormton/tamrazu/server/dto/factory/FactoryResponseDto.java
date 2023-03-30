package goormton.tamrazu.server.dto.factory;

import goormton.tamrazu.server.domain.Factory;

public record FactoryResponseDto(
	Long factoryId,
	float latitude,
	float longitude,
	String address) {

	public static FactoryResponseDto of(Factory factory) {
		return new FactoryResponseDto(
			factory.getId(),
			factory.getLatitude(),
			factory.getLongitude(),
			factory.getAddress());
	}
}
