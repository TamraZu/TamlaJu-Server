package goormton.tamrazu.server.dto.alcohol;

public record AlcoholResponseDto(
	Long alcoholId,
	String name,
	String imageUrl,
	Long volume,
	float level,
	Long price,
	int ateCount,
	boolean hasAte) {
}
