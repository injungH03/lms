<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="login.idPw.title" /></title>
<!--  -->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/idpw.css' />">
<!--  -->
<script type="text/javascript">
	//----------------------------------------------------------------------------------------------------
	//사용자 유형 선택 함수: 아이디 및 비밀번호 찾기 페이지에서 사용자 유형을 선택하는 함수
	//----------------------------------------------------------------------------------------------------

	/* fnCheckUsrId(userSe)
	 * - userSe: 선택된 사용자 유형 (문자열)
	 *
	 * 이 함수는 사용자가 아이디 찾기 페이지에서 사용자 유형(수강생, 강사, 관리자) 중 하나를 선택했을 때 호출됩니다.
	 * 선택된 유형에 따라 화면을 업데이트하고, 폼의 userSe 필드에 해당 유형을 설정합니다.
	 */
	function fnCheckUsrId(userSe) {
		if (userSe == "STU") { // 수강생
			document.getElementById("idStu").className = "on";
			document.getElementById("idIns").className = "";
			document.getElementById("idAdm").className = "";
			document.idForm.userSe.value = "STU";
		} else if (userSe == "INS") { // 강사
			document.getElementById("idStu").className = "";
			document.getElementById("idIns").className = "on";
			document.getElementById("idAdm").className = "";
			document.idForm.userSe.value = "INS";
		} else if (userSe == "ADM") { // 관리자
			document.getElementById("idStu").className = "";
			document.getElementById("idIns").className = "";
			document.getElementById("idAdm").className = "on";
			document.idForm.userSe.value = "ADM";
		}
	}

	/* fnCheckUsrPassword(userSe)
	 * - userSe: 선택된 사용자 유형 (문자열)
	 *
	 * 이 함수는 사용자가 비밀번호 찾기 페이지에서 사용자 유형(수강생, 강사, 관리자) 중 하나를 선택했을 때 호출됩니다.
	 * 선택된 유형에 따라 화면을 업데이트하고, 폼의 userSe 필드에 해당 유형을 설정합니다.
	 */
	function fnCheckUsrPassword(userSe) {
		if (userSe == "STU") { // 수강생
			document.getElementById("pwStu").className = "on";
			document.getElementById("pwIns").className = "";
			document.getElementById("pwAdm").className = "";
			document.passwordForm.userSe.value = "STU";
		} else if (userSe == "INS") { // 강사
			document.getElementById("pwStu").className = "";
			document.getElementById("pwIns").className = "on";
			document.getElementById("pwAdm").className = "";
			document.passwordForm.userSe.value = "INS";
		} else if (userSe == "ADM") { // 관리자
			document.getElementById("pwStu").className = "";
			document.getElementById("pwIns").className = "";
			document.getElementById("pwAdm").className = "on";
			document.passwordForm.userSe.value = "ADM";
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// 아이디 및 비밀번호 찾기 처리 함수: 사용자가 입력한 정보를 바탕으로 아이디 또는 비밀번호를 찾는 함수
	// ----------------------------------------------------------------------------------------------------

	/* fnSearchId()
	 *
	 * 이 함수는 사용자가 아이디 찾기 페이지에서 '찾기' 버튼을 클릭했을 때 호출됩니다.
	 * 이름과 이메일이 입력되지 않은 경우 경고 메시지를 표시합니다.
	 * 입력이 올바른 경우 폼을 제출하여 서버로 전송합니다.
	 */
	function fnSearchId() {
		if (document.idForm.name.value == "") {
			alert("<spring:message code='login.idPw.validate.name' />");
		} else if (document.idForm.email.value == "") {
			alert("<spring:message code='login.idPw.validate.email' />");
		} else {
			document.idForm.submit();
		}
	}

	/* fnSearchPassword()
	 *
	 * 이 함수는 사용자가 비밀번호 찾기 페이지에서 '찾기' 버튼을 클릭했을 때 호출됩니다.
	 * 아이디, 이름, 이메일이 입력되지 않은 경우 경고 메시지를 표시합니다.
	 * 입력이 올바른 경우 폼을 제출하여 서버로 전송합니다.
	 */
	function fnSearchPassword() {
		if (document.passwordForm.id.value == "") {
			alert("<spring:message code='login.idPw.validate.id' />");
		} else if (document.passwordForm.name.value == "") {
			alert("<spring:message code='login.idPw.validate.name' />");
		} else if (document.passwordForm.email.value == "") {
			alert("<spring:message code='login.idPw.validate.email' />");
		} else {
			document.passwordForm.submit();
		}
	}
</script>
</head>
<body>
	<!-- 자바스크립트 비활성화 경고  -->
	<noscript class="noScriptTitle">
		<spring:message code="common.noScriptTitle.msg" />
	</noscript>
	<!-- 아이디/비밀번호 찾기-->
	<div class="idpw_form">
		<!-- 아이디 찾기 -->
		<fieldset class="id_search">
			<form name="idForm" action="<c:url value='/losgin/searchId.do'/>" method="post">
				<legend>
					<spring:message code="login.idPw.searchId" />
				</legend>
				<h2>
					<spring:message code="login.idPw.searchId" />
				</h2>

				<!-- 사용자 유형 탭 -->
				<div class="login_type">
					<ul>
						<li>
							<a id="idStu" onclick="fnCheckUsrId('STU');" class="on">
								<spring:message code="login.idPw.STU" />
							</a>
						</li>
						<li>
							<a id="idIns" onclick="fnCheckUsrId('INS');">
								<spring:message code="login.idPw.INS" />
							</a>
						</li>
						<li>
							<a id="idAdm" onclick="fnCheckUsrId('ADM');">
								<spring:message code="login.idPw.ADM" />
							</a>
						</li>
					</ul>
				</div>

				<!-- 아이디 입력 필드 -->
				<div class="login_input">
					<ul>
						<li>
							<label for="name"><spring:message code="login.idPw.name" /></label>
							<input type="text" name="name" maxlength="20" title="<spring:message code='login.idPw.name' />" placeholder="<spring:message code='login.idPw.name' />" />
						</li>
						<li>
							<label for="email"><spring:message code="login.idPw.email" /></label>
							<input type="text" name="email" maxlength="30" title="<spring:message code='login.idPw.email' />" placeholder="<spring:message code='login.idPw.email' />" />
						</li>
						<li>
							<input type="button" class="btn_login" onclick="fnSearchId();" value="<spring:message code='login.idPw.searchId' />" />
						</li>
					</ul>
				</div>
				<input name="userSe" type="hidden" value="STU">
			</form>
		</fieldset>
		<!-- 비밀번호 찾기 -->
		<fieldset class="pw_search">
			<form name="passwordForm" action="<c:url value='/login/searchPassword.do'/>" method="post">
				<legend>
					<spring:message code="login.idPw.searchPassword" />
				</legend>
				<h2>
					<spring:message code="login.idPw.searchPassword" />
				</h2>

				<!-- 사용자 유형 탭 -->
				<div class="login_type">
					<ul>
						<li>
							<a id="pwStu" onclick="fnCheckUsrPassword('STU');" class="on">
								<spring:message code="login.idPw.STU" />
							</a>
						</li>
						<li>
							<a id="pwIns" onclick="fnCheckUsrPassword('INS');">
								<spring:message code="login.idPw.INS" />
							</a>
						</li>
						<li>
							<a id="pwAdm" onclick="fnCheckUsrPassword('ADM');">
								<spring:message code="login.idPw.ADM" />
							</a>
						</li>
					</ul>
				</div>

				<!-- 비밀번호 입력 필드 -->
				<div class="login_input">
					<ul>
						<li>
							<label for="id"><spring:message code="login.idPw.id" /></label>
							<input type="text" name="id" maxlength="15" title="<spring:message code='login.idPw.id' />" placeholder="<spring:message code='login.idPw.id' />" />
						</li>
						<li>
							<label for="name"><spring:message code="login.idPw.name" /></label>
							<input type="text" name="name" maxlength="20" title="<spring:message code='login.idPw.name' />" placeholder="<spring:message code='login.idPw.name' />" />
						</li>
						<li>
							<label for="email"><spring:message code="login.idPw.email" /></label>
							<input type="text" name="email" maxlength="30" title="<spring:message code='login.idPw.email' />" placeholder="<spring:message code='login.idPw.email' />" />
						</li>
						<li>
							<input type="button" class="btn_login" onclick="fnSearchPassword();" value="<spring:message code='login.idPw.searchPassword' />">
						</li>
					</ul>
				</div>
				<input name="userSe" type="hidden" value="STU">
			</form>
		</fieldset>
	</div>
</body>
</html>