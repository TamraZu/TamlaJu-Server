package goormton.tamrazu.server.dto.member;

import goormton.tamrazu.server.domain.Member;

public record MemberRequestDto(
	String username,
	String password) {

}
