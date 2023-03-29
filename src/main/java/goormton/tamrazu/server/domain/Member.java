package goormton.tamrazu.server.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "alcohol_id")
	private Alcohol alcohol;
}
