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
    <form id="instructorScheduleForm" name="instructorScheduleForm" action="<c:url value='/schedule/instructorScheduleList.do'/>" method="post">
        <!-- 스케줄 조회용 폼 -->
    </form>
</div>

<!-- FullCalendar 달력 -->
<div id='calendar'></div>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prevYear,prev,next,nextYear today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek,dayGridDay'
      },
      initialView: 'dayGridMonth',
      editable: true,
      dayMaxEvents: true, // 'more' 링크 추가
      events: function(fetchInfo, successCallback, failureCallback) {
        // 서버에서 스케줄 목록을 가져옴
        fetch('<c:url value="/schedule/instrScheduleList"/>')
          .then(response => response.json())
          .then(data => {
            var events = data.map(function(schedule) {
              return {
                title: schedule.title || '스케줄',
                start: schedule.scheduleDate,
                allDay: true
              };
            });
            successCallback(events);
          })
          .catch(() => {
            failureCallback();
          });
      }
    });

    calendar.render();
  });
</script>

<style>
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px; /* max-width 조정하여 여유 있게 표시 */
    margin: 0 auto;
  }
</style>
