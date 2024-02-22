package com.blogapp.payloads;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogapp.entities.Category;
import com.blogapp.entities.Comment;
import com.blogapp.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class PostDto {

private Integer postId;
private String title;
private String content;
private String imageName;
private CategoryDto category;
private UserDto user;
private Date addedDate;
private Set<CommentDto> comments=new HashSet<>();



	
}
