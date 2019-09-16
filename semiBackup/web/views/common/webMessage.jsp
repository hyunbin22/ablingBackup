<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.semi.message.model.vo.Message, com.semi.member.model.vo.Member, com.semi.mento.model.vo.Mento, java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
	<%
	Member m = null;
	String userId = null;
	
	if(session.getAttribute("loginMember")!=null) {
		m = (Member)session.getAttribute("loginMember");
		userId=m.getmId();
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
			<a class="navbar-brand" href="webMessage.jsp">ABLINGTALK<span id = "unread" class="label label-info"></span></a>
			<a class="navbar-brand" href="webMessage.jsp">멘토찾기</a>
			<a class="navbar-brand" href="<%=request.getContextPath() %>/message/memberFind.do">친구찾기</a>
			<!-- <a class="navbar-brand" href="box.jsp">메세지함<span id = "unread" class="label label-info"></span></a> -->
		</div>
	</nav>	
	<div class="container">
		<table class="table" style="margin: 0 auto;">
			<thead>
				<tr>
					<th><h4>메세지 목록</h4></th>
				</tr>
			</thead>
			<tbody>
				<td>
					<div style="overflow-y : auto; width: 100%; max-height: 450px;">
						<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd; margin: 0 auto;">
							<tbody id="boxTable">
							</tbody>
						</table>
					</div>
				</td>
			</tbody>
		</table>
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
	var lastId=0;

	
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
		},1000);
		
	});

	function showUnread(result){
		$('#unread').html(result);
	}
	
	//메세지 목록
	$(function(){
		timer = setInterval(function() {
			var userId = '<%=userId%>';
			$.ajax({
				type: "post",
				url:"<%=request.getContextPath()%>/message/messageMainList.do",
				data: {
					userId : encodeURIComponent(userId),
				},
				success: function(data) {
					if(data == "") return;
					$('#boxTable').html('');
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for(var i = 0; i < result.length; i++) {
						if(result[i][0].value == userId) {	//메세지 보낸사람이 나인지 상대방인지
							result[i][0].value = result[i][1].value;
						} else {
							result[i][1].value = result[i][0].value;
						}
						addBox(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value, result[i][4].value);
					}
				}
			});
		},1000);
		
	});
	
	
	
	function addBox(lastId, toId, chatContent, chatTime, unread) {
		console.log(toId);
		$('#boxTable').append('<tr onclick="location.href=\'<%=request.getContextPath()%>/message/messageList.do?toId=' + encodeURIComponent(toId) + '\'">' +
				'<td style="width: 150px;"><h5>' + lastId + '<span class="label label-info">' + unread + '</span></h5></td>' +
				'<td><h5>' + chatContent +
				'</h5>' +
				'<div class="pull-right">' + chatTime + '</div>' + 
				'</td>' +
				'</tr>' );
	}
	
	</script>
</body>

</html>