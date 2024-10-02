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
    const LOCAL_STORAGE_KEY = 'openMenus';

    // 캐싱 jQuery 셀렉터
    const $sidebar = $('.sidebar');
    const $menuGroups = $sidebar.find('.menu-group');
    const $menuLinks = $sidebar.find('ul li a');

    // 로컬 스토리지에서 열려 있는 메뉴 상태 복원
    let openMenus = [];
    try {
        const storedMenus = localStorage.getItem(LOCAL_STORAGE_KEY);
        openMenus = storedMenus ? JSON.parse(storedMenus) : [];
    } catch (error) {
        console.error('로컬 스토리지에서 openMenus를 가져오는 데 실패했습니다:', error);
        openMenus = [];
    }

    // 열려 있는 메뉴 열기
    openMenus.forEach(function(menuId) {
        if (menuId) {
            const $menuHeader = $('#' + menuId);
            if ($menuHeader.length) {
                $menuHeader.next('ul').show();
            }
        }
    });

    // 현재 URL 기반으로 메뉴 자동 열기 및 active 클래스 설정
    const currentUrl = window.location.pathname;

    $menuGroups.each(function() {
        const $this = $(this);
        const menuGroupUrl = $this.data('base-url');
        const baseUrl = menuGroupUrl.replace(/List\.do$/, '');

        if (currentUrl.startsWith(baseUrl)) {
            $this.find('ul').show();
            const menuId = $this.find('h3').attr('id');
            if (menuId && !openMenus.includes(menuId)) {
                openMenus.push(menuId);
                localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(openMenus));
            }

            // 하위 메뉴 중 현재 URL과 가장 일치하는 메뉴 찾기
            let matched = false;
            let bestMatchLength = 0;
            let bestMatchElement = null;

            $this.find('ul li a').each(function() {
                const $link = $(this);
                const linkUrl = $link.attr('href');
                if (currentUrl === linkUrl) {
                    $link.addClass('active');
                    matched = true;
                    return false; 
                } else if (currentUrl.startsWith(linkUrl.replace(/\.do$/, '')) && linkUrl.length > bestMatchLength) {
                    bestMatchLength = linkUrl.length;
                    bestMatchElement = $link;
                }
            });

            // 정확히 일치하는 항목이 없을 경우 가장 긴 일치 항목에 active 클래스 추가
            if (!matched && bestMatchElement) {
                bestMatchElement.addClass('active');
            }
        }
    });

    // 메뉴 클릭 시 active 클래스 관리 및 메뉴 상태 저장
    $sidebar.on('click', '.menu-group ul li a', function(e) {
        e.preventDefault(); // 기본 링크 이동 막기 (필요 시 제거)
        
        const $this = $(this);
        const href = $this.attr('href');

        // 모든 메뉴에서 active 클래스 제거
        $menuLinks.removeClass('active');

        // 클릭한 메뉴에 active 클래스 추가
        $this.addClass('active');

        // 링크로 이동
        window.location.href = href;
    });

    // 메뉴 그룹 헤더 클릭 시 메뉴 토글 및 상태 업데이트
    $sidebar.on('click', '.menu-group h3', function() {
        const $this = $(this);
        const $submenu = $this.next('ul');
        const menuId = $this.attr('id');

        $submenu.slideToggle();

        // 현재 열려 있는 메뉴 수집
        openMenus = [];
        $menuGroups.each(function() {
            const $group = $(this);
            if ($group.find('ul').is(':visible')) {
                const groupId = $group.find('h3').attr('id');
                if (groupId) {
                    openMenus.push(groupId);
                }
            }
        });

        // 로컬 스토리지에 저장
        try {
            localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(openMenus));
        } catch (error) {
            console.error('로컬 스토리지에 openMenus를 저장하는 데 실패했습니다:', error);
        }
    });
});
</script>
