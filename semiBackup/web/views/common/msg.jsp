<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String msg = (String) request.getAttribute("msg");
    	String loc = (String) request.getAttribute("loc");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지창</title>
</head>
<body>
	<script>
		//스크립트릿에서 작성한 코드는 반드시 표현식 방식으로 처리
		alert('<%=msg %>');
		
		location.href='<%=request.getContextPath()%><%=loc%>';
	
	</script>
</body>
</html>