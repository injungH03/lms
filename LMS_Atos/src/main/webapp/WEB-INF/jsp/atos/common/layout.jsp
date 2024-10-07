<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><tiles:getAsString name="title" /></title>
    <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/js/atos/common/fetchFunction.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/js/atos/common/CommonUtil.js'/>" ></script>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/test/style.css' />">
    <!-- 부트스트랩  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <!-- Header Section -->
    <header class="dashboard-header">
        <tiles:insertAttribute name="header" />
    </header>

    <!-- Sidebar Section -->
    <aside class="dashboard-sidebar">
        <tiles:insertAttribute name="sidebar" />
    </aside>

    <!-- Content Section -->
    <main class="dashboard-content">
        <tiles:insertAttribute name="body" />
    </main>
</body>
</html>