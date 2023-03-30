package goormton.tamrazu.server.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import goormton.tamrazu.server.domain.Factory;
import goormton.tamrazu.server.dto.factory.FactoryDetailResponseDto;
import goormton.tamrazu.server.dto.factory.FactoryResponseDto;
import goormton.tamrazu.server.repository.FactoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FactoryService {

	private final FactoryRepository factoryRepository;

	public List<FactoryResponseDto> getAllFactories() {
		return factoryRepository.findAll().stream().map(FactoryResponseDto::of).toList();
	}

	public FactoryDetailResponseDto getAlcoholsOfFactory(Long factoryId) {
		Factory factory = factoryRepository.findById(factoryId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 양조장입니다."));
		return FactoryDetailResponseDto.of(factory);
	}
}
