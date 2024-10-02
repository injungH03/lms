<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/common/sidebar.css' />">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="activeMenuUrl" value="${activeMenuUrl}" />

<div class="sidebar">
    <c:forEach var="menuItem" items="${menuItems}">
        <div class="menu-group ${menuItem.value.data}-menu" data-base-url="${menuItem.value.url}">
            <h3 id="${menuItem.value.data}Management">${menuItem.value.title}</h3>
            <ul>
                <c:forEach var="subMenuItem" items="${menuItem.value.subMenuItems}">
                    <li>
                        <a href="<c:url value='${subMenuItem.url}'/>"
                           class="${subMenuItem.url == activeMenuUrl ? 'active' : ''}">
                           ${subMenuItem.title}
                        </a>
                    </li>
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

    var currentUrl = window.location.pathname;

    $('.sidebar .menu-group').each(function() {
        var menuGroupUrl = $(this).data('base-url'); // 예: /education/lectureList.do
        var baseUrl = menuGroupUrl.replace(/List\.do$/, ''); // 예: /education/lecture

        if (currentUrl.startsWith(baseUrl)) {
            $(this).find('ul').show(); 
            var menuId = $(this).find('h3').attr('id');
            if (menuId && !openMenus.includes(menuId)) {
                openMenus.push(menuId);
                localStorage.setItem('openMenus', JSON.stringify(openMenus));
            }

            // 하위 메뉴 중 현재 URL과 가장 일치하는 메뉴 찾기
            var matched = false;
            var bestMatchLength = 0;
            var bestMatchElement = null;

            $(this).find('ul li a').each(function() {
                var linkUrl = $(this).attr('href');
                if (currentUrl === linkUrl) {
                    $(this).addClass('active');
                    matched = true;
                    return false; 
                }
                else if (currentUrl.startsWith(linkUrl.replace(/\.do$/, '')) && linkUrl.length > bestMatchLength) {
                    bestMatchLength = linkUrl.length;
                    bestMatchElement = $(this);
                }
            });

            // 정확히 일치하는 항목이 없을 경우 가장 긴 일치 항목에 active 클래스 추가
            if (!matched && bestMatchElement) {
                bestMatchElement.addClass('active');
            }
        }
    });
    
    
    // 메뉴 클릭 시 active 클래스 추가 및 다른 메뉴에서 제거
    $('.sidebar .menu-group ul li a').on('click', function(e) {
        e.preventDefault();  // 기본 링크 이동 막기 (필요 시 제거)
        // 모든 메뉴에서 active 클래스 제거
        $('.sidebar .menu-group ul li a').removeClass('active');
        
        // 클릭한 메뉴에 active 클래스 추가
        $(this).addClass('active');

        // 링크로 이동
        var href = $(this).attr('href');
        window.location.href = href;
    });



});
</script>
