package goormton.tamrazu.server.controller;

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
import goormton.tamrazu.server.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/sign")
	public ResponseEntity<ApiResponse> sign(@RequestBody MemberRequestDto memberRequestDto) {
		MemberResponseDto response = memberService.sign(memberRequestDto);
		return ResponseEntity.ok(ApiResponse.success("로그인 성공", response));
	}

	@GetMapping("/page/{memberId}")
	public ResponseEntity<ApiResponse> getPage(@PathVariable("memberId") Long memberId) {
		MemberPageResponseDto response = memberService.getPage(memberId);
		return ResponseEntity.ok(ApiResponse.success("유저 페이지 조회 성공", response));
	}
}
