package goormton.tamrazu.server.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class Member {

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String username;
	private String password;
	private String nickname;

	@OneToMany(mappedBy = "member")
	private List<Eat> ateAlcohols = new ArrayList<>();
}
