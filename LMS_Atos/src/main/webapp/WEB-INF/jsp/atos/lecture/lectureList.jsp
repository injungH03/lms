<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<div class="head-section" style="margin-bottom:20px;">
	<span>&nbsp;집합과정운영</span>
</div>

<div class="tab-section">
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="supervisor-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="supervisor" aria-selected="true">관리감독자 정기교육</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="worker-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="worker" aria-selected="false">근로자 정기교육</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="assessment-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="assessment" aria-selected="false">위험성평가교육</button>
        </li>
    </ul>
</div>


<div class="table-section">

    <form method="get" action="">
        <table class="search-table">
            <tr>
                 <th class="custom-th-width">신청시작/종료일</th>
                 <td colspan="2">
                    <div class="d-flex">
                        <span>시작일 :</span><input type="date" name="startDate" id="startDate" class="form-control me-2 custom-date-picker" /> 
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
						<span class="span-ml">종료일 :</span><input type="date" name="endDate" id="endDate" class="form-control me-2 custom-date-picker" />
						<span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td>
<!--                 <th class="custom-th-width">신청시작일</th>
                <td>
                    <div class="d-flex">
                        <input type="date" name="startDate" id="startDate" class="form-control me-2 custom-date-picker" /> 
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td>
                <th class="custom-th-width">신청종료일</th>
                <td>
                    <div class="d-flex">
						<input type="date" name="endDate" id="endDate" class="form-control me-2 custom-date-picker" />
						<span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td> -->
                <th class="custom-th-width">과정날짜</th>
                <td colspan="3">
                    <div class="d-flex">
                        <input type="date" name="learnDate" id="learningStartDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>검색</th>
                <td colspan="5">
                    <div class="d-flex">
                        <select name="searchType" class="form-select search-select me-2">
                            <option value="전체">전체</option>
                            <option value="전체">과정명</option>
                            <option value="전체">배정강사</option>
                        </select>
                        <input type="text" name="searchKeyword" class="form-control search-input me-2" placeholder="검색어를 입력하세요" />
                        <button type="submit" class="btn-search">검색</button>
                    </div>
                </td>
            </tr>
        </table>
    </form>


<!-- 테이블 위에 버튼 섹션 -->
<div class="d-flex justify-content-between mb-2 mt-5">
    <div>
        Total: <strong>38건</strong>
    </div>
    <div>
        <button class="btn-create-course">과정개설</button>
        <button class="btn-excel">EXCEL</button>
    </div>
</div>

<!-- 과정 테이블 섹션 -->
<div class="course-table-section">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>No</th>
                <th>과정명</th>
                <th>배정 강사명</th>
                <th>교육시간</th>
                <th>인원수</th>
                <th>접수 신청 기간</th>
                <th>과정날짜</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>테스트 과정 1 (제조업)</td>
                <td>홍길동</td>
                <td>8</td>
                <td>40/50</td>
                <td>2024.09.08 ~ 2024.09.19</td>
                <td>2024.09.11</td>
                <td>
                    <div class="btn-group">
                        <button>수정</button>
                        <button>삭제</button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>테스트 과정 1 (서비스업)</td>
                <td>홍길동</td>
                <td>8</td>
                <td>40/50</td>
                <td>2024.09.08 ~ 2024.09.19</td>
                <td>2024.09.11</td>
                <td>
                    <div class="btn-group">
                        <button>수정</button>
                        <button>삭제</button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>테스트 과정 1 (건설업)</td>
                <td>홍길동</td>
                <td>8</td>
                <td>40/50</td>
                <td>2024.09.08 ~ 2024.09.19</td>
                <td>2024.09.11</td>
                <td>
                    <div class="btn-group">
                        <button>수정</button>
                        <button>삭제</button>
                    </div>
                </td>
            </tr>

        </tbody>
    </table>
</div>
</div>
<script>
$(document).ready(function() {
    $('.custom-date-picker').flatpickr({
        dateFormat: 'Y-m-d',
        altInput: true,
        altFormat: 'Y-m-d',
    });

    // 달력 아이콘 클릭 시 달력 열기
    $('.calendar-icon').on('click', function() {
        $(this).prev('.custom-date-picker').flatpickr().open(); 
    });

});
</script>