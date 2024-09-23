package atos.lms.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {
	
	List<CategoryVO> selectCodeList(CategoryVO categoryVO);
	

}
