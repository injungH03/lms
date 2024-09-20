<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/js/atos/common/fetchFunction.js'/>" ></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">
    <title>수강생추가</title>
</head>
<body>
    <div class="popup-wrapper">
        <h4>수강생 추가</h4>
    <form method="get" action="" class="form-inline mb-3">
		<table class="search-table">
             <tr>
                <th>소속</th>
                <td colspan="2">
                    <select name="course" class="form-select search-select me-2">
                        <option value="all">선택</option>
                    </select>
                </td>
            </tr>
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
    
		<div class="d-flex justify-content-between mb-2 mt-5">
		    <div>
		        Total: <strong>38건</strong>
		    </div>
		    <div>
		    	<button class="btn-create-course" id="studentAdd">선택추가</button>
		    </div>
		</div>        
        <table class="table table-bordered course-table">
	        <colgroup>
		        <col style="width: 5%;">
		        <col style="width: 10%;">
		        <col style="width: 20%;">
		        <col style="width: 15%;">
		        <col style="width: 10%;">
		        <col style="width: 10%;">
		        <col style="width: 10%;">
	    	</colgroup>
            <thead>
                <tr>
                    <th>No</th>
                    <th>소속</th>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>추가</th>
                    <th><input type="checkbox" id="checkAll"></th>
                </tr>
            </thead>
            <tbody>
            	<tr>
            		<td>1</td>
            		<td>아토스</td>
            		<td>test</td>
            		<td>테스터</td>
            		<td>010-2222-222</td>
            		<td><span class="status-box status-추가">추가</span></td>
            		<td><input type="checkbox" name="rowCheck" value=""></td>
            	</tr>

            </tbody>
        </table>

    </div>

<script>
$(document).ready(function() {
	// 전체 선택/해제 기능
	$('#checkAll').on('click', function() {
	    $('tbody input[name="rowCheck"]').prop('checked', this.checked);
	});
});


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