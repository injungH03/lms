<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="modal fade" id="attendanceModal" tabindex="-1" aria-labelledby="attendanceModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="attendanceModalLabel">시간 입력</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3" id="dateInputContainer">
          <label for="modalDateInput" class="form-label">출석일</label>
          <input type="date" class="form-control" id="modalDateInput">
        </div>
        <div class="mb-3">
          <label for="modalTimeInput" class="form-label">시간</label>
          <input type="time" class="form-control" id="modalTimeInput">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="confirmTimeBtn">확인</button>
      </div>
    </div>
  </div>
</div>
