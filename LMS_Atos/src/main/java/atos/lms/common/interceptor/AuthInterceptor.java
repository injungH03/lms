package atos.lms.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 세션에서 로그인 유형 가져오기
		String userType = (String) request.getSession().getAttribute("userType");

		// 접근하려는 URI 가져오기
		String requestURI = request.getRequestURI();

		// 모든 URI에 대해 접근 허용 조건 <<-- 삭제 해야됨.
        if (request.getRequestURI().startsWith("/")) {
            return true;
        }
		
		/**
		 * 페이지 접근 예외 처리
		 * 
		 * 1. 로그인 관련 <br>
		 * 
		 */
		if (requestURI.startsWith("/login")) { // requestURI.startsWith("/login") || requestURI.startsWith("/test") << 예시
			return true;
		}

		// 로그인 유형에 따른 접근 권한 설정
		if ("STU".equals(userType)) { // 수강생
			// /student/로 시작하는 URL만 접근 가능
			if (requestURI.startsWith("/student") || requestURI.startsWith("/member")) {
				return true;
			} else {
				return false;
			}
		} else if ("INS".equals(userType)) { // 강사
			// /instructor/로 시작하는 URL만 접근 가능
			if (requestURI.startsWith("/instructor")) {
				return true;
			} else {
				return false;
			}
		} else if ("COM".equals(userType)) { // 업체
			// /company/로 시작하는 URL만 접근 가능
			if (requestURI.startsWith("/company")) {
				return true;
			} else {
				return false;
			}
		} else if ("ADM".equals(userType)) { // 관리자
			// 모든 URL 접근 가능
			return true;
		} else {
			// 로그인되지 않은 상태거나 잘못된 유형일 경우
			response.sendRedirect("/login/LoginUser.do");
			return false;
		}
	}
}
