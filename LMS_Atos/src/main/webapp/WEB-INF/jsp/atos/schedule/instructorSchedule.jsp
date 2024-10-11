<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/schedule/instructorSchedule.css' />">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- FullCalendar 6.1.10 JS/CSS 추가 -->
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.js"></script>

<div class="head-section">
    <span>&nbsp;스케줄 관리</span>
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
				            <option value="">강사 선택</option>
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

<script>
$(document).ready(function() {
    var calendarEl = document.querySelector('.instructorCalendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'ko', // 한국어 설정
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'dayGridMonth',
        editable: true,
        navLinks: true,
        events: [
            <c:forEach var="schedule" items="${scheduleList}">
                {
                    title: '${schedule.mainEvent != null ? schedule.mainEvent : schedule.subEvent}',
                    start: '${schedule.scheduleDate}' + '<c:out value="${schedule.startTime != null ? 'T' + schedule.startTime : ''}" />',
                    end: '<c:out value="${schedule.endTime != null ? schedule.scheduleDate + 'T' + schedule.endTime : ''}" />', 
                    allDay: '${schedule.startTime == null && schedule.endTime == null ? "true" : "false"}', 
                    color: '${schedule.mainEvent != null ? "blue" : "green"}' 
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ]
    });

    calendar.render();
});

document.getElementById("instructorId").addEventListener("change", function() {
    console.log("Selected Instructor ID: " + this.value);
});

</script>
