package atos.lms.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.common.service.CategoryService;
import atos.lms.common.service.CategoryVO;
import atos.lms.common.service.dao.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	
	@Autowired
	CategoryDAO categorydao;
	
	@Override
	public List<CategoryVO> selectCodeList(CategoryVO categoryVO) {
        try {
            return categorydao.selectCategoryCodeAll(categoryVO);
        } catch (Exception e) {
            // 예외 로그 남기기
        	LOGGER.error("Error in selectCodeList", e);
            throw e; // 예외를 다시 던져 트랜잭션 롤백 유도
        }
	}

	@Override
	public List<CategoryVO> selectCodeMain(CategoryVO categoryVO) {
		return categorydao.selectCategoryCodeMain(categoryVO);
	}

	@Override
	public List<CategoryVO> selectCodeSub(CategoryVO categoryVO) {
		return categorydao.selectCategoryCodeSub(categoryVO);
	}

}
