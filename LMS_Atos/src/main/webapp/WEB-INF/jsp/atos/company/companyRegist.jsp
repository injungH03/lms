<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/company/companyRegist.css' />">
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.3/jquery.validate.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="<c:url value='/js/atos/common/fetchFunction.js'/>"></script>

<script>
$(document).ready(function() {
    $("#registForm").validate({
        rules: {
            corpName: {
                required: true,
                maxlength: 300  // 사업장명 최대 길이 300자
            },
            bizRegNo: {
                required: true,
                maxlength: 10  // 사업자등록번호 최대 길이 10자
            },
            repName: {
                required: true,
                maxlength: 30  // 대표자명 최대 길이 30자
            },
            taxInvoice: {
                required: true,
                email: true,
                maxlength: 50  // 이메일 최대 길이 50자
            },
            phoneNo: {
                maxlength: 15  // 전화번호 최대 길이 15자
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
            taxInvoice: {
                required: "이메일을 입력하세요.",
                email: "올바른 이메일 형식을 입력하세요.",
                maxlength: "이메일은 최대 50자 이하여야 합니다."
            }
        }
    });

    $('#addressSearchButton').on('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                $('#zipcode').val(data.zonecode); // 우편번호
                $('#addr1').val(data.address); // 기본 주소
                $('#addr2').focus();
            }
        }).open();
    });
});
</script>

<div class="registration-container">
    <form action="<c:url value='/company/registerCompany.do'/>" id="registForm" method="post">
        <table class="form-table">
            <tr>
                <td>
                    <label for="corpName">사업장명*</label>
                    <input type="text" id="corpName" name="corpName" placeholder="사업장명을 입력하세요" maxlength="300" required />
                </td>
                <td>
                    <label for="bizRegNo">사업자등록번호*</label>
                    <input type="text" id="bizRegNo" name="bizRegNo" placeholder="사업자등록번호를 입력하세요" maxlength="10" required />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <label for="zipcode">우편번호*</label>
                    <input type="text" id="zipcode" name="zipcode" placeholder="우편번호" readonly />
                    <button type="button" id="addressSearchButton">주소 검색</button>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <label for="addr1">주소*</label>
                    <input type="text" id="addr1" name="addr1" placeholder="주소를 검색해주세요" readonly />
                    <label for="addr2">상세주소</label>
                    <input type="text" id="addr2" name="addr2" placeholder="상세주소를 입력하세요" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="repName">대표자명*</label>
                    <input type="text" id="repName" name="repName" placeholder="대표자명을 입력하세요" maxlength="30" required />
                </td>
                <td>
                    <label for="bizType">업태</label>
                    <input type="text" id="bizType" name="bizType" placeholder="업태를 입력하세요" maxlength="60" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="bizItem">종목</label>
                    <input type="text" id="bizItem" name="bizItem" placeholder="종목을 입력하세요" maxlength="60" />
                </td>
                <td>
                    <label for="phoneNo">전화번호*</label>
                    <input type="text" id="phoneNo" name="phoneNo" placeholder="전화번호를 입력하세요" maxlength="15" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="faxNo">팩스번호</label>
                    <input type="text" id="faxNo" name="faxNo" placeholder="팩스번호를 입력하세요" maxlength="15" />
                </td>
                <td>
                    <label for="taxInvoice">세금계산서 메일*</label>
                    <input type="email" id="taxInvoice" name="taxInvoice" placeholder="이메일을 입력하세요" maxlength="50" required />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="empCount">종업원 수</label>
                    <input type="number" id="empCount" name="empCount" placeholder="명" />
                </td>
                <td>
                    <label for="trainCount">교육인원</label>
                    <input type="number" id="trainCount" name="trainCount" placeholder="명" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <label for="memo">메모</label>
                    <textarea id="memo" name="memo" rows="4" placeholder="메모를 입력하세요"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <h3>교육 담당자</h3>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="trainManager">담당자명</label>
                    <input type="text" id="trainManager" name="trainManager" placeholder="담당자명을 입력하세요" />
                </td>
                <td>
                    <label for="trainEmail">이메일</label>
                    <input type="email" id="trainEmail" name="trainEmail" placeholder="이메일을 입력하세요" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="trainPhone">휴대폰</label>
                    <input type="tel" id="trainPhone" name="trainPhone" placeholder="휴대폰 번호를 입력하세요" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <h3>계산서 담당자</h3>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="taxManager">담당자명</label>
                    <input type="text" id="taxManager" name="taxManager" placeholder="담당자명을 입력하세요" />
                </td>
                <td>
                    <label for="taxEmail">이메일</label>
                    <input type="email" id="taxEmail" name="taxEmail" placeholder="이메일을 입력하세요" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="taxPhone">휴대폰</label>
                    <input type="tel" id="taxPhone" name="taxPhone" placeholder="휴대폰 번호를 입력하세요" />
                </td>
            </tr>
        </table>
        <div class="form-group">
            <button type="submit">등록</button>
            <button type="reset">취소</button>
        </div>
    </form>
</div>
