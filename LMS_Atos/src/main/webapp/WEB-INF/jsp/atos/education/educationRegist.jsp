<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/education/educationRegist.css' />">
<script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>

<div class="table-section">
    <h2>교육 과정 등록</h2>
    <form id="educationForm" method="post" action="<c:url value='/education/educationInsert'/>">
        <table class="search-table regist-table">
            <!-- 과정 선택 -->
            <tr>
                <th>과정 선택*</th>
                <td colspan="3">
				<select name="category" id="category" class="form-select me-2" required>
				    <option value="">과정 선택</option>
				    <c:forEach var="category" items="${categories}">
				        <option value="${category.code}"> <!-- mainCode 대신 code를 사용 -->
				            ${category.mainName}
				            <c:if test="${category.subName != null}"> > ${category.subName}</c:if>
				            <c:if test="${category.detailName != null}"> > ${category.detailName}</c:if>
				        </option>
				    </c:forEach>
				</select>
                </td>
            </tr>

            <!-- 과정명 -->
            <tr>
                <th>과정명*</th>
                <td colspan="3">
                    <input type="text" id="title" name="title" class="form-control" required />
                </td>
            </tr>

            <!-- 교육 시간 -->
            <tr>
                <th>교육 시간*</th>
                <td colspan="3">
                    <select name="trainingTime" id="trainingTime" class="form-select me-2">
                        <option value="">교육 시간 선택</option>
                        <c:forEach var="time" items="${trainingTimes}">
                            <option value="${time.value}">${time.label}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <!-- 수료 조건 -->
            <tr>
                <th>수료 조건*</th>
                <td colspan="3">
                    <select name="completionCriteria" id="completionCriteria" class="form-select" required>
                        <option value="">수료 조건 선택</option>
                        <c:forEach var="criteria" items="${completionCriteria}">
                            <option value="${criteria.completionCode}">
                                [ 코드: ${criteria.completionCode} ] [ 진도율: ${criteria.completionRate} ] 
                                [ 시험 점수: ${criteria.completionScore} ] [ 설문 유무: ${criteria.completionSurvey} ]
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <!-- 과정 소개 및 과정 목표 -->
            <tr>
                <th>과정 소개*</th>
                <td colspan="3">
                    <textarea name="description" id="description" class="form-control" rows="5" required></textarea>
                </td>
            </tr>
            <tr>
                <th>과정 목표*</th>
                <td colspan="3">
                    <textarea name="objective" id="objective" class="form-control" rows="5" required></textarea>
                </td>
            </tr>

            <!-- 비고란 -->
            <tr>
                <th>비고</th>
                <td colspan="3">
                    <textarea name="note" id="note" class="form-control" rows="3"></textarea>
                </td>
            </tr>
        </table>

        <!-- 등록 및 목록 버튼 -->
        <div class="d-flex justify-content-between mt-3">
            <button type="submit" class="btn btn-success">등록</button>
            <button type="button" class="btn btn-secondary" onclick="window.location.href='<c:url value='/education/educationList.do' />'">목록</button>
        </div>
    </form>
</div>

<script>
    // CKEditor 설정
    CKEDITOR.replace('description');
    CKEDITOR.replace('objective');

    $('#educationForm').on('submit', function(e) {
        e.preventDefault();

        // CKEditor에서 내용 가져오기
        var description = CKEDITOR.instances.description.getData();
        var objective = CKEDITOR.instances.objective.getData();
        
        var educationData = {
            title: $('#title').val(),
            category: $('#category').val(),
            trainingTime: $('#trainingTime').val(),
            description: description,
            objective: objective,
            completionCriteria: $('#completionCriteria').val(),
            note: $('#note').val()
        };

/*         
        // 필수 값 검증 | !educationData.trainingTime |
        if (!educationData.title || !educationData.category || !educationData.description || !educationData.objective || !educationData.completionCriteria) {
            alert('필수 항목을 모두 입력해주세요.');
            return;
        }

   */      
        // 전송 전에 데이터 확인
        console.log("전송할 데이터:", educationData);

        $.ajax({
            url: '/education/educationInsert',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(educationData),
            success: function(response) {
                console.log(response); 
                if (response.httpStatus === 'OK') {
                    alert('교육 과정이 성공적으로 등록되었습니다.');
                    window.location.href = '/education/educationList.do';
                } else {
                    alert('등록에 실패했습니다: ' + response.message);
                }
            },
            error: function(error) {
                alert('서버와의 통신에 실패했습니다.');
            }
        });
    });
</script>
