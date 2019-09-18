<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/adminHeader.jsp"%>
<%@ page import="java.util.List, com.semi.member.model.vo.Member"%>
<%
   List<Member> members=(List)request.getAttribute("members");
   int cPage=(int)request.getAttribute("cPage");
   String pageBar=(String)request.getAttribute("pageBar");
%>
    <section>
        <div>
        <article class="search">
            <select>
                <option value="이름">이름</option>
                <option value="이메일">이메일</option>
                <option value="전화번호">전화번호</option>
            </select>
            <input type="text" placeholder="검색어 입력"> <button>검색</button>
        </article>
        <article>
            <div>
                <table class="person-top-table">
                	<tr>
                        <th scope="col" class="th-name">이름</th>
                        <th scope="col" class="th-email">이메일</th>
                        <th scope="col" class="th-pwreset">비번초기화</th>
                        <th scope="col" class="th-phone">휴대전화인증</th>
                        <th scope="col" class="th-check">튜터인증</th>
                        <th scope="col" class="th-delete">계정삭제</th>
                    </tr>
                </table>
                <table class="person-table">
                    <tbody>
                  <%if(members!=null && !members.isEmpty()){
                     for(Member m : members){ %>
                     <tr onmousemove="this.style.background='rgb(231,231,231)'" onmouseout="this.style.background='none'">
                        <td class="th-name"><%=m.getmName() %></td>
                              <td class="th-email"><%=m.getmEmail() %></td>
                              <td class="th-pwreset"><input type="button" value="초기화"></td>
                              <td class="th-phone"><%=m.getmPhone() %></td>
                              <td class="th-check"><input type="checkbox" checked="checked"><button onclick="">edit</button></td>
                              <td class="th-delete"><input type="button" value="계정삭제"></td>
                     </tr>
                  <%}
                  }%>
               </tbody>
                </table>
               <div id="pageBar" class="center1">
               <br>
                  <%=request.getAttribute("pageBar")%>
               </div>
            </div>
        </article>
    </div>
    </section>
   
</body>
</html>