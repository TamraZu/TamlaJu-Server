package goormton.tamrazu.server.dto.auth;

public record AuthRequestDTO(
	String email,
	String nickname,
	String imageUrl
) {
}
