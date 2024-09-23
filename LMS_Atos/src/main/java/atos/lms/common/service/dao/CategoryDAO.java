package atos.lms.common.service.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.common.service.CategoryVO;

@Repository
public interface CategoryDAO  {
	
	List<CategoryVO> selectCategoryCodeAll(CategoryVO categoryVO);
	
	List<CategoryVO> selectCategoryCodeMain(CategoryVO categoryVO);
	
	List<CategoryVO> selectCategoryCodeSub(CategoryVO categoryVO);


}
