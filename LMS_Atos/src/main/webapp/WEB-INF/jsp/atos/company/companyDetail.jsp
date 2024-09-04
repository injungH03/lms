<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/company/companyDetail.css' />">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-4">
    <h1 class="mb-4">회사 상세정보</h1>
    <table class="table table-bordered table-striped">
        <tbody>
            <tr>
                <th>사업자등록번호</th>
                <td><c:out value="${company.bizRegNo}" /></td>
                <th>대표자명</th>
                <td><c:out value="${company.repName}" /></td>
            </tr>
            <tr>
                <th>업체명</th>
                <td><c:out value="${company.corpName}" /></td>
                <th>전화번호</th>
                <td><c:out value="${company.phoneNo}" /></td>
            </tr>
            <tr>
                <th>업태</th>
                <td><c:out value="${company.bizType}" /></td>
                <th>종목</th>
                <td><c:out value="${company.bizItem}" /></td>
            </tr>
            <tr>
                <th>팩스번호</th>
                <td><c:out value="${company.faxNo}" /></td>
                <th>세금계산서 이메일</th>
                <td><c:out value="${company.taxInvoice}" /></td>
            </tr>
            <tr>
                <th>직원수</th>
                <td><c:out value="${company.empCount}" /></td>
                <th>교육인원수</th>
                <td><c:out value="${company.trainCount}" /></td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td><c:out value="${company.zipcode}" /></td>
                <th>주소</th>
                <td><c:out value="${company.addr1}" /> <c:out value="${company.addr2}" /></td>
            </tr>
            <tr>
                <th>상태</th>
                <td><c:out value="${company.status}" /></td>
                <th>등록일</th>
                <td>${company.regDate}</td>
            </tr>
            <tr>
                <th>메모</th>
                <td colspan="3"><c:out value="${company.memo}" /></td>
            </tr>
        </tbody>
    </table>

    <div class="d-flex justify-content-end mt-3">
        <button type="button" class="btn btn-primary me-2" id="updateButton">수정</button>
        <button type="button" class="btn btn-secondary me-2" onclick="history.back();">취소</button>
        <button type="button" class="btn btn-danger" id="deleteButton">삭제</button>
    </div>
</div>

<script>
$(document).ready(function() {
    $('#updateButton').click(function() {
        window.location.href = "<c:url value='/company/companyUpdateView.do' />";
    });

    $('#deleteButton').click(function() {
        if(confirm("정말 삭제하시겠습니까?")) {
            var bizRegNo = '<c:out value="${company.bizRegNo}" />';
            $.ajax({
                url: '/company/deleteCompany',
                method: 'POST',
                data: { bizRegNo: bizRegNo },
                success: function(response) {
                    alert(response.message);
                    window.location.href = "<c:url value='/company/companyList.do'/>";
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('삭제 중 오류 발생:', textStatus, errorThrown);
                    alert('삭제에 실패했습니다.');
                }
            });
        }
    });
});
</script>
