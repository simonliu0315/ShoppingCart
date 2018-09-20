package com.waterproof.bjb.shopping.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.dto.CategoryDto;
import com.waterproof.bjb.shopping.entity.Category;
import com.waterproof.bjb.shopping.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
@Slf4j
@Service
public class CategoryService {

	@Resource
	private CategoryRepository categoryRepository;
	
	public List<CategoryDto> getAllCategory() {
		Sort sort = new Sort(Sort.Direction.ASC, new String[]{"id"});
		List<Category> list = categoryRepository.findAll(sort);
		List<CategoryDto> retList = new ArrayList<CategoryDto>();
		for (Category c : list) {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(c, dto);
		    retList.add(dto);
		}
		return retList;
	}
}
