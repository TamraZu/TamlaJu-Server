package goormton.tamrazu.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.eat.HistoryRequestDto;
import goormton.tamrazu.server.service.HistoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/eats")
public class HistoryController {

	private final HistoryService historyService;

	@PostMapping
	public ResponseEntity<ApiResponse> eatAlcohol(@RequestBody HistoryRequestDto requestDto) {
		boolean hasEat = historyService.eatAlcohol(requestDto);
		return ResponseEntity.ok(ApiResponse.success(hasEat ? "등록 성공" : "해제 성공"));
	}
}
