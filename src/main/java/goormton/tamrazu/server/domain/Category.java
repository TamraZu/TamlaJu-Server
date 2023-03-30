package goormton.tamrazu.server.domain;

public enum Category {
	Makgeolli("막걸리"),
	Yakju("약주"),
	Soju("소주/증류주"),
	Beer("맥주"),
	Wine("와인"),
	ETC("기타");

	private final String name;

	Category(String name) {
		this.name = name;
	}
}
