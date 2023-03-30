package goormton.tamrazu.server.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.dto.member.MemberPageResponseDto;
import goormton.tamrazu.server.dto.member.MemberRequestDto;
import goormton.tamrazu.server.dto.member.MemberResponseDto;
import goormton.tamrazu.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberResponseDto sign(MemberRequestDto memberRequestDto) {
		Member member = memberRepository
			.findByUsernameAndPassword(memberRequestDto.username(), memberRequestDto.password())
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));

		return new MemberResponseDto(member.getId());
	}

	public MemberPageResponseDto getPage(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

		return MemberPageResponseDto.of(member);
	}
}
