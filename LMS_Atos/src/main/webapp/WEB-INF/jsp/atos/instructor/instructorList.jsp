<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/member/member.css' />">

<script>
function fn_egov_select_linkPage(pageNo){
	document.memberForm.pageIndex.value = pageNo;
	document.memberForm.action = "<c:url value='/member/instructorList.do'/>";
   	document.memberForm.submit();
}
</script>
<div class="board member-management">
<form name="memberForm" id="memberForm" action="<c:url value='/member/instructorList.do'/>" method="post">

    <h3>강사 목록</h3>

    <!-- 검색 필터 부분 -->
    <div class="search_box">
    	<div>
    	<span>상태</span>
                <select id="status" name="statusCode">
                    <option value="">선택</option>
                    <c:forEach var="status" items="${status }">
                    	<option value="${status.statusCode }" <c:if test="${status.statusCode == searchVO.statusCode}">selected</c:if>>
                    		${status.statusName }
                    	</option>
                    </c:forEach>
                </select>
        </div>

        <div>
        <span>검색</span>       
                <select name="searchCnd" title="검색 조건 선택">
                	<option value="">선택</option>
                    <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if>>아이디</option>
                    <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if>>이름</option>
                </select>
                <input type="text" id="searchText" name="searchWrd" title="검색 조건 입력" placeholder="텍스트를 입력해 주세요." value='<c:out value="${searchVO.searchWrd}"/>' maxlength="155">
                <button type="submit" class="btn btn-primary">검색</button>
        </div> 
    </div>
    
    <input type="hidden" name="pageIndex" value="${searchVO.pageIndex}">
</form>

    <!-- 버튼 그룹 -->
   <div class="btn-group">
	<div class="left-group">
<!--             <select name="viewCount">
                <option value="20">20건 보기</option>
            </select>
            <select name="sortOrder">
                <option value="nameAsc">회원 오름차순</option>
            </select> -->
        </div>
	    <div class="right-group">
	        <button class="btn btn-primary" id="statusUpdate">상태변경</button>
	        <button class="btn btn-primary" id="excelDown">EXCEL</button>
	        <button class="btn btn-primary" id="regist">등록</button>
	    </div>
    </div>

    <!-- 테이블 -->
    <table class="board_list">
        <colgroup>
	        <col style="width: 5%;">
	        <col style="width: 15%;">
	        <col style="width: 10%;">
	        <col style="width: 15%;">
	        <col style="width: 10%;">
	        <col style="width: 10%;">
	        <col style="width: 10%;">
	        <col style="width: 10%;">
	        <col style="width: 10%;">
	        <col style="width: 5%;">
    	</colgroup>
        <thead>
            <tr>
                <th>번호</th>
                <th class="board_th_link">아이디</th>
                <th>이름</th>
                <th>전화번호</th>
                <th>소속기관</th>
                <th>소속</th>
                <th>직책</th>
                <th>직업</th>
                <th>상태</th>
                <th><input type="checkbox" id="checkAll"></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${resultList}" var="resultInfo" varStatus="status">
            <tr>
                <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
                <td class="left"><a href="<c:url value='/member/instructorDetail.do' />?id=${resultInfo.id }&pageIndex=${searchVO.pageIndex}"><c:out value="${resultInfo.id }" /></a></td>
                <td><c:out value="${resultInfo.name }" /></td>
                <td><c:out value="${resultInfo.phoneNo }" /></td>
                <td><c:out value="${resultInfo.affiliation }" /></td>
                <td><c:out value="${resultInfo.department }" /></td>
                <td><c:out value="${resultInfo.position }" /></td>
                <td><c:out value="${resultInfo.job }" /></td>
                <td><c:out value="${resultInfo.listStatusName }" /></td>
                <td><input type="checkbox" name="rowCheck" value="${resultInfo.id }"></td>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(resultList) == 0}">
        <tr>
            <td colspan="7">조회된 결과가 존재하지 않습니다.</td>
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
        <div class="d-flex justify-content-around">
            <div class="form-check me-3">
                <input class="form-check-input" type="radio" name="statusRadio" id="status1" value="1002" checked>
                <label class="form-check-label" for="status1">정상</label>
            </div>
            <div class="form-check me-3">
                <input class="form-check-input" type="radio" name="statusRadio" id="status2" value="1004">
                <label class="form-check-label" for="status2">정지</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="statusRadio" id="status3" value="1003">
                <label class="form-check-label" for="status3">휴면</label>
            </div>
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
	
	//등록 이동
	$('#regist').on('click', function() {
		window.location.href = "<c:url value='/member/instructorRegistView.do'/>";
	});
	
    // 상태 셀렉트 박스 변경 시
    $('#status').change(function() {
        $('#memberForm').submit();
    });
	
	
    // 전체 선택/해제 기능
    $('#checkAll').on('click', function() {
        $('tbody input[name="rowCheck"]').prop('checked', this.checked);
    });

    // 상태변경 버튼 클릭 시
    $('#statusUpdate').on('click', function() {
        var selectedData = [];

        $('tbody input[name="rowCheck"]:checked').each(function() {
            var tr = $(this).closest('tr');
            var id = tr.find('td:nth-child(2)').text(); //
            selectedData.push(id);
        });

        if (selectedData.length > 0) {
        	$('#statusModal').modal('show');
        } else {
            alert("선택된 항목이 없습니다.");
        }
    });

    // 확인 버튼 클릭 시 상태 변경 처리
    $('#confirmChangeBtn').on('click', function() {
        var selectedStatus = $('input[name="statusRadio"]:checked').val();
        var selectedIds = [];

        $('tbody input[name="rowCheck"]:checked').each(function() {
            var id = $(this).closest('tr').find('td:nth-child(2)').text(); 
            selectedIds.push(id);
        });

        if (selectedIds.length > 0) {
            
            $.ajax({
                url: '/member/updateInstructorStatus', 
                method: 'POST',
                data: {
                    ids: selectedIds.join(','), 
                    status: selectedStatus
                },
                success: function(response) {
                	alert(response);
                    location.reload(); 
                },
                error: function() {
                    alert('서버와의 통신에 실패했습니다.');
                }
            });

            // 모달 닫기
            $('#statusModal').modal('hide');
        } else {
            alert("선택된 항목이 없습니다.");
        }
    });
    
    $('#excelDown').on('click', function(){
        const memberForm = $('#memberForm');
        const originalAction = memberForm.attr('action');

        memberForm.attr('action', '/member/instructorListExcelDown');

        memberForm.submit();

        memberForm.attr('action', originalAction);
    });
    
    
});
</script>