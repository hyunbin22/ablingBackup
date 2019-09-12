<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<section class = "center1">
            <div class="wrap">
                <div class="bar">
                    <br>
                    <h1 style="font-size: 40px; text-align: center;">비밀번호</h1>
                    <hr>
                    <br><br>
                </div>
                <div class="regdata center1">
                 <form action = "" method="POST">
                <table class="tblreg">
                        <tr>
                            <td>아이디 입력</td>
                            <td>
                                <input class="textfield" type="text" name="id" placeholder="아이디를 입력해주세요">
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr></tr>
                        <tr></tr>
                        <tr>
                            <td>이메일 인증</td>
                            <td>
                                <input class="textfield" type="text" name="email" placeholder="이메일을 입력해주세요">
                            </td>
                  
                        </tr>
                        <tr>
                        <td>
                    	</td>
                            <td>
                                <button class = "nextCode" onclick="">인증코드보내기</button>
                            </td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td>인증번호 입력</td>
                            <td>
                                <input class="textfield" type="email" name="emailCode" placeholder="인증번호를 입력해주세요."> 
                            </td>
                            <td>
                            </td>
                        </tr>
                    
                    </table>
                    </form>
                    <br><br>
  					<div class = "center1">
                            <a href=""><input type="submit" value="확인" class="next center1"></a>
                    </div>
                
                </div>
            </div>
        </section>
        
<%@ include file="/views/common/footer.jsp"%>