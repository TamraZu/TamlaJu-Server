package goormton.tamrazu.server.dto.auth;

public record AuthResponseDTO(
	String email,
	String nickname,
	String accessToken
) {
}
