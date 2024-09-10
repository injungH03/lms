<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<div class="table-section">
<h2>집합과정운영</h2>
    <form method="get" action="">
        <table class="search-table">
            <tr>
                <th>분류</th>
                <td colspan="2">
                    <select name="course" class="form-select search-select me-2">
                        <option value="all">대분류</option>
                    </select>
                    <select name="course" class="form-select search-select me-2">
                        <option value="all">중분류</option>
                    </select>
                </td>
                <th>상태</th>
                <td colspan="2">
                    <div class="radio-group">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="전체" checked>
                            <label class="form-check-label">전체</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="대기">
                            <label class="form-check-label">대기</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="신청기간">
                            <label class="form-check-label">신청기간</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="학습기간">
                            <label class="form-check-label">학습기간</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="종료">
                            <label class="form-check-label">종료</label>
                        </div>
                    </div>
                </td>
            </tr>
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
                <th class="custom-th-width">학습시작/종료일</th>
                <td colspan="3">
                    <div class="d-flex">
                        <span>시작일 :</span> <input type="date" name="learningStartDate" id="learningStartDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                        <span class="span-ml">종료일 :</span> <input type="date" name="learningEndDate" id="learningEndDate" class="form-control me-2 custom-date-picker" />
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
                <th>년도/차수</th>
                <th>과정명</th>
                <th>정상</th>
                <th>승인 대기</th>
                <th>입금 대기</th>
                <th>수강 취소</th>
                <th>설문</th>
                <th>수강 신청 기간</th>
                <th>학습 기간</th>
                <th>상태</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>2024/01</td>
                <td>테스트 과정 1</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
                <td>2024.09.08 ~ 2024.09.19</td>
                <td>2024.09.11 ~ 2024.09.25</td>
                <td><span class="status-box status-신청">신청</span></td>
                <td>
                    <div class="btn-group">
                        <button>강사수정</button>
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