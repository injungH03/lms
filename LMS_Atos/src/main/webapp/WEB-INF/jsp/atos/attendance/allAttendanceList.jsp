<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/attendance/allAttendance.css' />">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


<%@ include file="/WEB-INF/jsp/atos/attendance/attendanceModal.jsp" %>


<script>
function fn_egov_select_linkPage(pageNo){
    document.allAttendancenForm.pageIndex.value = pageNo;
    document.allAttendancenForm.action = "<c:url value='/attendance/allAttendanceList.do'/>";
    document.allAttendancenForm.submit();
}
</script>


<div class="head-section">
	<span>&nbsp;통합수강생출석관리</span>
</div>


<div class="table-section">
	<form id="allAttendancenForm" name="allAttendancenForm" action="<c:url value='/attendance/allAttendanceList.do'/>" method="get">
		<input type="hidden" name="pageIndex" value="${attendanceSearchVO.pageIndex}">
	
<!-- 과정 선택 -->
        <table class="search-table">
            <tr>
                <th>과정</th>
                <td>
                    <div class="d-flex">
						<select name="lectureCode" class="form-select search-select me-2">
						    <option value="0">전체</option>
                        <c:forEach var="lecture" items="${educationList}"> <!-- 변경: items="${educationList}"로 변경 -->
	                        <option value="${lecture.eduCode}" 
							    <c:if test="${lecture.eduCode == attendanceSearchVO.lectureCode}">selected="selected"</c:if>>
							    ${lecture.title}
							</option>
                        </c:forEach>
						</select>
                    </div>
                </td>
            </tr>

<!-- 출석일 선택 -->
            <tr>
                <th>출석일</th>
                <td colspan="2">
                    <div class="d-flex">
                        <input type="date" name="srcStartDate" id="startDate" class="form-control me-2 custom-date-picker" 
                               value="${attendanceSearchVO.srcStartDate}"/>
                        <input type="date" name="srcEndDate" id="endDate" class="form-control me-2 custom-date-picker" 
                               value="${attendanceSearchVO.srcEndDate}"/>
                    </div>
                </td>
            </tr>

<!-- 검색 조건 -->
            <tr>
                <th>검색</th>
                <td>
                    <div class="d-flex">                  
                        <select name="searchCnd" class="form-select search-select me-2">
                            <option value="">전체</option>
                            <option value="0" <c:if test="${attendanceSearchVO.searchCnd == '0'}">selected</c:if>>회원명</option>
                            <option value="1" <c:if test="${attendanceSearchVO.searchCnd == '1'}">selected</c:if>>업체명</option>
                        </select>
                        <input type="text" name="searchWrd" class="form-control search-input me-2" placeholder="검색어를 입력하세요" value='<c:out value="${attendanceSearchVO.searchWrd}"/>'/>
                        <button type="submit" class="btn-search">검색</button>
                    </div>
                </td>
            </tr>
        </table>
    </form>


<!-- 테이블 위에 버튼 섹션 -->
<div class="d-flex justify-content-end mb-2 mt-5">
    <div>
    	<button class="btn-create-course" id="attendAllCheckIn">전체입실</button>
    	<button class="btn-create-course" id="attendAllCheckOut">전체퇴실</button>
    	<button class="btn-create-course" id="attendAllAbsence">결석처리</button>
        <button class="btn-excel">EXCEL</button>
    </div>
</div>

<!-- 과정 테이블 섹션 -->
<div class="course-table-section">
    <table class="table table-bordered">
       <colgroup>
	        <col style="width: 3%;">
	        <col style="width: 15%;">
	        <col style="width: 20%;">
	        <col style="width: 5%;">
	        <col style="width: 9%;">
	        <col style="width: 7%;">
	        <col style="width: 7%;">
	        <col style="width: 10%;">
	        <col style="width: 3%;">
    	</colgroup>
        <thead>
            <tr>
                <th>No</th>
                <th>소속</th>
                <th>수강생</th>
                <th>상태</th>
                <th>출석일</th>
                <th>입실시간</th>
                <th>퇴실시간</th>
                <th>출결</th>
                <th><input type="checkbox" id="checkAll"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="resultInfo" items="${resultList}" varStatus="status">
                <tr>
                    <td>${(attendanceSearchVO.pageIndex - 1) * attendanceSearchVO.recordCountPerPage + status.index + 1}</td>
                    <td>${resultInfo.corpName}</td> <!-- 소속 -->
                    <td>${resultInfo.id}(${resultInfo.name})</td> <!-- 수강생 아이디(이름) -->
                    <td>${resultInfo.statusName}</td> <!-- 상태 -->
                    <td>${resultInfo.attendDate}</td> <!-- 출석일 -->
                    <td>${resultInfo.inTime}</td> <!-- 입실 시간 -->
                    <td>${resultInfo.outTime}</td> <!-- 퇴실 시간 -->
                    <td class="manage">
<!--                     
                        <span class="status-box me-2 status-attend">입실</span>
                        <span class="status-box status-absent">퇴실</span>
                         -->
                        
                        <span class="status-box me-2 status-attend" data-attend-code="${resultInfo.attendCode}">입실</span>
                        <span class="status-box status-absent" data-attend-code="${resultInfo.attendCode}">퇴실</span>
       
                    </td>
                    <td><input type="checkbox" name="rowCheck" value="${resultInfo.attendCode}"></td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(resultList) == 0}">
                <tr>
                    <td colspan="9">조회된 결과가 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>
<div class="pagination justify-content-center">
    <ul>
        <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
    </ul>
</div>
</div>



<script>

$(document).ready(function() {
    // 입실 버튼 클릭 시
    $('.status-attend').click(function() {
        var attendCode = $(this).data('attend-code');
        $.ajax({
            url: "<c:url value='/attendance/checkIn' />",
            type: "POST",
            data: { attendCode: attendCode },
            success: function(response) {
                if (response === 'success') {
                    alert("입실 처리가 완료되었습니다.");
                    location.reload(); // 페이지 새로고침
                } else {
                    alert("입실 처리에 실패하였습니다.");
                }
            },
            error: function() {
                alert("입실 처리 중 오류가 발생하였습니다.");
            }
        });
    });

    // 퇴실 버튼 클릭 시
    $('.status-absent').click(function() {
        var attendCode = $(this).data('attend-code');
        $.ajax({
            url: "<c:url value='/attendance/checkOut' />",
            type: "POST",
            data: { attendCode: attendCode },
            success: function(response) {
                if (response === 'success') {
                    alert("퇴실 처리가 완료되었습니다.");
                    location.reload(); // 페이지 새로고침
                } else {
                    alert("퇴실 처리에 실패하였습니다.");
                }
            },
            error: function() {
                alert("퇴실 처리 중 오류가 발생하였습니다.");
            }
        });
    });

    // 전체 선택/해제 기능
    $('#checkAll').on('click', function() {
        $('tbody input[name="rowCheck"]').prop('checked', this.checked);
    });


    
    /* 전체 버튼 */
    
    const attendanceModal = new bootstrap.Modal(document.getElementById('attendanceModal'));

    // 전체입실 버튼 클릭 시
    $('#attendAllCheckIn').click(function() {
        console.log("전체입실 버튼 클릭됨");
        actionType = 'checkIn';
        selectedAttendanceCodes = [];
        $('input[name="rowCheck"]:checked').each(function() {
            selectedAttendanceCodes.push($(this).val());
        });

        if (selectedAttendanceCodes.length === 0) {
            alert("입실 처리할 수강생을 선택해주세요.");
            return;
        }

        $('#dateInputContainer').show();
        attendanceModal.show();  // 모달창 띄우기
    });

    // 전체퇴실 버튼 클릭 시
    $('#attendAllCheckOut').click(function() {
        console.log("전체퇴실 버튼 클릭됨");
        actionType = 'checkOut';
        selectedAttendanceCodes = [];
        $('input[name="rowCheck"]:checked').each(function() {
            selectedAttendanceCodes.push($(this).val());
        });

        if (selectedAttendanceCodes.length === 0) {
            alert("퇴실 처리할 수강생을 선택해주세요.");
            return;
        }

        $('#dateInputContainer').hide();
        attendanceModal.show();  // 모달창 띄우기
    });

    // 모달 확인 버튼 클릭 시
    $('#confirmTimeBtn').click(function() {
        console.log("모달 확인 버튼 클릭됨");
        const date = $('#modalDateInput').val();
        const time = $('#modalTimeInput').val();

        if (!time) {
            alert("시간을 입력해주세요.");
            return;
        }

        let url = '';
        let data = { attendCodes: selectedAttendanceCodes };

        if (actionType === 'checkIn') {
            if (!date) {
                alert("날짜를 입력해주세요.");
                return;
            }
            url = "<c:url value='/attendance/checkInAll' />";
            data.inTime = time;
            data.attendDate = date;
        } else if (actionType === 'checkOut') {
            url = "<c:url value='/attendance/checkOutAll' />";
            data.outTime = time;
        }

        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function(response) {
                if (response === 'success') {
                    alert(actionType === 'checkIn' ? "전체 입실 처리가 완료되었습니다." : "전체 퇴실 처리가 완료되었습니다.");
                    location.reload(); // 페이지 새로고침
                } else {
                    alert(actionType === 'checkIn' ? "전체 입실 처리에 실패하였습니다." : "전체 퇴실 처리에 실패하였습니다.");
                }
            },
            error: function(xhr, status, error) {
                console.error("Error status:", status);
                console.error("Error details:", error);
                alert(actionType === 'checkIn' ? "전체 입실 처리 중 오류가 발생하였습니다." : "전체 퇴실 처리 중 오류가 발생하였습니다.");
            }
        });

        attendanceModal.hide();  // 모달 닫기
    });

    // 결석 처리 버튼 클릭 시 (모달 없이 바로 처리)
    $('#attendAllAbsence').click(function() {
        console.log("결석 처리 버튼 클릭됨");
        selectedAttendanceCodes = [];
        $('input[name="rowCheck"]:checked').each(function() {
            selectedAttendanceCodes.push($(this).val());
        });

        if (selectedAttendanceCodes.length === 0) {
            alert("결석 처리할 수강생을 선택해주세요.");
            return;
        }

        $.ajax({
            url: "<c:url value='/attendance/allAbsence' />",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({ attendCodes: selectedAttendanceCodes }),
            success: function(response) {
                if (response === 'success') {
                    alert("전체 결석 처리가 완료되었습니다.");
                    location.reload(); // 페이지 새로고침
                } else {
                    alert("전체 결석 처리에 실패하였습니다.");
                }
            },
            error: function() {
                alert("전체 결석 처리 중 오류가 발생하였습니다.");
            }
        });
    });

}); 

</script>