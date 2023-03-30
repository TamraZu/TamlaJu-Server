package goormton.tamrazu.server.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.dto.category.CategoryResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	public List<CategoryResponseDto> getAllCategories() {
		return Arrays.stream(Category.values()).map(CategoryResponseDto::of).toList();
	}
}
