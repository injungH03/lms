<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">

<div class="head-section">
	<span>&nbsp;2024년 관리감독자 (제조업)</span>
</div>
<div class="table-section">
	
	<%@ include file="/WEB-INF/jsp/atos/common/menutab.jsp" %>
	<table class="search-table detail-table">
		<tr>
			<th>교육명</th>
			<td>2024년 관리감독자</td>
			<th>교육형태</th>
			<td>관리감독자/제조업</td>
		</tr>
		<tr>
			<th>과정목표</th>
			<td colspan="3">
				<div class="text-container">
					<p>과정에 대한 목표과정에 대한 목표과정에 대한 목표과정에 대한 목표과정에 대한 목표과정에 대한 목표과정에 대한
						목표과정에 대한 목표과정에 대한 목표</p>
				</div>
			</td>
		</tr>
		<tr>
			<th>과정소개</th>
			<td colspan="3">
				<div class="text-container">
					<p>과정에대한 소개과정에대한 소개과정에대한 소개과정에대한 소개과정에대한 소개과정에대한 소개과정에대한
						소개과정에대한 소개과정에대한 소개</p>
				</div>
			</td>
		</tr>
		<tr>
			<th>교육장소</th>
			<td colspan="3"></td>
		</tr>
		<tr>
			<th>접수시작일</th>
			<td>2024-02-01</td>
			<th>접수종료일</th>
			<td>2024-05-05</td>
		</tr>
		<tr>
			<th>과정날짜</th>
			<td>2024-02-21</td>
			<th>과정시간</th>
			<td>8시간</td>
		</tr>		
		<tr>
			<th>현재인원수</th>
			<td>30명</td>
			<th>모집인원수</th>
			<td>50명</td>
		</tr>
		<tr>
			<th>시작시간</th>
			<td>오전 9시</td>
			<th>종료시간</th>
			<td>오후 6시</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>홍길동</td>
			<th>연락처</th>
			<td>010-2222-2222</td>
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


<!-- jQuery Script -->
<script>
$(document).ready(function() {
	
    
});
</script>