package goormton.tamrazu.server.dto.member;

import java.util.List;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Member;

public record MemberPageResponseDto(
	Long memberId,
	String nickname,
	int count,
	List<MemberAlcoholVo> alcohols) {
	public static MemberPageResponseDto of(Member member) {

		List<MemberAlcoholVo> alcohols = member.getHistories()
			.stream()
			.map(eat -> MemberAlcoholVo.of(eat.getAlcohol()))
			.toList();

		return new MemberPageResponseDto(
			member.getId(),
			member.getNickname(),
			alcohols.size(),
			alcohols);
	}
}

record MemberAlcoholVo(Long alcoholId, String name, String imageUrl) {
	static MemberAlcoholVo of(Alcohol alcohol) {
		return new MemberAlcoholVo(alcohol.getId(), alcohol.getName(), alcohol.getImageUrl());
	}
}
