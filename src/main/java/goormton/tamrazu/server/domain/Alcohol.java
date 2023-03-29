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

@Entity
public class Alcohol {

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "alcohol_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "factory_id")
	private Factory factory;

	@OneToMany(mappedBy = "alcohol")
	List<Member> members = new ArrayList<>();
}
