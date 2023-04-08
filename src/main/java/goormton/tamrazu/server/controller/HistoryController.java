package goormton.tamrazu.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.eat.HistoryRequestDto;
import goormton.tamrazu.server.service.HistoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/eats")
public class HistoryController {

	private final HistoryService historyService;

	@ApiOperation(value = "전통주 먹어봤어요")
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "인가인증 실패"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping
	public ResponseEntity<ApiResponse> eatAlcohol(@RequestBody HistoryRequestDto requestDto) {
		boolean hasEat = historyService.eatAlcohol(requestDto);
		return ResponseEntity.ok(ApiResponse.success(hasEat ? "등록 성공" : "해제 성공"));
	}
}
