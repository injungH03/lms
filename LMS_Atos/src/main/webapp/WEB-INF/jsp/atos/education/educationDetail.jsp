<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/education/educationDetail.css' />">

<div class="head-section">
	<span>&nbsp;</span>
</div>
<div class="table-section">
	

	<table class="search-table detail-table">
		<tr>
			<th>교육명</th>
			<td></td>
			<th>교육분류</th>
			<td></td>
		</tr>
		<tr>
			<th>수료조건</th>
			<td></td>
			<th>과정시간</th>
			<td></td>
		</tr>
		<tr>
			<th>등록일</th>
			<td></td>
			<th>등록자</th>
			<td></td>
		</tr>	
		<tr>
			<th>과정소개</th>
			<td colspan="3">
				<div class="text-container">

				</div>
			</td>
		</tr>
		<tr>
			<th>과정목표</th>
			<td colspan="3">
				<div class="text-container">
				</div>
			</td>
		</tr>
		<tr>
			<th>비고</th>
			<td colspan="3">
				<div class="text-container">

				</div>
			</td>
		</tr>	
		<tr>
			<th>담당자</th>
			<td></td>
		</tr>
	</table>

        <div class="mt-3">
            <button type="submit" class="btn btn-success me-2">수정</button>
            <button type="button" class="btn btn-secondary me-2">목록</button>
            <button type="button" class="btn btn-danger" style="float:right">삭제</button>
        </div>
</div>


<!-- jQuery Script -->
<script>
$(document).ready(function() {
	
    
});
</script>