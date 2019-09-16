<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import= "com.semi.member.model.vo.Member, java.util.*"%>
<%@ page import= "com.semi.message.model.vo.Message"%>
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
	
	List<Integer> adminList = new ArrayList();
	for(int i = 1; i <= 5; i++) {
		adminList.add(i);
	}
	
	List<Message> list = (List)request.getAttribute("messageList");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Testing websockets</title>
<style>
@charset "UTF-8";


</style>

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
			<a class="navbar-brand" href="webMessage.jsp">ABLINGTALK<span id = "unread" class="label label-info"></span></a>
			<a class="navbar-brand" href="webMessage.jsp">멘토찾기</a>
			<a class="navbar-brand" href="messageMemberFind.jsp">친구찾기</a>
			<!-- <a class="navbar-brand" href="box.jsp">메세지함<span id = "unread" class="label label-info"></span></a> -->
		</div>
	</nav>	
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-xs-12">
				<div class="portlet portlet-default">
					<div class="portlet-heading">
						<div class="portlet-title">
							<h4><i class="fa fa-circle text-green"></i><%=toId %></h4>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id= "chat" class="panel-collapse collapse in">
						<div id="chatList" class="porlet-body chat-widget" style="overflow-y:auto; width: auto; height: 400px;">
						</div>
						<div class="portlet-footer">
							<!-- <div class="row">
								<div class="form-group col-xs-4">
									<input style="height: 40px;" type="text" id="chatName" class="form-control" placeholder="이름" maxlength="8">
								</div>
							</div> -->
							<div class="row" style="height:90px;">
								<div class="form-group col-xs-10">
								<%-- <%if() {%> --%>
									<textarea style="height: 80px;" id="chatContent" class="form-control" placeholder="메세지 입력" maxlength="100"></textarea>
								<%-- <%} %> --%>
								</div> 
								<div class="form-group col-xs-2">
									<button type="button" class="btn btn-default pull-right" onclick="submitFunction();">전송</button>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="alert alert-success" id="successMessage" style="display:none;">
		<strong>메세지 전송에 성공했습니다.</strong>
	</div>
	<div class="alert alert-danger" id="dangerMessage" style="display:none;">
		<strong>내용을 입력해주세요.</strong>
	</div>
	<div class="alert alert-warning" id="warningMessage" style="display:none;">
		<strong>오류가 발생했습니다.</strong>
	</div>
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
		
	</script>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		
		} %>

<script>
	function autoClosingAlert(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() {alert.hide()}, delay);
	}
	function submitFunction() {
		var fromId = '<%=userId%>';
		var toId = '<%=toId%>';
		var chatContent = $('#chatContent').val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/message/messageSubmit.do",
			data:{
				fromId: encodeURIComponent(fromId),
				toId: encodeURIComponent(toId),
				chatContent: encodeURIComponent(chatContent),
			},
			success: function(result){
				if(result == 1) {
					autoClosingAlert("#successMessage",2000);
				} else if(result==0) {
					autoClosingAlert("#dangerMessage",2000);
				} else {
					autoClosingAlert("#warningMessage",2000);
				}
			}
		});
		$('#chatContent').val("");
	}
	
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
				listType: type,
			},
			success: function(data){
				if(data=="") return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++) {
					if(result[i][0].value== fromId) {
						result[i][0].value = '나';
					}
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
			/* '<a class="pull-left" href="#">' +
			'<img class="media-object img-circle" style="width:30px; height:30px;" src="images.icon.png" alt="">' +
			'</a>' + */
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
		}, 1000);
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
						console.log(result);
					} else {
						showUnread('');
					}
				}
			});
		},2000);
		
	});

	function showUnread(result){
		$('#unread').html(result);
	}
	

	
	
</script>
<script>
	$(document).ready(function(){
		chatListFunction('0');
		getInfiniteChat();
	});

</script>

</body>
 
</html>