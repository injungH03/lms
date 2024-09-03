package atos.lms.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import atos.lms.login.service.LoginService;
import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovClntInfo;

@Controller
public class LoginController {

	/** EgovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** LoginService */
	@Resource(name = "loginService_")
	private LoginService loginService;

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 로그인 화면을 보여준다.
	 * 
	 * @param loginVO  - 로그인 후 이동할 URL 정보를 담고 있는 LoginVO 객체
	 * @param request  - HTTP 요청 객체
	 * @param response - HTTP 응답 객체
	 * @param model    - 뷰에 전달할 데이터를 담는 모델 객체
	 * @return 로그인 페이지의 뷰 이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/LoginUser.do")
	public String loginUserView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		// 멤버 관리 서비스가 존재하는지 확인하고, 존재하면 useMemberManage 속성을 모델에 추가
		if (EgovComponentChecker.hasComponent("mberManageService")) {
			model.addAttribute("useMemberManage", "true");
		}

		// 권한 체크 시 에러 발생 시 접근 거부 페이지로 이동
		String auth_error = request.getParameter("auth_error") == null ? "" : (String) request.getParameter("auth_error");
		if (auth_error != null && auth_error.equals("1")) {
			return "egovframework/com/cmm/error/accessDenied";
		}

		// 로그인 메시지가 있는 경우 모델에 추가
		String message = (String) request.getParameter("loginMessage");
		if (message != null)
			model.addAttribute("loginMessage", message);

		// 로그인 페이지로 이동
		return "/atos/login/LoginUser";
	}

	/**
	 * 일반 로그인(세션 기반)을 처리한다.
	 * 
	 * @param loginVO - 사용자의 아이디와 비밀번호가 담긴 LoginVO 객체
	 * @param request - 세션 처리를 위한 HttpServletRequest 객체
	 * @param model   - 뷰에 전달할 데이터를 담는 모델 객체
	 * @return 메인 페이지로 리다이렉트하거나 로그인 페이지로 리다이렉트
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/actionLogin.do")
	public String actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {

		// 로그인 처리 및 결과 반환
		LoginVO resultVO = loginService.actionLogin(loginVO);
		String userIp = EgovClntInfo.getClntIP(request);

		// 로그인 결과 객체에 사용자의 IP 주소를 설정
		resultVO.setIp(userIp);

		// 로그인 성공 여부 확인
		if (resultVO.getId() != null && !resultVO.getId().equals("")) {

			// 로그인 정보를 세션에 저장
			request.getSession().setAttribute("loginVO", resultVO);
			// 로그인 유형 정보를 세션에 저장
			request.getSession().setAttribute("userType", resultVO.getUserSe());
			// 로그인 인증세션
			request.getSession().setAttribute("accessUser", resultVO.getUserSe().concat(resultVO.getId()));

			// 메인 페이지로 리다이렉트
			return "redirect:/login/actionMain.do";
		} else {
			// 로그인 실패 시, 실패 메시지를 모델에 추가하고 로그인 페이지로 리다이렉트
			model.addAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login", request.getLocale()));
			return "redirect:/login/LoginUser.do";
		}
	}

	/**
	 * 애플리케이션의 메인 페이지로 이동하는 처리 로직을 수행한다.
	 *
	 * @param request - HTTP 요청 객체
	 * @param model   - 뷰에 전달할 데이터를 담는 모델 객체
	 * @return 메인 페이지의 뷰 이름 또는 설정된 페이지로 포워드
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/actionMain.do")
	public String actionMain(HttpServletRequest request, ModelMap model) throws Exception {
		// 인증된 사용자 정보를 가져옴
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 사용자 ID를 디버깅 로그로 출력 (2022 시큐어 코딩 적용)
		LOGGER.debug("User Id : {}", EgovStringUtil.isNullToString(user.getId()));

		// 메인 페이지로 이동
		String main_page = Globals.MAIN_PAGE;

		// 메인 페이지 설정 값을 디버깅 로그로 출력
		LOGGER.debug("Globals.MAIN_PAGE > " + Globals.MAIN_PAGE);
		LOGGER.debug("main_page > {}", main_page);

		// 메인 페이지가 "/"로 시작하는 경우, 해당 페이지로 포워드
		if (main_page.startsWith("/")) {
			return "forward:" + main_page;
		} else {
			// 설정된 메인 페이지가 있을 경우, 해당 페이지로 이동
			return main_page;
		}
	}

	/**
	 * 사용자 로그아웃을 처리한다.
	 *
	 * @param request - HTTP 요청 객체
	 * @param model   - 뷰에 전달할 데이터를 담는 모델 객체
	 * @return 로그아웃 후 이동할 페이지의 뷰 이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {

		// 세션에서 사용자 정보를 제거하여 로그아웃 처리
		request.getSession().setAttribute("loginVO", null);
		request.getSession().setAttribute("userType", null);
		request.getSession().setAttribute("accessUser", null);

		// 로그아웃 후 메인 컨텐츠 페이지로 리다이렉트
		return "redirect:/login/LoginUser.do";
	}

	/**
	 * 아이디 및 비밀번호 찾기 페이지로 이동한다.
	 *
	 * @param model - 뷰에 전달할 데이터를 담는 모델 객체
	 * @return 아이디 및 비밀번호 찾기 페이지의 뷰 이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/IdPasswordSearch.do")
	public String idPasswordSearchView(ModelMap model) throws Exception {
		// 아이디 및 비밀번호 찾기 페이지로 이동
		return "atos/login/IdPasswordSearch";
	}

	/**
	 * 사용자 ID를 찾는 처리 로직을 수행한다.
	 *
	 * @param loginVO - 사용자 이름, 이메일, 사용자 구분 정보를 담고 있는 LoginVO 객체
	 * @param model   - 뷰에 전달할 데이터를 담는 모델 객체
	 * @return 아이디 찾기 결과 페이지의 뷰 이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/searchId.do")
	public String searchId(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model) throws Exception {
		// 입력 값이 유효하지 않은 경우 에러 페이지로 이동
		if (loginVO == null || (loginVO.getName() == null || loginVO.getName().equals("")) && (loginVO.getEmail() == null || loginVO.getEmail().equals("")) && (loginVO.getUserSe() == null || loginVO.getUserSe().equals(""))) {
			return "egovframework/com/cmm/egovError";
		}

		// 1. 아이디 찾기 로직 실행
		loginVO.setName(loginVO.getName().replaceAll(" ", "")); // 이름에서 공백 제거
		LoginVO resultVO = loginService.searchId(loginVO);

		// 결과가 있는 경우 ID를 모델에 추가하고 결과 페이지로 이동
		if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {
			model.addAttribute("resultInfo", "아이디는 " + resultVO.getId() + " 입니다.");
			return "atos/login/IdPasswordResult";
		} else {
			// 결과가 없는 경우 실패 메시지를 모델에 추가하고 결과 페이지로 이동
			model.addAttribute("resultInfo", egovMessageSource.getMessage("fail.common.idsearch"));
			return "atos/login/IdPasswordResult";
		}
	}

	/**
	 * 사용자 비밀번호를 찾는 처리 로직을 수행한다.
	 *
	 * @param loginVO - 사용자 아이디, 이름, 이메일, 비밀번호 힌트, 힌트 답변 등을 담고 있는 LoginVO 객체
	 * @param model   - 뷰에 전달할 데이터를 담는 모델 객체
	 * @return 비밀번호 찾기 결과 페이지의 뷰 이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/searchPassword.do")
	public String searchPassword(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model) throws Exception {
		// 입력 값이 유효하지 않은 경우 에러 페이지로 이동
		if (loginVO == null || (loginVO.getId() == null || loginVO.getId().equals("")) && (loginVO.getName() == null || "".equals(loginVO.getName())) && (loginVO.getEmail() == null || loginVO.getEmail().equals("")) && (loginVO.getPasswordHint() == null || "".equals(loginVO.getPasswordHint())) && (loginVO.getPasswordCnsr() == null || "".equals(loginVO.getPasswordCnsr())) && (loginVO.getUserSe() == null || "".equals(loginVO.getUserSe()))) {
			return "egovframework/com/cmm/egovError";
		}

		// 비밀번호 찾기 로직 실행
		boolean result = loginService.searchPassword(loginVO);

		// 결과에 따라 적절한 메시지를 모델에 추가하고 결과 페이지로 이동
		if (result) {
			model.addAttribute("resultInfo", "임시 비밀번호를 발송하였습니다.");
			return "atos/login/IdPasswordResult";
		} else {
			model.addAttribute("resultInfo", egovMessageSource.getMessage("fail.common.pwsearch"));
			return "atos/login/IdPasswordResult";
		}
	}

}
