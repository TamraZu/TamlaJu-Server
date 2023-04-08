package goormton.tamrazu.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.auth.AuthRequestDTO;
import goormton.tamrazu.server.dto.auth.AuthResponseDTO;
import goormton.tamrazu.server.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@ApiOperation(value = "로그인")
	@ApiResponses({
		@io.swagger.annotations.ApiResponse(code = 200, message = "성공"),
		@io.swagger.annotations.ApiResponse(code = 401, message = "인가인증 실패"),
		@io.swagger.annotations.ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping
	public ResponseEntity<ApiResponse> login(@RequestBody final AuthRequestDTO requestDTO) {
		AuthResponseDTO response = authService.login(requestDTO);
		return ResponseEntity.ok(ApiResponse.success("로그인 성공", response));
	}
}
