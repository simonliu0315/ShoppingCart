package com.waterproof.bjb.shopping.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.dto.CategoryDto;
import com.waterproof.bjb.shopping.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {

	@Resource
	private CategoryRepository categoryRepository;
	
	public List<CategoryDto> getAllCategory() {
		CategoryDto dto = new CategoryDto();
		List<CategoryDto> list = new ArrayList<CategoryDto>();
		dto.setId(1L);
		dto.setName("防水工具");
		dto.setType(1);
		list.add(dto);
		
		dto = new CategoryDto();
		dto.setId(2L);
		dto.setName("防水漆");
		dto.setType(1);
		list.add(dto);
		
		dto.setId(3L);
		dto.setName("防水套件");
		dto.setType(1);
		list.add(dto);
		log.info("size:{}", categoryRepository.findAll().size());
		return list;
	}
}
