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
        <h3 id="menu1">상위메뉴1</h3>
        <ul>
            <li><a href="#">하위메뉴1</a></li>
            <li><a href="#">하위메뉴2</a></li>
            <li><a href="#">하위메뉴3</a></li>
        </ul>
    </div>
    <div class="menu-group company-menu" style="display:none;">
        <h3>업체관리</h3>
        <ul>
            <li><a href="#">업체목록</a></li>
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
    // 로컬 스토리지에서 열려 있는 메뉴 상태 복원
    var openMenus = JSON.parse(localStorage.getItem('openMenus')) || [];

    openMenus.forEach(function(menuId) {
        if (menuId) {
            $('#' + menuId).next('ul').show(); // 저장된 메뉴 상태를 복원
        }
    });

    // 현재 URL에 따라 해당 메뉴 열기
    var currentUrl = window.location.href;

    $('.sidebar .menu-group ul li a').each(function() {
        if (currentUrl.includes($(this).attr('href'))) {
            var $submenu = $(this).closest('ul');
            $submenu.show(); // 해당 메뉴를 펼침

            var parentMenu = $submenu.prev('h3').attr('id');
            if (parentMenu && !openMenus.includes(parentMenu)) {
                openMenus.push(parentMenu);
                localStorage.setItem('openMenus', JSON.stringify(openMenus));
            }
        }
    });

    // 메뉴 클릭 시 토글 및 상태 저장
    $('.sidebar .menu-group h3').off('click').on('click', function(event) {
        event.preventDefault(); // 기본 클릭 동작 방지
        event.stopPropagation(); // 이벤트 전파 방지

        var $submenu = $(this).next('ul');
        var menuId = $(this).attr('id');

        // menuId가 존재하는 경우에만 로컬 스토리지에 추가
        if (menuId) {
            $submenu.stop(true, true).slideToggle();

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
        }
    });

    // 메뉴 상태 디버깅 - 로컬 스토리지 확인용
    console.log("현재 열려 있는 메뉴: ", JSON.parse(localStorage.getItem('openMenus')));
});


/* $(document).ready(function() {
    $('.sidebar .menu-group h3').off('click').on('click', function(event) {
        event.preventDefault(); // 기본 클릭 동작 방지
        event.stopPropagation(); // 이벤트 전파 방지

        var $submenu = $(this).next('ul');


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
}); */
</script>
