package com.blogapp.services;

import java.util.List;

import com.blogapp.entities.Post;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto ,Long userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
//	delete
	void deletePost(Integer postId);
	
//	get all post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String sortDir);
	
//	get single post
	PostDto getPostById(Integer postId);
	
//	get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
//	get all posts by user
	List<PostDto> getPostsByUser(Long userId);
	
//	serach posts
	List<PostDto> searchPosts(String keyWord);
	
	
	
}
