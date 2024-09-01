<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/member/memberRegist.css' />">
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.3/jquery.validate.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="<c:url value='/js/atos/common/fetchFunction.js'/>" ></script>
<script>
$(document).ready(function() {
	  $.validator.addMethod("passwordComplexity", function(value, element) {
	        return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{5,}$/.test(value);
	    }, "비밀번호는 영문, 숫자, 특수문자를 포함하며 최소 5자 이상이어야 합니다.");
	  
	  $.validator.addMethod("phoneFormat", function(value, element) {
		    return this.optional(element) || /^010-\d{4}-\d{4}$/.test(value);
		}, "전화번호는 010-XXXX-XXXX 형식이어야 합니다.");
	
    $("#registForm").validate({
        rules: {
            userId: {
                required: true,
                minlength: 5,
                maxlength: 15
            },
            name: {
                required: true,
                minlength: 2,
                maxlength: 10
            },
            password: {
                required: true,
                minlength: 5,
                maxlength: 15,
                passwordComplexity: true
            },
            confirmPassword: {
                required: true,
                equalTo: "#password"  // 비밀번호와 일치하는지 확인
            },
            birthDate: {
                required: true,
                date: true
            },
            phoneNumber: {
                required: true,
                phoneFormat: true
            },
            email: {
                required: true,
                email: true
            },        
            corpBiz: {
                required: true
            }
        },
        messages: {
            userId: {
                required: "아이디를 입력하세요.",
                minlength: "아이디는 최소 5자 이상이어야 합니다.",
                maxlength: "아이디는 최대 15자 이하여야 합니다."
            },
            name: {
                required: "이름을 입력하세요.",
                minlength: "이름은 최소 2자 이상이어야 합니다.",
                maxlength: "이름은 최대 10자 이하여야 합니다."
            },
            password: {
                required: "비밀번호를 입력하세요.",
                minlength: "비밀번호는 최소 5자 이상이어야 합니다.",
                maxlength: "비밀번호는 최대 15자 이하여야 합니다.",
                passwordComplexity: "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다."
            },
            confirmPassword: {
                required: "비밀번호 확인을 입력하세요.",
                equalTo: "비밀번호가 일치하지 않습니다."
            },
            birthDate: {
                required: "생년월일을 입력하세요.",
                dateISO: "올바른 날짜 형식(YYYY-MM-DD)을 사용하세요."
            },
            phoneNumber: {
                required: "전화번호를 입력하세요.",
                phoneFormat: "전화번호는 010-XXXX-XXXX 형식이어야 합니다."
            },
            email: {
                required: "이메일을 입력하세요.",
                email: "올바른 이메일 형식을 입력하세요."
            },
            corpBiz: {
                required: "소속기업을 선택해주세요."
            }
        },
        
    });
    
    $("#registForm").on("submit", function(event) {
        if (!$(this).valid()) {
            event.preventDefault();
            alert("유효성 검사를 통과하지 못했습니다. 입력 값을 확인해주세요.");
        }
    });
    
    $('#group').change(function() {
        var selectedCorpBiz = $(this).val();

        if (selectedCorpBiz) {
            myFetch({
                url: '/member/companyDetail', 
                data: { corpBiz: selectedCorpBiz }, 
                success: function(response) {
                    $('#businessRegistrationNumber').val(response.businessRegistrationNumber);
                    $('#position').val(response.position);
                    $('#businessName').val(response.businessName);
                    $('#representativeName').val(response.representativeName);
                    $('#industryType').val(response.industryType);
                    $('#businessCategory').val(response.businessCategory);
                    $('#businessAddress').val(response.businessAddress);
                },
                error: function(error) {
                    console.error('AJAX 오류:', error);
                }
            });
        } else {
            $('#businessRegistrationNumber, #position, #businessName, #representativeName, #industryType, #businessCategory, #businessAddress').val('');
        }
    });
});
</script>

<div class="registration-container">
    <form action="registerMember" id="registForm" method="post">
        <div class="form-row">
            <div class="form-group">
                <label for="userId">아이디*</label>
                <input type="text" id="userId" name="userId" class="left-input" placeholder="아이디를 입력하세요" required />
            </div>
            <div class="form-group">
                <label for="birthDate">생년월일</label>
                <input type="date" id="birthDate" name="birthDate" class="right-input" />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="name">이름*</label>
                <input type="text" id="name" name="name" class="left-input" placeholder="이름을 입력하세요" required />
            </div>
            <div class="form-group">
                <label for="phoneNumber">전화번호*</label>
                <input type="tel" id="phoneNumber" name="phoneNumber" class="right-input" placeholder="전화번호를 입력하세요" required />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="password">비밀번호*</label>
                <input type="password" id="password" name="password" class="left-input" placeholder="영문/숫자/특수문자 조합 5~15자리" required />
            </div>
            <div class="form-group">
                <label for="email">이메일*</label>
                <input type="email" id="email" name="email" class="right-input" required />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="confirmPassword">비밀번호 확인*</label>
                <input type="password" id="confirmPassword" name="confirmPassword" class="left-input" placeholder="영문/숫자/특수문자 조합 5~15자리" required />
            </div>
            <div class="form-group">
                <label for="company">소속기업*</label>
                <select id="group" name="corpBiz" class="right-input" required>
                    <option value="">선택</option>
                    <c:forEach var="company" items="${company }">
                    	<option value="${company.corpBiz }" <c:if test="${company.corpBiz == searchVO.corpBiz}">selected</c:if>>
                    		${company.corpName }
                    	</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
				<label for="zipcode">우편번호*</label> <input type="text" id="zipcode" name="zipcode" class="left-input-address" placeholder="우편번호"
					readonly /><button type="button" id="addressSearchButton">주소 검색</button><br /> 
				<label for="address">주소*</label> 
					<input type="text" id="address" name="address" class="left-input-address" placeholder="주소를 검색해주세요" readonly />
				<br /> 
				<label for="detailedAddress">상세주소</label> 
					<input type="text"id="detailedAddress" name="detailedAddress" class="left-input-address" placeholder="상세주소를 입력하세요" />
			</div>
        </div>

        <div class="form-gap"></div>

        <div class="form-row">
            <div class="form-group">
                <label for="businessRegistrationNumber">사업자등록번호</label>
                <input type="text" id="businessRegistrationNumber" name="businessRegistrationNumber" class="left-input" readonly />
            </div>
            <div class="form-group">
                <label for="position">직책</label>
                <input type="text" id="position" name="position" class="right-input" readonly />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="businessName">사업장명</label>
                <input type="text" id="businessName" name="businessName" class="left-input" readonly />
            </div>
            <div class="form-group">
                <label for="representativeName">대표자명</label>
                <input type="text" id="representativeName" name="representativeName" class="right-input" readonly />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="industryType">업태</label>
                <input type="text" id="industryType" name="industryType" class="left-input" readonly />
            </div>
            <div class="form-group">
                <label for="businessCategory">업종</label>
                <input type="text" id="businessCategory" name="businessCategory" class="right-input" readonly />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="businessAddress">사업장주소</label>
                <input type="text" id="businessAddress" name="businessAddress" class="left-input" readonly />
            </div>
        </div>

        <div class="form-group">
            <button type="submit">확인</button>
            <button type="reset">취소</button>
        </div>
    </form>
</div>
<script>
    $('#addressSearchButton').on('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                $('#zipcode').val(data.zonecode); // 우편번호
                $('#address').val(data.address); // 기본 주소
                $('#detailedAddress').focus();
            }
        }).open();
    });
</script>

