<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int mtNum = 0;
	int lecNum = 0;
	String mtReason = "";
	String lecReason = "";
	String mtLec = (String)request.getAttribute("mtLec");
	if(mtLec.equals("mt")) {
		mtNum = (int)request.getAttribute("mtNum");
		mtReason = (String)request.getAttribute("mtReason");
	} 
	if(mtLec.equals("lec")){
		lecNum = (int)request.getAttribute("lecNum");
		lecReason = (String)request.getAttribute("lecReason");
	}
	
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body class="center1">
	<!-- 멘토 -->
    <form method="post" class="center1" id="refusal-frm" action="<%=request.getContextPath()%>/admin/saveRefusal.do">
	<%if(mtLec.equals("mt")) {%>
    	<input type="hidden" name="mtNum" value=<%=mtNum %>>
    	<input type="hidden" name="mtLec" value=<%=mtLec %>>
        <h1>거절사유</h1>
        <textarea name="reason" id="reason" cols="50" rows="10" style="resize:none;"><%if(mtReason!=null) {%><%=mtReason %><%} %></textarea>    
    <!-- 강의 -->
    <%} 
	if(mtLec.equals("lec")) {%>
    	<input type="hidden" name="lecNum" value=<%=lecNum %>>
    	<input type="hidden" name="mtLec" value=<%=mtLec %>>
        <h1>거절사유</h1>
        <textarea name="reason" id="reason" cols="50" rows="10" style="resize:none;"><%if(lecReason!=null) {%><%=lecReason %><%} %></textarea>
    <%} %>
        <br>
        <button type="button" id="saveRefusal">등록</button>
    </form>
    
	<script>
		$(function(){
			$('#saveRefusal').click(function(){
				if(confirm('승인거절하시겠습니까?')) {
					var frm = $("#refusal-frm");
					frm.submit();
					self.close();
				}
			});
		});
	</script>
</body>
</html>