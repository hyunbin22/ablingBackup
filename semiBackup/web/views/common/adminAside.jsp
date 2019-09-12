<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <aside class="center">
        <div class="wrap">
            <div class="admin-floatMenu" >
                <div class="admin-floatTitle">
                    	<h3>승인관리</h3>
                    <hr>
                </div>
                <ul class="admin-aside-menu">
                	<li><a href="<%=request.getContextPath()%>/admin/AdminMentoApproval.do" class="appro-aTag">멘토승인신청목록</a></li>
                	<li><a href="<%=request.getContextPath()%>/admin/AdminLectureApproval.do" class="appro-aTag">강의승인신청목록</a></li>
                	<li><a href="<%=request.getContextPath()%>/admin/AdminMentoNoApproval.do" class="appro-aTag">멘토승인거절목록</a></li>
                	<li><a href="<%=request.getContextPath()%>/admin/AdminLectureNoApproval.do" class="appro-aTag">강의승인거절목록</a></li>
                </ul>            
            </div>
        </div>

    </aside>
