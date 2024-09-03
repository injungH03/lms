<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/common/header.css' />">

<div class="header">
	<a href="<spring:message code="header.site.url" />" >
		<img src="<c:url value='/images/atos/logo.png'/>" style="height: 5vh;" alt="title logo" title="title logo">
	</a>
	<div class="logo">
		<spring:message code="header.logo.title" />
	</div>
	<nav class="header-links">
		<c:forEach var="menuItem" items="${menuItems}">
	        <a href="${menuItem.value.url}" class="menu-link" data-menu="${menuItem.value.data}">
	            ${menuItem.value.title}
	        </a>
	    </c:forEach>
	</nav>
	<div class="header-right">
		<span class="welcome-msg"><spring:message code="header.login.msg" arguments="${sessionScope.loginVO.name}" /></span>
		<a href="<spring:message code="header.logout.url" />" class="logout-btn"><spring:message code="header.logout.title" /></a>
		<a href="<spring:message code="header.center.url" />" class="customer-center-btn"><spring:message code="header.center.title" /></a>
		<a href="<spring:message code="header.site.url" />" class="site-btn"><spring:message code="header.site.title" /></a>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 현재 URL 기반으로 헤더의 메뉴와 사이드바 메뉴 설정
		var currentUrl = window.location.pathname;

		$('.menu-link').each(function() {
			var menuType = $(this).data('menu');

			// menuType과 currentUrl을 비교하여 메뉴를 활성화
			if (currentUrl.includes(menuType)) {
				$(this).addClass('active');

				// 사이드바 메뉴 표시
				$('.sidebar .menu-group').hide(); // 모든 메뉴 숨기기

				$('.sidebar .' + menuType + '-menu').show();

			}
		});

		// 헤더 메뉴 클릭 시 active 상태 관리
		$('.menu-link').click(function(e) {
			e.preventDefault(); // 기본 클릭 동작 방지
			$('.menu-link').removeClass('active'); // 모든 메뉴에서 active 클래스 제거
			$(this).addClass('active');

			var menuType = $(this).data('menu');
			$('.sidebar .menu-group').hide(); // 모든 메뉴를 숨기고
			$('.sidebar .' + menuType + '-menu').show(); // 해당 메뉴를 표시
		});

		// 사이드바의 하위 메뉴 토글
		$('.sidebar .menu-group h3').click(function() {
			$(this).next('ul').slideToggle(); // 클릭 시 하위 메뉴 슬라이드 토글
		});
	});
</script>