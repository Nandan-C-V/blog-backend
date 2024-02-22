package com.blogapp.services.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.services.CludinaryImageService;
import com.cloudinary.Cloudinary;

@Service
public class CloudinaryServiceImple implements CludinaryImageService {

	@Autowired
	private Cloudinary cloudinary;
	@Override
	public Map upload(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			Map data = this.cloudinary.uploader().upload(file.getBytes(),Map.of());
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}

}
