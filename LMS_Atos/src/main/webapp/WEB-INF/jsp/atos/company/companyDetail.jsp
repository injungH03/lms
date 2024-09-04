<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/company/companyDetail.css' />">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-4 p-4 bg-light rounded shadow-sm">
    <h3 class="mb-4">회사 상세정보</h3>
    <table class="table table-bordered">
        <tbody>
            <tr>
                <th scope="row">사업자등록번호</th>
                <td><c:out value="${company.bizRegNo}" /></td>
                <th scope="row">대표자명</th>
                <td><c:out value="${company.repName}" /></td>
            </tr>
            <tr>
                <th scope="row">업체명</th>
                <td><c:out value="${company.corpName}" /></td>
                <th scope="row">전화번호</th>
                <td><c:out value="${company.phoneNo}" /></td>
            </tr>
            <tr>
                <th scope="row">업태</th>
                <td><c:out value="${company.bizType}" /></td>
                <th scope="row">종목</th>
                <td><c:out value="${company.bizItem}" /></td>
            </tr>
            <tr>
                <th scope="row">팩스번호</th>
                <td><c:out value="${company.faxNo}" /></td>
                <th scope="row">세금계산서 이메일</th>
                <td><c:out value="${company.taxInvoice}" /></td>
            </tr>
            <tr>
                <th scope="row">직원수</th>
                <td><c:out value="${company.empCount}" /></td>
                <th scope="row">교육인원수</th>
                <td><c:out value="${company.trainCount}" /></td>
            </tr>
            <tr>
                <th scope="row">우편번호</th>
                <td><c:out value="${company.zipcode}" /></td>
                <th scope="row">주소</th>
                <td><c:out value="${company.addr1}" /> <c:out value="${company.addr2}" /></td>
            </tr>
            <tr>
                <th scope="row">상태</th>
                <td><c:out value="${company.status}" /></td>
                <th scope="row">등록일</th>
                <td><c:out value="${company.regDate}" /></td>
            </tr>
            <tr>
                <th scope="row">메모</th>
                <td colspan="3"><c:out value="${company.memo}" /></td>
            </tr>
        </tbody>
    </table>

    <div class="d-flex justify-content-end mt-3">
        <button type="button" class="btn btn-success me-2" id="updateButton">수정</button>
        <button type="button" class="btn btn-secondary me-2" onclick="history.back();">취소</button>
        <button type="button" class="btn btn-danger" id="deleteButton">삭제</button>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('updateButton').addEventListener('click', function() {
            window.location.href = "<c:url value='/company/companyUpdateView.do' />";
        });

        document.getElementById('deleteButton').addEventListener('click', function() {
            if(confirm("정말 삭제하시겠습니까?")) {
                var bizRegNo = '<c:out value="${company.bizRegNo}" />';
                fetch('<c:url value="/company/deleteCompany" />', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({ bizRegNo: bizRegNo })
                })
                .then(response => response.json())
                .then(data => {
                    alert(data.message);
                    window.location.href = "<c:url value='/company/companyList.do' />";
                })
                .catch(error => {
                    console.error('삭제 중 오류 발생:', error);
                    alert('삭제에 실패했습니다.');
                });
            }
        });
    });
</script>
