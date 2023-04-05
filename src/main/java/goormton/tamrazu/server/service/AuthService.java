package goormton.tamrazu.server.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.dto.auth.AuthRequestDTO;
import goormton.tamrazu.server.dto.auth.AuthResponseDTO;
import goormton.tamrazu.server.repository.MemberRepository;
import goormton.tamrazu.server.security.JwtTokenProvider;
import goormton.tamrazu.server.security.MemberAuthentication;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public AuthResponseDTO login(AuthRequestDTO requestDTO) {
		Member member = getMember(requestDTO);

		Authentication authentication = new MemberAuthentication(member.getId(), null, null);

		return new AuthResponseDTO(member.getEmail(), member.getNickname(),
			jwtTokenProvider.generateAccessToken(authentication));
	}

	private Member getMember(AuthRequestDTO requestDTO) {
		if (memberRepository.existsByEmail(requestDTO.email())) {
			return memberRepository.findByEmail(requestDTO.email())
				.orElseThrow(EntityNotFoundException::new);
		}
		return memberRepository.save(new Member(requestDTO.email(), requestDTO.nickname(), requestDTO.imageUrl()));
	}
}
