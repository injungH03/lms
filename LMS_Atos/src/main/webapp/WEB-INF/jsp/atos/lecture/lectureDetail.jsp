<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">

<div class="head-section">
	<span>&nbsp;<c:out value="${result.title}"/> (<c:out value="${result.subName}"/>) </span>
</div>
<div class="table-section">
	
	<%@ include file="/WEB-INF/jsp/atos/common/menutab.jsp" %>
	<table class="search-table detail-table">
		<tr>
			<th>교육명</th>
			<td><c:out value="${result.title}"/></td>
			<th>교육형태</th>
			<td><c:out value="${result.mainName}"/>/<c:out value="${result.subName}"/></td>
		</tr>
		<tr>
			<th>과정목표</th>
			<td colspan="3">
				<div class="text-container">
					<p><c:out value="${result.objective}"/></p>
				</div>
			</td>
		</tr>
		<tr>
			<th>과정소개</th>
			<td colspan="3">
				<div class="text-container">
					<p><c:out value="${result.description}"/></p>
				</div>
			</td>
		</tr>
		<tr>
			<th>교육장소</th>
			<td colspan="3"><c:out value="${result.location}"/> <c:out value="${result.locationDetail}"/></td>
		</tr>
		<tr>
			<th>배정강사</th>
			<td><c:out value="${result.instructorName}"/></td>
			<th>인원수</th>
			<td><c:out value="${result.enrolled}"/> / <c:out value="${result.capacity}"/></td>
		</tr>
		<tr>
			<th>접수시작일</th>
			<td><c:out value="${result.recStartDate}"/></td>
			<th>접수종료일</th>
			<td><c:out value="${result.recEndDate}"/></td>
		</tr>
		<tr>
			<th>과정날짜</th>
			<td><c:out value="${result.learnDate}"/></td>
			<th>과정 총시간</th>
			<td><c:out value="${result.trainingTime}"/></td>
		</tr>		

		<tr>
			<th>시작시간</th>
			<td><c:out value="${result.startTime}"/></td>
			<th>종료시간</th>
			<td><c:out value="${result.endTime}"/></td>
		</tr>
		<tr>
			<th>담당자</th>
			<td><c:out value="${result.manager}"/></td>
			<th>연락처</th>
			<td><c:out value="${result.managerContact}"/></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="3"><c:if test="${not empty result.atchFileId}">
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_atchFileId"
							value="${egovc:encrypt(result.atchFileId)}" />
					</c:import>
				</c:if></td>
		</tr>
	</table>

        <div class="mt-3">
            <button type="submit" class="btn btn-success me-2">수정</button>
            <button type="button" class="btn btn-secondary me-2">목록</button>
            <button type="button" class="btn btn-danger" style="float:right">삭제</button>
        </div>
</div>
<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}">

<!-- jQuery Script -->
<script>
$(document).ready(function() {
	
    
});
</script>