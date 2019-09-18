<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.semi.message.model.vo.Message, com.semi.member.model.vo.Member, com.semi.mento.model.vo.Mento, java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
	<%
	Member m = null;
	String userId = null;
	String toId = null;
	
	if(session.getAttribute("loginMember")!=null) {
		m = (Member)session.getAttribute("loginMember");
		userId=m.getmId();
	}
	if(request.getParameter("toId") != null) {
		toId = (String)request.getParameter("toId");
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>ABLINGTALK</title>
</head>
<body onresize="parent.resizeTo(450,600)" onload="parent.resizeTo(450,600)">
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/message/openMessage.do">ABLINGTALK<span id = "unread" class="label label-info"></span></a>
			<a class="navbar-brand" href="#">친구찾기</a>
		</div>
	</nav>	
	
	<div class="container">
		<table class="table table-bordered toable-hover" style="text-align:center; border: 1px solid #dddddd;">
			<thead>
				<tr>
					<th colspan="2">검색으로 친구찾기</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 110px;"><h5>아이디</h5></td>
					<td><input class="form-control" type="text" id="findId" maxlength="20" placeholder="찾을 아이디를 입력하세요"></td>
				</tr>
				<tr>
					<td colspan="2"><button class=" pull-right next" id="findMember">검색</button></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="container">
		<table id="memberResult" class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd;">
			
		</table>
	</div>

	
	<script>

	
	//친구찾기
	$(function() {
		$('#findMember').click(function(){
			var userId = $('#findId').val();
			console.log(userId);
			$.ajax({
				type: "post",
				url: "<%=request.getContextPath()%>/member/MemberIdDupliCheckServlet.do",
				data: {userId: userId},
				success: function(result) {
					if(result==0) {
						$('#checkMessage').html('친구찾기에 성공했습니다.');
						$('#checkType').attr('class','modal-content panel-success');
						getFriend(userId);
					} else {
						$('#checkMessage').html('등록된 사용자가 없습니다.');
						$('#checkType').attr('class','modal-content panel-warning');
						failFriend();
					}
					$('#checkModal').modal("show");
				}
			});
		});
	});
	
	function getFriend(findId){
		$('#memberResult').html(
			'<thead>' +
			'<tr>' + 
			'<th><h4>검색 결과</h4></th>' +
			'</tr></thead>' +
			'<tbody><tr>' +
			'<td style="text-align: center;"><h3>' + findId + '</h3><a href="<%=request.getContextPath()%>/message/messageList.do?toId='+encodeURIComponent(findId) + '" class="pull-right next">' +
			'메세지 보내기</a></td>' +
			'</tr></tbody>'
		);
	}
	
	function failFriend(){
		$('#memberResult').html("<h5>등록된 사용자가 없습니다.</h5>");
	}
	
	//안읽은메세지수 출력
	$(function(){
		timer = setInterval(function(){
			$.ajax({
				type:"post",
				url: "<%=request.getContextPath()%>/message/readCount.do",
				data: {
					userId: encodeURIComponent('<%=userId%>'),
				},
				success: function(result) {
					if(result>=1) {
						showUnread(result);
					} else {
						showUnread('0');
					}
				}
			});
		},500);
		
	});

	function showUnread(result){
		$('#unread').html(result);
	}
	
	
	</script>
</body>

</html>