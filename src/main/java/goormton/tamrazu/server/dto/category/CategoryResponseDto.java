package goormton.tamrazu.server.dto.category;

import goormton.tamrazu.server.domain.Category;

public record CategoryResponseDto(
	String value,
	String name) {

	public static CategoryResponseDto of(Category category) {
		return new CategoryResponseDto(category.name(), category.getName());
	}

}
