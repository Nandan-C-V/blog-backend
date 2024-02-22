package com.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.entities.Category;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.repositories.CategoryRepo;
import com.blogapp.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto createCategory(CategoryDto cDto) {
		// TODO Auto-generated method stub
		Category category=modelMapper.map(cDto,Category.class);
		Category c=this.categoryRepo.save(category);
		return modelMapper.map(c, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto cDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category c=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId", categoryId));
		c.setCategoryTitle(cDto.getCategoryTitle());
		c.setCategoryDescription(cDto.getCategoryDescription());
		Category cat=this.categoryRepo.save(c);
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category c=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId", categoryId));
		return this.modelMapper.map(c, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> cat=this.categoryRepo.findAll();
		List<CategoryDto> cDto= cat.stream().map((c)->this.modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
		return cDto ;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category c=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId", categoryId));
		this.categoryRepo.delete(c);
		
	}

}
