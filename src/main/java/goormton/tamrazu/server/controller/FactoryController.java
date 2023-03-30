package goormton.tamrazu.server.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.factory.FactoryResponseDto;
import goormton.tamrazu.server.service.FactoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factories")
public class FactoryController {

	private final FactoryService factoryService;

	@GetMapping
	public ResponseEntity<ApiResponse> getAllAlcohols() {
		List<FactoryResponseDto> response = factoryService.getAllFactories();
		return ResponseEntity.ok(ApiResponse.success("양조정 전체 조회 성공", response));
	}
}
