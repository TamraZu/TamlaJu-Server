package goormton.tamrazu.server.dto.alcohol;

import goormton.tamrazu.server.domain.Alcohol;

public record AlcoholDetailResponseDto(
	Long alcoholId,
	String name,
	String imageUrl,
	Long price,
	String address,
	String region,
	Long volume,
	float level,
	String category,
	String description,
	String tasteImage,
	int ateCount,
	boolean hasAte) {
}
