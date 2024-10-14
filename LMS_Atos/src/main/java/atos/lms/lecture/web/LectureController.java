package atos.lms.lecture.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import atos.lms.common.service.CategoryService;
import atos.lms.common.service.CategoryVO;
import atos.lms.common.sort.SortFieldValidator;
import atos.lms.common.utl.FileUtil;
import atos.lms.common.utl.ResponseVO;
import atos.lms.exam.web.TestBoardController;
import atos.lms.instructor.service.InstructorVO;
import atos.lms.lecture.service.LectureEnrollDTO;
import atos.lms.lecture.service.LectureInsDTO;
import atos.lms.lecture.service.LectureMemDTO;
import atos.lms.lecture.service.LectureService;
import atos.lms.lecture.service.LectureVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;

@Controller
@RequestMapping("/education")
public class LectureController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LectureController.class);
	
	@Autowired
	LectureService lectureService;
	
	@Autowired
	CategoryService categoryservice;
	
    @Autowired
    private FileUtil fileUtil;

	
	@RequestMapping("/lectureList.do")
	public String lectureList(@ModelAttribute("searchVO") LectureVO lectureVO, ModelMap model) throws Exception {
		
		List<CategoryVO> categoryList = categoryservice.selectCodeMain(null);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(lectureVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(lectureVO.getPageUnit());
    	paginationInfo.setPageSize(lectureVO.getPageSize());
    	
    	lectureVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	lectureVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	lectureVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = lectureService.selectLecture(lectureVO);

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
		
    	
    	model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("resultList", map.get("resultList"));
		
		return "lecture/lectureList";
	}
	
	@RequestMapping("/lectureRegist.do")
	public String lectureRegist(@ModelAttribute("searchVO") LectureVO lectureVO, ModelMap model) throws Exception {
		
		//첨부 파일 갯수
		lectureVO.setAtchPosblFileNumber(2);
		
		return "lecture/lectureRegist";
	}
	
	@RequestMapping("/lectureDetail.do")
	public String lectureDetail(@ModelAttribute("searchVO") LectureVO lectureVO, ModelMap model) throws Exception {
		
		LectureVO lecture = lectureService.selectLectureKey(lectureVO);
		
		
		model.addAttribute("result", lecture);
		model.addAttribute("page", "lectureDetail");
		
		return "lecture/lectureDetail";
	}
	
	@RequestMapping("/lectureUpdt.do")
	public String lectureUpdt(@ModelAttribute("searchVO") LectureVO lectureVO, ModelMap model) throws Exception {
		
		LectureVO lecture = lectureService.selectLectureKey(lectureVO);
		
		lectureVO.setAtchPosblFileNumber(2);
		
		//날짜 시간 포멧
		String recStartDate = (lecture.getRecStartDate() != null) ? lecture.getRecStartDate().substring(0, 10) : null;
		String recEndDate = (lecture.getRecEndDate() != null) ? lecture.getRecEndDate().substring(0, 10) : null;
		String startTime = (lecture.getStartTime() != null) ? lecture.getStartTime().substring(0, 5) : null;
		String endTime = (lecture.getEndTime() != null) ? lecture.getEndTime().substring(0, 5) : null;
		
		lecture.setRecStartDate(recStartDate);
		lecture.setRecEndDate(recEndDate);
		lecture.setStartTime(startTime);
		lecture.setEndTime(endTime);
		
		model.addAttribute("result", lecture);
		
		return "lecture/lectureUpdt";
	}
	

	@RequestMapping("/lectureStudentList.do")
	public String lectureStudentList(@ModelAttribute("searchVO") LectureEnrollDTO lectureEnrollDTO, ModelMap model) throws Exception {
		
		LectureVO lecture = lectureService.selectLectureTitle(lectureEnrollDTO);
		
		System.out.println(">>>>lecture " + lecture);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(lectureEnrollDTO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(lectureEnrollDTO.getPageUnit());
    	paginationInfo.setPageSize(lectureEnrollDTO.getPageSize());
    	
    	lectureEnrollDTO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	lectureEnrollDTO.setLastIndex(paginationInfo.getLastRecordIndex());
    	lectureEnrollDTO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = lectureService.selectEnrollList(lectureEnrollDTO);
    	
    	

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
		
    	
    	model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("result", lecture);
		model.addAttribute("page", "lectureStudentList");
		
		return "lecture/lectureStudentList";
	}
	
	@RequestMapping("/lectureAttendance.do")
	public String attendanceInfo(@ModelAttribute("searchVO") LectureEnrollDTO lectureEnrollDTO, ModelMap model) throws Exception {
		
		LectureVO lecture = lectureService.selectLectureTitle(lectureEnrollDTO);
		
		
		
		model.addAttribute("result", lecture);
		model.addAttribute("page", "lectureAttendance");
		
		return "lecture/lectureAttendance";
	}
	
	
	
	
	@RequestMapping("/educationPopup.do")
	public String educationPopup(@ModelAttribute("searchVO") LectureVO lectureVO, ModelMap model) throws Exception {
		
		List<CategoryVO> categoryList = categoryservice.selectCodeMain(null);
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(lectureVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(lectureVO.getPageUnit());
    	paginationInfo.setPageSize(lectureVO.getPageSize());
    	
    	lectureVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	lectureVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	lectureVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = lectureService.selectEducation(lectureVO);

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
    	
    	model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("resultList", map.get("resultList"));
		
		return "atos/lecture/popupEducation";
	}
	
	@RequestMapping("/instructorPopup.do")
	public String instructorPopup(@ModelAttribute("searchVO") LectureInsDTO lectureInsDTO, ModelMap model) throws Exception {
		
    	Map<String, Object> map = lectureService.selectInstructor(lectureInsDTO);
    	
    	
		model.addAttribute("resultList", map.get("resultList"));
    	
		return "atos/lecture/popupInstructor";
	}
	
	@RequestMapping("/studentListPopup.do")
	public String studentListPopup(@ModelAttribute("searchVO") LectureMemDTO lectureMemDTO, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(lectureMemDTO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(lectureMemDTO.getPageUnit());
    	paginationInfo.setPageSize(lectureMemDTO.getPageSize());
    	
    	lectureMemDTO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	lectureMemDTO.setLastIndex(paginationInfo.getLastRecordIndex());
    	lectureMemDTO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = lectureService.selectStudentList(lectureMemDTO);

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
    	
    	model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("biz", map.get("biz"));
		model.addAttribute("totalCount", totalcount);
		
		return "atos/lecture/popupStudentList";
	}
	
	@RequestMapping("/studentDetailPopup.do")
	public String studentDetailPopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/popupStudentDetail";
	}
	
	@RequestMapping("/studentLecturePopup.do")
	public String studentLecturePopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/popupStudentLecture";
	}
	
	
	@RequestMapping("/instructorPopupSave")
	@ResponseBody
	public ResponseEntity<ResponseVO> instructorPopupSave(@RequestBody LectureInsDTO lectureInsDTO) throws Exception {
		
		System.out.println(">>>>>데이터 = " + lectureInsDTO);
		
		Map<String, Object> map = lectureService.saveInstructor(lectureInsDTO);
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage((String) map.get("message"));

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
	@RequestMapping("/deleteLecture")
	@ResponseBody
	public ResponseEntity<ResponseVO> deleteLecture(@RequestBody LectureVO lectureVO) throws Exception {
		
		Map<String, Object> map = lectureService.deleteLecture(lectureVO);
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage((String) map.get("message"));

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
	@RequestMapping("/deleteStudent")
	@ResponseBody
	public ResponseEntity<ResponseVO> deleteStudent(@RequestBody LectureMemDTO lectureMemDTO) throws Exception {
		
		Map<String, Object> map = lectureService.deleteStudent(lectureMemDTO);
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage((String) map.get("message"));

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
	@RequestMapping("/addStudent")
	@ResponseBody
	public ResponseEntity<ResponseVO> addStudent(@RequestBody LectureMemDTO lectureMemDTO) throws Exception {
		
		Map<String, Object> map = lectureService.insertStudent(lectureMemDTO);
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage((String) map.get("message"));

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
	@RequestMapping("/addSelectedStudents")
	@ResponseBody
	public ResponseEntity<ResponseVO> addSelectedStudents(@RequestBody LectureMemDTO lectureMemDTO) throws Exception {
		
		lectureMemDTO.getMemberIds().forEach(mem -> System.out.println("넘어온 셀렉트 데이터 = " + mem));
		
		Map<String, Object> map = lectureService.insertSelectedStudents(lectureMemDTO);
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage((String) map.get("message"));

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
	@RequestMapping(value = "/saveLecture", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseVO> saveLecture(final MultipartHttpServletRequest multiRequest, @ModelAttribute LectureVO lectureVO) {
	    ResponseVO responseVO = new ResponseVO();
	    try {

	        LOGGER.info("폼데이터 = " + lectureVO);

	        final Map<String, MultipartFile> files = multiRequest.getFileMap();
	        
	        String atchFileId = fileUtil.handleFileProcessing(files, lectureVO.getAtchFileId(), "Lecture_");
	        
	        lectureVO.setAtchFileId(atchFileId);
	        
	        Map<String, Object> map = lectureService.saveLecture(lectureVO);

	        responseVO.setHttpStatus(HttpStatus.OK);
	        responseVO.setMessage((String) map.get("message"));
	    } catch (Exception e) {
	        LOGGER.error("파일 저장 중 오류 발생", e);
	        responseVO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        responseVO.setMessage("파일 저장 중 오류가 발생했습니다." + e.getMessage());
	    }

	    return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
}



