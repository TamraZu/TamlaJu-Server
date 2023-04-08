package goormton.tamrazu.server.controller;

import static java.util.Objects.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.member.MemberPageResponseDto;
import goormton.tamrazu.server.dto.member.MemberRequestDto;
import goormton.tamrazu.server.dto.member.MemberResponseDto;
import goormton.tamrazu.server.dto.member.MemberSignupRequestDTO;
import goormton.tamrazu.server.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> signup(@RequestBody MemberSignupRequestDTO requestDTO) {
		Long response = memberService.signup(requestDTO);
		return ResponseEntity.ok(ApiResponse.success("회원가입 성공", response));
	}

	@GetMapping("/page")
	public ResponseEntity<ApiResponse> getPage(Principal principal) {
		MemberPageResponseDto response = memberService.getPage(getMemberId(principal));
		return ResponseEntity.ok(ApiResponse.success("유저 페이지 조회 성공", response));
	}

	private Long getMemberId(Principal principal) {
		return nonNull(principal) ? Long.parseLong(principal.getName()) : null;
	}
}
