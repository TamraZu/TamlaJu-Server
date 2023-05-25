package goormton.tamrazu.server.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.repository.alcohol.AlcoholRepository;

@Component
public class Util {

	public static List<Alcohol> getAlcoholsOfMember(AlcoholRepository repository, Long memberId) {
		return Objects.nonNull(memberId)
			? repository.getAlcoholsMemberHistory(memberId)
			: new ArrayList<>();
	}
}
