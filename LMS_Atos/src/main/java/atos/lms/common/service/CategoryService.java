package atos.lms.common.service;

import java.util.List;
import java.util.Map;



public interface CategoryService {
	
	List<CategoryVO> selectCodeList(CategoryVO categoryVO);
	
	List<CategoryVO> selectCodeMain(CategoryVO categoryVO);
	
	List<CategoryVO> selectCodeSub(CategoryVO categoryVO);
	

}
