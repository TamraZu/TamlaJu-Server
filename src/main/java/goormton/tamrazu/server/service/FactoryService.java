package goormton.tamrazu.server.service;

import static java.util.Objects.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Factory;
import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.dto.factory.FactoryDetailResponseDto;
import goormton.tamrazu.server.dto.factory.FactoryResponseDto;
import goormton.tamrazu.server.repository.FactoryRepository;
import goormton.tamrazu.server.repository.HistoryRepository;
import goormton.tamrazu.server.repository.MemberRepository;
import goormton.tamrazu.server.repository.alcohol.AlcoholRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FactoryService {

	private final FactoryRepository factoryRepository;
	private final MemberRepository memberRepository;
	private final AlcoholRepository alcoholRepository;

	public List<FactoryResponseDto> getAllFactories(Long memberId) {
		List<Alcohol> histories = getMemberHistory(getMember(memberId));
		return factoryRepository.findAll()
			.stream().map(factory -> getFactory(factory, histories))
			.toList();
	}

	public FactoryDetailResponseDto getAlcoholsOfFactory(Long factoryId, Long memberId) {
		Factory factory = factoryRepository.findById(factoryId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 양조장입니다."));
		return FactoryDetailResponseDto.of(factory, getMemberHistory(getMember(memberId)));
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
