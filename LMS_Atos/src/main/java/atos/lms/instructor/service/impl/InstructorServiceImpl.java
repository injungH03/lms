package atos.lms.instructor.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import atos.lms.common.utl.ExcelUtil;
import atos.lms.instructor.service.InstructorMasterVO;
import atos.lms.instructor.service.InstructorService;
import atos.lms.instructor.service.InstructorVO;

@Service("InstructorService")
public class InstructorServiceImpl extends EgovAbstractServiceImpl implements InstructorService {
	
	@Resource(name = "InstructorDAO")
	private InstructorDAO instructorDAO;
	
	@Override
	public List<InstructorMasterVO> selectStatusCode() {
		return instructorDAO.selectStatusCode();
	}

	@Override
	public Map<String, Object> selectInstructorList(InstructorVO instructorVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", instructorDAO.selectInstructorList(instructorVO));
		map.put("resultCnt", instructorDAO.selectInstructorListCnt(instructorVO));
		
		return map;
	}

	@Override
	public void instructorListExcelDown(HttpServletResponse response, InstructorVO instructorVO) throws Exception {
		List<InstructorVO> list = instructorDAO.selectInstructorListExcel(instructorVO);
		
		Map<String, String> fieldToHeaderMap = new HashMap<>();
        fieldToHeaderMap.put("id", "아이디");
        fieldToHeaderMap.put("name", "이름");
        fieldToHeaderMap.put("birthdate", "생년월일");
        fieldToHeaderMap.put("phoneNo", "휴대폰번호");
        fieldToHeaderMap.put("email", "이메일");
        fieldToHeaderMap.put("zipcode", "우편번호");
        fieldToHeaderMap.put("addr1", "주소1");
        fieldToHeaderMap.put("addr2", "주소2");
        fieldToHeaderMap.put("job", "직업");
        fieldToHeaderMap.put("affiliation", "소속기관");
        fieldToHeaderMap.put("department", "소속");
        fieldToHeaderMap.put("position", "직책");
        fieldToHeaderMap.put("bios", "강사소개");
        fieldToHeaderMap.put("career", "경력사항");
		
        ExcelUtil.exportToExcel(response, list, "강사목록", "강사목록엑셀파일", fieldToHeaderMap);
		
	}

	@Override
	public void updateInstructorStatus(String ids, String status) {
		InstructorMasterVO instructorMasterVO = new InstructorMasterVO();
		instructorMasterVO.setIdlist(Arrays.asList(ids.split(",")));
		instructorMasterVO.setStatus(status);
		
		instructorDAO.updateInstructorStatus(instructorMasterVO);
	}

	@Override
	public void instructorInsert(InstructorVO instructorVO) {
		instructorDAO.insertinstructor(instructorVO);
	}

	@Override
	public InstructorVO selectInstructorKey(InstructorVO instructorVO) {
		return instructorDAO.selectInstructorKey(instructorVO);
	}

	@Override
	public void instructorDelete(InstructorVO instructorVO) {
		instructorDAO.deleteInstructor(instructorVO);
	}

	@Override
	public void updateInstructor(InstructorVO instructorVO) {
		instructorDAO.updateInstructor(instructorVO);
	}
	

}
