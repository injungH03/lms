<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/member/memberDetail.css' />">

<div class="content">
	<h1>회원 상세정보</h1>
	<table class="info-table membertable">
		<tr>
			<th>아이디</th>
			<td><c:out value="${member.id }" /></td>
			<th>생년월일</th>
			<td><c:out value="${member.birthdate }" /></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><c:out value="${member.name }" /></td>
			<th>주소</th>
			<td><c:out value="${member.addr1 }" />&nbsp;<c:out value="${member.addr2 }" /></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><c:out value="${member.phoneNo }" /></td>
			<th>이메일</th>
			<td><c:out value="${member.email }" /></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><c:out value="${member.zipcode }" /></td>
			<th>소속기업</th>
			<td><c:out value="${member.corpName }" /></td>
		</tr>
		<tr>
			<th>소속부서</th>
			<td><c:out value="${member.department }" /></td>
			<th>직책</th>
			<td><c:out value="${member.position }" /></td>
		</tr>
	</table>

	<h2>회사정보</h2>
	<table class="info-table companytable">
		<tr>
			<th>사업자등록번호</th>
			<td><c:out value="${member.companybizRegNo }" /></td>
			<th>대표전화번호</th>
			<td><c:out value="${member.companyPhoneNo }" /></td>
		</tr>
		<tr>
			<th>사업장명</th>
			<td><c:out value="${member.corpName }" /></td>
			<th>대표자명</th>
			<td><c:out value="${member.repName }" /></td>
		</tr>
		<tr>
			<th>업태</th>
			<td><c:out value="${member.bizType }" /></td>
			<th>종목</th>
			<td><c:out value="${member.bizItem }" /></td>
		</tr>
		<tr>
			<th>사업장주소</th>
			<td colspan="3"><c:out value="${member.companyAddr1 }" />&nbsp;<c:out value="${member.companyAddr2 }" /></td>
		</tr>
		
	</table>

	<div class="button-container">
		<button type="button" id="updateButton">수정</button>
		<button type="reset" class="cancel-btn" onclick="history.back();">취소</button>
		<button type="reset" class="cancel-btn deletebutton" id="deleteButton">삭제</button>
	</div>
</div>
<script>
    $(document).ready(function() {
        $('#updateButton').click(function() {
            window.location.href = "<c:url value='/member/memberUpdateView.do' />";
        });
        
        $('#deleteButton').click(function() {
    	    if(confirm("삭제 하시겠습니까?")){
    	    	const memId = '<c:out value="${member.id }" />';
    	    	const pageIndex = '<c:out value="${searchVO.pageIndex }" />';
                myFetch({
                    url: '/member/memberDelete', 
                    data: { id: memId }, 
                    success: function(response) {
                        alert(response.message);
                        window.location.href = "<c:url value='/member/memberList.do'/>?pageIndex=" + pageIndex;
                    },
                    error: function(error) {
                        console.error('AJAX 오류:', error);
                    }
                });
    	    }
        });
    });
</script>
