<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.semi.message.model.vo.Message, com.semi.member.model.vo.Member, com.semi.mento.model.vo.Mento, java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	List<Message> list = (List)request.getAttribute("messageList");
	String compareDate1 = "";
	String compareDate2 = "";	//마지막 날짜
	int index = 0;	//마지막날짜인  list인덱스를 받음
	int toMNum;
	int fromMNum;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	
	//마지막 날짜를 얻어와서 보여주기 위함
	for(int i = 0; i < list.size(); i++) {
		compareDate1 = sdf.format(list.get(i).getMessageSendDate());
		toMNum = list.get(i).getToMNum();
		fromMNum = list.get(i).getFromMNum();
		for(int j = 0; j < list.size(); j++) {
			compareDate1 = sdf.format(list.get(j).getMessageSendDate());
			
			if(compareDate1.compareTo(compareDate2) > 0) {
				
			}
		}
	}
	
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
.chat_list_wrap .list ul {
  width: 100%;
  list-style: none;
  margin-top: 3px;
}
.chat_list_wrap .list ul li {
  padding-top: 10px;
  padding-bottom: 10px;
  border-bottom: 1px solid #e5e5e5;
}
.chat_list_wrap .list ul li table {
  width: 100%;
}
.chat_list_wrap .list ul li table td.profile_td {
  width: 50px;
  padding-right: 11px;
}
.chat_list_wrap .list ul li table td.profile_td img {
  width: 50px;
  height: auto;
}
.chat_list_wrap .list ul li table td.chat_td .email {
  font-size: 12px;
  font-weight: bold;
}
.chat_list_wrap .list ul li table td.time_td {
  width: 90px;
  text-align: center;
}
.chat_list_wrap .list ul li table td.time_td .time {
  padding-bottom: 4px;
}
.chat_list_wrap .list ul li table td.time_td .check p {
  width: 5px;
  height: 5px;
  margin: 0 auto;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  background: #e51c23;
}

</style>
</head>
<body>
	<div class="chat_list_wrap">
		<div class="header">KKOTKKIO</div>
		<div class="search">
			<input type="text" placeholder="아이디 / 이름 검색" />
		</div>
		<div class="list">
			<ul>
				<li>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td class="profile_td">
								<!--Img--> <img src="./images/profile_preview.png" />
							</td>
							<td class="chat_td">
								<!--Email & Preview-->
								<div class="email">kkotkkio@gmail.com</div>
								<div class="chat_preview">안녕하세요~</div>
							</td>
							<td class="time_td">
								<!--Time & Check-->
								<div class="time">2016.09.29 17:54</div>
								<div class="check">
									<p></p>
								</div>
							</td>
						</tr>
					</table>
				</li>
				<li>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td class="profile_td">
								<!--Img--> <img src="./images/profile_preview.png" />
							</td>
							<td class="chat_td">
								<!--Email & Preview-->
								<div class="email">kkotkkio@gmail.com</div>
								<div class="chat_preview">안녕하세요~</div>
							</td>
							<td class="time_td">
								<!--Time & Check-->
								<div class="time">2016.09.29 17:54</div>
								<div class="check"></div>
							</td>
						</tr>
					</table>
				</li>
			</ul>
		</div>
	</div>
<%-- 	<%
		String messageContent = null;
		if(session.getAttribute("messageContent") != null) {
			messageContent = (String)session.getAttribute("messageContent");
		}
		
		String messageType = null;
		if(session.getAttribute("messageType") != null) {
			messageType = (String)session.getAttribute("messageType");
		}
		if(messageContent != null) {
		}
		
	%> --%>
	
</body>

</html>