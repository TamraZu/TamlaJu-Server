package goormton.tamrazu.server.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goormton.tamrazu.server.domain.Category;
import goormton.tamrazu.server.dto.category.CategoryResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

	public List<CategoryResponseDto> getAllCategories() {
		return Arrays.stream(Category.values()).map(CategoryResponseDto::of).toList();
	}
}
