<%@page import="com.semi.report.model.vo.ReportUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/adminHeader.jsp"%>
<%@ page import = "com.semi.report.model.vo.Report , com.semi.report.model.vo.ReportUpload" %>
<% 
	Report rp = (Report)request.getAttribute("report"); 
	Report rp2 = (Report)request.getAttribute("report2");
 	ReportUpload rpu = (ReportUpload)request.getAttribute("reportUp");
%>

 <section class="center1">

        <article class="admin-list-container wrap">
          <div class="row">
         <div class="col">
            <div class="notice-table">
            <form id = "replyFrm" action = "" method = "post">
            <input type="hidden" name="reportId" value=<%=rp.getMember().getmId() %>>
                <table class="notice-top">
                	<tr>
	                    <th class="notice-highlight1"></th>
	                                        <th></th>
	                    <th class="notice-date">첨부파일</th>
	                                        <th></th>
	                    <th class="notice-title"> 제목</th>
	                    <th class="notice-id">작성자</th>
                    </tr>
                </table>
                <table>
                    <tr class="click-notice">
                        	<td class="notice-highlight">
                        </td>
                        <td class="notice-date"><%if(rpu.getFileReNmae()!=null) {%>
						<%-- <a href="<%=request.getContextPath()%>/notice/noticeFileDown?fileName=<%=n.getFilePath()%>"> --%>
						<a href="javascript:fn_filedown('<%=rpu.getFileOriName()%>','<%=rpu.getFileReNmae()%>')">
							<img src="<%=request.getContextPath() %>/image/첨부파일.png"
							width='40px'/>
							<%=rpu.getFileReNmae() %>
						 </a>
					<%} %>
					<script>
						function fn_filedown(ori,re){
							var file=encodeURIComponent(re);
							location.href="<%=request.getContextPath()%>/admin/reportFileDown?orifileName="+file+"&refileName="+re;
						}
						
					</script>
						</td>
                       	<td class="notice-title"><%=rp.getReportTitle() %></td>
                        <td class="notice-id"><%=rp.getMember().getmId() %></td>
                        <tr><td colspan = "4"><hr></td></tr>
                        <tr><td colspan = "4">신고 당한 ID : <%=rp2.getMember().getmId() %></td></tr>
                        <tr class="notice-content">
                            <td colspan="4">
                                <div class="notice-content2">
                                <div class = "report-content">
                                   <%=rp.getReportContent() %>

								</div>
                                </div>
                            </td>
                        </tr>
                        <% if(rp.getReportCheck() != 'Y') {%>
                        <tr>
                        <td colspan="4">답변</td>
                        </tr>
                        <tr>
                        <td colspan="4">
                        <textarea rows="10" cols="110" style="resize: none;" name = "rReply"></textarea>
                        </td>
                        </tr>
                        <tr class = "notice-top">
                        <td colspan="2">
                        <input type = "radio" name = "check" id = "checkName1" value = "Y" style = "cursor: pointer;">
                        <label for = "checkName1" style = "cursor: pointer;">예</label>
                        </td>
                        <td colspan="2">
                        <input type = "radio" name = "check" id = "checkName" value = "N" style = "cursor: pointer;">
                        <label for = "checkName" style = "cursor: pointer;">아니오</label>
                        </td>
                        <%} else {%>
                        <tr>
                        <td colspan="4">답변</td>
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
                        <tr class = "notice-top">
                        <td colspan="1">
                        	결과
                        </td>
                        <td colspan="3">
                        
                        <%if(rp2.getMember().getmUse() != 'Y'){ %>
                        	이용정지 O
                        <%} else {%>
                        	이용정지 X
                        <% } %>	
                 
                        </td>
                        <%} %>
                        <td colspan="3">
                        </td>
                        </tr>
                </table>
                </form>
                <table class = "center1">
                    <tr class="null">
                    <td>
                    <button id ="seeMore" name = "seeMore"  onclick="back();">대기목록으로</button>
                    <% if(rp.getReportCheck() != 'Y') {%>
                    <input type = "submit" id ="seeMore" name = "seeMore" value = "처리하기" onclick = "memberBlack();">
                    <%} %>
                    </td>
                    </tr>
                </table>
                </div>
                </div>
            </div>
        </article>
    </section>
	<script>
		function memberBlack(){
    	
		var frm=$('#replyFrm');
		var url="<%=request.getContextPath()%>/admin/memberBlacklist?attNo=<%=rp.getmAttackerNum() %>";
		frm.attr("action",url);
		frm.submit();
		
		}
		
		function back()
		{
			var url="<%=request.getContextPath()%>/admin/AdminReportApproval.do";
			location.href=url;
		}
	</script>



<%@ include file="/views/common/adminFooter.jsp"%>