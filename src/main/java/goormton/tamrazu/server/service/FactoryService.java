package goormton.tamrazu.server.service;

import static goormton.tamrazu.server.common.Util.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Factory;
import goormton.tamrazu.server.dto.factory.FactoryDetailResponseDto;
import goormton.tamrazu.server.dto.factory.FactoryResponseDto;
import goormton.tamrazu.server.repository.factory.FactoryRepository;
import goormton.tamrazu.server.repository.alcohol.AlcoholRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FactoryService {

	private final FactoryRepository factoryRepository;
	private final AlcoholRepository alcoholRepository;

	public List<FactoryResponseDto> getAllFactories(Long memberId) {
		List<Alcohol> histories = getAlcoholsOfMember(alcoholRepository, memberId);
		return factoryRepository.findAll().stream()
			.map(factory -> getFactory(factory, histories))
			.toList();
	}

	public FactoryDetailResponseDto getAlcoholsOfFactory(Long factoryId, Long memberId) {
		Factory factory = factoryRepository.getFactoryFetchJoinAlcohols(factoryId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 양조장입니다."));
		return FactoryDetailResponseDto.of(factory, getAlcoholsOfMember(alcoholRepository, memberId));
	}

	private FactoryResponseDto getFactory(Factory factory, List<Alcohol> histories) {
		return new FactoryResponseDto(
			factory.getId(),
			factory.getLatitude(),
			factory.getLongitude(),
			factory.getAddress(),
			hasHistory(factory.getAlcohols(), histories));
	}

	private boolean hasHistory(List<Alcohol> alcohols, List<Alcohol> histories) {
		return histories.stream().anyMatch(alcohols::contains);
	}
}
