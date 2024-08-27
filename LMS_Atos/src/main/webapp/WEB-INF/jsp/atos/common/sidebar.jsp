<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
    <ul>
        <li><a href="<c:url value='/test/boardList.do'/>">Board List</a></li>
        <li><a href="<c:url value='/member/memberList.do'/>">회원 목록</a></li>
        <li><a href="/dashboard?page=settings">Settings</a></li>
        <li><a href="/dashboard?page=profile">Profile</a></li>
    </ul>
</nav>