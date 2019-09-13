<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/adminHeader.jsp"%>
<%@ page import="com.semi.lecture.model.vo.Lecture" %>
<% 
	Lecture lec = (Lecture)request.getAttribute("lecture");
	int temp = (int)request.getAttribute("temp");

%>

<section>
	<article class="admin-mento-detailwrap">
		<div class="admin-mento-detailwrap">
		<br><br><h1>강의 상세보기</h1>
		<div class="admin-detail-frm-wrap">
			<form id="admin-mento-detail-frm" method="post" onsubmit="return checkAppro();">
			<input type="hidden" name="lecNum" id="lecNum" value="<%=lec.getLecNum()%>">
			<br>
			<%for(int i = 0; i < lec.getLectureUpList().size(); i++) {
				if(lec.getLectureUpList().get(i).getUpLectureCategory().equals("cover")) {%>
			<img src="<%=request.getContextPath()%>/upload/lecture/<%=lec.getLectureUpList().get(i).getUpLectureReName() %>" class="lecture-cover-img" alt="...">
			<%}
			}%>
			<div class="detailbody">
				<div class="class_info">
					<h5 class="title"><%=lec.getLecName() %></h5>
					<table class="detailbar">
						<tr>
							<td>프로필</td>
							<td>수업</td>
						</tr>
					</table>
					<p class="proinfo">
						<h2>멘토소개</h2>
						<p>
							<%=lec.getLecMentoContent() %>
						</p>
					</p>
					<hr>
					<p class="classinfo">
						<h2>강의사진</h2>
						<p>
							<%for(int i = 0; i < lec.getLectureUpList().size(); i++) {
								if(lec.getLectureUpList().get(i).getUpLectureCategory().equals("lecimage")) {%>
							<img src=<%=request.getContextPath() %>/upload/lecture/<%=lec.getLectureUpList().get(i) %>>
							<%}
							}%>
						</p>
					</p>
					<p class="classinfo">
						<h2>커리큘럼</h2>
						<p>
							<%=lec.getLecLectureContent() %>
						</p>
					</p>
					<hr>
					<%if(temp==2) {%>
						<p class="refusalinfo">
							<h2>거절사유</h2>
							<p>
								<%=lec.getLecReason() %>
							</p>
						</p>
					<%} %>
				<button class="mentosubmit" onclick="sendMessage">문의하기</button>
				</div>
			</div>
			<%if(temp!=0) {%>
				<button type="submit" class="mentosubmit" id="btnclassAppro">승인</button>
				<button class="mentosubmit" id="btnclassRefusal" onclick="popOpen();">거절</button>
			<%} %>
			</form>
	
			<form name="saveRefusalFrm" method="post">
				<input type="hidden" name="lecNum" id="inputlecNum">
			</form>
			</div>
		</div>
		</article>
		<%if(temp!=0) {%>
	<%@ include file="/views/common/adminAside.jsp" %>
	<%} %>

</section>
	<script>
		$(function(){
			<%if(temp==0) {%>
				$('#admin-mento-wrap').css('float','none');
				$('#admin-mento-wrap').addClass('center1');
				
			<%} else {%>
				$('#btnclassAppro').addClass('mentosubmit');
				$('#btnclassAppro').addClass('mentosubmit');
				
			<%}%>
		});
        function popOpen(){
    		var lecNum = $("#lecNum").val().trim();
    		var url = "<%=request.getContextPath()%>/lecture/lectureRefusalView.do?lecNum="+lecNum;
    		var status = "width=500, height=500, resizable=no, scrollbars=no, status=no;";
    		var title="거절상세"
    		var popUp = open("", title, status);
    		window.name="parentWin";
    		saveRefusalFrm.lecNum.value = lecNum;
    		saveRefusalFrm.target = title;
    		saveRefusalFrm.action=url;
    		saveRefusalFrm.submit();
		};

		$('#btnclassAppro').click(function() {
			if(confirm('승인하시겠습니까?')){
				var lecNum = <%=lec.getLecNum()%>;
				var url="<%=request.getContextPath()%>/lecture/updateCheckLecture.do?lecNum="+lecNum;
				location.href=url;
			}
		});
		
		$('#sendMessage').click(function(){
			var url = "<%=request.getContextPath()%>/message/openMessageView.do";
			var status = "width=500, height=700, resizable=no, scrollbars=yes, status=no;";
			var title="메세지"
			var popUp = open("", title, status);
			window.name="parentWin";
			saveRefusalFrm.target = title;
			saveRefusalFrm.action=url;
			saveRefusalFrm.submit();
		});
	</script>
<%@ include file="/views/common/adminFooter.jsp"%>