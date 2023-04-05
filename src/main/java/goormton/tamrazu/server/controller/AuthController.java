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
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping
	public ResponseEntity<ApiResponse> login(@RequestBody final AuthRequestDTO requestDTO) {
		AuthResponseDTO response = authService.login(requestDTO);
		return ResponseEntity.ok(ApiResponse.success("로그인 성공", response));
	}
}
