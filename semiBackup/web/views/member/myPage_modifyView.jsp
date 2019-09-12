<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "/views/common/header.jsp" %>
    <%@ page import="com.semi.member.model.vo.Member" %>
    <%@ page import="com.semi.mento.model.vo.Mento" %>
    <% 
    	Mento mt = (Mento)session.getAttribute("loginMento");
    	Member m = (Member) session.getAttribute("loginMember");
    %>
<section class = "center1">
            <div class="wrap">
                <div class="bar">
                        <br>
					<table class = "MYLIST">
                                <tr>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/member/memberMyPage.do?mId=<%=m.getmId()%>'" id = "listBtn">마이페이지</button></td>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/member/mypageModify.do?mId=<%=m.getmId()%>'" id = "listBtn">내정보수정</button></td>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/member/studyList.do?mNum=<%=m.getmNum()%>'" id = "listBtn">신청한강의</button></td>
                                    <td><button onclick="location.href='LIKELIST.html'" id = "listBtn">즐겨찾기목록</button></td>
                                    <%if(mt != null && m.getmNum() == mt.getmNum()) { %>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/mento/enrollLecture.do?mtNum=<%=mt.getMtNum()%>'" id = "listBtn">강의만들기</button></td>
                                    <%} else { %>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/mento/mentoRegister.do?mId=<%=m.getmId()%>'" id = "listBtn">멘토신청하기</button></td>    
                                	<%} %>
                                	
                                </tr>
                        </table>
                        <br><br>
                    <h1 class="center1">내정보수정</h1>
                    <br><br>
                    <hr>
                </div>
                <div class="regdata center1">
                <form action="member/memberUpdate.do" method="POST" id = "memberFrm" onsubmit="return checkValue();">
                   <table class="tblreg center1">
					<tr>
						<td colspan="3">아이디</td>
						<td><%=m.getmId() %></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">비밀번호 변경</td>
						<td><input class="textfield" type="password" name="mPw"
							id="newPw" placeholder="변경하실 비밀번호를 입력해주세요" required></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">비밀번호 재입력</td>
						<td><input class="textfield" type="password" name="userPwd2"
							id="newPw2" placeholder="" required></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3">이메일 변경</td>
						<td><input class="textfield" type="email" name="email"
							id="email" placeholder="변경하실 이메일을 입력해주세요" required></td>
					</tr>
					<tr>
						<td colspan="3">휴대폰 번호 변경</td>
						<td>
							<!-- <input class="textfield" type="text" name="phone" placeholder="휴대폰번호를 입력해주세요"> -->

							<input type="text" name="tel1" id="tel1" class="phone"
							list="data" required> - <datalist id="data">
								<option value="010"></option>
								<option value="011"></option>
								<option value="019"></option>
							</datalist> <input type="text" name="tel2" id="tel2" class="phone"
							maxlength=4 required> - <input type="text" name="tel3"
							id="tel3" class="phone" maxlength=4 required>
						</td>

						<td><input type="button" value="인증번호받기" class="inputbtn">
						</td>
					</tr>
					<tr>
						<td colspan="3">인증번호</td>
						<td><input class="textfield" type="text" name="number"
							placeholder="인증번호를 입력해주세요"></td>
						<td><input type="button" value="인증번호확인" class="inputbtn"
							onclick=""></td>
					</tr>
					<tr>
						<td colspan='3'>
                   <input type="submit" id = "btnModify" value="수정하기" class="next" onclick="updateMember();"></td>
					</tr>
				</table>
                </form>

    
    
                </div>
    
            </div>
        </section>
        <script>
        function checkValue(){ 
    		var email = $('#email');
    		var phone1 = $('#phone1');
    		var phone2 = $('#phone2');
    		var phone3 = $('#phone3');
    		
    		var pw = $('#newPw');
    		var pw2 = $('#newPw2');

    		if(phone1.val().length==0 || phone2.val().length==0 || phone3.val().lenght==0){
                alert('전화번호를 입력하세요');
                phone1.focus();
                return false;
            }
    		
    		if(email.val().length==0){
    			alert('이메일을 입력하세요');
                email.focus();
                return false;
    		}
    		
    		
              if(pw.val().length==0){
                  alert('비밀번호를 입력하세요');
                  pw.focus();
                  return false;
              }
              if(pw.val().length<=5){
                  alert('비밀번호를 6글자 이상 입력하세요');
                  pw.focus();
                  return false;
              }
              /* if(pw.val()) */
              if(pw.val()!=pw2.val()) {
            	  alert('비밀번호가 맞지 않습니다.')
            	  pw.focus();
                  return false;
              }
              
              return true;
    	}
        
        function updateMember(){
        	
				var frm=$('#memberFrm');
				var url="<%=request.getContextPath()%>/member/memberUpdate.do?mId=<%=m.getmId()%>";
				frm.attr("action",url);
				frm.submit();
		}
		

        </script>
    


    <%@ include file = "/views/common/footer.jsp" %>