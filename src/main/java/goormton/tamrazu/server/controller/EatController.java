package goormton.tamrazu.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.eat.EatRequestDto;
import goormton.tamrazu.server.service.EatService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/eats")
public class EatController {

	private final EatService eatService;

	@PostMapping
	public ResponseEntity<ApiResponse> eatAlcohol(@RequestBody EatRequestDto eatRequestDto) {
		boolean hasEat = eatService.eatAlcohol(eatRequestDto);
		return ResponseEntity.ok(ApiResponse.success(hasEat ? "등록 성공" : "해제 성공"));
	}
}
