package atos.lms.member.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import atos.lms.commons.utl.ExcelUtil;
import atos.lms.member.service.CompanyVO;
import atos.lms.member.service.MemberAllDTO;
import atos.lms.member.service.MemberDTO;
import atos.lms.member.service.MemberExcelVO;
import atos.lms.member.service.MemberMasterVO;
import atos.lms.member.service.MemberService;
import atos.lms.member.service.MemberVO;
import egovframework.com.utl.sim.service.EgovFileScrty;

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

	@Override
	public void deleteMember(MemberVO memberVO) {
		memberDao.deleteMember(memberVO);
	}

	@Override
	public void updateMember(MemberVO memberVO) {
		memberDao.updateMember(memberVO);
	}

	@Override
	public void memberListExcelDown(HttpServletResponse response, MemberVO memberVO) throws Exception {
		List<MemberExcelVO> memberList = memberDao.selectMemberListExcel(memberVO);
		
		Map<String, String> fieldToHeaderMap = new HashMap<>();
        fieldToHeaderMap.put("id", "아이디");
        fieldToHeaderMap.put("name", "이름");
        fieldToHeaderMap.put("birthdate", "생년월일");
        fieldToHeaderMap.put("phoneNo", "휴대폰번호");
        fieldToHeaderMap.put("zipcode", "우편번호");
        fieldToHeaderMap.put("addr1", "주소1");
        fieldToHeaderMap.put("addr2", "주소2");
        fieldToHeaderMap.put("department", "소속부서");
        fieldToHeaderMap.put("position", "직책");
        fieldToHeaderMap.put("email", "이메일");
        fieldToHeaderMap.put("companybizRegNo", "사업자번호");
        fieldToHeaderMap.put("corpName", "사업장명");
        fieldToHeaderMap.put("repName", "대표자명");
        fieldToHeaderMap.put("bizType", "업태");
        fieldToHeaderMap.put("bizItem", "종목");
        fieldToHeaderMap.put("companyPhoneNo", "대표전화번호");
        fieldToHeaderMap.put("companyZipcode", "회사우편번호");
        fieldToHeaderMap.put("companyAddr1", "회사주소1");
        fieldToHeaderMap.put("companyAddr2", "회사주소2");
		
        ExcelUtil.exportToExcel(response, memberList, "회원목록", "회원목록엑셀파일", fieldToHeaderMap);
	}

	@Override
	public void sampleExcelDown(HttpServletResponse response) throws Exception {
		Map<String, String> fieldToHeaderMap = new LinkedHashMap<>();
        fieldToHeaderMap.put("name", "이름");
        fieldToHeaderMap.put("birthdate", "생년월일");
        fieldToHeaderMap.put("phoneNumber", "전화번호");
        fieldToHeaderMap.put("email", "이메일");
        fieldToHeaderMap.put("department", "소속부서");
        fieldToHeaderMap.put("position", "직책");

        ExcelUtil.exportTemplateToExcel(response, "회원정보 양식", "회원정보_양식", fieldToHeaderMap);
	}

	@Override
	public List<MemberVO> uploadExcel(MultipartFile file) throws Exception {
		InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<MemberVO> memberList = new ArrayList<MemberVO>();
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
         
            MemberVO member = new MemberVO();
            
            member.setName(ExcelUtil.getCellValue(row.getCell(0)));       // 이름 (String)
            member.setBirthdate(ExcelUtil.getCellValue(row.getCell(1)));  // 생년월일 (String or Numeric)
            member.setPhoneNo(ExcelUtil.getCellValue(row.getCell(2)));    // 전화번호 (String)
            member.setEmail(ExcelUtil.getCellValue(row.getCell(3)));      // 이메일 (String)
            member.setDepartment(ExcelUtil.getCellValue(row.getCell(4))); // 소속부서 (String)
            member.setPosition(ExcelUtil.getCellValue(row.getCell(5)));   // 직책 (String)
            memberList.add(member);
        }
        
        workbook.close();
        
		return memberList;
	}

	@Override
	public void memberAllSave(MemberAllDTO memberAllDTO) {
		List<MemberVO> memberList = memberAllDTO.getPreviewData();
		String corpBiz = memberAllDTO.getCorpBiz();
    	
		
	}



	
	
	
}
