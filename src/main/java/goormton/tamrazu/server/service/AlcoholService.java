package goormton.tamrazu.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import goormton.tamrazu.server.dto.alcohol.AlcoholResponseDto;
import goormton.tamrazu.server.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlcoholService {

	private final AlcoholRepository alcoholRepository;

	public List<AlcoholResponseDto> getAlcoholsByRank() {
		return alcoholRepository
			.findTop3ByOrderByAteCountDesc()
			.stream().map(AlcoholResponseDto::of).toList();
	}
}
