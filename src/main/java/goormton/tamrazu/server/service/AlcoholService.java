package goormton.tamrazu.server.service;

import static java.util.Objects.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.dto.alcohol.AlcoholDetailResponseDto;
import goormton.tamrazu.server.dto.alcohol.AlcoholRankResponseDto;
import goormton.tamrazu.server.dto.alcohol.AlcoholResponseDto;
import goormton.tamrazu.server.repository.alcohol.AlcoholRepository;
import goormton.tamrazu.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlcoholService {

	private final AlcoholRepository alcoholRepository;
	private final MemberRepository memberRepository;

	public List<AlcoholRankResponseDto> getAlcoholsByRank() {
		return alcoholRepository
			.findTop3ByOrderByAteCountDesc()
			.stream().map(AlcoholRankResponseDto::of).toList();
	}

	public List<AlcoholResponseDto> getAlcohols(Long memberId, Category category) {
		List<Alcohol> histories = getMemberHistory(getMember(memberId));
		return alcoholRepository
			.getAllByCategory(category)
			.stream().map(alcohol -> getAlcoholDto(alcohol, histories))
			.toList();
	}

	public AlcoholDetailResponseDto getAlcoholDetail(Long alcoholId, Long memberId) {
		Alcohol alcohol = alcoholRepository.findById(alcoholId)
			.orElseThrow(() -> new EntityNotFoundException("해당 전통주가 존재하지 않습니다."));
		List<Alcohol> histories = getMemberHistory(getMember(memberId));
		return getAlcoholDetailDto(alcohol, histories);
	}

	private AlcoholDetailResponseDto getAlcoholDetailDto(Alcohol alcohol, List<Alcohol> histories) {
		return new AlcoholDetailResponseDto(
			alcohol.getId(),
			alcohol.getName(),
			alcohol.getImageUrl(),
			alcohol.getPrice(),
			alcohol.getFactory().getAddress(),
			alcohol.getRegion(),
			alcohol.getFactory().getName(),
			alcohol.getVolume(),
			alcohol.getLevel(),
			alcohol.getCategory().getName(),
			alcohol.getDescription(),
			alcohol.getTasteImage(),
			alcohol.getAteCount(),
			histories.contains(alcohol));
	}

	private AlcoholResponseDto getAlcoholDto(Alcohol alcohol, List<Alcohol> histories) {
		return new AlcoholResponseDto(
			alcohol.getId(),
			alcohol.getName(),
			alcohol.getImageUrl(),
			alcohol.getVolume(),
			alcohol.getLevel(),
			alcohol.getPrice(),
			alcohol.getAteCount(),
			histories.contains(alcohol));
	}

	private List<Alcohol> getMemberHistory(Member member) {
		return nonNull(member)
			? alcoholRepository.getAlcoholsMemberHistory(member)
			: new ArrayList<>();
	}

	private Member getMember(Long memberId) {
		return memberId != null
			? memberRepository
			.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."))
			: null;
	}
}
