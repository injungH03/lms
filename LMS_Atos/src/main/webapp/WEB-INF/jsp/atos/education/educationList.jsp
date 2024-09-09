<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/company/company.css' />">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>

	function fn_egov_select_linkPage(pageNo) {
	    document.companyForm.pageIndex.value = pageNo;
	    document.companyForm.statusCode.value = "${educationSearchVO.statusCode}";
	    document.companyForm.searchCnd.value = "${educationSearchVO.searchCnd}";
	    document.companyForm.searchWrd.value = "${educationSearchVO.searchWrd}";
	    document.companyForm.action = "<c:url value='/education/educationList.do' />";
	    document.companyForm.submit();
	}

</script>
<div class="board company-management">
	<form name="companyForm" action="<c:url value='/education/educationList.do'/>" method="post">
 		<h3>교육 과정 목록</h3>

		<!-- 검색 필터 부분 -->
		<div class="search_box">

			<div>
				<span>상태</span> <select id="status" name="statusCode">
					<option value="">선택</option>
					<c:forEach var="status" items="${status }">
						<option value="${status.statusCode }"
							<c:if test="${status.statusCode == educationSearchVO.statusCode}">selected</c:if>>
							${status.statusName }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<span>검색</span> 
				<select name="searchCnd" title="검색 조건 선택">
					<option value="">선택</option>
					<option value="0" <c:if test="${educationSearchVO.searchCnd == '0'}">selected="selected"</c:if>>교육명</option>
					<option value="1" <c:if test="${educationSearchVO.searchCnd == '1'}">selected="selected"</c:if>>교육분류</option>
				</select> 
				<input type="text" id="searchText" name="searchWrd" title="검색 조건 입력" placeholder="텍스트를 입력해 주세요." value='<c:out value="${educationSearchVO.searchWrd}"/>' maxlength="155">
				<button type="submit" class="s_btn">검색</button>
			</div>
		</div>
		
		<input type="hidden" name="pageIndex" value="${educationSearchVO.pageIndex}">
		 
	</form>


	<!-- 버튼 그룹 -->
   <div class="btn-group">
	<div class="left-group">
        </div>
	    <div class="right-group">
	        <button class="s_submit" id="statusUpdate">상태변경</button>
	        <button class="s_submit" id="excelDown">EXCEL</button>
	        <button class="s_submit" onclick="location.href='<c:url value='/education/EducationRegistView.do' />'">등록</button>
	    </div>
    </div>

    <!-- 테이블 -->
    <table class="board_list">
        <thead>
            <tr>
                <th>번호</th>
                <th>교육 분류</th>
                <th class="board_th_link">교육명</th>
                <th>교육 시간</th>
                <th>상태</th>
                <th><input type="checkbox" id="checkAll"></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
            <tr>
                <td><c:out value="${(educationSearchVO.pageIndex-1) * educationSearchVO.pageSize + status.count}"/></td>
                <td>${resultInfo.category }</td>
				<td><a href="<c:url value='/education/educationDetail.do?eduCode=${resultInfo.eduCode}'/>">
				${resultInfo.title} </a></td>
				<td>${resultInfo.trainingTime }</td>
                <td>${resultInfo.status }</td>
                <td><input type="checkbox" name="rowCheck" value="${resultInfo.eduCode }"></td>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(resultList) == 0}">
        <tr>
            <td colspan="6">조회된 결과가 존재하지 않습니다.</td>
        </tr>
        </c:if>
        </tbody>
    </table>

    <!-- 페이지네이션 -->
    <div class="pagination justify-content-center">
        <ul>
            <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
        </ul>
    </div>
</div>

<div id="statusModal" class="modal fade" tabindex="-1" aria-labelledby="statusModalLabel" aria-hidden="true">
  <!-- 상태 변경 모달 내용 유지 -->
</div>

<script>
$(document).ready(function() {
    // 전체 선택/해제 기능
    $('#checkAll').on('click', function() {
        $('tbody input[name="rowCheck"]').prop('checked', this.checked);
    });

    // 상태변경 버튼 클릭 시
    $('#statusUpdate').on('click', function() {
        var selectedEduCode = [];

        $('tbody input[name="rowCheck"]:checked').each(function() {
            var tr = $(this).closest('tr');
            var eduCode = tr.find('td:nth-child(4)').text(); // 교육코드 (4번째 컬럼)
            selectedEduCode.push(eduCode);
        });

        if (selectedEduCode.length > 0) {
        	$('#statusModal').modal('show');
        } else {
            alert("선택된 항목이 없습니다.");
        }
    });

    // 상태변경 확인 버튼 클릭 시
    $('#confirmChangeBtn').on('click', function() {
        var selectedStatus = $('input[name="statusRadio"]:checked').val();
        var selectedEduCode = [];

        $('tbody input[name="rowCheck"]:checked').each(function() {
            var eduCode = $(this).closest('tr').find('td:nth-child(4)').text(); // 교육코드
            selectedEduCode.push(eduCode);
        });

        if (selectedEduCode.length > 0) {
            $.ajax({
                url: '/education/updateStatus', // 서버에서 상태를 업데이트하는 엔드포인트
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    eduCode: selectedEduCode.join(','), // 선택된 교육코드들을 전송
                    status: selectedStatus // 선택된 상태 값
                }),
                success: function(response) {
                    if (response.httpStatus === 'OK') {
                        alert('상태 변경이 완료되었습니다.');
                        location.reload(); // 페이지 새로고침하여 변경 사항 반영
                    } else {
                        alert('상태 변경에 실패했습니다.');
                    }
                },
                error: function() {
                    alert('서버와의 통신에 실패했습니다.');
                }
            });

            $('#statusModal').modal('hide');
        } else {
            alert("선택된 항목이 없습니다.");
        }
    });
});
</script>
