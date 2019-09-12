<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<script src="https://sdk.accountkit.com/en_US/sdk.js"></script>
<!-- sms인증 -->
<section class="center1">
	<div class="wrap">
		<div class="bar">
			<br> <br>
			<h1>회원가입</h1>
			<br>
			<hr>
		</div>
		<div class="regdata">
			<form
				action="<%=request.getContextPath()%>/member/MemberRegisterServlet.do"
				method="POST" id="registerFrm" onsubmit="return checkValue();">
				<table class="tblreg center1">
					<tr>
						<td>아이디</td>
						<td><input class="textfield" type="text" name="id"
							id="userId" placeholder="아이디를 입력해주세요" required></td>
						<td id="idCheck" class="rg-checkMsg"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input class="textfield" type="password" name="pw"
							id="password" placeholder="비밀번호를 입력해주세요" required></td>
						<td id="pwCheck" class="rg-checkMsg"></td>
					</tr>
					<tr>
						<td>비밀번호확인</td>
						<td><input class="textfield" type="password" name="pw2"
							id="password2" placeholder="비밀번호를 입력해주세요" required></td>
						<td id="pwCheck2" class="rg-checkMsg"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input class="textfield" type="text" name="name"
							id="userName" placeholder="이름을 입력해주세요" required></td>
					</tr>
					<tr>
						<td>성별</td>
						<td><label class="rdGender"><input type="radio"
								name="gender" id="mgender" value="M" checked>남자</label> <label
							class="rdGender"><input type="radio" name="gender"
								id="fgender" value="F">여자</label></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input class="textfield" type="date" name="birth"
							id="birth" required></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input class="textfield" type="email" name="email"
							id="email" placeholder="이메일을 입력해주세요" required></td>
						<td id="emailCheck" class="rg-checkMsg"></td>
					</tr>
					<tr>
						<td>휴대폰번호</td>
						<td>
							<!-- <input placeholder="01012345678(-제외)" id="phone_number" class="textfield"/> -->
							<input type="text" name="tel1" id="tel1" class="phone" list="data" required> - 
							<datalist id="data">
								<option value="010"></option>
								<option value="011"></option>
								<option value="019"></option>
							</datalist> 
							<input type="text" name="tel2" id="tel2" class="phone" maxlength=4 required> - 
							<input type="text" name="tel3" id="tel3" class="phone" maxlength=4 required>
						</td>

						<td><input type="button" value="인증번호받기" class="inputbtn"
							id="sendSms"></td>
					</tr>
					<tr>
						<td>인증번호</td>
						<td><input class="textfield" type="text" name="number"
							placeholder="인증번호를 입력해주세요" id="checkNum" required></td>
						<td><input type="button" value="인증번호확인" class="inputbtn"
							id="checkKey"></td>
					</tr>
					<tr>
						<td></td>
						<td id="keyCheck"></td>
					</tr>
					<tr class="register-submit">
						<td colspan='3'><input type="submit" value="회원가입"
							class="btnregister center1"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</section>
<script>
	//핸드폰 번호 인증 facebook
	  // initialize Account Kit with CSRF protection
/* 	  AccountKit_OnInteractive = function(){
	    AccountKit.init(
	      {
	    	debug:true,
	        appId:2351518755115588, 
	        state:"12345", 
	        version:"v1.1",
	        fbAppEventsEnabled:true,
	        redirect:""
	      }
	    );
	  };
	
	  // login callback
	  function loginCallback(response) {
	    if (response.status === "PARTIALLY_AUTHENTICATED") {
	      var code = response.code;
	      var csrf = response.state;
	      $('#phone_number').readOnly=true;
	      console.log(code);
	      console.log(scrf);
	    }
	    else if (response.status === "NOT_AUTHENTICATED") {
	    	alert("정상적인 방법이 아닙니다.");
			$("input[name=phone_number]").val("");
	    }
	    else if (response.status === "BAD_PARAMS") {
	    	alert("정상적인 방법이 아닙니다.");
			$("input[name=phone_number]").val("");
	    }
	  }
	
	  // phone form submission handler
	  function smsLogin() {
	    var countryCode = "+82";
	    var phoneNumber = document.getElementById("phone_number").value;
	    console.log(phoneNumber);
	    AccountKit.login('PHONE', 
	      {
	    	countryCode: countryCode, 
	      	phoneNumber: phoneNumber
	      }, // will use default values if not specified
	      loginCallback
	    );
	  } */

	var count = 0;	//핸드폰인증이 됐는지 확인
	  //핸드폰번호 인증
	$(function(){
		var key = "";
		$('#sendSms').click(function(){
			var tel1 = $('#tel1').val();
			var tel2 = $('#tel2').val();
			var tel3 = $('#tel3').val();
			var phone = tel1 + tel2 + tel3;
			if(phone.length!=11) {
				alert("유효한 휴대폰번호를 입력해주세요.");
				return;
			}
			console.log(phone);
			$.ajax({
				url:"<%=request.getContextPath()%>/sendSms.do?phone="+phone,
				type:"get",
				dataType:"text",
				success:function(data) {
					console.log(data);
					key = data;
				},
				error:function(request,status,error){
			        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			    }
			});
		});
		
		//인증번호 확인
		$('#checkKey').click(function(){
			var userKey = $('#checkNum').val();
			if(userKey.length == 0){
				$('#keyCheck').text("인증번호를 입력해주세요.");
				$('#keyCheck').css({"color":"red","font-size":"11px"});
				$('#keyCheck').prop("disabled", true);
				
			} else if(key!=userKey){
				$('#keyCheck').text("인증번호가 일치하지 않습니다.");
				$('#keyCheck').css({"color":"red","font-size":"11px"});
				$('#keyCheck').prop("disabled", true);

			} else if(key==userKey) {
				$('#sendSms').prop("disabled", false);
				$('#checkKey').prop("disabled", false);
				$('#tel1').prop("readonly", true);
				$('#tel2').prop("readonly", true);
				$('#tel3').prop("readonly", true);
				$('#checkNum').readOnly=true;
				$('#keyCheck').prop("disabled",false);
				count=1;
			} 
			
		});
	});	//핸드폰번호 인증 끝 --%>
	

	
	//아이디 중복검사 
	$(function(){
	 	var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
	 	var idCheck = $('#idCheck');
		$('#userId').blur(function(){
			var rgUserId = $('#userId').val();
			$.ajax({
				url:"<%=request.getContextPath()%>/member/MemberIdDupliCheckServlet.do?userId="+ rgUserId,
				type : "get",
				dataType : "html",
				success : function(result) {
					if (result == 0) { //아이디가 중복될경우
						$(idCheck).text("사용중인 아이디입니다.");
						$(idCheck).css({
							"color" : "red",
							"font-size" : "11px"
						});
						$(idCheck).prop("disabled",true);
					} else if (result == 1) {
						if (re.test(rgUserId)) {
							$(idCheck).text("");
							$(idCheck).prop("disabled",false);
						} else if (rgUserId == "") {
							$(idCheck).text('아이디를 입력해주세요.');
							$(idCheck).css({"color" : "red","font-size" : "11px"});
							$(idCheck).prop("disabled",true);
						} else {
							$(idCheck).text('4~12자의 영문자와 숫자로만 입력하세요.');
							$(idCheck).css({"color" : "red","font-size" : "11px"});
							$(idCheck).prop("disabled",true);
						}
					}
				},
				error : function(request, status, error) {
					alert("code = " + request.status
							+ " message = "
							+ request.responseText
							+ " error = " + error); // 실패 시 처리
				}
			});
		});
	});
	
	//이메일 중복검사
	$(function(){
	 	var emailCheck = $('#emailCheck');
		$('#email').blur(function(){
			var email = $('#email').val();
			$.ajax({
				url:"<%=request.getContextPath()%>/member/MemberEmailDupliCheckServlet.do?email="+ email,
				type : "get",
				dataType : "html",
				success : function(result) {
					if (result == 0) { //이메일이 중복될경우
						$(emailCheck).text("사용중인 이메일입니다.");
						$(emailCheck).css({
							"color" : "red",
							"font-size" : "11px"
						});
						$(emailCheck).prop("disabled",true);
					} else {
						$(emailCheck).text("");
						$(emailCheck).prop("disabled",false);
						} 
					
					},
					error : function(request, status, error) {
						alert("code = " + request.status
								+ " message = "
								+ request.responseText
								+ " error = " + error); // 실패 시 처리
					}
				});
			});
		});

	//비밀번호 입력체크
	$(function() {
		var re = /^[a-zA-Z0-9]{6,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식

		var pattern1 = /[0-9]/;
		var pattern2 = /[a-zA-Z]/;
		var pattern3 = /[~!@\#$%<>^&*]/;

		var pwCheck = $('#pwCheck');
		$('#password').blur(function() {
				var pw = $('#password').val();

				if (pw == "") {
					$(pwCheck).text('비밀번호를 입력해주세요.');
					$(pwCheck).css({
						"color" : "red",
						"font-size" : "11px"
					});
					$(pwCheck).prop("disabled", true);
				} else if (!pattern1.test(pw) || !pattern2.test(pw)	|| !pattern3.test(pw)) {
					$(pwCheck).text('비밀번호는 영문자와 숫자, 특수기호를 포함해주세요.');
					$(pwCheck).css({
						"color" : "red",
						"font-size" : "11px"
					});
					$(pwCheck).prop("disabled", true);
				} else if (pw.length < 6) {
					$(pwCheck).text('비밀번호는 6글자 이상 입력해주세요.');
					$(pwCheck).css({
						"color" : "red",
						"font-size" : "11px"
					});
					$(pwCheck).prop("disabled", true);
				} else {
					$(pwCheck).text("");
					$(pwCheck).prop("disabled", false);

				}
			});
	});

	$(function() {
		var pwCheck2 = $('#pwCheck2');
		$('#password2').blur(function() {
			var pw2 = $('#password2').val();
			if (pw2 == "") {
				$(pwCheck).text('비밀번호를 입력해주세요.');
				$(pwCheck).css({
					"color" : "red",
					"font-size" : "11px"
				});
				$(pwCheck).prop("disabled", true);
			} else if (!(pw2 == $('#password').val())) {
				$(pwCheck2).text("비밀번호확인이 일치하지 않습니다.");
				$(pwCheck2).css({
					"color" : "red",
					"font-size" : "11px"
				});
				$(pwCheck2).prop("disabled", true);
			} else {
				$(pwCheck2).text("");
				$(pwCheck2).prop("disabled", false);
			}

		});

	});

	//회원가입 정규식
	function checkValue() {

		var rgUserName = $('#userName');
		var email = $('#email');
		var tel2 = $('#tel2');
		var tel3 = $('#tel3');
		console.log("checkNum.val" + $('#checkNum').val());

		var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;// 이메일이 적합한지 검사할 정규식

		//아이디 체크글자가 보일때
		if ($('#idCheck').prop('disabled')) {
			alert("아이디를 확인하세요.");
			return false;
		}

		//비밀번호 체크 글자가 보일때
		if ($('#pwCheck').prop('disabled') || $('#pwCheck2').prop('disabled')) {
			alert("비밀번호를 확인하세요.");
			return false;
		}

		//인증번호 체크글자가 보일때
		if ($('#keyCheck').prop('disabled')) {
			alert("인증번호를 확인하세요.");
			return false;
		}
		
		//이메일 체크 글자가 보일때
		if ($('#emailCheck').prop('disabled')) {
			alert("인증번호를 확인하세요.");
			return false;
		}
		
		//핸드폰인증을 진행하지 않았을때
		if(count!=1) {
			alert("핸드폰번호 인증을 진행해주세요.");
			$('#checkNum').focus();
			return false;
		}
		return true;
	};
	//전화번호 체크
	//전화번호 입력시 숫자가 아니면 지움
	$(tel2).on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g, ""));
	});

	$(tel3).on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g, ""));
	});
	//전화번호 4글자 입력시 다음 input태그로 이동
	$(function() {
		$(tel2).keyup(function() {
			var limit = $(this).attr("maxlength");
			if (this.value.length >= limit) {
				$(this).next(tel3).focus();
				return false;
			}
		});
	});
</script>
<%@ include file="/views/common/footer.jsp"%>
