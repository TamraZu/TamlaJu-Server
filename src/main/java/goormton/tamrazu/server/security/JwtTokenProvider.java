package goormton.tamrazu.server.security;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtTokenProvider {

	@Value("${spring.jwt.secretKey}")
	private String secretKey;
	private final ZoneId KST = ZoneId.of("Asia/Seoul");

	public String generateAccessToken(Authentication authentication) {
		byte[] bytes = DatatypeConverter.parseBase64Binary(secretKey);
		SecretKeySpec key = new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());

		JwtBuilder jwtBuilder = Jwts.builder()
			.setSubject(String.valueOf(authentication.getPrincipal()))
			.setHeader(createHeader())
			.setExpiration(createExpireDate(JwtTokenType.ACCESS_TOKEN))
			.signWith(key, SignatureAlgorithm.HS256);

		return jwtBuilder.compact();
	}

	public String resolveToken(HttpServletRequest req) {
		return req.getHeader("Authorization");
	}

	public boolean validateTokenExpiration(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token);

			return !claims.getBody().getExpiration().toInstant().atZone(KST).toLocalDateTime().isBefore(getCurrentTime());
		} catch(Exception exception) {
			return false;
		}
	}

	public MemberAuthentication getAuthentication(String token) {
		return new MemberAuthentication(getId(token), null, null);
	}

	public Long getId(String token) {
		try {
			return Long.parseLong(Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject());
		} catch(ExpiredJwtException e) {
			throw new IllegalStateException("만료된 토큰입니다");
		}
	}

	private Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();

		header.put("typ", "JWT");
		header.put("alg", "HS256");
		header.put("regDate", System.currentTimeMillis());

		return header;
	}

	private Date createExpireDate(JwtTokenType jwtTokenType) {
		return Date.from(setExpireTime(getCurrentTime(), jwtTokenType).atZone(ZoneId.systemDefault()).toInstant());
	}

	private LocalDateTime getCurrentTime() {
		return LocalDateTime.now(KST);
	}

	private LocalDateTime setExpireTime(LocalDateTime now, JwtTokenType jwtTokenType) {
		return switch (jwtTokenType) {
			case ACCESS_TOKEN -> now.plusHours(5);
			case REFRESH_TOKEN -> now.plusWeeks(2);
		};
	}
}
