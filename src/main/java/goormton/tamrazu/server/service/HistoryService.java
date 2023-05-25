package goormton.tamrazu.server.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.History;
import goormton.tamrazu.server.domain.Member;
import goormton.tamrazu.server.repository.HistoryRepository;
import goormton.tamrazu.server.repository.alcohol.AlcoholRepository;
import goormton.tamrazu.server.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {

	private final HistoryRepository historyRepository;
	private final MemberRepository memberRepository;
	private final AlcoholRepository alcoholRepository;

	@Transactional
	public boolean eatAlcohol(Long memberId, Long alcoholId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

		Alcohol alcohol = alcoholRepository.findById(alcoholId)
			.orElseThrow(() -> new EntityNotFoundException("해당 전통주가 존재하지 않습니다."));

		return historyRepository.findByMemberAndAlcohol(member, alcohol)
			.map(history -> deleteHistory(history, member, alcohol))
			.orElseGet(() -> createHistory(member, alcohol));
	}

	private boolean deleteHistory(History history, Member member, Alcohol alcohol) {
		member.getHistories().remove(history);
		alcohol.getHistories().remove(history);
		historyRepository.deleteByMemberAndAlcohol(member, alcohol);
		alcohol.minusAteCount();
		return false;
	}

	private boolean createHistory(Member member, Alcohol alcohol) {
		historyRepository.save(new History(member, alcohol));
		return true;
	}
}
