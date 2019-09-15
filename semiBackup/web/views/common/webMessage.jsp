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
<title>메세지</title>
</head>
<body onresize="parent.resizeTo(450,600)" onload="parent.resizeTo(450,600)">
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<!-- <button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button> -->
			<a class="navbar-brand" href="webMessage.jsp">ABLINGTALK</a>
			<a class="navbar-brand" href="webMessage.jsp">멘토찾기</a>
			<a class="navbar-brand" href="messageMemberFind.jsp">친구찾기</a>
			<a class="navbar-brand" href="box.jsp">메세지함<span id = "unread" class="label label-info"></span></a>
		</div>
	</nav>	
	<%
		String messageContent = null;
		if(session.getAttribute("messageContent") != null) {
			messageContent=(String)session.getAttribute("messageContent");
		}
		String messageType=null;
		if(session.getAttribute("messageType")!=null) {
			messageType=(String)session.getAttribute("messageType");
		}
		
		if(messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-cener">
				<div class="modal-content <%if(messageType.equals("오류 메세지")) out.println("panel-warning"); else out.println("panel-success");%>">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType %>
						</h4>
					</div>
					<div class="modal-body">
						<%=messageContent %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div> 
	<script>
		$('#messageModal').modal("show");
		
		$(document).ready(function(){
			chatListFunction('ten');
			getInfiniteChat();
			/* getInfiniteUnread(); */
		})

	</script>

	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		
	} %>
	<script>
	var lastId=0;
	function chatListFunction(type){
		var fromId = '<%=userId%>';
		var toId = '<%=toId%>';
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/message/messageList.do",
			data:{
				fromId: encodeURIComponent(fromId),
				toId: encodeURIComponent(toId),
				chatContent: encodeURIComponent(chatContent),
			},
			success: function(data){
				if(data=="") return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++) {
					addChat(result[i][0].value, result[i][2].value, result[i][3].value);
				}
				lastId = Number(parsed.last);
			}
		})
	}
	
	function addChat(chatName, chatContent, chatTime) {
		$('#chatList').append('<div class="row">' +
			'<div class="col-lg-12">' +
			'<div class="media">' +
			'<a class="pull-left" href="#">' +
			'<img class="media-object img-circle" src="images.icon.png" alt="">' +
			'</a>' +
			'<div class="media-body">' +
			'<h4 class="media-heading">' +
			chatName +
			'<span class="small pull-right">' +
			chatTime +
			'</span></h4>'+
			'<p>' + chatContent + '</p>' +
			'</div></div></div></div>' +
			'<hr>');
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	}
	function getInfiniteChat() {
		setInterval(function() {
			chatListFunction(lastId);
		}, 3000);
	}
	
	//안읽은메세지수 출력
	$(function(){
		$.ajax({
			type:"post",
			url: "<%=request.getContextPath()%>/message/readCount.do",
			data: {
				userId: encodeURIComponent('<%=userId%>'),
			},
			success: function(result) {
				if(result>=1) {
					showUnread(result);
					console.log(result);
				} else {
					showUnread('');
				}
			}
		},3000);
	});

	function showUnread(result){
		$('#unread').html(result);
	}
	

	
	</script>
</body>

</html>