<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/member/memberDetail.css' />">

<div class="content">
	<h1>강사 상세정보</h1>
	<table class="info-table membertable">
		<tr>
			<th>아이디</th>
			<td><c:out value="${instructor.id }" /></td>
			<th>생년월일</th>
			<td><c:out value="${instructor.birthdate }" /></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><c:out value="${instructor.name }" /></td>
			<th>전화번호</th>
			<td><c:out value="${instructor.phoneNo }" /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><c:out value="${instructor.email }" /></td>
			<th>소속기관</th>
			<td><c:out value="${instructor.affiliation }" /></td>
		</tr>
		<tr>

			<th>소속</th>
			<td><c:out value="${instructor.department }" /></td>
			<th>직책</th>
			<td><c:out value="${instructor.position }" /></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><c:out value="${instructor.job }" /></td>
			<th>우편번호</th>
			<td><c:out value="${instructor.zipcode }" /></td>
		</tr>
		<tr>
			<th>주소</th>
			<td colspan="3"><c:out value="${instructor.addr1 }" />&nbsp;<c:out value="${instructor.addr2 }" /></td>
		</tr>
		
		
	</table>
    <div class="info-section">
        <h4>강사소개</h4>
        <div class="text-container">
            <p><c:out value="${instructor.bios }" /></p>
        </div>
    </div>

    <div class="info-section">
        <h4>경력사항</h4>
        <div class="text-container">
            <p><c:out value="${instructor.career }" /></p>
        </div>
    </div>

	<div class="button-container">
		<button type="button" class="btn btn-primary" id="updateButton">수정</button>
		<button type="button" class="cancel-btn btn btn-danger" id="listButton">목록</button>
		<button type="button" class="cancel-btn deletebutton btn btn-danger" id="deleteButton">삭제</button>
	</div>
</div>
<script>
    $(document).ready(function() {
        $('#updateButton').click(function() {
        	const memId = '<c:out value="${instructor.id }" />';
            window.location.href = "<c:url value='/member/instructorUpdateView.do' />?id=" + memId;
        });
        
        $('#listButton').click(function() {
        	const pageIndex = '<c:out value="${searchVO.pageIndex }" />';
        	window.location.href = "<c:url value='/member/instructorList.do'/>?pageIndex=" + pageIndex;
        });
        
        $('#deleteButton').click(function() {
    	    if(confirm("삭제 하시겠습니까?")){
    	    	const memId = '<c:out value="${instructor.id }" />';
    	    	const pageIndex = '<c:out value="${searchVO.pageIndex }" />';
                myFetch({
                    url: '/member/instructorDelete', 
                    data: { id: memId }, 
                    success: function(response) {
                        alert(response.message);
                        window.location.href = "<c:url value='/member/instructorList.do'/>?pageIndex=" + pageIndex;
                    },
                    error: function(error) {
                        console.error('AJAX 오류:', error);
                    }
                });
    	    }
        });
    });
</script>
