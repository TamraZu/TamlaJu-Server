package goormton.tamrazu.server.dto;

import goormton.tamrazu.server.domain.Member;

public record MemberRequestDto(
	String username,
	String password) {

}
