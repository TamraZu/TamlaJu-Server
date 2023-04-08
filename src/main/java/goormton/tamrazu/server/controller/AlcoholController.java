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
import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.dto.alcohol.AlcoholDetailResponseDto;
import goormton.tamrazu.server.dto.alcohol.AlcoholRankResponseDto;
import goormton.tamrazu.server.dto.alcohol.AlcoholResponseDto;
import goormton.tamrazu.server.service.AlcoholService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alcohols")
public class AlcoholController {

	private final AlcoholService alcoholService;

	@ApiOperation(value = "전통주 탑 랭킹 3개 조회")
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "인가인증 실패"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/rank")
	public ResponseEntity<ApiResponse> getAlcoholsByRank() {
		List<AlcoholRankResponseDto> response = alcoholService.getAlcoholsByRank();
		return ResponseEntity.ok(ApiResponse.success("전통술 많이 마신 순 조회 성공", response));
	}

	@ApiOperation(value = "잔통주 전체 조회 성공")
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "인가인증 실패"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping
	public ResponseEntity<ApiResponse> getAlcohols(
		Principal principal, @RequestParam(required = false) Category category) {
		List<AlcoholResponseDto> response = alcoholService.getAlcohols(getMemberId(principal), category);
		return ResponseEntity.ok(ApiResponse.success("전통주 전체 조회 성공", response));
	}

	@ApiOperation(value = "전통주 상세 조회")
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "인가인증 실패"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{alcoholId}")
	public ResponseEntity<ApiResponse> getAlcoholDetail(
		Principal principal, @PathVariable("alcoholId") Long alcoholId) {
		AlcoholDetailResponseDto response = alcoholService.getAlcoholDetail(alcoholId, getMemberId(principal));
		return ResponseEntity.ok(ApiResponse.success("전통주 상세 조회 성공", response));
	}

	private Long getMemberId(Principal principal) {
		return nonNull(principal) ? Long.parseLong(principal.getName()) : null;
	}
}
