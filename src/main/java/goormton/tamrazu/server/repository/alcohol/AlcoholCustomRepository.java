package goormton.tamrazu.server.repository.alcohol;

import java.util.List;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Category;

public interface AlcoholCustomRepository {
	List<Alcohol> getAlcoholsMemberHistory(Long memberId);
	List<Alcohol> getAllByCategory(Category category);
}
