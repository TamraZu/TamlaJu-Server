package goormton.tamrazu.server.dto.member;

import java.util.List;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.History;
import goormton.tamrazu.server.domain.Member;

public record MemberPageResponseDto(
	Long memberId,
	String nickname,
	int count,
	List<MemberAlcohol> alcohols) {
	public static MemberPageResponseDto of(Member member) {
		List<MemberAlcohol> alcohols = getAlcohols(member.getHistories());
		return new MemberPageResponseDto(
			member.getId(),
			member.getNickname(),
			alcohols.size(),
			alcohols);
	}

	private static List<MemberAlcohol> getAlcohols(List<History> histories) {
		return histories.stream()
			.map(history -> MemberAlcohol.of(history.getAlcohol()))
			.toList();
	}
}

record MemberAlcohol(Long alcoholId, String name, String imageUrl) {
	static MemberAlcohol of(Alcohol alcohol) {
		return new MemberAlcohol(alcohol.getId(), alcohol.getName(), alcohol.getImageUrl());
	}
}
