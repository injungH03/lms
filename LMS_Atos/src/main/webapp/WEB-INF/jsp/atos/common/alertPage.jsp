<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	공통 alert page
--%>

	<c:if test="${!empty alert.message}">
		<script type="text/javascript">
			var sMsg = "${alert.message }";
			alert(sMsg);
		</script>
	</c:if> 
	
	<c:if test="${!empty alert.href}">
		<script type="text/javascript">
			location.href = "${alert.href }";
		</script>
	</c:if>
	
	<c:if test="${!empty alert.script}">
		<script type="text/javascript">
			${alert.script }
		</script>
	</c:if>

	
