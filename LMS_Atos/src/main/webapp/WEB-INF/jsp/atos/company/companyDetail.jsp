<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/company/companyDetail.css' />">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-4 p-4 bg-light rounded shadow-sm">
    <h3 class="mb-4">회사 상세정보</h3>

    <!-- 회사 상세정보 그리드 레이아웃 -->
    <div class="company-details-grid">
        <!-- 각 항목에 대한 그리드 항목 -->
        <div class="company-detail-item">
            <span class="label">사업자등록번호</span>
            <span class="value"><c:out value="${company.bizRegNo}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">대표자명</span>
            <span class="value"><c:out value="${company.repName}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">업체명</span>
            <span class="value"><c:out value="${company.corpName}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">전화번호</span>
            <span class="value"><c:out value="${company.phoneNo}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">업태</span>
            <span class="value"><c:out value="${company.bizType}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">종목</span>
            <span class="value"><c:out value="${company.bizItem}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">팩스번호</span>
            <span class="value"><c:out value="${company.faxNo}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">세금계산서 이메일</span>
            <span class="value"><c:out value="${company.taxInvoice}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">직원수</span>
            <span class="value"><c:out value="${company.empCount}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">교육인원수</span>
            <span class="value"><c:out value="${company.trainCount}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">우편번호</span>
            <span class="value"><c:out value="${company.zipcode}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">주소</span>
            <span class="value"><c:out value="${company.addr1}" /> <c:out value="${company.addr2}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">상태</span>
            <span class="value"><c:out value="${company.status}" /></span>
        </div>
        <div class="company-detail-item">
            <span class="label">등록일</span>
            <span class="value"><c:out value="${company.regDate}" /></span>
        </div>
        <div class="company-detail-item full-width">
            <span class="label">메모</span>
            <span class="value"><c:out value="${company.memo}" /></span>
        </div>
    </div>

    <!-- 버튼 영역 -->
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
    
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('updateButton').addEventListener('click', function() {
            var bizRegNo = '<c:out value="${company.bizRegNo}" />';
            window.location.href = "<c:url value='/company/companyUpdateView.do' />?bizRegNo=" + bizRegNo;
        });
    });
    
</script>
