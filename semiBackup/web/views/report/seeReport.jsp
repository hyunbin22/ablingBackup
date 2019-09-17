<%@page import="com.semi.report.model.vo.ReportUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import = "com.semi.report.model.vo.Report , com.semi.report.model.vo.ReportUpload" %>
<% 
	Report rp = (Report)request.getAttribute("seeReport");
	Member m = (Member) session.getAttribute("loginMember");
%>

	<section class = "center1">
            <div class="notice-table">
                <table class="notice-top2">
                    <th class="notice-highlight1"></th>
                                        <th></th>
                    <th class="notice-date">작성 날짜</th>
                                        <th></th>
                    <th class="notice-title"> 제목</th>
                    <th class="notice-id">작성자</th>
                </table>
                <table>
                    <tr class="click-notice">
                        	<td class="notice-highlight">
                        </td>
                        <td class="notice-date"><%=rp.getReportDate() %></td>
                       	<td class="notice-title"><%=rp.getReportTitle() %></td>
                        <td class="notice-id"><%=rp.getMember().getmId() %></td>
                        <tr>
                        <td colspan = "4"><hr></td>
                        </tr>
                        <tr>
                        <td colspan = "4">문의 내용</td>
                        </tr>
                        <tr class="notice-content">
                            <td colspan="4">
                                <div class="notice-content2">
                                <div class = "report-content">
                                   		<%=rp.getReportContent() %>

								</div>
                                </div>
                            </td>
                        </tr>
                        <%if(rp.getReportReason() != null) {%>
                        <tr>
                        <td colspan="4">관리자 답글</td>
                        </tr>
                        <tr class="notice-content">
                            <td colspan="4">
                                <div class="notice-content3">
                                <div class = "report-content">
                                   		<%=rp.getReportReason() %>		

								</div>
                                </div>
                            </td>
                        </tr>
                        <%} else {%>
                        <tr>
                        <td colspan="4">처리 대기중</td>
                        </tr>
                        <% } %>
                </table>
                <table class = "center1">
                    <tr class="null">
                    <td>
                    <button id ="seeMore" name = "seeMore"  onclick="back();">목록으로</button>
                    </td>
                    </tr>
                </table>
                </div>
    </section>
	
	
	<script>	
		function back()
		{
			var url="<%=request.getContextPath()%>/member/reportHistroy?mNum=<%=m.getmNum() %>";
			location.href=url;
		}
	</script>



<%@ include file="/views/common/footer.jsp"%>