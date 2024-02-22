package com.blogapp.services;

import java.util.List;

import com.blogapp.payloads.CategoryDto;

public interface CategoryService {

	//create
	public CategoryDto createCategory(CategoryDto cDto);
	
	//update
	CategoryDto updateCategory(CategoryDto cDto,Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
//	get all
	List<CategoryDto>  getAllCategory();
	
//	delete
	void deleteCategory(Integer categoryId);
}
