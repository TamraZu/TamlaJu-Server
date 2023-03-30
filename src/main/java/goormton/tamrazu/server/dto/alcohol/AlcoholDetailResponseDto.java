package goormton.tamrazu.server.dto.alcohol;

public record AlcoholDetailResponseDto(
	Long alcoholId,
	String name,
	String imageUrl,
	Long price,
	String address,
	String region,
	String factory,
	Long volume,
	float level,
	String category,
	String description,
	String tasteImage,
	int ateCount,
	boolean hasAte) {
}
