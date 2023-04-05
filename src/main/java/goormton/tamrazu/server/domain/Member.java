package goormton.tamrazu.server.domain;

import static javax.persistence.GenerationType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(unique = true)
	private String email;
	private String nickname;
	private String imageUrl;

	@OneToMany(mappedBy = "member")
	private final List<History> ateAlcohols = new ArrayList<>();

	public Member(String email, String nickname, String imageUrl) {
		this.email = email;
		this.nickname = nickname;
		this.imageUrl = imageUrl;
	}
}
