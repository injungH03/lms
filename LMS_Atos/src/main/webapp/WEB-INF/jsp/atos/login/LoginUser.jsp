<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="login.title" /></title>
<!--  -->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/login.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>"></script>
<!--  -->
<script type="text/javaScript">
	// ----------------------------------------------------------------------------------------------------
	// 유틸리티 함수: 공통적으로 사용되는 작은 기능을 제공하는 함수
	// ----------------------------------------------------------------------------------------------------
	
	/* setCookie(name, value, expires)
	 * - name: 쿠키의 이름 (문자열)
	 * - value: 쿠키에 저장할 값 (문자열)
	 * - expires: 쿠키의 만료 시간 (Date 객체)
	 * 
	 * 이 함수는 주어진 이름과 값을 가진 쿠키를 설정하며, 만료 날짜를 지정할 수 있습니다.
	 */
	function setCookie(name, value, expires) {
		document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expires.toGMTString();
	}
	
	
	/* getCookie(Name)
	 * - Name: 가져올 쿠키의 이름 (문자열)
	 * 
	 * 이 함수는 주어진 이름의 쿠키 값을 가져옵니다. 쿠키가 존재하면 해당 값을 반환하고, 그렇지 않으면 빈 문자열을 반환합니다.
	 */
	function getCookie(Name) {
		var search = Name + "=";
		if (document.cookie.length > 0) {
			offset = document.cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if (end == -1)
					end = document.cookie.length;
				return unescape(document.cookie.substring(offset, end));
			}
		}
		return "";
	}
	
	// ----------------------------------------------------------------------------------------------------
	// 페이지 초기화 함수: 페이지 로드 시 실행되는 초기화 작업을 수행하는 함수
	// ----------------------------------------------------------------------------------------------------
	
	/* fnInit()
	 *
	 * 이 함수는 페이지가 로드될 때 실행됩니다. 
	 * 쿠키에 저장된 아이디를 가져와서 폼에 자동으로 입력하고, 기본 사용자 유형을 설정합니다.
	 * 서버에서 전송된 메시지가 있을 경우 이를 경고창으로 표시합니다.
	 * 상위 프레임이 없는 경우 콘솔에 경고 메시지를 로그로 기록하고, 최상위 프레임 페이지를 다시 로드합니다.
	 */
	function fnInit() {
		// 쿠키 아이디 확인 함수 실행
		getid(document.loginForm);
		// 사용자 유형 선택 함수 실행 -> 수강생(typeStu), 강사(typeIns), 관리자(typeAdm)
		fnLoginTypeSelect("typeStu");
		
		// 서버에서 전송된 message 있는 경우 표시
		<c:if test="${not empty fn:trim(loginMessage) &&  loginMessage ne ''}">
	    	alert("loginMessage:<c:out value='${loginMessage}'/>");
	    </c:if>
	    
	    // 상위 프레인이 없는 경우 콘솔에 로그
	    if (parent.frames["_top"] == undefined)
	    	console.log("'_top' frame is not exist!");
	    // 최상위 프레임 페이지 다시 로드
	    parent.frames["_top"].location.reload();
	}

	// ----------------------------------------------------------------------------------------------------
	// 쿠키 관련 함수: 쿠키에 아이디를 저장하고 불러오는 함수
	// ----------------------------------------------------------------------------------------------------
	
	/* saveid(form)
	 * - form: 로그인 폼 객체 (HTMLFormElement)
	 * 
	 * 이 함수는 사용자가 아이디 저장 옵션을 선택했는지 확인하고, 선택한 경우 아이디를 쿠키에 저장합니다.
	 * 아이디 저장이 선택되지 않은 경우 기존 쿠키를 만료시킵니다.
	 */
	function saveid(form) {
		var expdate = new Date();
		if (form.checkId.checked)
			expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 쿠키 만료 시간: 30일
		else
			expdate.setTime(expdate.getTime() - 1); // 쿠키 즉시 만료
		setCookie("saveid", form.id.value, expdate);
	}

	/* getid(form)
	 * - form: 로그인 폼 객체 (HTMLFormElement)
	 * 
	 * 이 함수는 쿠키에 저장된 아이디를 가져와 폼의 아이디 입력 필드에 자동으로 입력합니다.
	 * 만약 쿠키에 아이디가 저장되어 있다면, 아이디 저장 체크박스도 체크 상태로 설정됩니다.
	 */
	function getid(form) {
		form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
	}

	// ----------------------------------------------------------------------------------------------------
	// 사용자 유형 관련 함수: 사용자 유형을 선택하거나 보여주는 함수
	// ----------------------------------------------------------------------------------------------------
	
	/* fnLoginTypeSelect(objName)
	 * - objName: 선택된 사용자 유형의 ID (문자열)
	 * 
	 * 이 함수는 사용자가 사용자 유형(수강생, 강사, 관리자) 중 하나를 선택했을 때 호출됩니다.
	 * 선택된 유형에 따라 화면을 업데이트하고, 폼의 userSe 필드에 해당 유형을 설정합니다.
	 */
	function fnLoginTypeSelect(objName) {
		// 세가지 유형 클래스 비우기
		document.getElementById("typeStu").className = "";
		document.getElementById("typeIns").className = "";
		document.getElementById("typeAdm").className = "";
		// 선택된 유형 적용
		document.getElementById(objName).className = "on";
		// userSe 값 세팅
		if (objName == "typeStu") { // 수강생
			document.loginForm.userSe.value = "STU";
		} else if (objName == "typeIns") { // 강사
			document.loginForm.userSe.value = "INS";
		} else if (objName == "typeAdm") { // 관리자
			document.loginForm.userSe.value = "ADM";
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// 로그인 및 회원가입 처리 함수: 로그인 및 회원가입, 아이디/비밀번호 찾기 등의 구체적인 동작을 수행하는 함수
	// ----------------------------------------------------------------------------------------------------
	
	/* actionLogin()
	 * 
	 * 이 함수는 사용자가 로그인 버튼을 클릭했을 때 호출됩니다.
	 * 아이디 또는 비밀번호가 입력되지 않은 경우 경고 메시지를 표시합니다.
	 * 입력이 올바른 경우 로그인 폼을 제출하여 서버로 전송합니다.
	 */
	function actionLogin() {
		if (document.loginForm.id.value == "") {
			// 아이디를 입력하세요.
			alert("<spring:message code="login.validate.idCheck" />");
		} else if (document.loginForm.password.value == "") {
			// 비밀번호를 입력하세요.
			alert("<spring:message code="login.validate.passCheck" />");
		} else {
			document.loginForm.action = "<c:url value='/login/actionLogin.do'/>";
			document.loginForm.submit();
		}
	}

	/* goRegiUsr()
	 * 
	 * 이 함수는 사용자가 회원가입 버튼을 클릭했을 때 호출됩니다.
	 * 사용자 관리 컴포넌트가 설치되어 있는지 확인하고, 설치되지 않은 경우 경고 메시지를 표시합니다.
	 * 사용자 유형에 따라 회원가입 페이지로 리다이렉트합니다.
	 */
	function goRegiUsr() {
		// 사용자 관리 컴포넌트 사용 여부
		var useMemberManage = '${useMemberManage}';
		if (useMemberManage != 'true') {
			// 사용자 관리 컴포넌트가 설치되어 있지 않습니다. 관리자에게 문의하세요.
			alert("<spring:message code="login.validate.userManagmentCheck" />");
			return false;
		}
		// 사용자 유형에 따른 회원가입 페이지로 이동
		var userSe = document.loginForm.userSe.value;
		if (userSe == "STU") {
			// 수강생 회원가입 페이지로 이동
			document.loginForm.action = "<c:url value='/uss/umt/EgovStplatCnfirmMber.do'/>";
			document.loginForm.submit();
		} else if (userSe == "INS") {
			// 강사 회원가입 불필요 메시지 표시
			alert("<spring:message code='login.validate.instructorCheck' />");
		} else if (userSe == "ADM") {
			// 관리자 회원가입 불필요 메시지 표시
			alert("<spring:message code='login.validate.administratorCheck' />");
		}
	}

	/* goFindId()
	 * 
	 * 이 함수는 사용자가 '아이디 & 비밀번호 찾기' 링크를 클릭했을 때 호출됩니다.
	 * 아이디/비밀번호 찾기 페이지로 리다이렉트합니다.
	 */
	function goFindId() {
		document.defaultForm.action = "<c:url value='/uat/uia/egovIdPasswordSearch.do'/>";
		document.defaultForm.submit();
	}
</script>
</head>
<body onLoad="fnInit();">
	<!-- 자바스크립트 비활성화 경고  -->
	<noscript class="noScriptTitle">
		<spring:message code="common.noScriptTitle.msg" />
	</noscript>
	<!-- 로그인 폼 -->
	<div class="login_form">
		<form name="loginForm" id="loginForm" action="<c:url value='/login/actionLogin.do'/>" method="post" onsubmit="return actionLogin();">
			<!-- 숨겨진 입력 필드 -->
			<input type="hidden" id="message" name="message" value="<c:out value='${message}'/>">
			<input name="userSe" type="hidden" value="GNR" />
			<input name="j_username" type="hidden" />
			<!--  -->
			<fieldset>
				<!-- 로고 -->
				<img src="<c:url value='/images/atos/logo.png'/>" style="margin: 30px 0 0px 60px" alt="login title image" title="login title image">
				<!-- 사용자 유형 탭 -->
				<div class="login_type">
					<ul id="ulLoginType">
						<li>
							<a href="#" onclick="fnLoginTypeSelect('typeStu'); return false;" id="typeStu" title="수강생">
								<spring:message code="login.loginForm.STU" />
							</a>
						</li>
						<li>
							<a href="#" onclick="fnLoginTypeSelect('typeIns'); return false;" id="typeIns" title="강사">
								<spring:message code="login.loginForm.INS" />
							</a>
						</li>
						<li>
							<a href="#" onclick="fnLoginTypeSelect('typeAdm'); return false;" id="typeAdm" title="관리자">
								<spring:message code="login.loginForm.ADM" />
							</a>
						</li>
					</ul>
				</div>
				<!-- 로그인 입력 필드 -->
				<div class="login_input">
					<ul>
						<!-- 아이디 -->
						<li>
							<c:set var="title">
								<spring:message code="login.loginForm.id" />
							</c:set>
							<label for="id">${title}</label>
							<input type="text" name="id" id="id" maxlength="20" title="${title}" placeholder="${title}" autocomplete="username">
						</li>

						<!-- 비밀번호 -->
						<li>
							<c:set var="title">
								<spring:message code="login.loginForm.pw" />
							</c:set>
							<label for="password">${title}</label>
							<input type="password" name="password" id="password" maxlength="20" title="${title}" placeholder="${title}" autocomplete="current-password">
						</li>

						<!-- 아이디 저장 -->
						<li class="chk">
							<c:set var="title">
								<spring:message code="login.loginForm.idSave" />
							</c:set>
							<input type="checkbox" name="checkId" class="check2" onclick="saveid(document.loginForm);" id="checkId">${title}
						</li>

						<!-- 로그인 버튼: input을 button으로 바꾸고 싶었지만 안됨. -->
						<li>
							<input type="button" class="btn_login" value="<spring:message code="login.loginForm.login"/>" onclick="actionLogin()">
						</li>

						<!-- 회원가입 및 아이디/비밀번호 찾기 -->
						<li>
							<ul class="btn_idpw">
								<li>
									<a href="#" onclick="goRegiUsr(); return false;">
										<spring:message code="login.loginForm.regist" />
									</a>
								</li>
								<li>
									<a href="#" onclick="goFindId(); return false;">
										<spring:message code="login.loginForm.idPwSearch" />
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>
