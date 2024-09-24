<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/education/educationDetail.css' />">

<div class="head-section">
    <span>&nbsp;교육 상세 정보</span>
</div>
<div class="table-section">

    <!-- 교육 상세 정보를 보여주는 테이블 -->
    <table class="search-table detail-table">
        <tr>
            <th>교육명</th>
            <td>${educationDetail.title}</td>
            <th>교육분류</th>
            <td>
                <!-- 교육분류는 대분류와 중분류로 표시 -->
                ${educationDetail.mainName}
                <c:if test="${educationDetail.subName != null && !educationDetail.subName.isEmpty()}">
                    > ${educationDetail.subName}
                </c:if>
            </td>
        </tr>
        <tr>
            <th>수료조건</th>
            <td>
                <!-- 수료 조건을 진도율/시험 점수/설문 유무로 표시 -->
                [ 진도율: ${educationDetail.completionRate} ]
                [ 시험 점수: ${educationDetail.completionScore} ]
                [ 설문 유무: ${educationDetail.completionSurvey} ]
            </td>
            <th>교육시간</th>
            <td>${educationDetail.trainingTimeName}</td> <!-- 교육시간 명칭으로 표시 -->
        </tr>
        <tr>
            <th>등록일</th>
            <td>${educationDetail.regDate}</td>
            <th>등록자</th>
            <td>${educationDetail.register}</td>
        </tr>   
        <tr>
            <th>과정소개</th>
            <td colspan="3">
                <div class="text-container">
                    ${educationDetail.description}
                </div>
            </td>
        </tr>
        <tr>
            <th>과정목표</th>
            <td colspan="3">
                <div class="text-container">
                    ${educationDetail.objective}
                </div>
            </td>
        </tr>
        <tr>
            <th>비고</th>
            <td colspan="3">
                <div class="text-container">
                    ${educationDetail.note}
                </div>
            </td>
        </tr>
    </table>

    <!-- 버튼 그룹 -->
    <div class="mt-3">
        <button type="submit" class="btn btn-success me-2">수정</button>
        <button type="button" class="btn btn-secondary me-2" onclick="location.href='<c:url value='/education/educationList.do' />'">목록</button>
        <button type="button" class="btn btn-danger" style="float:right" id="deleteBtn">삭제</button>
    </div>
</div>

<!-- jQuery Script -->
<script>
$(document).ready(function() {
    // 삭제 버튼 클릭 시 확인 메시지
    $('#deleteBtn').on('click', function() {
        if(confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
                url: '/education/deleteEducation',
                method: 'POST',
                data: { eduCode: '${educationDetail.eduCode}' },
                success: function(response) {
                    if(response.success) {
                        alert('삭제 완료되었습니다.');
                        location.href = '/education/educationList.do';
                    } else {
                        alert('삭제에 실패했습니다.');
                    }
                }
            });
        }
    });
});
</script>
