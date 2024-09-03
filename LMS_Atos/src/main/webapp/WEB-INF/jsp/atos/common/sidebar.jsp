<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/common/sidebar.css' />">

<div class="sidebar">
    <c:forEach var="menuItem" items="${menuItems}">
        <div class="menu-group ${menuItem.value.data}-menu" data-url="${menuItem.value.url}">
            <h3 id="${menuItem.value.data}Management">${menuItem.value.title}</h3>
            <ul>
                <c:forEach var="subMenuItem" items="${menuItem.value.subMenuItems}">
                    <li><a href="<c:url value='${subMenuItem.url}'/>">${subMenuItem.title}</a></li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
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
    
 // 사이드바 메뉴 클릭 시 활성화 처리
    $('.sidebar .menu-group h3').click(function(event) {
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

    // 헤더 메뉴 클릭 시 사이드바 메뉴를 활성화 처리
    $('.menu-link').off('click').on('click', function(e) {
        e.preventDefault(); // 기본 클릭 동작 방지

        $('.menu-link').removeClass('active'); // 모든 메뉴에서 active 클래스 제거
        $(this).addClass('active');

        var menuType = $(this).data('menu');
        localStorage.setItem('activeMenu', menuType); // 활성화된 메뉴 저장

        $('.sidebar .menu-group').hide(); // 모든 메뉴를 숨기고
        $('.sidebar .' + menuType + '-menu').show(); // 해당 메뉴를 표시
    });
});
</script>
