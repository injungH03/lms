<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/common/sidebar.css' />">
<div class="sidebar">
    <div class="menu-group member-menu" data-url="/member">
        <h3 id="memberManagement">회원관리</h3>
        <ul>
            <li><a href="<c:url value='/member/memberList.do'/>">회원목록</a></li>
            <li><a href="#">강사목록</a></li>
            <li><a href="#">회원속성관리</a></li>
        </ul>
        <h3 id="menu1">상위메뉴1</h3>
        <ul>
            <li><a href="#">하위메뉴1</a></li>
            <li><a href="#">하위메뉴2</a></li>
            <li><a href="#">하위메뉴3</a></li>
        </ul>
    </div>
    <div class="menu-group company-menu" style="display:none;" data-url="/company">
        <h3 id="companyManagement">업체관리</h3>
        <ul>
            <li><a href="<c:url value='/company/companyList.do'/>">업체목록</a></li>
            <li><a href="#">메뉴2</a></li>
            <li><a href="#">메뉴3</a></li>
        </ul>
    </div>
    <div class="menu-group education-menu" style="display:none;" data-url="/education">
        <h3 id="educationManagement">교육정보관리</h3>
        <ul>
            <li><a href="#">교육목록</a></li>
            <li><a href="#">메뉴2</a></li>
            <li><a href="#">메뉴3</a></li>
        </ul>
    </div>
</div>

<script>
$(document).ready(function() {
    // 로컬 스토리지에서 열려 있는 메뉴 상태 복원
    var openMenus = JSON.parse(localStorage.getItem('openMenus')) || [];

    openMenus.forEach(function(menuId) {
        if (menuId) {
            $('#' + menuId).next('ul').show(); // 저장된 메뉴 상태를 복원
        }
    });

    // 현재 URL 기반으로 메뉴 자동 열기
    var currentUrl = window.location.pathname;

    $('.sidebar .menu-group').each(function() {
        var menuUrl = $(this).data('url');
        if (currentUrl.startsWith(menuUrl)) {
            $(this).show();
            var menuId = $(this).find('h3').attr('id');
            if (menuId && !openMenus.includes(menuId)) {
                openMenus.push(menuId);
                localStorage.setItem('openMenus', JSON.stringify(openMenus));
            }
        }
    });

    // 메뉴 클릭 시 active 클래스 관리 및 상태 저장
    $('.menu-link').off('click').on('click', function(e) {
        e.preventDefault(); // 기본 클릭 동작 방지

        $('.menu-link').removeClass('active'); // 모든 메뉴에서 active 클래스 제거
        $(this).addClass('active');

        var menuType = $(this).data('menu');
        localStorage.setItem('activeMenu', menuType); // 활성화된 메뉴 저장

        $('.sidebar .menu-group').hide(); // 모든 메뉴를 숨기고
        $('.sidebar .' + menuType + '-menu').show(); // 해당 메뉴를 표시
    });

    // 메뉴 상태 유지: 메뉴를 클릭할 때 로컬 스토리지에 저장
    $('.sidebar .menu-group h3').off('click').on('click', function(event) {
        event.preventDefault(); // 기본 클릭 동작 방지
        event.stopPropagation(); // 이벤트 전파 방지

        var $submenu = $(this).next('ul');
        var menuId = $(this).attr('id');

        // 현재 열려 있는 메뉴 토글
        $submenu.stop(true, true).slideToggle();

        // 로컬 스토리지에 열린 메뉴 상태 저장
        if ($submenu.is(':visible')) {
            if (!openMenus.includes(menuId)) {
                openMenus.push(menuId);
            }
        } else {
            openMenus = openMenus.filter(function(id) {
                return id !== menuId;
            });
        }

        localStorage.setItem('openMenus', JSON.stringify(openMenus));
    });
});
</script>
