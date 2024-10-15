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
<%@ include file="/WEB-INF/jsp/atos/schedule/instructorEditModal.jsp" %>


<script>
    // FullCalendar 초기화 및 설정
    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.querySelector('.instructorCalendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            locale: 'ko',
            headerToolbar: {
                left: 'prev,next today register',
                center: 'title',
                right: 'dayGridMonth,dayGridWeek,listMonth'
            },
            customButtons: {
                register: {
                    text: '등록',
                    click: function() {
                        $('#scheduleModal').modal('show');
                    }
                }
            },
            initialView: 'dayGridMonth',
            editable: false,
            navLinks: true,
            events: [
                <c:forEach var="schedule" items="${scheduleList}" varStatus="status">
                {
                    id: '${schedule.scheduleCode}', // 스케줄 코드 추가
                    title: '${schedule.name} - ${schedule.mainEvent}',
                    start: '${schedule.startDate}',
                    end: '${schedule.endDate}',
                    allDay: true,
                    color: '${schedule.scheduleColor}',
                    extendedProps: {
                        instructorId: '${schedule.id}' // 강사 ID를 extendedProps에 포함
                    }
                }<c:if test="${!status.last}">,</c:if>
                </c:forEach>
            ],
            eventClick: function(info) {
                // 스케줄 정보를 수정 모달에 표시
                var event = info.event;

                // 강사 선택 드롭다운에서 해당 강사 선택하기
                $('#editModalInstructorId').val(event.extendedProps.instructorId).change(); // 강사 ID

                // 타이틀, 시작일, 종료일, 색상 설정
                $('#editMainEvent').val(event.title.split(' - ')[1]); // 타이틀
                $('#editStartDate').val(event.startStr); // 시작일
                $('#editEndDate').val(event.endStr); // 종료일
                $('#editScheduleColor').val(event.backgroundColor); // 색상

                // 수정 및 삭제 모달 띄우기
                $('#editScheduleModal').modal('show');

                // 수정 버튼 클릭 시 이벤트 등록
                $('#updateScheduleBtn').off('click').on('click', function() {
                    updateSchedule(event.id);
                });

                // 삭제 버튼 클릭 시 이벤트 등록
                $('#deleteScheduleBtn').off('click').on('click', function() {
                    deleteSchedule(event.id);
                });
            }
        });

        calendar.render();
        
        
        
        
        // 수정 기능 구현
        function updateSchedule(scheduleId) {
            var instructorId = $('#editModalInstructorId').val();
            var mainEvent = $('#editMainEvent').val();
            var startDate = $('#editStartDate').val();
            var endDate = $('#editEndDate').val();
            var scheduleColor = $('#editScheduleColor').val();

            if (!instructorId || !mainEvent || !startDate || !scheduleColor) {
                alert("모든 필수 입력값을 입력해주세요.");
                return;
            }

            // 수정할 데이터를 JSON으로 변환
            var formDataJson = {
                scheduleCode: scheduleId,
                id: instructorId,
                mainEvent: mainEvent,
                startDate: startDate,
                scheduleColor: scheduleColor
            };
            
            if (endDate) {
                formDataJson.endDate = endDate;
            }
			
            console.log("수정할 데이터:", formDataJson); // 로그로 데이터 확인
            
            $.ajax({
                type: 'PUT',
                url: '<c:url value="/education/updateSchedule"/>',
                contentType: 'application/json', // JSON 형식으로 전송
                data: JSON.stringify(formDataJson), // 데이터를 JSON 문자열로 변환
                success: function(response) {
                    if (response.message) {
                        alert(response.message);
                        $('#editScheduleModal').modal('hide'); // 모달 닫기
                        window.location.reload(); // 페이지 새로고침
                    } else {
                        alert('스케줄 수정에 실패했습니다.');
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error occurred: ", error);
                    alert('오류가 발생했습니다. 다시 시도해 주세요.');
                }
            });
        }

        // 삭제 기능 구현
        function deleteSchedule(scheduleId) {
            if (!confirm("정말로 삭제하시겠습니까?")) {
                return;
            }

            var formDataJson = {
                scheduleCode: scheduleId
            };

            $.ajax({
                type: 'DELETE',
                url: '<c:url value="/education/deleteSchedule"/>',
                contentType: 'application/json', // JSON 형식으로 전송
                data: JSON.stringify(formDataJson), // 데이터를 JSON 문자열로 변환
                success: function(response) {
                    if (response.message) {
                        alert(response.message);
                        $('#editScheduleModal').modal('hide'); // 모달 닫기
                        window.location.reload(); // 페이지 새로고침
                    } else {
                        alert('스케줄 삭제에 실패했습니다.');
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error occurred: ", error);
                    alert('오류가 발생했습니다. 다시 시도해 주세요.');
                }
            });
        }
        
               
        
        // 종료 날짜에 하루 추가하기 위해 JavaScript로 처리
        calendar.getEvents().forEach(function(event) {
            if (event.end) {
                let endDate = new Date(event.end);
                endDate.setDate(endDate.getDate() + 1);
                event.setEnd(endDate);
            }
        });

     // 모달의 저장 버튼 클릭 시 이벤트 핸들러 등록
        $('#saveScheduleBtn').on('click', function(event) {
            event.preventDefault(); // 기본 동작인 폼 제출을 막음

            var instructorId = $('#modalInstructorId').val();
            var mainEvent = $('#mainEvent').val();
            var startDate = $('#startDate').val();
            var endDate = $('#endDate').val();
            var scheduleColor = $('#scheduleColor').val();

            if (!instructorId) {
                console.warn("강사 ID가 선택되지 않았습니다.");
            }
            if (!mainEvent) {
                console.warn("타이틀이 입력되지 않았습니다.");
            }
            if (!startDate) {
                console.warn("시작일이 입력되지 않았습니다.");
            }
            if (!endDate) {
                console.warn("종료일이 입력되지 않았습니다.");
            }
            if (!scheduleColor) {
                console.warn("스케줄 색상이 선택되지 않았습니다.");
            }

            if (!instructorId || !mainEvent || !startDate || !endDate || !scheduleColor) {
                alert("모든 필수 입력값을 입력해주세요.");
                return;
            }

            // 폼 데이터를 JSON 형식으로 변환
            var formDataJson = {
                id: instructorId,
                mainEvent: mainEvent,
                startDate: startDate,
                endDate: endDate,
                scheduleColor: scheduleColor
            };
            console.log("Form Data as JSON: ", formDataJson);
            
            $.ajax({
                type: 'POST',
                url: '<c:url value="/education/registerSchedule"/>',
                contentType: 'application/json',  // JSON 형식으로 전송
                data: JSON.stringify(formDataJson), // 데이터를 JSON 문자열로 변환
                success: function(response) {
                    if (response.message) {
                        alert(response.message);
                        $('#scheduleModal').modal('hide'); // 모달 닫기

                        // 페이지 새로고침
                        window.location.reload(); // 전체 페이지를 새로고침하여 최신 데이터를 반영
                    } else {
                        alert('스케줄 등록에 실패했습니다.');
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error occurred: ", error);
                    alert('오류가 발생했습니다. 다시 시도해 주세요.');
                }
            });

        });
     
     
    });

  
</script>