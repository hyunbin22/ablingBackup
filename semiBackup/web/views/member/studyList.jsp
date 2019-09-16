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
					<br>
					<br>
                    <h1 class="center1">내강좌목록</h1>
                    <br><br>
                    <hr>
                    <br>
                </div>
                <div class="center1">
                    <form action = "" method="POST">
                    <table id = "list" border="1">
                    
                            <tr class = "listName">
                                    <td class = "listNumber">No</td>
                                    <td class = "listContent">강의 이름</td>
                                    <!-- <td class = "listCheck">선택</td> -->
                                </tr>
                        <tr class = "list">
                            <td class = "listNumber">1</td>
                            <td class = "listContent"><span>박기오와 함께하는 기타 수업</span></td>
                            <td><button id="btn-goPay" class="btn btn-primary">결제하기</button></td>
                            <!-- <td class = "listCheck"><input type = "checkbox" name = "check" value = "1"></td> -->
                        </tr>
                        <tr class = "list">
                            <td class = "listNumber">2</td>
                            <td class = "listContent"><span>정현빈의 누구나 따라하는 뷰티학개론</span></td>
                            <!-- <td class = "listCheck"><input type = "checkbox" name = "check" value = "1"></td> -->
                        </tr>
                        <tr class = "list">
                            <td class = "listNumber">3</td>
                            <td class = "listContent"><span>만능 유튜버 여동규의 간단한 동영상 제작</span></td>
                            <!-- <td class = "listCheck"><input type = "checkbox" name = "check" value = "1"></td> -->
                        </tr>
                        <tr class = "list">
                            <td class = "listNumber">4</td>
                            <td class = "listContent"><span>송주미의 말랑 말랑 슬라임의 모든것을 알아보는 시간</span></td>
                            <!-- <td class = "listCheck"><input type = "checkbox" name = "check" value = "1"></td> -->
                        </tr>
                        <tr class = "list">
                            <td class = "listNumber">5</td>
                            <td class = "listContent"><span>이걸 만들고 있는 김기호 ㅠㅠ</span></td>
                            <!-- <td class = "listCheck"><input type = "checkbox" name = "check" value = "1"></td> -->
                        </tr>
                    
                    </table>
                    </form>
                    <br>
  
           
    
    
                </div>
            </div>
      </section>
      
      <!-- 결제창 보낼 데이터 -->
      <form method="post" name="orderPayFrm">
      	<input type="hidden" name="mNum" value=<%=m.getmNum()%>>
      	<!-- <input type="hidden" name="lecNum" value=2> -->
      	<input type="hidden" name="oNum" value=1>
      </form>
      
      <script>
      	$(function(){
      		$('#btn-goPay').click(function(){
      			console.log('야야');
      			var url="<%=request.getContextPath()%>/order/orderPaymentView.do";
      			orderPayFrm.action=url;
      			orderPayFrm.submit();
      		});
      	});
      
      
      </script>
    <%@ include file = "/views/common/footer.jsp" %>