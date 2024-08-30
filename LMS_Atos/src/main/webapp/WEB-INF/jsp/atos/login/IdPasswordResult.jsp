<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="login.idPw.title" /> <spring:message code="login.idPw.result" /></title>
<!--  -->
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />" type="text/css">
<!--  -->
<script type="text/javascript">
	// ----------------------------------------------------------------------------------------------------
	//페이지 이동 함수: 특정 동작 후 사용자를 다른 페이지로 이동시키는 함수
	//-----------------------------------------------------------------------------------------------------

	/* fncGoAfterPage()
	 *
	 * 이 함수는 사용자가 이전 페이지로 돌아가도록 합니다.
	 * history.back(-2)는 브라우저 히스토리에서 두 단계를 뒤로 이동합니다.
	 */
	function fncGoAfterPage() {
		history.back(-2);
	}

	/* fncGoIdPwd()
	 *
	 * 이 함수는 사용자를 아이디/비밀번호 찾기 페이지로 이동시킵니다.
	 * location.href를 사용하여 지정된 URL로 페이지를 이동합니다.
	 */
	function fncGoIdPwd() {
		location.href = "<c:url value='/login/egovIdPasswordSearch.do'/>";
	}

	/* fncGoLogin()
	 *
	 * 이 함수는 사용자를 로그인 페이지로 이동시킵니다.
	 * location.href를 사용하여 지정된 URL로 페이지를 이동합니다.
	 */
	function fncGoLogin() {
		location.href = "<c:url value='/login/LoginUser.do'/>";
	}
</script>
</head>
<body>
	<!-- 자바스크립트 비활성화 경고 -->
	<noscript class="noScriptTitle">
		<spring:message code="common.noScriptTitle.msg" />
	</noscript>
	<!--  -->
<body>
	<div style="width: 1000px; margin: 50px auto 50px;">
		<!-- 페이지 상단 로고 영역 -->
		<p style="font-size: 18px; color: #000; margin-bottom: 10px;">
			<img src="<c:url value='/images/atos/logo.png' />" width="379" height="57" />
		</p>

		<!-- 컨텐츠 영역: 에러 메시지 및 버튼 -->
		<div style="border: 0px solid #666; padding: 20px;">
			<!-- 에러 메시지 표시 영역 -->
			<p style="color: red; margin-bottom: 8px;"></p>

			<!-- 메시지 및 버튼을 포함하는 박스 -->
			<div class="boxType1" style="width: 500px;">
				<div class="box">
					<div class="error">
						<!-- 페이지 제목 표시 -->
						<p class="title">${pageTitle}</p>

						<!-- 결과 정보 표시 -->
						<p class="cont mb20">${resultInfo}<br>
						</p>

						<!-- 아이디 및 비밀번호 찾기 버튼 -->
						<span class="btn_style1 blue">
							<a href="javascript:fncGoIdPwd();">
								<spring:message code="login.idPasswordResult.searchIdPwd" />
							</a>
						</span>

						<!-- 로그인 버튼 -->
						<span class="btn_style1 blue">
							<a href="javascript:fncGoLogin();">
								<spring:message code="login.loginForm.login" />
							</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>