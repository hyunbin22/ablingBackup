<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import= "com.semi.member.model.vo.Member, java.util.*"%>
<%@ page import= "com.semi.message.model.vo.Message"%>
<%
	Member m = (Member)session.getAttribute("loginMember");
	String userId = m.getmId();
	String toId = null;
	if(request.getParameter("toId") != null) {
		toId = (String)request.getParameter("toId");
	}
	List<Message> list = (List)request.getAttribute("messageList");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/messageChat.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Testing websockets</title>
<style>
@charset "UTF-8";
* {
  margin: 0;
  padding: 0;
}
body {
  font-size: 11px;
}
.chat_list_wrap {
  list-style: none;
}
.chat_list_wrap .header {
  font-size: 14px;
  padding: 15px 0;
  background: #F18C7E;
  color: white;
  text-align: center;
  font-family: "Josefin Sans", sans-serif;
}
.chat_list_wrap .search {
  background: #eee;
  padding: 5px;
}
.chat_list_wrap .search input[type="text"] {
  width: 100%;
  border-radius: 4px;
  padding: 5px 0;
  border: 0;
  text-align: center;
}
.chat_list_wrap .list {
  padding: 0 16px;
}

#messageWindow{
	height: auto;
	min-height: 30px;
}


</style>
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
			type="post",
			url="<%=request.getContextPath()%>/message/messageSubmit.do",
			data:{
				fromID: encodeURLComponent(fromID),
				fromID: encodeURLComponent(fromID),
				fromID: encodeURLComponent(fromID),
			},
			success: function(result){
				if(result == 1) {
					autoClosingAlert();
				}
			}
		})
	}

</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" 
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
		</div>
	
	</nav>
	<div class="chat_list_wrap">
		<div class="header">상대방이름</div>
		<div class="list">
			<fieldset>
				<div id="messageWindow">
					<p></p>
				</div>
		        <!-- <textarea id="messageWindow" rows="2" cols="50" readonly></textarea> -->
		        <br/>
		        <input id="chatContent" type="text" placeholder="메세지를 입력하세요."/>
		        <input type="button" value="전송" onclick="submitFunction();" />
		    </fieldset>
		</div>
	</div>
	<div class="alert alert-success" id="successMessage" style="display:none;">
		<strong>메세지 전송에 성공했습니다.</strong>
	</div>
	<div class="alert alert-success" id="dangerMessage" style="display:none;">
		<strong>이름과 내용을 모두 입력해주세요.</strong>
	</div>
	<div class="alert alert-success" id="warningMessage" style="display:none;">
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
	
	if(messageContent != null)
	
	
	%>
</body>




    <script type="text/javascript">
        var textarea = document.getElementById("messageWindow");
        var webSocket = new WebSocket('ws://localhost:9090/<%=request.getContextPath()%>/message/broadcasting');
        var inputMessage = document.getElementById('inputMessage');
    webSocket.onerror = function(event) {
      onError(event)
    };
    webSocket.onopen = function(event) {
      onOpen(event)
    };
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    function onMessage(event) {
        textarea.value += "상대 : " + event.data + "\n";
    }
    function onOpen(event) {
        textarea.value += "연결 성공\n";
    }
    function onError(event) {
      alert(event.data);
    }
    function send() {
        textarea.value += "나 : " + inputMessage.value + "\n";
        webSocket.send(inputMessage.value);
        inputMessage.value = "";
    }
  </script>
</html>