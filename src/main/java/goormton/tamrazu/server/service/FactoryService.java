package goormton.tamrazu.server.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Factory;
import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.dto.factory.FactoryDetailResponseDto;
import goormton.tamrazu.server.dto.factory.FactoryResponseDto;
import goormton.tamrazu.server.repository.EatRepository;
import goormton.tamrazu.server.repository.FactoryRepository;
import goormton.tamrazu.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FactoryService {

	private final FactoryRepository factoryRepository;
	private final MemberRepository memberRepository;
	private final EatRepository eatRepository;

	public List<FactoryResponseDto> getAllFactories(Long memberId) {

		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

		return factoryRepository.findAll().stream().map(factory -> getFactory(factory, member)).toList();
	}

	public FactoryDetailResponseDto getAlcoholsOfFactory(Long factoryId) {
		Factory factory = factoryRepository.findById(factoryId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 양조장입니다."));
		return FactoryDetailResponseDto.of(factory);
	}

	private FactoryResponseDto getFactory(Factory factory, Member member) {
		return new FactoryResponseDto(
			factory.getId(),
			factory.getLatitude(),
			factory.getLongitude(),
			factory.getAddress(),
			hasAte(factory, member));
	}

	private boolean hasAte(Factory factory, Member member) {
		for (Alcohol alcohol : factory.getAlcohols()) {
			if (eatRepository.existsByMemberAndAlcohol(member, alcohol)) {
				return true;
			}
		}
		return false;
	}
}
