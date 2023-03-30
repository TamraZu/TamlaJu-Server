package goormton.tamrazu.server.dto.factory;

import java.util.List;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Factory;

public record FactoryDetailResponseDto(
	Long factoryId,
	String name,
	String address,
	List<AlcoholVo> alcohols) {

	public static FactoryDetailResponseDto of(Factory factory) {
		return new FactoryDetailResponseDto(
			factory.getId(),
			factory.getName(),
			factory.getAddress(),
			FactoryDetailResponseDto.getAlcohols(factory.getAlcohols()));
	}

	private static List<AlcoholVo> getAlcohols(List<Alcohol> alcohols) {
		return alcohols.stream().map(AlcoholVo::of).toList();
	}
}

record AlcoholVo(
	Long alcoholId,
	String imageUrl,
	String name
) {
	static AlcoholVo of(Alcohol alcohol) {
		return new AlcoholVo(alcohol.getId(), alcohol.getImageUrl(), alcohol.getName());
	}
}