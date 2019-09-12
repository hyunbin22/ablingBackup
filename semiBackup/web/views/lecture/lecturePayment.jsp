<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.semi.lecture.model.vo.Lecture" %>
<%
	Lecture lec = (Lecture)request.getAttribute("lecture"); 
%>

<section class="center1">
	<article>
		<div id="class-pay-container">
			<div class="card mb-3 center1" style="max-width: 800px;">
				<div class="row no-gutters">
					<div class="col-md-4">
						<!-- <img src="..." class="card-img" alt="..."> 프로필사진-->
						<h1>튜터이름</h1>
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h2 class="card-title"><%=lec.getLecName() %></h2>
							<div class="class-pay-table-wrap">
								<form action="" id="class-pay-frm" name="class-pay-frm">
									<table id="class-pay-tbl">
										<tr>
											<th>수업횟수</th>
											<td><%=lec.getLecCount() %>회</td>
										</tr>
										<tr>
											<th>수업시간</th>
											<td><%=lec.getLecTime() %>시간</td>
										</tr>
										<tr>
											<th>가격(1회 수업 금액)</th>
											<td><%=lec.getLecPrice() %></td>
										</tr>
										<tr>
											<th>이벤트할인</th>
											<td>0</td>
										</tr>
										<tr>
											<th>총결제금액</th>
											<td><%=lec.getLecPrice()*lec.getLecTime() %></td>
										</tr>
										<tr>
											<th>결제수단</th>
											<td><label><input type="radio" name="kakaopay">카카오페이</label></td>
										</tr>
										<tr>
											<td colspan='2'>
												   <button onclick="fn_iampay();">카카오페이로 결제하기</button>
											</td>
										</tr>

									</table>

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</article>
</section>
<script>
    function fn_iampay(){
        var IMP = window.IMP; // 생략가능
        IMP.init('imp65013128'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : 'ABLING 수업 결제',
            amount : <%=lec.getLecPrice()*lec.getLecTime() %>,	//결제금액
            buyer_email : '<%=lec.getLecMento().getMember().getmEmail()%>',	
            buyer_name : '<%=lec.getLecMento().getMember().getmName()%>',
            buyer_tel : '<%=lec.getLecMento().getMember().getmPhone()%>',
            buyer_postcode : '123-456',
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
                });
                //성공시 이동할 페이지
                location.href='<%=request.getContextPath()%>/order/paySuccess?msg='+msg;
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                location.href="<%=request.getContextPath()%>/order/payFail";
                alert(msg);
            }
        });
        
    });
    </script>

<%@ include file="/views/common/footer.jsp"%>