<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.semi.lecture.model.vo.Lecture" %>
<%@ page import="java.util.List"%>
<%
   int cPage=(int)request.getAttribute("cPage");
   String pageBar=(String)request.getAttribute("pageBar");
   List<Lecture> list = (List)request.getAttribute("list");
   
%>

<%@ include file="/views/common/header.jsp"%>

<script>
   console.log(list);
</script>

<p id="title">강좌리스트</p>
        
    <section class="center">
    <div class="class1">
        
        
        <%for(Lecture lec : list){ %>
        
        

                  
        <table class="classtable"  style="cursor:pointer;" onclick="location.href='<%=request.getContextPath() %>/lecture/lectureView?lecnum=<%=lec.getLecNum()%>'">
            <tr>
                <td colspan="4"><img src="<%=request.getContextPath()%>/upload/lecture/<%=lec.getLectureUpload().getUpLectureReName()%>"></td>
            </tr>
            <tr>
                <td colspan="3"class="className" ><%=lec.getLecName() %></td>
                <td class="classplace">위치</td>
            </tr>
            <tr>
                <td colspan="4" class="classinfo">

                    멘토사진 

                    <hr>
                    
                </td>
            </tr>
            <tr>
                <td colspan="2" class="classScore"><b>Type</b><br><%=lec.getLecType() %></td>
                <td colspan="2" class="classmoneytime"><b>Money</b><br><%=lec.getLecPrice() %></td>
            </tr>
            
        </table>
        
        
        <%} %>

      <!-- 원래 틀
      <table class="classtable">
            
            <tr>
                <td colspan="4"><img src="사이즈300_300.png"></td>
            </tr>
            <tr>
                <td colspan="3"class="className" >강좌제목가나다라마바사아자aaaa지금은 5시 21분aaaaaaaaaaa차카타파하</td>
                <td class="classplace">위치 | 경기수원</td>
            </tr>
            <tr>
                <td colspan="4" class="classinfo">
                    소개설명<br>제매력에 빠져보실래요?<br>개피곤하다우<hr>
                    
                </td>
            </tr>
            <tr>
                <td colspan="2" class="classScore"><b>평점</b><br>4.4/5.0</td>
                <td colspan="2" class="classmoneytime"><b>돈/시간</b><br>20000원/1시간</td>
            </tr>
        </table> -->
        
    </div>
</section>

    <section class= "page1">
    <div class="page" id="pageBar">
       <%=pageBar %>
       <br>
        
    </div>
</section>

<%@ include file="/views/common/footer.jsp"%>