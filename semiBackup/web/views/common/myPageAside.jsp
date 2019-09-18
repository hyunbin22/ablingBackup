<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.semi.member.model.vo.Member" %>
    <%@ page import="com.semi.mento.model.vo.Mento" %>
	<% 
    	Mento mt = (Mento)session.getAttribute("loginMento");
    	Member m = (Member) session.getAttribute("loginMember");
    %>
<section class = "center1">
	<div id="myPageWrap">
	<aside id="myPageNavWrap">
		<nav id="myPageNav">
			<ul>
               	<li><a href="<%=request.getContextPath()%>/member/memberMyPage.do?mId=<%=m.getmId()%>" class="appro-aTag">마이페이지</a></li>
               	<li><a href="<%=request.getContextPath()%>/member/mypageModify.do?mId=<%=m.getmId()%>" class="appro-aTag">내정보수정</a></li>
               	<li><a href="<%=request.getContextPath()%>/member/studyList.do?mNum=<%=m.getmNum()%>" class="appro-aTag">신청한강의</a></li>
               	<li><a href="#" class="appro-aTag">즐겨찾기목록</a></li>
               	<%if(mt != null && m.getmNum() == mt.getmNum()) { %>
                                
                <%} else { %>
              	<li><a href="<%=request.getContextPath()%>/mento/mentoRegister.do?mId=<%=m.getmId()%>" class="appro-aTag">멘토신청하기</a></li>
              	<%} %>
                <%if(mt != null && m.getmNum() == mt.getmNum()) { %>
                <li><a href="<%=request.getContextPath()%>/mento/mentoMyPage.do?getmNum=<%=mt.getmNum()%>&getMtNum=<%=mt.getMtNum() %>" class="appro-aTag">멘토페이지</a></li>
                <li><a href="<%=request.getContextPath()%>/mento/mypageModify.do?mtnum=<%=mt.getMtNum()%>" class="appro-aTag">멘토정보수정</a></li>
                <li><a href="<%=request.getContextPath()%>/mento/studyList.do?mtnum=<%=mt.getMtNum()%>" class="appro-aTag">내강의목록</a></li>
                <li><a href="<%=request.getContextPath()%>/mento/enrollLecture.do?mtNum=<%=mt.getMtNum()%>" class="appro-aTag">강의만들기</a></li>
            	<%} %>
            </ul>         
		</nav>
	</aside>