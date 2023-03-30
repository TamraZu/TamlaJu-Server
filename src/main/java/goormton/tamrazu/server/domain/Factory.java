package goormton.tamrazu.server.domain;

import static javax.persistence.GenerationType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Factory {

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "factory_id")
	private Long id;

	private String name;
	private String address;
	private Long latitude;
	private Long longitude;

	@OneToMany(mappedBy = "factory")
	private List<Alcohol> alcohols = new ArrayList<>();
}
