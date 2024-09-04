<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.3/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
$(document).ready(function() {
    // 폼 유효성 검사 초기화
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
            },
            bizRegNo: {
                required: "사업자등록번호를 입력하세요.",
            },
            repName: {
                required: "대표자명을 입력하세요.",
            },
            bizType: {
                required: "업태를 입력하세요.",
            },
            bizItem: {
                required: "종목을 입력하세요.",
            },
            phoneNo: {
                required: "전화번호를 입력하세요.",
            },
            taxInvoice: {
                required: "이메일을 입력하세요.",
            },
            zipcode: {
                required: "우편번호를 검색하세요.",
            },
            addr1: {
                required: "주소를 검색하세요.",
            },
            addr2: {
                required: "상세 주소를 입력하세요.",
            }
        },
        errorElement: 'div',
        errorClass: 'invalid-feedback',
        highlight: function(element) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function(element) {
            $(element).removeClass('is-invalid');
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
                    $('#bizRegNo').val('').focus();
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
                $('#zipcode').val(data.zonecode);
                $('#addr1').val(data.address);
                $('#addr2').prop('disabled', false).focus(); // 상세 주소 필드를 활성화하고 포커스를 줌
            }
        }).open();
    });
 
 
    // 등록 버튼 클릭 이벤트
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
                    console.log('등록 성공:', response);
                    var message = response.message || "등록이 완료되었습니다.";
                    alert(message);
                    window.location.href = "<c:url value='/company/companyList.do'/>";
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

<div class="container mt-5">
    <form id="registForm" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="corpName" class="form-label">사업장명*</label>
            <input type="text" id="corpName" name="corpName" class="form-control" placeholder="사업장명을 입력하세요" maxlength="300" required>
            <div class="invalid-feedback">
                사업장명을 입력하세요.
            </div>
        </div>
        <div class="mb-3">
            <label for="bizRegNo" class="form-label">사업자등록번호*</label>
            <input type="text" id="bizRegNo" name="bizRegNo" class="form-control" placeholder="사업자등록번호를 입력하세요" maxlength="10" required>
            <div class="invalid-feedback">
                사업자등록번호를 입력하세요.
            </div>
        </div>
        <!-- 우편번호 및 주소 검색 -->
		<div class="mb-3 row align-items-start">
		    <label for="zipcode" class="col-sm-2 col-form-label">우편번호*</label>
		    <div class="col-sm-4">
		        <input type="text" id="zipcode" name="zipcode" class="form-control" placeholder="우편번호" readonly required>
		    </div>
		    <div class="col-sm-2 row align-items-center">
		        <button type="button" id="addressSearchButton" class="btn btn-primary btn-sm w-85">주소 검색</button>
		    </div>
		</div>
		<div class="mb-3 row">
            <label for="addr1" class="col-sm-2 col-form-label">주소*</label>
            <div class="col-sm-8">
                <input type="text" id="addr1" name="addr1" class="form-control" placeholder="주소를 검색해주세요" readonly required>
                <div class="invalid-feedback">
                    주소를 입력하세요.
                </div>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="addr2" class="col-sm-2 col-form-label">상세주소*</label>
            <div class="col-sm-8">
                <input type="text" id="addr2" name="addr2" class="form-control" placeholder="상세주소를 입력하세요" required disabled>
                <div class="invalid-feedback">
                    상세주소를 입력하세요.
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label for="repName" class="form-label">대표자명*</label>
            <input type="text" id="repName" name="repName" class="form-control" placeholder="대표자명을 입력하세요" maxlength="30" required>
            <div class="invalid-feedback">
                대표자명을 입력하세요.
            </div>
        </div>
        <div class="mb-3">
            <label for="bizType" class="form-label">업태*</label>
            <input type="text" id="bizType" name="bizType" class="form-control" placeholder="업태를 입력하세요" maxlength="60" required>
            <div class="invalid-feedback">
                업태를 입력하세요.
            </div>
        </div>
        <div class="mb-3">
            <label for="bizItem" class="form-label">종목*</label>
            <input type="text" id="bizItem" name="bizItem" class="form-control" placeholder="종목을 입력하세요" maxlength="60" required>
            <div class="invalid-feedback">
                종목을 입력하세요.
            </div>
        </div>
        <div class="mb-3">
            <label for="phoneNo" class="form-label">전화번호*</label>
            <input type="text" id="phoneNo" name="phoneNo" class="form-control" placeholder="전화번호를 입력하세요" maxlength="15" required>
            <div class="invalid-feedback">
                전화번호를 입력하세요.
            </div>
        </div>
        <div class="mb-3">
            <label for="taxInvoice" class="form-label">세금계산서 메일*</label>
            <input type="email" id="taxInvoice" name="taxInvoice" class="form-control" placeholder="이메일을 입력하세요" maxlength="50" required>
            <div class="invalid-feedback">
                올바른 이메일을 입력하세요.
            </div>
        </div>
        <div class="mb-3">
            <label for="empCount" class="form-label">종업원 수</label>
            <input type="number" id="empCount" name="empCount" class="form-control" placeholder="명">
        </div>
        <div class="mb-3">
            <label for="trainCount" class="form-label">교육인원</label>
            <input type="number" id="trainCount" name="trainCount" class="form-control" placeholder="명">
        </div>
        <div class="mb-3">
            <label for="memo" class="form-label">메모</label>
            <textarea id="memo" name="memo" class="form-control" rows="4" placeholder="메모를 입력하세요"></textarea>
        </div>
        <h3 class="mt-4">교육 담당자</h3>
        <div class="mb-3">
            <label for="trainManager" class="form-label">담당자명</label>
            <input type="text" id="trainManager" name="trainManager" class="form-control" placeholder="담당자명을 입력하세요">
        </div>
        <div class="mb-3">
            <label for="trainEmail" class="form-label">이메일</label>
            <input type="email" id="trainEmail" name="trainEmail" class="form-control" placeholder="이메일을 입력하세요">
        </div>
        <div class="mb-3">
            <label for="trainPhone" class="form-label">휴대폰</label>
            <input type="tel" id="trainPhone" name="trainPhone" class="form-control" placeholder="휴대폰 번호를 입력하세요">
        </div>
        <h3 class="mt-4">계산서 담당자</h3>
        <div class="mb-3">
            <label for="taxManager" class="form-label">담당자명</label>
            <input type="text" id="taxManager" name="taxManager" class="form-control" placeholder="담당자명을 입력하세요">
        </div>
        <div class="mb-3">
            <label for="taxEmail" class="form-label">이메일</label>
            <input type="email" id="taxEmail" name="taxEmail" class="form-control" placeholder="이메일을 입력하세요">
        </div>
        <div class="mb-3">
            <label for="taxPhone" class="form-label">휴대폰</label>
            <input type="tel" id="taxPhone" name="taxPhone" class="form-control" placeholder="휴대폰 번호를 입력하세요">
        </div>
        <div class="mb-3">
            <button type="button" id="submitBtn" class="btn btn-success">등록</button>
            <button type="reset" class="btn btn-secondary">취소</button>
        </div>
    </form>
</div>
