<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/adminHeader.jsp"%>
<%@ page
	import="com.semi.member.model.vo.Member, com.semi.mento.model.vo.Mento, java.util.*"%>
<%
	List<Mento> list = (List) request.getAttribute("mentoApproList");
	int cPage = (int)request.getAttribute("cPage");
	String searchType = (String)request.getAttribute("searchType");
	String searchKey = (String)request.getAttribute("searchKeyword");

%>
<section>

		<article id="search1">
		<div id="admin-Mento-Search-container">
			<select id="searchType">
				<option value="mId" <%="mId".equals(searchType)?"selected":"" %>>아이디</option>
				<option value="mName" <%="mName".equals(searchType)?"selected":"" %>>이름</option>
			</select> 
			<div id="search-mId">
				<form action="<%=request.getContextPath()%>/admin/mentoApproFinder.do">
					<input type="hidden" name="searchType" value="mId">
					<input type="hidden" name="cPage" value="<%=cPage%>"> 
					<input type="text" name="searchKeyword" placeholder="검색어 입력">
					<button type="submit">검색</button>
				</form>
			</div>
	 		<div id="search-mName">
				<form action="<%=request.getContextPath()%>/admin/mentoApproFinder.do">
					<input type="hidden" name="searchType" value="mName">
					<input type="hidden" name="cPage" value="<%=cPage%>"> 
					<input type="text" name="searchKeyword" placeholder="이름 입력"
						value='<%="mName".equals(searchType)?searchKey:""%>'>
					<button type="submit">검색</button>
				</form>
			</div> 
		</div>
	</article>
	<article class="admin-list-container wrap">
		<div class="row">
			<div class="col">
				<h3 class="admintitle">멘토승인거절목록</h3>
				<div class="tab-content">
					<div class="tab-pane fade show active" id="mentoAppro">
						<div class="card appro-frm-wrap">
							<%
							for (int i = 0; i < list.size(); i++) {
								if(list.get(i).getMtReason()!=null) {
							%>
							<div class="mentoAppro-frm" style="height: 280px">
								<!-- 멘토승인신청목록 -->
								<div class="card-header mtAppro-name"><%=list.get(i).getMember().getmName()%>
									(<%=list.get(i).getMember().getmId()%>)
								</div>
								<div class="card-body">
									<table class="tbl-appro">
										<tr>
											<td><h5 class="card-title">멘토신청합니다.</h5></td>
										</tr>
										<tr>
											<td rowspan="2"><img
												src="<%=request.getContextPath()%>/upload/mento/<%=list.get(i).getList().get(i).getUpMentoReProfile() %>"
												class="approImg"></td>
											<td><p class="approDate"><%=list.get(i).getMtaDate()%></p></td>
											<td>
												<button type="submit" class="btn btn-primary btn-appro-view"
													onclick="location.href='<%=request.getContextPath()%>/admin/AdminMentoDetailServlet.do?mtNum=<%=list.get(i).getMtNum()%>'">
													More</button>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<%
								}
							}
								
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="admin-appro-pageBar">
			<%=request.getAttribute("pageBar")%>
		</div>

	</article>

	<%@ include file="/views/common/adminAside.jsp"%>

</section>
<%@ include file="/views/common/adminFooter.jsp"%>