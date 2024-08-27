<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<!-- 페이지 제목 : resources/egovframework/message/com/uat/uia/message_ko.properties -->
<title><spring:message code="comUatUia.title" /></title>
<!-- 문자 인코딩: UTF-8 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<!-- Style Sheet & Script -->
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/login.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>"></script>
<!-- Class JS -->
<script type="text/javaScript">
	// 쿠키 설정 (set & get)
	function setCookie(name, value, expires) {
		document.cookie = name + "=" + escape(value) + "; path=/; expires="
				+ expires.toGMTString();
	}
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
	// 페이지 로드때 실행되는 초기화 함수
	function fnInit() {
		// 쿠키 아이디 확인 함수 실행
		getid(document.loginForm);
		// 로그인 유형 선택 함수 실행 -> 수강행(typeStu), 강사(typeIns), 관리자(typeAdm)
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

	// 쿠키에 아디지 저장하기
	function saveid(form) {
		var expdate = new Date();
		if (form.checkId.checked)
			expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30);
		else
			expdate.setTime(expdate.getTime() - 1);
		setCookie("saveid", form.id.value, expdate);
	}

	// 쿠키에 저장된 아이디 값 확인 -> form에 자동 입력
	function getid(form) {
		form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
	}

	// 로그인 유형 함수 -> 화면 업데이트 & form의 userSe 값 설정
	function fnLoginTypeSelect(objName) {
		// 세가지 유형 클래스 비우기
		document.getElementById("typeStu").className = "";
		document.getElementById("typeIns").className = "";
		document.getElementById("typeAdm").className = "";
		// 선택된 유형 적용
		document.getElementById(objName).className = "on";
		// userSe 값 세팅
		if (objName == "typeStu") { //일반회원
			document.loginForm.userSe.value = "STU";
		} else if (objName == "typeIns") { //기업회원
			document.loginForm.userSe.value = "INS";
		} else if (objName == "typeAdm") { //업무사용자
			document.loginForm.userSe.value = "ADM";
		}
	}
	
	// 무조건 일반 로그인
	function fnShowLogin(stat) {
		$(".login_input").eq(0).show();
		$(".login_input").eq(1).hide();
	}

	// 사용자 유형 -> userSe 값 설정
	function checkLogin(userSe) {
		// 일반회원
		if (userSe == "STU") {
			document.loginForm.rdoSlctUsr[0].checked = true;
			document.loginForm.rdoSlctUsr[1].checked = false;
			document.loginForm.rdoSlctUsr[2].checked = false;
			document.loginForm.userSe.value = "STU";
			// 기업회원
		} else if (userSe == "INS") {
			document.loginForm.rdoSlctUsr[0].checked = false;
			document.loginForm.rdoSlctUsr[1].checked = true;
			document.loginForm.rdoSlctUsr[2].checked = false;
			document.loginForm.userSe.value = "INS";
			// 업무사용자
		} else if (userSe == "ADM") {
			document.loginForm.rdoSlctUsr[0].checked = false;
			document.loginForm.rdoSlctUsr[1].checked = false;
			document.loginForm.rdoSlctUsr[2].checked = true;
			document.loginForm.userSe.value = "ADM";
		}
	}

	// 로그인 요청
	function actionLogin() {
		if (document.loginForm.id.value == "") {
			// 아이디를 입력하세요.
			alert("<spring:message code="comUatUia.validate.idCheck" />");
		} else if (document.loginForm.password.value == "") {
			// 비밀번호를 입력하세요.
			alert("<spring:message code="comUatUia.validate.passCheck" />");
		} else {
			document.loginForm.action = "<c:url value='/login/actionLogin.do'/>";
			document.loginForm.submit();
		}
	}

	// 회원가입
	function goRegiUsr() {
		// 사용자 관리 컴포넌트 사용 여부
		var useMemberManage = '${useMemberManage}';
		if (useMemberManage != 'true') {
			// 사용자 관리 컴포넌트가 설치되어 있지 않습니다. 관리자에게 문의하세요.
			alert("<spring:message code="comUatUia.validate.userManagmentCheck" />");
			return false;
		}
		// 로그인 유형
		var userSe = document.loginForm.userSe.value;
		if (userSe == "STU") {
			// 수강생
			document.loginForm.action = "<c:url value='/uss/umt/EgovStplatCnfirmMber.do'/>";
			document.loginForm.submit();
		} else if (userSe == "INS") {
			// 강사
			document.loginForm.action = "<c:url value='/uss/umt/EgovStplatCnfirmEntrprs.do'/>";
			document.loginForm.submit();
		} else if (userSe == "ADM") {
			// 관리자
			// 업무사용자는 별도의 회원가입이 필요하지 않습니다.
			alert("<spring:message code='comUatUia.validate.membershipCheck' />");
		}
	}

	// 아이디&비밀번호 찾기
	function goFindId() {
		document.defaultForm.action = "<c:url value='/uat/uia/egovIdPasswordSearch.do'/>";
		document.defaultForm.submit();
	}
</script>
</head>
<body onLoad="fnInit();">
	<!-- javascript warning tag  -->
	<noscript class="noScriptTitle">
		<spring:message code="common.noScriptTitle.msg" />
	</noscript>

	<!-- 로그인 -->
	<div class="login_form">
		<form name="loginForm" id="loginForm" action="<c:url value='/login/actionLogin.do'/>" method="post">
			<!-- hidden input tag -->
			<input type="hidden" id="message" name="message" value="<c:out value='${message}'/>"> <input name="userSe" type="hidden" value="GNR" /> <input name="j_username" type="hidden" />
			<!--  -->
			<fieldset>
				<!-- 로고 -->
				<img src="<c:url value='/images/atos/logo.png'/>" style="margin: 30px 0 0px 60px" alt="login title image" title="login title image">
				<!-- 로그인 유형 탭 -->
				<div class="login_type">
					<ul id="ulLoginType">
						<li><a href="javascript:fnLoginTypeSelect('typeStu');" id="typeStu" title=""><spring:message code="comUatUia.loginForm.STU" /></a></li>
						<!-- 일반 -->
						<li><a href="javascript:fnLoginTypeSelect('typeIns');" id="typeIns" title=""><spring:message code="comUatUia.loginForm.INS" /></a></li>
						<!-- 기업 -->
						<li><a href="javascript:fnLoginTypeSelect('typeAdm');" id="typeAdm" title=""><spring:message code="comUatUia.loginForm.ADM" /></a></li>
						<!-- 업무 -->
					</ul>
				</div>
				<!-- 로그인 입력 -->
				<div class="login_input">
					<ul>
						<!-- 아이디 -->
						<c:set var="title">
							<spring:message code="comUatUia.loginForm.id" />
						</c:set>
						<li><label for="id">${title}</label> <input type="text" name="id" id="id" maxlength="20" title="${title} ${inputTxt}" placeholder="${title} ${inputTxt}"></li>
						<!-- 비밀번호 -->
						<c:set var="title">
							<spring:message code="comUatUia.loginForm.pw" />
						</c:set>
						<li><label for="password">${title}</label> <input type="password" name="password" id="password" maxlength="20" title="${title} ${inputTxt}" placeholder="${title} ${inputTxt}"></li>
						<!-- 아이디 저장 -->
						<c:set var="title">
							<spring:message code="comUatUia.loginForm.idSave" />
						</c:set>
						<li class="chk"><input type="checkbox" name="checkId" class="check2" onclick="javascript:saveid(document.loginForm);" id="checkId">${title}</li>
						<!-- 로그인 버튼 -->
						<li><input type="button" class="btn_login" value="<spring:message code="comUatUia.loginForm.login"/>" onclick="actionLogin()"></li>
						<li>
							<ul class="btn_idpw">
								<!-- 회원가입 -->
								<li><a href="#" onclick="goRegiUsr(); return false;"><spring:message code="comUatUia.loginForm.regist" /></a></li>
								<!-- 아이디&비밀번호 찾기 -->
								<li><a href="#" onclick="goFindId(); return false;"><spring:message code="comUatUia.loginForm.idPwSearch" /></a></li>
							</ul>
						</li>
					</ul>
				</div>
			</fieldset>
		</form>
	</div>
	<!-- 팝업 -->
	<form name="defaultForm" action="" method="post" target="_blank">
		<div style="visibility: hidden; display: none;">
			<input name="iptSubmit3" type="submit" value="<spring:message code="comUatUia.loginForm.submit"/>" title="<spring:message code="comUatUia.loginForm.submit"/>">
		</div>
	</form>
</body>
</html>
