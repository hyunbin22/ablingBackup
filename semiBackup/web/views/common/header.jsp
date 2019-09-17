<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.semi.member.model.vo.Member"%>
    
    <%
    	
     Member memberLogin = (Member) session.getAttribute("loginMember");
     Cookie[] cookies = request.getCookies();
     String userId = "";
	 if(memberLogin!=null) {
		 userId = memberLogin.getmId();
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
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script> <!-- 결제인증 -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
    <title>ABLING</title>

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
	
	 $(document).ready(function() {
         var $banner = $(".banner").find("ul");
         var $bannerWidth = $banner.children().outerWidth();//이미지의 폭
         var $bannerHeight = $banner.children().outerHeight(); // 높이
         var $length = $banner.children().length;//이미지의 갯수
         var rollingId;
         //정해진 초마다 함수 실행
         rollingId = setInterval(function() { rollingStart(); }, 3000);//다음 이미지로 롤링 애니메이션 할 시간차
         function rollingStart() {
             $banner.css("width", $bannerWidth * $length + "px");
             $banner.css("height", $bannerHeight + "px");
             //alert(bannerHeight);
             //배너의 좌측 위치를 옮겨 준다.
             $banner.animate({left: - $bannerWidth + "px"}, 1, function() { //숫자는 롤링 진행되는 시간이다.
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
        	<div id="topWrap">
	            <div id='logoWrap'><a href = "<%=request.getContextPath()%>"><img id = 'logoImg' src = "<%=request.getContextPath()%>/image/logo.png"></a></div> <!--메인 아이콘 이미지-->
	              <%if(memberLogin == null){ %>
	            		
	            <div id="loginWrap">
		            <div id = loginmenu>
		            <form id = "loginFrm" action = "<%=request.getContextPath() %>/member/memberLoginServlet.do" method="POST" onsubmit="return validate();">
		                <input id = "id" type = "text" name = "mId" placeholder="아이디">
		                <input id = "pwd" type = "password" name = "mPw" placeholder="비밀번호">
		                <input type="submit" value="로그인" id="login">
		            </form>
		            </div>
		                <div id = "loginmenu2">
		                <span onclick = "location.href='<%=request.getContextPath()%>/views/member/member/memberRegAgree.do'" id = "join">회원가입</span>
		                <span onclick = "location.href='<%=request.getContextPath()%>/views/member/views/member/findId.jsp'" id = findId>아이디 찾기</span>
		                <span onclick = "location.href='<%=request.getContextPath()%>/views/member/views/member/findPwd.jsp'" id = findPw>비밀번호 찾기</span>
		             <%} else { %>       
		                <div id = loginWrap>
		                <div id = "myPro">
		                    <div id = "myPro2"><%=memberLogin.getmId()%>님 환영합니다!
		                    <img src="<%=request.getContextPath() %>/image/messageIcon.png" id="messageIcon">
                			<span id = "unread" class="label label-info"></span>
		        
		                    </div>
		                </div>
		                <div id = "mypromenu">
		                    <button onclick = "location.href='<%=request.getContextPath()%>/memberLogoutServlet.do'" id = logout>로그아웃</button>
		                    <button onclick = "location.href='<%=request.getContextPath()%>/member/memberMyPage.do?mId=<%=memberLogin.getmId()%>'" id = mypage>마이페이지</button>
		                </div>
		            </div>
		          
	            <%} %>
	            </div>
	        </div>
        </div>
        <%if(memberLogin!=null) {%>
		 <br><br><br><br><br><br><br><br><br><br><br>
		<%} %>
		   <div id = menu class = "center1">
		 
				<nav>
			        <ul class="center1">
			        
				        <li><a href="#">ABLING</a></li>
				        <li><a href="#">강의찾기</a></li>
				        <li><a href="#">수업모임</a></li>
				        <li><a href="#">이벤트</a></li>
				        <li><a href="#">고객지원</a>
			         <ul>
			            <li><a href="#">공지사항</a></li>
				        <li><a href="#">F & A</a></li>
			            <%if(memberLogin != null) { %>
						      <li><a href="#">1대1문의</a>
						      </li>
						      
						      <li><a href="<%=request.getContextPath()%>/views/report/reportForm.jsp">신고</a>
						      </li>
			      		<% } %>
			         </ul>
			         <%if(memberLogin != null) {
			         	if (memberLogin.getmId().equals("kiho") || memberLogin.getmId().equals("admin") || memberLogin.getmId().equals("gusqls897") || memberLogin.getmId().equals("rldh8") || memberLogin.getmId().equals("thd9292")) {%>
				        <li><a href="<%=request.getContextPath()%>/admin/AdminMentoApproval.do">관리자</a></li>
				      <%} 
			         }%>
			      </li>
			    </ul>
			</nav>
		</div>
     </div>
 </header> 

<!-- 채팅시 넘어갈 데이터 -->
<form name="openMessageFrm" method="post">
</form>

<!-- 메세지 -->
<script>
	$(function(){
		$('#messageIcon').click(function(){
			var url = "<%=request.getContextPath()%>/message/openMessage.do";
			var status = "width=400, height=600, resizable=no, status=no, toolbars=no, menubar=no";
			var title="메세지"
			var popUp = open("", title, status);
			window.name="parentWin"; 
			openMessageFrm.target = title;
			openMessageFrm.action=url;
			openMessageFrm.submit();
			
		});
	});
	
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
	//알림기능
	window.onload=function(){
		if(window.Notification){
			Notification.requestPermission();
		}
	}


</script>


    <br>