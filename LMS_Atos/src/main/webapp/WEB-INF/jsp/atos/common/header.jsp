<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/common/header.css' />">

<div class="header">
    <div class="logo">교육통합관리시스템</div>
    <nav class="header-links">
        <a href="#" class="menu-link" data-menu="member">회원정보관리</a>
        <a href="#" class="menu-link" data-menu="company">업체정보관리</a>
        <a href="#" class="menu-link" data-menu="education">교육정보관리</a>
        <a href="#" class="menu-link" data-menu="option4">메뉴4</a>
        <a href="#" class="menu-link" data-menu="option5">메뉴5</a>
    </nav>
    <div class="header-right">
        <span class="welcome-msg">운영자님, 환영합니다. 2024. 9. 2 오전 8:14:49</span>
        <a href="#" class="logout-btn">로그아웃</a>
        <a href="#" class="customer-center-btn">고객센터</a>
        <a href="#" class="site-btn">사이트</a>
    </div>
</div>

<script>

$(document).ready(function() {
    $('.menu-link').click(function(e) {
    	e.preventDefault(); // 기본 클릭 동작 방지
        $('.menu-link').removeClass('active'); // 모든 메뉴에서 active 클래스 제거
        $(this).addClass('active');
        
        var menuType = $(this).data('menu');
        $('.sidebar .menu-group').hide(); // 모든 메뉴를 숨기고
        if (menuType === 'member') {
            $('.sidebar .member-menu').show(); // 회원 관리 메뉴를 표시
        } else if (menuType === 'company') {
            $('.sidebar .company-menu').show(); // 업체 관리 메뉴를 표시
        } else if (menuType === 'education') {
            $('.sidebar .education-menu').show(); // 교육 관리 메뉴를 표시
        }
        // 필요한 다른 메뉴들에 대한 조건을 여기에 추가
    });

    // 사이드바의 하위 메뉴 토글
    $('.sidebar .menu-group h3').click(function() {
        $(this).next('ul').slideToggle(); // 클릭 시 하위 메뉴 슬라이드 토글
    });
});

</script>





<div id="loadingContainer">
    <div id="loadingIndicator">
        <img src="<c:url value='/images/atos/loading.gif'/>" alt="Loading..." />
    </div>
</div>