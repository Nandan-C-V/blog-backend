package com.blogapp.payloads;

import java.util.ArrayList;
import java.util.List;

import com.blogapp.entities.Post;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
private Integer categoryId;
@NotBlank
@Size(min=4)
private String categoryTitle;
@NotBlank
@Size(min=4)
private String categoryDescription;


}
