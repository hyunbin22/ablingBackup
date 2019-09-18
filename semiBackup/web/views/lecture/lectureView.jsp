<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.semi.lecture.model.vo.Lecture"%>
<%@ page import="com.semi.lecture.model.vo.LectureReview"%>
<%@ page import="java.util.List"%>
<%
   Lecture lec = (Lecture) request.getAttribute("lecture");
   List<LectureReview> list = (List) request.getAttribute("list");
%>



<%@ include file="/views/common/header.jsp"%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/classdetail.css">
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout.css"> --%>

<section class="center">
   <div class="wrap">
      <article class="detailwrap">
         <img src="사이즈500_500.png" class="detailimg" alt="...">
         <!--card-img-top -->
         <div class="detailbody">
            <div class="class_info">
               <h5 class="" name="lectype">
                  [
                  <%=lec.getLecType()%>
                  ]
               </h5>
               <h5 class="title"><%=lec.getLecName()%></h5>

               <table class="detailbar">
                  <tr>
                     <td><a href="#pinfo">프로필</a></td>
                     <td><a href="#cinfo">수업</a></td>
                     <td><a href="#rinfo">리뷰</a></td>
                     <td><a href="#qinfo">질문</a></td>
                  </tr>
               </table>
               <script>
                  $(window).scroll(function() {
                     if ($(window).scrollTop() > 1609) {
                        $('.detailbar').addClass("detailbar2");
                        $('.detailbar2').removeClass("detailbar");

                     } else {
                        $('.detailbar2').addClass("detailbar");
                        $('.detailbar').removeClass("detailbar2");

                     }
                  });
               </script>
               <table border="1" class="proinfo" id="pinfo">
                  <tr>
                     <td colspan="2">
                        <h2 class="subtext">튜터프로필</h2>
                     </td>

                  </tr>
                  <tr>
                     <td><img src=""></td>
                     <td>
                        <ul class="tutorpro" name="lecmentocontent">
                           <%=lec.getLecMentoContent()%>
                        </ul>
                     </td>
                  </tr>
               </table>
               <hr>
               <p class="content" name="leclecturecontent"><%=lec.getLecLectureContent()%></p>

               <!-- <h2 class="subtext">커리큘럼</h2>
                            <ul>
                                <li>1회차 : 아이라이너 테크닉</li>
                                <li>2회차 : 립스틱 테크닉</li>
                                <li>2회차 : 립스틱 테크닉</li>
                                <li>2회차 : 립스틱 테크닉</li>
                                <li>2회차 : 립스틱 테크닉</li>
                                <li>2회차 : 립스틱 테크닉</li>
                                <li>2회차 : 립스틱 테크닉</li>
                            </ul> -->
               <hr>


               <!-- 리뷰화면 -->


               <!-- 리뷰 댓글창 -->
               <p class="reviewinfo" id="rinfo">
               <form
                  action="<%=request.getContextPath()%>/LectureReview/reviewWrite"
                  method="post">
                  <input type="hidden" name="rNum"> <input type="hidden"
                     name="lecNum" value="<%=lec.getLecNum()%>"> <input
                     type="hidden" name="mNum"
                     value="<%=memberLogin != null ? memberLogin.getmNum() : ""%>">
                  <textarea name="rTitle" rows="1" cols="60"></textarea>
                  <textarea name="rText" rows="5" cols="60"></textarea>

                  <button type="submit">등록</button>

               </form>

               <script>
                  $(function() {
                     $("textarea[name=rTitle]").focuse(function() {
                        if (
               <%=memberLogin == null%>
                  ) {
                           alert("로그인해주시기 바랍니다.");
                        }
                     });
                  });
                  $(function() {
                     $("textarea[name=rText]").focuse(function() {
                        if (
               <%=memberLogin == null%>
                  ) {
                           alert("로그인해주시기 바랍니다.");
                        }
                     });
                  });
               </script>

               <%
                  if (list != null && !list.isEmpty()) {
                     for (LectureReview lr : list) {
                        if (memberLogin != null
                              && (lr.getmNum() == memberLogin.getmNum() || memberLogin.getmId().equals("admin"))) {
               %>

               <div class="review">
                  <div class="memprofile">
                     <table class="reviewtable">
                        <tr>
                           <td class="reviewId">수강생<br> <b><%=lr.getmNum() %></b>님
                              <small>3.3/5.0</small>
                           </td>

                           <td class="reviewContent"><%=lr.getrTitle() %>
                              <hr> <%=lr.getrText() %></td>
                              
                           <td>
                              <%if(memberLogin!=null
                              &&("admin".equals(memberLogin.getmId())/*&& 리뷰작성자와 동일한 멤버로그인아이디일경우 */
                              )) {%>
                              <button class="btn-delete"
                                 value="<%=lr.getLecnum() %>">삭제</button> <%} %>
                           </td>
                        </tr>

                     </table>


                  </div>
                  <!-- <div class="review_content">
                                    <input type="text">
                                    <input type="submit">
                                </div> -->

               </div>

               <%
                        }
                     }
                  }
               %>

               </p>
               <p class="questioninfo" id="qinfo"></p>
            </div>
         </div>
      </article>

   </div>
   <!-- <div id="floatMenu" onload="floatMenu"> 내용 입력 </div> -->

</section>

<aside class="center">
   <form action="<%=request.getContextPath()%>/lecturePayment"
      method="post" enctype="multipart/form-data">
      <div class="wrap">
         <div class="floatMenu">
            <div class="floatTitle">
               결제
               <hr>
            </div>
            <div class="floatsubtitle">수업시간</div>
            <div id="select_box">
               <label for="color"><%=lec.getLecTot()%> ~ <%=lec.getLecTot2()%></label>
               <!-- <select id="color" title="select color">
                        <option selected="selected">asdf</option>
                    </select> -->
            </div>
            <div class="floatsubtitle">총 수업 횟수</div>
            <div id="select_box">
               <label for="color">한달 <%=lec.getLecCount()%>회
               </label>
               <!-- <select id="color" title="select color">
                        <option selected="selected">asdf</option>
                        <option>asdf</option>
                        <option>asdf</option>
                        <option>asdf</option>
                        <option>asdf</option>
                    </select> -->
            </div>
            <div class="floatsubtitle">1회 시간</div>
            <div id="select_box">
               <label for="color"><%=lec.getLecTime()%></label>

            </div>
            <div class="floatsubtitle">요일</div>
            <div id="select_box">
               <label for="color"><%=lec.getLecWeek()%></label>

            </div>
            <div class="floatsubtitle">장소</div>
            <div id="select_box">
               <label for="color"><%=lec.getLecMeet()%></label>

            </div>
            <div class="floatsubtitle">가격</div>
            <div id="select_box">
               <label for="color"><%=lec.getLecPrice()%></label>

            </div>
            <script>
               $(function() {
                  var select = $("select#color");

                  select.change(function() {
                     var select_name = $(this).children(
                           "option:selected").text();
                     $(this).siblings("label").text(select_name);
                  });
               });
            </script>
            <div>
               <!-- <input type=""> -->
               <input type="submit" value="확인" class="classSubmit">
            </div>

         </div>
      </div>

   </form>
</aside>

<script>
   $(window).scroll(function() {
      if ($(window).scrollTop() > 371) {
         $('.floatMenu').addClass("fix");
         $('.floatMenu').addClass("right");
         $('.floatMenu').removeClass("floatMenu");

      } else {
         $('.fix').addClass("floatMenu");
         $('.fix').removeClass("fix");

      }
   });
</script>

<script src="js/bootstrap.js"></script>


<%@ include file="/views/common/footer.jsp"%>