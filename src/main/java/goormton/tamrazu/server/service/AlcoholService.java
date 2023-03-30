package goormton.tamrazu.server.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.dto.alcohol.AlcoholRankResponseDto;
import goormton.tamrazu.server.dto.alcohol.AlcoholResponseDto;
import goormton.tamrazu.server.repository.AlcoholRepository;
import goormton.tamrazu.server.repository.EatRepository;
import goormton.tamrazu.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlcoholService {

	private final AlcoholRepository alcoholRepository;
	private final EatRepository eatRepository;
	private final MemberRepository memberRepository;

	public List<AlcoholRankResponseDto> getAlcoholsByRank() {
		return alcoholRepository
			.findTop3ByOrderByAteCountDesc()
			.stream().map(AlcoholRankResponseDto::of).toList();
	}

	public List<AlcoholResponseDto> getAlcohols(Long memberId, Category category) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));

		if (Objects.isNull(category)) {
			return alcoholRepository.findAll()
				.stream().map(alcohol -> getAlcoholDto(alcohol, member)).toList();
		} else {
			return alcoholRepository.findByCategory(category)
				.stream().map(alcohol -> getAlcoholDto(alcohol, member)).toList();
		}
	}

	private AlcoholResponseDto getAlcoholDto(Alcohol alcohol, Member member) {
		return new AlcoholResponseDto(
			alcohol.getId(),
			alcohol.getName(),
			alcohol.getImageUrl(),
			alcohol.getVolume(),
			alcohol.getLevel(),
			alcohol.getPrice(),
			alcohol.getAteCount(),
			getHasAte(alcohol, member));
	}

	private boolean getHasAte(Alcohol alcohol, Member member) {
		return eatRepository.existsByMemberAndAlcohol(member, alcohol);
	}
}
