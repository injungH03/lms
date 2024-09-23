package atos.lms.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.common.service.CategoryService;
import atos.lms.common.service.CategoryVO;
import atos.lms.common.service.dao.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDAO categorydao;
	
	@Override
	public List<CategoryVO> selectCodeList(CategoryVO categoryVO) {
		return categorydao.selectCategoryCodeAll(categoryVO);
	}

}
