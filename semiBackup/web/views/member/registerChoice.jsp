<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<section class="center1">
	<article>
		<div class="wrap">
			<div class="bar">
				<br> <br>
				<h1>회원가입</h1>
				<br>
				<hr>
			</div>
			<div class="selectregister center1">
				<div class="rgEmail">
					<button id="rgEmail"><p>이메일로<br>가입하기</p></button>
				</div>
				<div class="rgkakao">
					<button id="rgKakao"><p>카카오로<br>가입하기</p></button>
				</div>
				<div class="rgFacebook">
					<button id="rgFacebook"><p>페이스북으로<br>가입하기</p></button>
				</div>

			</div>
		</div>
	</article>
</section>
<script>
 $(function(){
	$('#rgEmail').click(function(){
		location.href="<%=request.getContextPath()%>/views/member/register2.jsp";
	});
	
	$('#rgKakao').click(function(){
		 //<![CDATA[
        // 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('45dc15dc2155090cfe34cf61363e3f93');
        // 카카오 로그인 버튼을 생성합니다.
        Kakao.Auth.login({
        	persistAccessToken : true,
        	persistRefreshToken: true,
         	success: function(authObj) {
         		setCookie("kakao_cookie","done",1);
         		cookiedata = document.cookie;
         		getKakaotalkUserProfile();
         		createKakaotalkLogout();
         		
         		
         		
	            alert("success"+JSON.stringify(authObj));
	            var refreshToken = kakao.Auth.getRefreshToken();
	            Kakao.Auth.setAccessToken(accessTokenFromServer);
	        },
	        fail: function(err) {
	            alert("fail"+JSON.stringify(err));
	        }
        });
      //]]>
	});
	
	$('#rgFacebook').click(function(){
		location.href="<%=request.getContextPath()%>/views/member/register2.jsp";
	});
	
 });



</script>
<%@ include file="/views/common/footer.jsp"%>