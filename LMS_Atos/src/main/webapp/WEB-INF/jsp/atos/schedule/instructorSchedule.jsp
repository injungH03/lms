<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/schedule/instructorSchedule.css' />">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- FullCalendar 6.1.10 JS/CSS 추가 -->
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.js"></script>





<div class="head-section">
    <span>&nbsp;강사스케줄관리</span>
</div>

<div class="table-section">
    <form id="instructorScheduleForm" name="instructorScheduleForm" action="<c:url value='/education/instructorScheduleList.do'/>" method="post">
        <table class="search-table">
            <tr>
				<th>강사 선택</th>
				<td>
				    <div class="d-flex">
				        <!-- 강사 목록을 조회하여 표시 -->
				        <select name="id" id="instructorId" class="form-select search-selectle me-2" onchange="this.form.submit();">
				            <option value="">전체 강사</option> <!-- 전체 강사를 선택할 수 있도록 옵션 추가 -->
				            <c:forEach var="entry" items="${instructorList}">
				                <!-- 선택된 강사와 일치하는 경우 selected 속성을 추가 -->
				                <option value="${entry.key}" <c:if test="${entry.key == param.id}">selected="selected"</c:if>>${entry.value.name}</option>
				            </c:forEach>
				        </select>
				    </div>
				</td>
            </tr>
        </table>
    </form>
</div>




<!-- FullCalendar 달력 -->
<div class="instructorCalendar"></div>

<!-- 모달 내용 포함 -->
<%@ include file="/WEB-INF/jsp/atos/schedule/instructorModal.jsp" %>


<script>
    // FullCalendar 초기화 및 설정
    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.querySelector('.instructorCalendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            locale: 'ko', // 한국어 설정
            headerToolbar: {
                left: 'prev,next today register', // 'register' 버튼 추가
                center: 'title',
                right: 'dayGridMonth,listMonth'
            },
            customButtons: {
                register: {
                    text: '등록', // 버튼에 표시될 텍스트
                    click: function() {
                        // 모달 띄우기
                        $('#scheduleModal').modal('show');
                    }
                }
            },
            initialView: 'dayGridMonth',
            editable: true,
            navLinks: true,
            events: [
                <c:forEach var="schedule" items="${scheduleList}" varStatus="status">
                    {
                        title: '${schedule.mainEvent}', // 메인 이벤트
                        start: '${schedule.startDate != null ? schedule.startDate.toString() : ""}', // 시작 날짜
                        end: '${schedule.endDate != null ? schedule.endDate.plusDays(1).toString() : ""}', // 종료 날짜에 하루 추가
                        allDay: true, // All-day 이벤트로 설정
                        color: '${schedule.scheduleColor}',  // DB에서 가져온 색상 정보 사용
                    }<c:if test="${!status.last}">,</c:if>
                </c:forEach>
            ]
        });

        calendar.render(); // 달력 렌더링

        // 부모 창에서 FullCalendar을 참조할 수 있도록 전역 변수로 설정
        window.FullCalendarInstance = calendar;
    });

    // 부모 창에서 호출할 수 있는 reloadCalendar 함수 정의
    function reloadCalendar() {
        if (window.FullCalendarInstance) {
            window.FullCalendarInstance.refetchEvents(); // 이벤트 다시 가져오기
        }
    }
</script>