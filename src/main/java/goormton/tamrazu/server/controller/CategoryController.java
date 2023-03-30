package goormton.tamrazu.server.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import goormton.tamrazu.server.common.ApiResponse;
import goormton.tamrazu.server.dto.category.CategoryResponseDto;
import goormton.tamrazu.server.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<ApiResponse> getCategories() {
		List<CategoryResponseDto> response = categoryService.getAllCategories();
		return ResponseEntity.ok(ApiResponse.success("카테고리 전체 조회 성공", response));
	}
}
