<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/company/companyRegist.css' />">
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.3/jquery.validate.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
$(document).ready(function() {
    // 폼 유효성 검사 설정
    $("#registForm").validate({
        rules: {
            corpName: {
                required: true,
                maxlength: 300
            },
            bizRegNo: {
                required: true,
                maxlength: 10
            },
            repName: {
                required: true,
                maxlength: 30
            },
            bizType: {
                required: true,
                maxlength: 60
            },
            bizItem: {
                required: true,
                maxlength: 60
            },
            phoneNo: {
                required: true,
                maxlength: 15
            },
            taxInvoice: {
                required: true,
                email: true,
                maxlength: 50
            },
            zipcode: {
                required: true,
                maxlength: 5
            },
            addr1: {
                required: true,
                maxlength: 300
            },
            addr2: {
                required: true,
                maxlength: 300
            }
        },
        messages: {
            corpName: {
                required: "사업장명을 입력하세요.",
                maxlength: "사업장명은 최대 300자 이하여야 합니다."
            },
            bizRegNo: {
                required: "사업자등록번호를 입력하세요.",
                maxlength: "사업자등록번호는 최대 10자 이하여야 합니다."
            },
            repName: {
                required: "대표자명을 입력하세요.",
                maxlength: "대표자명은 최대 30자 이하여야 합니다."
            },
            bizType: {
                required: "업태를 입력하세요.",
                maxlength: "업태는 최대 60자 이하여야 합니다."
            },
            bizItem: {
                required: "종목을 입력하세요.",
                maxlength: "종목은 최대 60자 이하여야 합니다."
            },
            phoneNo: {
                required: "전화번호를 입력하세요.",
                maxlength: "전화번호는 최대 15자 이하여야 합니다."
            },
            taxInvoice: {
                required: "이메일을 입력하세요.",
                email: "올바른 이메일 형식을 입력하세요.",
                maxlength: "이메일은 최대 50자 이하여야 합니다."
            },
            zipcode: {
                required: "우편번호를 입력하세요.",
                maxlength: "우편번호는 최대 5자 이하여야 합니다."
            },
            addr1: {
                required: "주소를 입력하세요.",
                maxlength: "주소는 최대 300자 이하여야 합니다."
            },
            addr2: {
                required: "상세 주소를 입력하세요.",
                maxlength: "상세 주소는 최대 300자 이하여야 합니다."
            }
        }
    });

    // 사업자등록번호 중복 체크
    $('#bizRegNo').on('change', function() {
        var bizRegNo = $(this).val();
        $.ajax({
            url: '<c:url value="/company/checkDuplicateBizRegNo" />',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ bizRegNo: bizRegNo }),
            success: function(response) {
                if (response.duplicate) {
                    alert("이미 존재하는 사업자등록번호입니다.");
                    $('#bizRegNo').val('').focus(); // 사업자등록번호 필드를 비우고 포커스를 줌
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('중복 체크 중 오류 발생:', textStatus, errorThrown);
            }
        });
    });
    
    
    // 주소 검색 버튼 클릭 시
    $('#addressSearchButton').on('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                $('#zipcode').val(data.zonecode); // 우편번호
                $('#addr1').val(data.address); // 기본 주소
                $('#addr2').focus();
            }
        }).open();
    });

    // 등록 버튼 클릭 이벤트
/*     $('#submitBtn').click(function() {
        if ($("#registForm").valid()) {
            var formData = $('#registForm').serializeArray();
            var jsonData = {};
            $(formData).each(function(index, obj){
                jsonData[obj.name] = obj.value;
            });

            $.ajax({
                url: '<c:url value="/company/companyInsert" />',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(jsonData),
                success: function(response) {
                    var message = response.message || "등록이 완료되었습니다.";
                    alert(message);
                    window.location.href = "<c:url value='/company/companyList.do'/>";  // 업체 목록 페이지로 이동
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('등록 중 오류 발생:', textStatus, errorThrown);
                    alert('등록이 실패하였습니다.');
                }
            });
        } else {
            alert("필수 입력란을 확인해주세요.");
        }
    }); */
    
    
    $('#submitBtn').click(function() {
        if ($("#registForm").valid()) {
            var formData = $('#registForm').serializeArray();
            var jsonData = {};
            $(formData).each(function(index, obj){
                jsonData[obj.name] = obj.value;
            });

            $.ajax({
                url: '<c:url value="/company/companyInsert" />',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(jsonData),
                success: function(response) {
                    console.log('등록 성공:', response);  // 디버깅 로그 추가
                    var message = response.message || "등록이 완료되었습니다.";
                    alert(message);
                    window.location.href = "<c:url value='/company/companyList.do'/>";  // 업체 목록 페이지로 이동
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('등록 중 오류 발생:', textStatus, errorThrown);
                    alert('등록이 실패하였습니다.');
                }
            });
        } else {
            alert("필수 입력란을 확인해주세요.");
        }
    });
    
    
    
    
    
    
    
    
    
    
    
    
    
    
});
</script>

<div class="registration-container">
    <form id="registForm" action = "/company/companyInsert">
        <div class="form-row">
            <div class="form-group">
                <label for="corpName">사업장명*</label>
                <input type="text" id="corpName" name="corpName" class="left-input" placeholder="사업장명을 입력하세요" maxlength="300" required />
            </div>
            <div class="form-group">
                <label for="bizRegNo">사업자등록번호*</label>
                <input type="text" id="bizRegNo" name="bizRegNo" class="right-input" placeholder="사업자등록번호를 입력하세요" maxlength="10" required />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="zipcode">우편번호*</label>
                <input type="text" id="zipcode" name="zipcode" class="left-input-address" placeholder="우편번호" readonly required />
                <button type="button" id="addressSearchButton">주소 검색</button>
            </div>
            <div class="form-group">
                <label for="addr1">주소*</label>
                <input type="text" id="addr1" name="addr1" class="left-input-address" placeholder="주소를 검색해주세요" readonly required />
                <label for="addr2">상세주소*</label>
                <input type="text" id="addr2" name="addr2" class="right-input" placeholder="상세주소를 입력하세요" required />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="repName">대표자명*</label>
                <input type="text" id="repName" name="repName" class="left-input" placeholder="대표자명을 입력하세요" maxlength="30" required />
            </div>
            <div class="form-group">
                <label for="bizType">업태*</label>
                <input type="text" id="bizType" name="bizType" class="right-input" placeholder="업태를 입력하세요" maxlength="60" required />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="bizItem">종목*</label>
                <input type="text" id="bizItem" name="bizItem" class="left-input" placeholder="종목을 입력하세요" maxlength="60" required />
            </div>
            <div class="form-group">
                <label for="phoneNo">전화번호*</label>
                <input type="text" id="phoneNo" name="phoneNo" class="right-input" placeholder="전화번호를 입력하세요" maxlength="15" required />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="faxNo">팩스번호</label>
                <input type="text" id="faxNo" name="faxNo" class="left-input" placeholder="팩스번호를 입력하세요" maxlength="15" />
            </div>
            <div class="form-group">
                <label for="taxInvoice">세금계산서 메일*</label>
                <input type="email" id="taxInvoice" name="taxInvoice" class="right-input" placeholder="이메일을 입력하세요" maxlength="50" required />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="empCount">종업원 수</label>
                <input type="number" id="empCount" name="empCount" class="left-input" placeholder="명" />
            </div>
            <div class="form-group">
                <label for="trainCount">교육인원</label>
                <input type="number" id="trainCount" name="trainCount" class="right-input" placeholder="명" />
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="memo">메모</label>
                <textarea id="memo" name="memo" class="left-input" rows="4" placeholder="메모를 입력하세요"></textarea>
            </div>
        </div>

        <div class="form-row">
            <h3>교육 담당자</h3>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label for="trainManager">담당자명</label>
                <input type="text" id="trainManager" name="trainManager" class="left-input" placeholder="담당자명을 입력하세요" />
            </div>
            <div class="form-group">
                <label for="trainEmail">이메일</label>
                <input type="email" id="trainEmail" name="trainEmail" class="right-input" placeholder="이메일을 입력하세요" />
            </div>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label for="trainPhone">휴대폰</label>
                <input type="tel" id="trainPhone" name="trainPhone" class="left-input" placeholder="휴대폰 번호를 입력하세요" />
            </div>
        </div>

        <div class="form-row">
            <h3>계산서 담당자</h3>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label for="taxManager">담당자명</label>
                <input type="text" id="taxManager" name="taxManager" class="left-input" placeholder="담당자명을 입력하세요" />
            </div>
            <div class="form-group">
                <label for="taxEmail">이메일</label>
                <input type="email" id="taxEmail" name="taxEmail" class="right-input" placeholder="이메일을 입력하세요" />
            </div>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label for="taxPhone">휴대폰</label>
                <input type="tel" id="taxPhone" name="taxPhone" class="left-input" placeholder="휴대폰 번호를 입력하세요" />
            </div>
        </div>

        <div class="form-group">
            <button type="submit" id="submitBtn">등록</button>
            <button type="reset">취소</button>
        </div>
    </form>
</div>
