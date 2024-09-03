package atos.lms.member.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import atos.lms.member.service.CompanyVO;
import atos.lms.member.service.MemberDTO;
import atos.lms.member.service.MemberMasterVO;
import atos.lms.member.service.MemberService;
import atos.lms.member.service.MemberVO;

@Service("MemberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {

	@Resource(name = "MemberDAO")
	private MemberDAO memberDao;

	@Override
	public Map<String, Object> selectMemberList(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", memberDao.selectMemberList(memberVO));
		map.put("resultCnt", memberDao.selectMemberListCnt(memberVO));
		
		return map;
	}

	@Override
	public List<MemberMasterVO> selectStatusCode() {
		return memberDao.selectStatusCode();
	}

	@Override
	public List<MemberMasterVO> selectCompany() {
		return memberDao.selectCompany();
	}

	@Override
	public void updateStatus(String ids, String status) {
		MemberMasterVO memberMasterVO = new MemberMasterVO();
		memberMasterVO.setIdlist(Arrays.asList(ids.split(",")));
		memberMasterVO.setStatus(status);
		
		memberDao.updateStatus(memberMasterVO);
	}

	@Override
	public CompanyVO selectCompanyKey(String corpBiz) {
		return memberDao.selectCompanyKey(corpBiz);
	}

	@Override
	public void insertMember(MemberVO memberVO) {
		memberDao.insertMember(memberVO);
	}

	@Override
	public MemberDTO selectMemberKey(MemberVO memberVO) {
		return memberDao.selectMemberKey(memberVO);
	}


	
	
	
}
