package goormton.tamrazu.server.dto.alcohol;

import goormton.tamrazu.server.domain.Alcohol;

public record AlcoholResponseDto(
	Long alcoholId,
	String name,
	String imageUrl,
	String address,
	int ateCount) {

	public static AlcoholResponseDto of(Alcohol alcohol) {
		return new AlcoholResponseDto(
			alcohol.getId(),
			alcohol.getName(),
			alcohol.getImageUrl(),
			alcohol.getFactory().getAddress(),
			alcohol.getAteCount());
	}
}
