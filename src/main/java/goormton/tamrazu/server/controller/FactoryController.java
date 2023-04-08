package goormton.tamrazu.server.controller;

import static java.util.Objects.*;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.factory.FactoryDetailResponseDto;
import goormton.tamrazu.server.dto.factory.FactoryResponseDto;
import goormton.tamrazu.server.service.FactoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factories")
public class FactoryController {

	private final FactoryService factoryService;

	@ApiOperation(value = "양조장 전체 조회")
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "인가인증 실패"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping
	public ResponseEntity<ApiResponse> getAllAlcohols(Principal principal) {
		List<FactoryResponseDto> response = factoryService.getAllFactories(getMemberId(principal));
		return ResponseEntity.ok(ApiResponse.success("양조장 전체 조회 성공", response));
	}

	@ApiOperation(value = "양조장 단일 조회")
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "인가인증 실패"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{factoryId}")
	public ResponseEntity<ApiResponse> getAlcoholsOfFactory(
		Principal principal, @PathVariable("factoryId") Long factoryId) {

		FactoryDetailResponseDto response = factoryService.getAlcoholsOfFactory(factoryId, getMemberId(principal));
		return ResponseEntity.ok(ApiResponse.success("양조장 단일 조회 성공", response));
	}

	private Long getMemberId(Principal principal) {
		return nonNull(principal) ? Long.parseLong(principal.getName()) : null;
	}
}
