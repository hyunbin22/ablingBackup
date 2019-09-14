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
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css"> 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>메세지</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="webMessage.jsp">ABLINGTALK</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="webMessage.jsp">메인</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
				aria-haspopup="true" aria-expanded="false">멘토에게<span class="caret"></span>
				</a>
			</li>
		</ul>

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
			getInfiniteChat
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
	

	
	</script>
</body>

</html>