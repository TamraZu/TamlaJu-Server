package goormton.tamrazu.server.repository.alcohol;

import java.util.List;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.domain.Member;

public interface AlcoholCustomRepository {
	List<Alcohol> getAlcoholsMemberHistory(Member member);
	List<Alcohol> getAllByCategory(Category category);
}
