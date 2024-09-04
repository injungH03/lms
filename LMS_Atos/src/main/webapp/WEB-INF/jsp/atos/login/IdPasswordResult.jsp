<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="login.idPw.title" /> <spring:message code="login.idPw.result" /></title>
<!--  -->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/login/login.css' />">
<!--  -->
<script type="text/javascript">
	// ----------------------------------------------------------------------------------------------------
	//페이지 이동 함수: 특정 동작 후 사용자를 다른 페이지로 이동시키는 함수
	//-----------------------------------------------------------------------------------------------------

	/* fncGoIdPwd()
	 *
	 * 이 함수는 사용자를 아이디/비밀번호 찾기 페이지로 이동시킵니다.
	 * location.href를 사용하여 지정된 URL로 페이지를 이동합니다.
	 */
	function fncGoIdPwd() {
		location.href = "<c:url value='/login/IdPasswordSearch.do'/>";
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
	<!-- 아이디/비밀번호 찾기 결과 -->
	<div class="result_form">
		<fieldset>
			<!-- 로고 -->
			<img class="logoImg" src="<c:url value='/images/atos/logo.png'/>" alt="login title image" title="login title image">
			<!-- 메시지 및 버튼을 포함하는 박스 -->
			<div class="result_box">
				<div class="box">
					<div class="error">
						<!-- 페이지 제목 표시 -->
						<p class="title">${pageTitle}</p>

						<!-- 결과 정보 표시 -->
						<p class="cont">${resultInfo}<br>
						</p>

						<!-- 아이디 및 비밀번호 찾기 버튼 -->
						<span class="result_btn">
							<a href="javascript:fncGoIdPwd();">
								<spring:message code="login.idPasswordResult.searchIdPwd" />
							</a>
						</span>

						<!-- 로그인 버튼 -->
						<span class="result_btn">
							<a href="javascript:fncGoLogin();">
								<spring:message code="login.loginForm.login" />
							</a>
						</span>

					</div>
				</div>
			</div>
		</fieldset>
	</div>
</body>
</html>