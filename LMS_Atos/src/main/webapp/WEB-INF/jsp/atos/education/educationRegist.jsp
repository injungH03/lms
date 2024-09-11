<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/education/educationRegist.css' />">
<script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>

<div class="table-section">
    <h2>교육 과정 등록</h2>
    <form id="educationForm" method="post" action="<c:url value='/educationRegist'/>" enctype="multipart/form-data">
        <table class="search-table regist-table">
            <!-- 교육 분류 -->
			<tr>
			    <th>교육 분류*</th>
			    <td colspan="3">
			        <div class="d-flex">
			            <!-- 대분류 -->
			            <select name="mainCategory" id="mainCategory" class="form-select me-2" required>
			                <option value="">대분류 선택</option>
			                <c:forEach var="mainCategory" items="${mainCategories}">
			                    <option value="${mainCategory.mainCode}">${mainCategory.mainName}</option>
			                </c:forEach>
			            </select>
			
			            <!-- 중분류 -->
			            <select name="subCategory" id="subCategory" class="form-select me-2">
			                <option value="">중분류 선택</option>
			                <c:forEach var="subCategory" items="${subCategories}">
			                    <option value="${subCategory.subCode}">${subCategory.subName}</option>
			                </c:forEach>
			            </select>
			
			            <!-- 소분류 -->
			            <select name="detailCategory" id="detailCategory" class="form-select">
			                <option value="">소분류 선택</option>
			                <c:forEach var="detailCategory" items="${detailCategories}">
			                    <option value="${detailCategory.detailCode}">${detailCategory.detailName}</option>
			                </c:forEach>
			            </select>
			        </div>
			    </td>
			</tr>

            <!-- 과정명 -->
            <tr>
                <th>과정명*</th>
                <td colspan="3">
                    <input type="text" id="title" name="title" class="form-control" required />
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
						       [ 코드: ${criteria.completionCode} ] [ 진도율: ${criteria.completionRate} ] [ 시험 점수: ${criteria.completionScore} ] [ 설문 유무: ${criteria.completionSurvey } ]
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

    $(document).ready(function() {
        // 대분류 선택 시 중분류 로드
        $('#mainCategory').on('change', function() {
            const mainCode = $(this).val();
            console.log("Selected mainCode:", mainCode); // 선택된 mainCode 로그 확인
            if (mainCode) {
                $.ajax({
                    url: '<c:url value="/education/subCategories" />',
                    method: 'GET',
                    data: { mainCode: mainCode },
                    success: function(data) {
                    	console.log("Received subCategories:", data); // 응답 받은 데이터 로그 확인
                        let subCategoryOptions = '<option value="">중분류 선택</option>';
                        $.each(data, function(index, item) {
                            subCategoryOptions += `<option value="${item.subCode}">${item.subName}</option>`;
                        });
                        $('#subCategory').html(subCategoryOptions);
                        $('#detailCategory').html('<option value="">소분류 선택</option>'); // 소분류 초기화
                    },
                    error: function() {
                        alert('중분류 데이터를 불러오는 중 오류가 발생했습니다.');
                    }
                });
            } else {
                $('#subCategory').html('<option value="">중분류 선택</option>');
                $('#detailCategory').html('<option value="">소분류 선택</option>');
            }
        });

        // 중분류 선택 시 소분류 로드
        $('#subCategory').on('change', function() {
            const subCode = $(this).val();
            console.log("Received subCode:", subCode); // subCode 확인
            if (subCode) {
                $.ajax({
                    url: '<c:url value="/education/detailCategories" />',
                    method: 'GET',
                    data: { subCode: subCode },
                    success: function(data) {
                    	console.log("Received detailCategories:", data); // 소분류 응답 데이터 확인
                        let detailCategoryOptions = '<option value="">소분류 선택</option>';
                        $.each(data, function(index, item) {
                            detailCategoryOptions += `<option value="${item.detailCode}">${item.detailName}</option>`;
                        });
                        $('#detailCategory').html(detailCategoryOptions);
                    },
                    error: function() {
                        alert('소분류 데이터를 불러오는 중 오류가 발생했습니다.');
                    }
                });
            } else {
                $('#detailCategory').html('<option value="">소분류 선택</option>');
            }
        });
    });

    // 유효성 검사
    $('#educationForm').on('submit', function(e) {
        const mainCategory = $('#mainCategory').val();
        const title = $('#title').val().trim();
        const description = CKEDITOR.instances.description.getData().trim();
        const objective = CKEDITOR.instances.objective.getData().trim();
        const completionCriteria = $('#completionCriteria').val();

        if (!mainCategory) {
            alert('대분류를 선택해주세요.');
            e.preventDefault(); // 폼 제출 중단
            return false;
        }

        if (!title || !description || !objective || !completionCriteria) {
            alert('모든 필수 입력란을 입력해주세요.');
            e.preventDefault(); // 폼 제출 중단
            return false;
        }

        return true; // 유효성 검사 통과 시 폼 제출
    });
</script>
