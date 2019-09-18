<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "/views/common/header.jsp" %>
	<%@ include file="/views/common/myPageAside.jsp" %>
	  	<div class="wrap">
             <div class="bar">
				<br>
                <h1 class="center1">신청한 강의</h1>
                <br><br>
                <hr>
                <br>
             </div>
             <div class="center1 myPage-content-wrap">
                 <table id = "list" border="1">
                 
                         <tr class = "listName">
                                 <td class = "listNumber">No</td>
                                 <td class = "listContent">강의 이름</td>
                                 <!-- <td class = "listCheck">선택</td> -->
                             </tr>
                     <tr class = "list">
                         <td class = "listNumber">1</td>
                         <td class = "listContent"><span>박기오와 함께하는 기타 수업</span></td>
                         <td><button id="btn-goPay" class="btn btn-primary next">결제하기</button></td>
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
                 <br>

         
  
  
                </div>
            </div>
      </section>
      
      <!-- 결제창 보낼 데이터 -->
      <form method="post" name="orderPayFrm" id="orderPayFrm" action="<%=request.getContextPath()%>/order/orderPaymentView.do">
      	<input type="hidden" name="mNum" value="<%=m.getmNum()%>">
      	<input type="hidden" name="oNum" value="1">
      </form>
      
      <script>
      	$(function(){
      		$('#btn-goPay').click(function(){
      			console.log('야야');
      			var url="<%=request.getContextPath()%>/order/orderPaymentView.do?oNum=1";
      			/* form.attr("action",url); 
      			orderPayFrm.submit();*/
      			orderPayFrm.action=url;
      			orderPayFrm.submit();
      		});
      	});
      
      
      </script>
    <%@ include file = "/views/common/footer.jsp" %>