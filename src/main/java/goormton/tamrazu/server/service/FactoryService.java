package goormton.tamrazu.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
}
