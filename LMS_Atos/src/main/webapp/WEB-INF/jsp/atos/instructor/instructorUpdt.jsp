<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/member/memberUpdt.css' />">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div class="content">
    <h1>강사 수정</h1>
    <form id="updateMember">
        <table class="info-table">
            <tr>
                <th>아이디</th>
                <td><input type="text" name="id" value="${instructor.id }" class="input-field" readonly /></td>
                <th>생년월일</th>
                <td><input type="date" name="birthdate" value="${instructor.birthdate }" class="input-field"  /></td>
            </tr>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" value="${instructor.name }" class="input-field" /></td>
                <th>주소</th>
                <td>
                    <div style="display: flex; align-items: center;">
                        <input type="text" id="zipcode" name="zipcode" value="${instructor.zipcode }" class="left-input-address" placeholder="우편번호" readonly />
                        <button type="button" class="btn btn-primary" id="addressSearchButton" style="margin-bottom:10px;">주소 검색</button>
                    </div>
                    <input type="text" id="address" name="addr1" class="left-input-address" value="${instructor.addr1 }" placeholder="주소를 검색해주세요" readonly />
                    <br /> 
                    <input type="text" id="detailedAddress" name="addr2" class="left-input-address detail-addr" value="${instructor.addr2 }" placeholder="상세주소를 입력하세요" />
                </td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td><input type="text" name="phoneNo" value="${instructor.phoneNo }" class="input-field" /></td>
                <th>이메일</th>
                <td><input type="email" name="email" value="${instructor.email }" class="input-field"  /></td>
            </tr>
            <tr>
                <th>소속기관</th>
                <td><input type="text" name="affiliation" value="${instructor.affiliation }" class="input-field" /></td>
                <th>소속</th>
                <td><input type="email" name="department" value="${instructor.department }" class="input-field"  /></td>
            </tr>
            <tr>
                <th>직책</th>
                <td><input type="text" name="position" value="${instructor.position }" class="input-field" /></td>
                <th>직업</th>
                <td><input type="email" name="job" value="${instructor.job }" class="input-field"  /></td>
            </tr>

        </table>
        
		<div class="textarea-form">
		    <div class="form-group">
		        <label for="bios" class="label-area">강사소개 (최대 300자)</label>
		        <textarea id="bios" name="bios" class="area" maxlength="300"><c:out value="${instructor.bios}" /></textarea>
		        <span id="bios-count" class="char-count">0/300</span>
		    </div>
		
		    <div class="form-group">
		        <label for="career" class="label-area">경력사항 (최대 500자)</label>
		        <textarea id="career" name="career" class="area" maxlength="500"><c:out value="${instructor.career}" /></textarea>
		        <span id="career-count" class="char-count">0/500</span>
		    </div>
		</div>


        <div class="button-container">
            <button type="submit" class="btn btn-primary" id="submitBtn">저장</button>
            <button type="button" class="cancel-btn btn btn-danger" onclick="history.back();">취소</button>
        </div>
    </form>
</div>

<script>
function updateCharCount(textareaId, maxLength) {
    const textarea = $('#' + textareaId);
    const countSpan = $('#' + textareaId + '-count');
    countSpan.text(textarea.val().length + '/' + maxLength);
    
}
$(document).ready(function() {
    $('#bios').on('input', function() {
        updateCharCount('bios', 300);
    });

    // 경력사항 textarea
    $('#career').on('input', function() {
        updateCharCount('career', 500);
    });
    
    updateCharCount('bios', 300);
    updateCharCount('career', 500);
	
	$('#addressSearchButton').on('click', function() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            $('#zipcode').val(data.zonecode); // 우편번호
	            $('#address').val(data.address); // 기본 주소
	            $('#detailedAddress').focus();
	        }
	    }).open();
	});

    $('#submitBtn').click(function() {
    	const memId = '<c:out value="${instructor.id }" />';
    	
        myFetch({
            url: '/member/instructorUpdate', 
            data: 'updateMember',
            success: function(response) {
                alert(response.message);
                window.location.href = "<c:url value='/member/instructorDetail.do'/>?id=" + memId;
            },
            error: function(error) {
                console.error('수정 중 오류 발생:', error);
                alert('수정에 실패하였습니다.');
            }
        });
    });
	
});
</script>