package atos.lms.exam.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import atos.lms.exam.service.AlertModel;
import atos.lms.exam.service.TestBoardService;
import atos.lms.exam.service.TestBoardVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovXssChecker;


@Controller
public class TestBoardController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestBoardController.class);
	
	@Resource(name = "TestBoardService")
	private TestBoardService testBoardService;
	
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    /**
     * XSS 방지 처리.
     * 
     * @param data
     * @return
     */
    protected String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
    }
    
    @RequestMapping("/test/boardList.do")
    public String boardList(@ModelAttribute("searchVO") TestBoardVO testBoardVO,  ModelMap model) throws Exception {
    	
    	testBoardVO.setPageUnit(propertyService.getInt("pageUnit"));
    	testBoardVO.setPageSize(propertyService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(testBoardVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(testBoardVO.getPageUnit());
    	paginationInfo.setPageSize(testBoardVO.getPageSize());
    	
    	System.out.println(">>>>>>>>>>>>getPageSize = " + paginationInfo.getPageSize());
    	System.out.println(">>>>>>>>>>>>getLastPageNo = " + paginationInfo.getLastPageNo());
    	System.out.println(">>>>>>>>>>>>getRecordCountPerPage = " + paginationInfo.getRecordCountPerPage());
    	
    	testBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	testBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	testBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = testBoardService.selectBoardList(testBoardVO);
    	
    	System.out.println("총갯수 = " + map.get("resultCnt"));
    	
    	
    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
    	
    	model.addAttribute("resultList", map.get("resultList"));
//    	model.addAttribute("resultCnt", map.get("resultCnt"));
    	model.addAttribute("boardVO", testBoardVO);
    	model.addAttribute("paginationInfo", paginationInfo);
    	
    	return "board/TestBoardList";
    }
    
    
    @RequestMapping("/test/boardDetail.do")
    public String boardDetail(@ModelAttribute("searchVO") TestBoardVO boardVO, ModelMap model) throws Exception {
    	TestBoardVO board = testBoardService.selectBoardKey(boardVO);
    	
    	System.out.println("결과값 = " + board.toString());
	
		model.addAttribute("result", board);
	
		return "board/TestBoardDetail";
    }
    
    @RequestMapping("/test/updateBoardView.do")
    public String updateBoardView(@ModelAttribute("searchVO") TestBoardVO boardVO, ModelMap model) throws Exception {
    	TestBoardVO board = testBoardService.selectBoardKey(boardVO);
    	
    	//첨부파일 가능 숫자
    	boardVO.setAtchPosblFileNumber(3);
    	
    	System.out.println("결과값 = " + board.toString());
	
		model.addAttribute("boardVO", board);
		
    	return "board/TestBoardUpdt";
    }
    
    
    @RequestMapping("/test/updateBoard.do")
    public String updateBoard(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") TestBoardVO boardVO,
	    @ModelAttribute("board") TestBoardVO board, ModelMap model) throws Exception {

    	System.out.println(">>>>>>파일번호 = " + board.getAtchFileId());
    	System.out.println(">>>>>>게시판글번호 = " + board.getBoardNum());
    	
		String atchFileId = board.getAtchFileId();
		
//		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
	    if (!files.isEmpty()) {
			if (atchFileId == null || "".equals(atchFileId)) {
			    List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
			    atchFileId = fileMngService.insertFileInfs(result);
			    board.setAtchFileId(atchFileId);
			} else {
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
			    fileMngService.updateFileInfs(_result);
			}
	    }
	    
	    board.setContent((unscript(board.getContent())));	// XSS 방지
	    
	    testBoardService.update(board);
	    
	    AlertModel alert = new AlertModel();
	    
	    alert.setHref("/test/boardDetail.do?boardNum=" + board.getBoardNum() + "&pageIndex=" + board.getPageIndex());
	    alert.setMessage("수정 완료");
	    
	    model.addAttribute("alert", alert);
		
		return alert.getAlertPage();
    }
    
    
    
    @RequestMapping("/test/insertBoardView.do")
    public String insertBoardView(@ModelAttribute("searchVO") TestBoardVO boardVO, ModelMap model) throws Exception {
	
    	//첨부파일 가능 숫자
    	boardVO.setAtchPosblFileNumber(3);
    	
    	model.addAttribute("boardVO", boardVO);
    	
		return "board/TestBoardRegist";
    }
    
    @RequestMapping("/test/insertBoard.do")
    public String insertBoard(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") TestBoardVO boardVO, @ModelAttribute("boardVO") TestBoardVO board, ModelMap model) throws Exception {
    	
    	System.out.println("데이터 확인 = " + boardVO.toString());
    	System.out.println("데이터 확인2 = " + board.toString());

    	AlertModel alert = new AlertModel();
    	
    	List<FileVO> result = null;
	    String atchFileId = "";
	    
	    System.out.println("파일정보 = " + multiRequest.toString());
    	
//	    final List<MultipartFile> files = multiRequest.getFiles("file_1");
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
	    	result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
	    	atchFileId = fileMngService.insertFileInfs(result);
	    }
	    
	    board.setAtchFileId(atchFileId);
	    board.setContent(unscript(board.getContent()));
	    
	    testBoardService.insert(board);
    	
	    alert.setHref("/test/boardList.do");
	    alert.setMessage("저장 완료");
	    
	    model.addAttribute("alert", alert);
//    	model.addAttribute("TestBoardVO", boardVO);
    	
    	return alert.getAlertPage();
    }

}
