package atos.lms.member.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	
	Map<String, Object> selectMemberList(MemberVO memberVO);
	
	List<MemberMasterVO> selectStatusCode();
	
	List<MemberMasterVO> selectCompany();

}
