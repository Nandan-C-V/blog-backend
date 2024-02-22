package com.blogapp.services;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CludinaryImageService {

	public Map upload(MultipartFile file);
}
