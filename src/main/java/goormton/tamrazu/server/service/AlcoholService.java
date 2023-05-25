package goormton.tamrazu.server.service;

import static goormton.tamrazu.server.common.Util.*;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.dto.alcohol.AlcoholDetailResponseDto;
import goormton.tamrazu.server.dto.alcohol.AlcoholRankResponseDto;
import goormton.tamrazu.server.dto.alcohol.AlcoholResponseDto;
import goormton.tamrazu.server.repository.alcohol.AlcoholRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlcoholService {

	private final AlcoholRepository alcoholRepository;

	public List<AlcoholRankResponseDto> getAlcoholsByRank() {
		return alcoholRepository
			.findTop3ByOrderByAteCountDesc()
			.stream().map(AlcoholRankResponseDto::of).toList();
	}

	public List<AlcoholResponseDto> getAlcohols(Long memberId, Category category) {
		List<Alcohol> histories = getAlcoholsOfMember(alcoholRepository, memberId);
		return alcoholRepository.getAllByCategory(category).stream()
			.map(alcohol -> getAlcoholDto(alcohol, histories))
			.toList();
	}

	public AlcoholDetailResponseDto getAlcoholDetail(Long alcoholId, Long memberId) {
		Alcohol alcohol = alcoholRepository.findById(alcoholId)
			.orElseThrow(() -> new EntityNotFoundException("해당 전통주가 존재하지 않습니다."));
		List<Alcohol> histories = getAlcoholsOfMember(alcoholRepository, memberId);
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
}
