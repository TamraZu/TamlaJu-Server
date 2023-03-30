package goormton.tamrazu.server.dto.alcohol;

import goormton.tamrazu.server.domain.Alcohol;

public record AlcoholRankResponseDto(
	Long alcoholId,
	String name,
	String imageUrl,
	String address,
	int ateCount) {

	public static AlcoholRankResponseDto of(Alcohol alcohol) {
		return new AlcoholRankResponseDto(
			alcohol.getId(),
			alcohol.getName(),
			alcohol.getImageUrl(),
			alcohol.getFactory().getAddress(),
			alcohol.getAteCount());
	}
}
