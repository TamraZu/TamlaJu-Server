package goormton.tamrazu.server.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.History;
import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.dto.eat.EatRequestDto;
import goormton.tamrazu.server.repository.HistoryRepository;
import goormton.tamrazu.server.repository.alcohol.AlcoholRepository;
import goormton.tamrazu.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EatService {

	private final HistoryRepository historyRepository;
	private final MemberRepository memberRepository;
	private final AlcoholRepository alcoholRepository;

	@Transactional
	public boolean eatAlcohol(EatRequestDto eatRequestDto) {
		Member member = memberRepository.findById(eatRequestDto.memberId())
			.orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

		Alcohol alcohol = alcoholRepository.findById(eatRequestDto.alcoholId())
			.orElseThrow(() -> new EntityNotFoundException("해당 전통주가 존재하지 않습니다."));

		Optional<History> eat = historyRepository.findByMemberAndAlcohol(member, alcohol);

		if (eat.isPresent()) {
			member.getHistories().remove(eat.get());
			alcohol.getHistories().remove(eat.get());
			historyRepository.deleteByMemberAndAlcohol(member, alcohol);
			alcohol.minusAteCount();
			return false;
		} else {
			History savedEat = historyRepository.save(new History(member, alcohol));
			savedEat.setAlcohol(alcohol);
			savedEat.setMember(member);
			alcohol.plusAteCount();
			return true;
		}
	}
}
