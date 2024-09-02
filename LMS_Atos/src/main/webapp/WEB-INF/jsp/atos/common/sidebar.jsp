<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/common/sidebar.css' />">
<div class="sidebar">
    <div class="menu-group member-menu">
        <h3>회원관리</h3>
        <ul>
            <li><a href="<c:url value='/member/memberList.do'/>">회원목록</a></li>
            <li><a href="#">강사목록</a></li>
            <li><a href="#">회원속성관리</a></li>
        </ul>
    </div>
    <div class="menu-group company-menu" style="display:none;">
        <h3>업체관리</h3>
        <ul>
            <li><a href="/company/companyList.do">업체목록</a></li>
            <li><a href="#">메뉴2</a></li>
            <li><a href="#">메뉴3</a></li>
        </ul>
    </div>
    <div class="menu-group education-menu" style="display:none;">
        <h3>교육정보관리</h3>
        <ul>
            <li><a href="#">교육목록</a></li>
            <li><a href="#">메뉴2</a></li>
            <li><a href="#">메뉴3</a></li>
        </ul>
    </div>
</div>

<script>
$(document).ready(function() {
    $('.sidebar .menu-group h3').off('click').on('click', function(event) {
        event.preventDefault(); // 기본 클릭 동작 방지
        event.stopPropagation(); // 이벤트 전파 방지

        var $submenu = $(this).next('ul');

        // 모든 서브메뉴 닫기 (현재 클릭한 메뉴 제외)
        $('.sidebar .menu-group ul').not($submenu).slideUp();

        // 클릭한 메뉴 토글
        $submenu.stop(true, true).slideToggle();
    });
    
    // 현재 URL에 따라 사이드바 메뉴 유지
    var currentUrl = window.location.href;

    $('.sidebar .menu-group ul li a').each(function() {
        if (currentUrl.includes($(this).attr('href'))) {
            $(this).closest('ul').show(); // 해당 메뉴를 펼침
        }
    });
});
</script>
