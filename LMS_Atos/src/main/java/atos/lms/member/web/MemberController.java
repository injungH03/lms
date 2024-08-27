package atos.lms.member.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import atos.lms.member.service.MemberMasterVO;
import atos.lms.member.service.MemberService;
import atos.lms.member.service.MemberVO;



@Controller
public class MemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	
	@Resource(name = "MemberService")
	private MemberService memberService;
    
    @RequestMapping("/member/memberList.do")
    public String memberList(@ModelAttribute("searchVO") MemberVO memberVO,  ModelMap model) throws Exception {
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
    	paginationInfo.setPageSize(memberVO.getPageSize());
    	
    	memberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	memberVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	memberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = memberService.selectMemberList(memberVO);
    	

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);

    	List<MemberMasterVO> status = memberService.selectStatusCode();
    	List<MemberMasterVO> company = memberService.selectCompany();
    	
    	model.addAttribute("resultList", map.get("resultList"));
    	model.addAttribute("paginationInfo", paginationInfo);
    	model.addAttribute("status", status);
    	model.addAttribute("company", company);
    	
    	return "member/memberList";
    }
    

}
