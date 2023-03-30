package goormton.tamrazu.server.dto.member;

import java.util.List;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Member;

public record MemberPageResponseDto(Long memberId, String nickname, List<MemberAlcoholVo> alcohols) {
	public static MemberPageResponseDto of(Member member) {
		return new MemberPageResponseDto(member.getId(), member.getNickname(),
			member.getAteAlcohols().stream().map(eat -> MemberAlcoholVo.of(eat.getAlcohol())).toList());
	}
}

record MemberAlcoholVo(Long alcoholId, String name, String imageUrl) {
	static MemberAlcoholVo of(Alcohol alcohol) {
		return new MemberAlcoholVo(alcohol.getId(), alcohol.getName(), alcohol.getImageUrl());
	}
}
