<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/education/education.css' />">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>

	function fn_egov_select_linkPage(pageNo) {
	    document.educationForm.pageIndex.value = pageNo;
	    document.educationForm.action = "<c:url value='/education/educationList.do' />";
	    document.educationForm.submit();
	}

</script>
<div class="board education-management">
	<form name="educationForm" action="<c:url value='/education/educationList.do'/>" method="post">
 		<h3>교육 과정 목록</h3>

		<!-- 검색 필터 부분 -->
		<div class="search_box">

			<div>
				<span>상태</span> 
				<select id="status" name="statusCode">
					<option value="">선택</option>
					<c:forEach var="status" items="${status}">
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
	        <button class="s_submit" onclick="location.href='<c:url value='/education/educationRegistView.do' />'">등록</button>
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
                <!-- 교육 분류 명칭을 대분류, 중분류, 소분류로 출력 -->
                <td>${resultInfo.mainName } > ${resultInfo.subName } > ${resultInfo.detailName }</td> 
				<td><a href="<c:url value='/education/educationDetail.do?eduCode=${resultInfo.eduCode}'/>">
				${resultInfo.title} </a></td>
				<td></td>
                <td>${resultInfo.statusName }</td> <!-- 상태 코드 대신 명칭 -->
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
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="statusModalLabel">상태 변경</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="modalContent">
        <div class="form-check">
            <input class="form-check-input" type="radio" name="statusRadio" id="status1" value="1002" checked>
            <label class="form-check-label" for="status1">정상</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="statusRadio" id="status2" value="1001">
            <label class="form-check-label" for="status2">대기</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="statusRadio" id="status3" value="1004">
            <label class="form-check-label" for="status3">정지</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="statusRadio" id="status4" value="1003">
            <label class="form-check-label" for="status4">휴면</label>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="confirmChangeBtn">확인</button>
      </div>
    </div>
  </div>
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
            var eduCode = $(this).val(); // 교육코드 값 가져오기
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
            var eduCode = $(this).val(); // 교육코드 값 가져오기
            selectedEduCode.push(eduCode);
        });

        if (selectedEduCode.length > 0) {
            $.ajax({
                url: '/education/updateStatus', // 서버에서 상태를 업데이트하는 엔드포인트
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    eduCodes: selectedEduCode, // 배열 형태로 eduCode 전송
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

$(document).ready(function() {
    $('#excelDown').on('click', function() {
        const educationForm = $('form[name="educationForm"]');
        const originalAction = educationForm.attr('action');

        educationForm.attr('action', '/education/educationListExcelDown');
        educationForm.submit();

        educationForm.attr('action', originalAction);
    });
});
</script>
