package goormton.tamrazu.server.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.alcohol.AlcoholResponseDto;
import goormton.tamrazu.server.service.AlcoholService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alcohols")
public class AlcoholController {

	private final AlcoholService alcoholService;

	@GetMapping
	public ResponseEntity<ApiResponse> getAlcoholsByRank() {
		List<AlcoholResponseDto> response = alcoholService.getAlcoholsByRank();
		return ResponseEntity.ok(ApiResponse.success("전통술 많이 마신 순 조회 성공", response));
	}
}
