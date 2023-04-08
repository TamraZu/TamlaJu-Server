package goormton.tamrazu.server.dto.factory;

import java.util.List;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Factory;

public record FactoryDetailResponseDto(
	Long factoryId,
	String name,
	String address,
	List<AlcoholVo> alcohols) {

	public static FactoryDetailResponseDto of(Factory factory, List<Alcohol> histories) {
		return new FactoryDetailResponseDto(
			factory.getId(),
			factory.getName(),
			factory.getAddress(),
			FactoryDetailResponseDto.getAlcohols(factory.getAlcohols(), histories));
	}

	private static List<AlcoholVo> getAlcohols(List<Alcohol> alcohols, List<Alcohol> histories) {
		return alcohols.stream().map(alcohol -> AlcoholVo.of(alcohol, histories)).toList();
	}
}

record AlcoholVo(
	Long alcoholId,
	String imageUrl,
	String name,
	boolean hasAte
) {
	static AlcoholVo of(Alcohol alcohol, List<Alcohol> histories) {
		return new AlcoholVo(
			alcohol.getId(),
			alcohol.getImageUrl(),
			alcohol.getName(),
			histories.contains(alcohol));
	}
}