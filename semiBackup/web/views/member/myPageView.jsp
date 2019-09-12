<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.semi.member.model.vo.Member" %>
    <%@ page import="com.semi.mento.model.vo.Mento" %>
    <%@ include file = "/views/common/header.jsp" %>
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
                    <h1 class="center1">마이페이지</h1>
                    <br><br>
                    <hr>
                </div>
                <div class="regdata center1">
                    <table class="tblreg">
                        <tr>
                            <td>아이디</td>
                            <td>
                     			 <%=m.getmId() %>
                            </td>
                            <td>
   
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td>
                                <%=m.getmName() %>
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>생년월일</td>
                            <td> 
                            	<%=m.getmBirth() %> 
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>성별</td>
                            <td> <%=m.getmGender()=='F'?"여자":"남자"%></td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                <span><%=m.getmEmail() %></span> 
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>휴대폰번호</td>
                            <td>
                               <%=m.getmPhone() %>
                            </td>
    
                            <td>

                            </td>
                        </tr>
                        <tr>
                        </tr>
                    </table>
                </div>
                <div class = "center1">
                    <input type="button" id = "btnDelete" value="탈퇴하기" class="next center1" onclick="deleteMember();">
                </div>
            </div>
        </section>
        
        <script>
        function deleteMember(){
			if(confirm("정말로 삭제하시겠습니까?")){
				var url="<%=request.getContextPath()%>/member/memberDelete.do?mId=<%=m.getmId()%>";
				location.href=url;
			}
		}
        </script>
    	<%@ include file = "/views/common/footer.jsp" %>