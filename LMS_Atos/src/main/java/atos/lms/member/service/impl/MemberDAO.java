package atos.lms.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.member.service.CompanyVO;
import atos.lms.member.service.MemberDTO;
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
	
	public CompanyVO selectCompanyKey(String corpBiz) {
		return selectOne("member.selectCompanyKey", corpBiz);
	}
	
	public MemberDTO selectMemberKey(MemberVO memberVO) {
		return selectOne("member.selectMemberKey", memberVO);
	}
	
	
	public void updateStatus(MemberMasterVO memberMasterVO) {
		update("member.updateStatus", memberMasterVO);
	}
	
	public void insertMember(MemberVO memberVO) {
		insert("member.insertMember", memberVO);
	}
	
	public void deleteMember(MemberVO memberVO) {
		update("member.deleteMember", memberVO);
	}

}
