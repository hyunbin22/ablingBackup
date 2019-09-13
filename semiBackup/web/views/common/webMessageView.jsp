<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import= "com.semi.member.model.vo.Member, java.util.*"%>
<%@ page import= "com.semi.message.model.vo.Message"%>
<%
	Member m = (Member)session.getAttribute("loginMember");
	List<Message> list = (List)request.getAttribute("messageList");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/messageChat.css">
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
</head>
<body>
	<div class="chat_list_wrap">
		<div class="header">상대방이름</div>
		<div class="list">
			<fieldset>
				<div id="messageWindow">
					<p></p>
				</div>
		        <!-- <textarea id="messageWindow" rows="2" cols="50" readonly></textarea> -->
		        <br/>
		        <input id="inputMessage" type="text"/>
		        <input type="submit" value="send" onclick="send()" />
		    </fieldset>
		</div>
	</div>
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