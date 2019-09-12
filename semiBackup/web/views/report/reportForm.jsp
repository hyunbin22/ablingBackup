<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="com.semi.member.model.vo.Member" %>
    <%@ include file = "/views/common/header.jsp" %>
    <% 
    	Member m = (Member) session.getAttribute("loginMember");
    %>
	<section class = "center1">
            <div class="wrap">
                <div class="bar">
                    <br><br><br>
                    <h1 class="center1">신고하기</h1>
                    <br><br>
                    <hr>
    		</div>
    		<div class="center1">
                <form action="<%=request.getContextPath()%>/Report.do?mNum=<%=m.getmNum()%>" method="POST" onsubmit="return checkValue();" enctype="multipart/form-data">
                    <table class="tblreg">
                        <tr>
                            <td>작성자 ID</td>
                            <td>
                                <%=m.getmId() %>
                            </td>
                            <td>
   
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>신고할 사용자 ID</td>
                            <td>
                                <input class="textfield" type="text" name="rId" id = "reportId1">
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>제목</td>
                            <td>
                                <input class="textfield" type="text" name="rTitle" id = "reportTitle1">
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>신고 사유를 정확하게 작성해주세요.</td>
                        </tr>
                        <tr>
 							<td>
 							</td>
                            <td>
                               <textarea id = "reportContent1" name = "rContent" rows="30" cols="50" style="resize: none;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>첨부 파일</td>
                            <td>
                                <input id="reportPhoto1" type="file" name="reportPhoto" onchange="previewImage(this,'View_area')">
                            </td>
                        </tr>
                        <tr>
                        </tr>    
                    </table>
                   	<div class = "center1">
                   <input type="submit" id = "btnReport" value="신고하기" class="next center1">   
                	</div>
                </form>
                </div>
                </div>
    </section>
    <script>
    function checkValue(){ 
    		var reportId = $('#reportId1');
    		var reportTitle = $('#reportTitle1');
    		var reportContent = $('#reportContent1');
    	
    		
    		if(reportId.val().length==0){
    			alert('신고할 ID를 입력하세요');
    			reportId.focus();
                return false;
    			}
    		
    		
              if(reportTitle.val().length==0){
                  alert('제목을 입력하세요');
                  reportTitle.focus();
                  return false;
              	}
   
              if(reportContent.val().length==0){
      			alert('신고 내용을 입력하세요');
      			reportId.focus();
                  return false;
      			}
              return true;
              }
              

    	
    </script>
    <%@ include file = "/views/common/footer.jsp" %>