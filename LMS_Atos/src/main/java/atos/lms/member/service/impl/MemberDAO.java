package atos.lms.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.member.service.MemberMasterVO;
import atos.lms.member.service.MemberVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("MemberDAO")
public class MemberDAO extends EgovComAbstractDAO {
	
	public List<MemberVO> selectMemberList(MemberVO memberVO) {
		return selectList("member.selectMemberList", memberVO);
	}
	
	public int selectMemberListCnt(MemberVO memberVO) {
		return (Integer)selectOne("member.selectMemberListCnt", memberVO);
	}
	
	public List<MemberMasterVO> selectStatusCode() {
		return selectList("member.selectStatusCode");
	}
	
	public List<MemberMasterVO> selectCompany() {
		return selectList("member.selectCompany");
	}
	
	public void updateStatus(MemberMasterVO memberMasterVO) {
		update("member.updateStatus", memberMasterVO);
	}

}
