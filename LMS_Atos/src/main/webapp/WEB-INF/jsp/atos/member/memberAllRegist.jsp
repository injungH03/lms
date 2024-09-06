<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/member/memberAllRegist.css' />">

<div class="container">
    <div class="section-title">
        회원일괄 등록 [엑셀등록]
    </div>

    <div class="form-section">
        <div class="form-title">
            1. 회원 샘플양식을 다운로드 받아 작성한 후 업로드 하십시오.
        </div>
        <div class="form-title">
            2. 업로드 된 회원 정보를 확인 한 후 등록하기 버튼을 클릭하면 회원등록이 완료 됩니다.
        </div>

        <div class="form-group">
            <label for="affiliation" class="form-label">회원소속</label>
                <select class="form-select" id="group" name="corpBiz">
                    <option value="">선택</option>
                    <c:forEach var="company" items="${company }">
                    	<option value="${company.corpBiz }" <c:if test="${company.corpBiz == searchVO.corpBiz}">selected</c:if>>
                    		${company.corpName }
                    	</option>
                    </c:forEach>
                </select>
        </div>

<form id="excelUploadForm" enctype="multipart/form-data">
        <div class="form-group">
            <label for="uploadFile" class="form-label">업로드파일</label>
            <input type="file" class="form-control" id="uploadFile" name="file">
            <small class="form-text text-muted">
                <!-- 업로드 파일은 샘플양식(*.xls) 파일만 가능합니다.  -->복수 시트는 지원하지 않습니다.
            </small>
        </div>
</form>
        <button class="btn btn-secondary" id="sampleExcel">샘플양식 다운로드</button>
    </div>

    <div class="btn-section">
        <button class="btn btn-danger" id="member-List">목록</button>
        <div>
            <button class="btn btn-secondary" id="preview">미리보기</button>
            <button class="btn btn-primary" id="save">등록하기</button>
        </div>
    </div>

    <div class="table-container">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>생년월일</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                    <th>소속부서</th>
                    <th>직책</th>
                </tr>
            </thead>
            <tbody id="previewTableBody">
            
            </tbody>
        </table>
    </div>

    <div id="pagination" class="table-pagination">
    </div>
</div>

<script>
let previewData = [];
const pageSize = 10;  // 한 페이지에 보여줄 데이터 수
let currentPage = 1;

function goToPage(pageNumber) {
    currentPage = pageNumber;
    updateTable();
}

function updateTable() {
    var tableBody = $('#previewTableBody');
    tableBody.empty();

    // 현재 페이지에서 보여줄 데이터의 범위 설정
    let start = (currentPage - 1) * pageSize;
    let end = start + pageSize;
    let pagedData = previewData.slice(start, end);

    // 테이블에 데이터 추가
    pagedData.forEach(function(member) {
        var row = '<tr>' +
            '<td>' + member.name + '</td>' +
            '<td>' + member.birthdate + '</td>' +
            '<td>' + member.phoneNo + '</td>' +
            '<td>' + member.email + '</td>' +
            '<td>' + member.department + '</td>' +
            '<td>' + member.position + '</td>' +
            '</tr>';
        tableBody.append(row);
    });
}

function createPagination() {
    var totalPages = Math.ceil(previewData.length / pageSize);
    var pagination = $('#pagination');
    pagination.empty();  // 기존 페이징 버튼 초기화

    for (let i = 1; i <= totalPages; i++) {
        var pageItem = '<button class="btn btn-light" onclick="goToPage(' + i + ')">' + i + '</button>';
        pagination.append(pageItem);
    }
}

$(document).ready(function(){
	$('#sampleExcel').on('click', function(){
		window.location.href = "<c:url value='/member/sampleExcelDown'/>";
	});
	
	$('#member-List').on('click', function(){
		window.location.href = "<c:url value='/member/memberList.do'/>";
	});
	
	$('#preview').on('click', function(){
	    var fileInput = $('#uploadFile').get(0); 
	    if (!fileInput.files || fileInput.files.length === 0) {
	        alert("업로드할 파일을 선택해주세요."); // 파일 미선택 시 경고 메시지
	        return; // 파일이 없으면 업로드 중단
	    }
		
	    myFetch({
	        url: '/member/uploadExcel',  
	        isFormData: true,     
	        data: 'excelUploadForm', 
	        success: function(response) {
	        	previewData = [];
	        	
	            previewData = response.result;
	            
	            //console.log(">>>>" + JSON.stringify(previewDzata));
	            
	            currentPage = 1;  
	            updateTable();   
	            createPagination();  
	        },
	        error: function(err) {
	            alert("엑셀 파일을 업로드하는 중 오류가 발생했습니다: " + err);
	        }
	    });
	});
	
	$('#save').on('click', function(){
		const selectedCorpBiz = $('#group').val();
		
	 	if (!selectedCorpBiz) { 
	        alert("회원 소속을 선택해주세요.");
	        return; // 선택되지 않았을 경우 작업 중단
	    }
		
		const sendData = {
			previewData: previewData,
		    corpBiz: selectedCorpBiz  
		};
		
	    myFetch({
	        url: '/member/memberAllSave',
	        data: sendData,  // 미리보기 데이터를 전송
	        success: function(response) {
	            alert("회원 정보가 성공적으로 등록되었습니다.");
	        },
	        error: function(err) {
	            alert("회원 정보 등록 중 오류가 발생했습니다: " + err);
	        }
	    });
		
	});

	
});



</script>