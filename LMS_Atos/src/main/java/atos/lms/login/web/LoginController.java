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
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;

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
	 * 로그인 화면으로 들어간다
	 * 
	 * @param vo - 로그인후 이동할 URL이 담긴 LoginVO
	 * @return 로그인 페이지
	 * @exception Exception
	 */
	@IncludedInfo(name = "로그인2", listUrl = "/login/LoginUser.do", order =9, gid = 10)
	@RequestMapping(value = "/login/LoginUser.do")
	public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		if (EgovComponentChecker.hasComponent("mberManageService")) {
			model.addAttribute("useMemberManage", "true");
		}

		// 권한체크시 에러 페이지 이동
		String auth_error = request.getParameter("auth_error") == null ? ""
				: (String) request.getParameter("auth_error");
		if (auth_error != null && auth_error.equals("1")) {
			return "egovframework/com/cmm/error/accessDenied";
		}

		String message = (String) request.getParameter("loginMessage");
		if (message != null)
			model.addAttribute("loginMessage", message);

		return "login/LoginUser";
	}
}
