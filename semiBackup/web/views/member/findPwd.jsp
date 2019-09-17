<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<section class = "center1">
            <div class="wrap">
                <div class="bar">
                    <br>
                    <h1 style="font-size: 40px; text-align: center;">비밀번호 찾기</h1>
                    <h5 class = "center1">찾으시려는 비밀번호의 정보를 입력해주세요</h5>
                    <hr>
                    <br><br>
                </div>
                <div class="regdata center1">
                 <form action = "<%=request.getContextPath() %>/member/findPwd" method="POST">
                <table class="tblreg">
                        <tr>
                            <td>아이디</td>
                            <td>
                                <input class="textfield" type="text" name="mId" placeholder="아이디를 입력해주세요">
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr></tr>
                        <tr></tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                <input class="textfield" type="text" name="email" placeholder="이메일을 입력해주세요">
                            </td>
                  
                        </tr>
                        <tr>
                        <td>
                    	</td>
                        </tr>

                    
                    </table>
                    <br><br>
  					<div class = "center1">
                            <input type="submit" value="확인" class="next center1">
                    </div>
                    </form>

                
                </div>
            </div>
        </section>
        
<%@ include file="/views/common/footer.jsp"%>