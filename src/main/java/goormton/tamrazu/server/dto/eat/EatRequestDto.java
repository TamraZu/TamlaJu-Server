package goormton.tamrazu.server.dto.eat;

import javax.swing.text.html.parser.Entity;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Eat;
import goormton.tamrazu.server.domain.Member;

public record EatRequestDto(Long memberId, Long alcoholId) {
}
