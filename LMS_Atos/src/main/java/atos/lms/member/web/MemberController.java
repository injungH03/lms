package atos.lms.member.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import atos.lms.common.ResponseVO;
import atos.lms.member.service.CompanyVO;
import atos.lms.member.service.MemberMasterVO;
import atos.lms.member.service.MemberService;
import atos.lms.member.service.MemberVO;
import egovframework.com.utl.sim.service.EgovFileScrty;



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
    
    @RequestMapping("/member/memberRegistView.do")
    public String memberRegistView(@ModelAttribute("searchVO") MemberVO memberVO,  ModelMap model) throws Exception {
    	
    	List<MemberMasterVO> company = memberService.selectCompany();
    	model.addAttribute("company", company);
    	
    	return "member/memberRegist";
    }
    
    @RequestMapping("/member/memberDetail.do")
    public String memberDetail(@ModelAttribute("searchVO") MemberVO memberVO,  ModelMap model) throws Exception {
    	
    	System.out.println("넘어온 데이터  = " + memberVO);
    	System.out.println("페이징사이즈 = " + memberVO.getPageSize());
    	
    	model.addAttribute("member", memberService.selectMemberKey(memberVO));
    	
    	return "member/memberDetail";
    }
    
    @RequestMapping("/member/memberUpdateView.do")
    public String memberUpdateView(@ModelAttribute("searchVO") MemberVO memberVO,  ModelMap model) throws Exception {
    	
    	System.out.println("넘어온 데이터  = " + memberVO);
    	System.out.println("페이징사이즈 = " + memberVO.getPageSize());
    	
    	List<MemberMasterVO> company = memberService.selectCompany();
    	model.addAttribute("company", company);
    	
    	return "member/memberUpdt";
    }
    
    
    
    @RequestMapping("/member/updateStatus")
    @ResponseBody
    public ResponseEntity<String> updateStatus(
    		@RequestParam("ids") String ids,
            @RequestParam("status") String status) throws Exception {
    	
    	memberService.updateStatus(ids, status);
    	
    	return ResponseEntity.ok("상태 변경 완료");
    }
    
    @RequestMapping("/member/companyDetail")
    @ResponseBody
    public ResponseEntity<ResponseVO> companyDetail(
    		@RequestBody Map<String, String> data) throws Exception {
    	
    	System.out.println("사업자번호 확인 = " + data.get("corpBiz"));
    	String corpBiz = data.get("corpBiz");
    	
    	CompanyVO companyVO = memberService.selectCompanyKey(corpBiz);
    	
    	System.out.println("companyVO 값 = " + companyVO.toString());
    	List<CompanyVO> companyList = new ArrayList<CompanyVO>();
    	companyList.add(companyVO);
    	
    	ResponseVO responseVO = new ResponseVO();
    	responseVO.setHttpStatus(HttpStatus.OK);
    	responseVO.setResult(companyList);
    	
    	return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
    }
    
    @RequestMapping("/member/memberInsert")
    @ResponseBody
    public ResponseEntity<ResponseVO> memberInsert(@RequestBody MemberVO memberVO) throws Exception {
    	
    	System.out.println(">>>>입력값  =  " + memberVO.toString());
    	
    	// 1. 입력한 비밀번호 암호화
    	String enpassword = EgovFileScrty.encryptPassword(memberVO.getPassword(), memberVO.getId());
    	
    	// 2. 암호화한 비밀번호 재설정
    	memberVO.setPassword(enpassword);
    	
    	memberService.insertMember(memberVO);
    	
    	
    	ResponseVO responseVO = new ResponseVO();
    	responseVO.setHttpStatus(HttpStatus.OK);
    	responseVO.setMessage("등록 완료");
    	
    	return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
    }
    

}
