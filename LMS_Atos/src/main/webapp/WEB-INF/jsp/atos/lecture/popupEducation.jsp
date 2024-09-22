<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">
    <title>과정 선택</title>
</head>
<body>
    <div class="popup-wrapper">
        
	<div class="tab-section" style="margin-bottom:20px;">
	    <ul class="nav nav-tabs" id="myTab" role="tablist">
	        <li class="nav-item" role="presentation">
	            <button class="nav-link active" id="supervisor-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="supervisor" aria-selected="true">관리감독자 정기교육</button>
	        </li>
	        <li class="nav-item" role="presentation">
	            <button class="nav-link" id="worker-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="worker" aria-selected="false">근로자 정기교육</button>
	        </li>
	        <li class="nav-item" role="presentation">
	            <button class="nav-link" id="assessment-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="assessment" aria-selected="false">위험성평가교육</button>
	        </li>
	    </ul>
	</div>
    <form method="get" action="" class="form-inline mb-3">
		<table class="search-table">
            <tr>
                <th>검색</th>
                <td colspan="5">
                    <div class="d-flex">
		               <select name="searchCnd" title="검색 조건 선택" class="form-select search-select me-2">
		                	<option value="">선택</option>
		                    <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if>>아이디</option>
		                    <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if>>이름</option>
		                </select>
                        <input type="text" name="searchKeyword" class="form-control search-input me-2" placeholder="검색어를 입력하세요" />
                        <button type="submit" class="btn-search">검색</button>
                    </div>
                </td>
            </tr>
        </table>
    </form>
        
        <table class="table table-bordered course-table">
	        <colgroup>
		        <col style="width: 5%;">
		        <col style="width: 10%;">
		        <col style="width: 10%;">
	    	</colgroup>
            <thead>
                <tr>
                    <th>No</th>
                    <th>교육명</th>
                    <th>교육시간</th>
                </tr>
            </thead>
            <tbody>
            	<tr>
            		<td>1</td>
            		<td>2024년 관리감독자 제조업</td>
            		<td>8시간</td>
            	</tr>
            	<tr>
            		<td>2</td>
            		<td>2024년 관리감독자 제조업</td>
            		<td>8시간</td>
            	</tr>
            </tbody>
        </table>

        <!-- 페이지네이션 예시 -->
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="?page=1">&laquo;</a></li>
                <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
                <li class="page-item"><a class="page-link" href="?page=2">2</a></li>
                <li class="page-item"><a class="page-link" href="?page=3">&raquo;</a></li>
            </ul>
        </nav>
    </div>

    <script>
    // 부모 창으로 데이터 전달
    function selectCourse(courseName, courseId) {
        // 부모 창에 값 설정
        window.opener.document.getElementById('selectedEduInfo').innerText = courseName;
        window.opener.document.getElementById('selectedEduInfo').value = courseId; // Hidden input에 id 전달 가능

        // 팝업창 닫기
        window.close();
    }
    </script>
</body>
</html>