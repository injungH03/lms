package atos.lms.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
	
	Map<String, Object> selectMemberList(MemberVO memberVO);
	
	List<MemberMasterVO> selectStatusCode();
	
	List<MemberMasterVO> selectCompany();
	
	CompanyVO selectCompanyKey(String corpBiz);
	
	MemberDTO selectMemberKey(MemberVO memberVO);
	
	void updateStatus(String ids, String status);
	
	void insertMember(MemberVO memberVO);
	
	void deleteMember(MemberVO memberVO);
	
	void updateMember(MemberVO memberVO);
	
	void memberAllSave(MemberAllDTO memberAllDTO);
	
	void memberListExcelDown(HttpServletResponse response, MemberVO memberVO) throws Exception;
	
	void sampleExcelDown(HttpServletResponse response) throws Exception;
	
	List<MemberVO> uploadExcel(MultipartFile file) throws Exception;

}
