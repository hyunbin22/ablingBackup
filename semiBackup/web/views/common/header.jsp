<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.semi.member.model.vo.Member"%>
    
    <%
    	
     Member memberLogin = (Member) session.getAttribute("loginMember");
     Cookie[] cookies = request.getCookies();
	 int readCount = 0;
	 if(memberLogin!=null) {
		 readCount = (int)session.getAttribute("readCount");
	 }
     
   	 String saveId = null;
	 if(cookies!=null){
     for(Cookie c : cookies){

         String key=c.getName();
         String value=c.getValue();
         System.out.println("key : "+key);
         System.out.println("value : "+value);
         if(key.equals("saveId")){
            saveId=value;
   			}
      	}
	}
   
    
    
    %>
    

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
    <script src="https://sdk.accountkit.com/en_US/sdk.js"></script>	<!-- sms인증 -->
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>	<!-- kakao -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
    <link href="https://fonts.googleapis.com/css?family=Stylish&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap" rel="stylesheet">
    <title>KH SEMI</title>

</head>
<body class="center1">

 <script>

 function validate(){
     if($('#id').val().length==0){
         alert('아이디를 입력하세요');
         $('#id').focus();
         return false; //form제출 막는 것
     }
     if($('#pwd').val().length==0){
         alert('비밀번호를 입력하세요');
         $('#pwd').focus();
         return false;
     }
     return true;
 }
 
   function test()
   {
       alert("테스트");
   }

       $(document).ready(function() {
           var $banner = $(".banner").find("ul");
           var $bannerWidth = $banner.children().outerWidth();//이미지의 폭
           var $bannerHeight = $banner.children().outerHeight(); // 높이
           var $length = $banner.children().length;//이미지의 갯수
           var rollingId;
           //정해진 초마다 함수 실행
           rollingId = setInterval(function() { rollingStart(); }, 5000);//다음 이미지로 롤링 애니메이션 할 시간차
           function rollingStart() {
               $banner.css("width", $bannerWidth * $length + "px");
               $banner.css("height", $bannerHeight + "px");
               //alert(bannerHeight);
               //배너의 좌측 위치를 옮겨 준다.
               $banner.animate({left: - $bannerWidth + "px"}, 5000, function() { //숫자는 롤링 진행되는 시간이다.
                   //첫번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
                   $(this).append("<li>" + $(this).find("li:first").html() + "</li>");
                   //뒤로 복사된 첫번재 이미지는 필요 없으니 삭제한다.
                   $(this).find("li:first").remove();
                   //다음 움직임을 위해서 배너 좌측의 위치값을 초기화 한다.
                   $(this).css("left", 0);
                   //이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
               });
           }
       }); 
     </script>
    <header id="mainHeader">
        <div id="head" >
            <div><a href = "<%=request.getContextPath()%>"><img id = "logoImg" src = "<%=request.getContextPath()%>/image/logo.png"></a></div> <!--메인 아이콘 이미지-->
                <%if(memberLogin == null){ %>
            <div id = loginmenu>
            <form id = "loginFrm" action = "<%=request.getContextPath() %>/member/memberLoginServlet.do" method="POST" onsubmit="return validate();">
                <input id = "id" type = "text" name = "mId" placeholder="아이디">
                <input id = "pwd" type = "password" name = "mPw" placeholder="비밀번호">
                <input type="submit" value="로그인" id="login">
            </form>
            </div>
                <div id = "loginmenu2">
                <span onclick = "location.href='<%=request.getContextPath()%>/views/member/registerChoice.jsp'" id = "join">회원가입</span>
                <span onclick = "location.href='<%=request.getContextPath()%>/views/member/FINDID.jsp'" id = findId>아이디 찾기</span>
                <span onclick = "location.href='<%=request.getContextPath()%>/views/member/FINDPWD.jsp'" id = findPw>비밀번호 찾기</span>
                   <%} else {%>
                <div id = profile1>
                <div id = "myPro">
                	<div>
                    <div id = "myPro2"><%=memberLogin.getmId()%>님</div>
                    <div style = "text-align: center">환영합니다!</div>
                		<img src="<%=request.getContextPath() %>/image/messageIcon.png" id="messageIcon">
                		<p id="messageCount"><%=readCount %></p>
                	</div>
                </div>
                <div id = "mypromenu">
                    <button onclick = "location.href='<%=request.getContextPath()%>/memberLogoutServlet.do'" id = logout>로그아웃</button>
                    <button onclick = "location.href='<%=request.getContextPath()%>/member/memberMyPage.do?mId=<%=memberLogin.getmId()%>'" id = mypage>마이페이지</button>
                </div>
            </div>
                   <%} %>
            </div>
 <br><br><br><br><br><br><br><br><br><br>
        
        <br>
        <div id = menu class = "center1">
    
 		<nav>
             <ul class="center1">
       
                <li><a href="<%=request.getContextPath()%>">홈으로</a></li>
       
                <li><a href="<%=request.getContextPath()%>/admin/AdminMentoApproval.do">뷰티</a>
       
                   <ul>
                      <li><a href="#">메이크업</a></li>
                      <li><a href="#">네일</a></li>
                      <li><a href="#">헤어</a></li>
                   </ul>
                </li>
       
                <li><a href="#">유튜브</a>
       
                    <ul>
                        <li><a href="#">마케팅</a></li>
                        <li><a href="#">편집</a></li>
                        <li><a href="#">촬영</a></li>
                    </ul>
                </li>
       
                <li><a href="#">운동</a>
       
                     <ul>
                         <li><a href="#">헬스</a></li>
                         <li><a href="#">축구</a></li>
                         <li><a href="#">요가</a></li>
                     </ul>
                 </li>

                 <li><a href="#">음악</a>
       
                     <ul>
                         <li><a href="#">기타</a></li>
                         <li><a href="#">DJ</a></li>
                         <li><a href="#">보컬</a></li>
                     </ul>
                 </li>

                 <li><a href="#">외국어</a>
       
                     <ul>
                         <li><a href="#">영어</a></li>
                         <li><a href="#">중국어</a></li>
                         <li><a href="#">스페인어</a></li>
                     </ul>
                 </li>
                 </ul>
                 
                 </nav>
			</div>
        </div>
    </header> 

<!-- 채팅시 넘어갈 데이터 -->
<form name="openMessageFrm" method="post">

</form>
<script>
$(function(){
	$('#messageIcon').click(function(){
		var url = "<%=request.getContextPath()%>/message/openMessage.do";
		/* var status = "width=500, height=700, resizable=no, scrollbars=yes, status=no;";
		var title="메세지"
		var popUp = open("", title, status);
		window.name="parentWin"; 
		openMessageFrm.target = title;*/
		openMessageFrm.action=url;
		openMessageFrm.submit();
		
	});
});



//안읽은메세지수 2초마다 불러옴
$(function(){
	if(memberLogin!=null) {
		//알림기능
		$.ajax ({
			"url" : "<%=request.getContextPath()%>/message/readCount.do",
			cache: false,
			type:"get",
			dataType:"json",
			success : function(data) {
				$('#messageCount').text(data);
			}
			
		},2000);
	}
});

//알림기능
window.onload=function(){
	if(window.Notification){
		Notification.requestPermission();
	}
}


</script>


    <br>