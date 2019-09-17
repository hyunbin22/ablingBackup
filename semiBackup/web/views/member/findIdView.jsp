<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<% Member m = (Member)request.getAttribute("member"); %>

<section class = "center1">
            <div class="wrap">
                <div class="bar">
                    <br>
                    <h1 style="font-size: 40px; text-align: center;">아이디 찾기</h1>
                    <hr>
                    <br><br>
                </div>
                <div class="center1">
                 <table id = "list" border="1">
                    
                            <tr class = "listName">
                                   
                                    <td class = "listContent">결과</td>
                                </tr>
                        <tr class = "list">

                            <td class = "listContent"><span><%=m.getmId() %></span></td>

                        </tr>
                    
                    </table>
                    <br><br>
  					<div class = "center1">
                            <a href="<%=request.getContextPath()%>/"><button class = "next">메인화면으로</button></a>
                    </div>
                </div>
            </div>
        </section>
        
<%@ include file="/views/common/footer.jsp"%>