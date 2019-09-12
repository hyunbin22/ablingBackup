<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "/views/common/header.jsp" %>
    <%@ page import="com.semi.member.model.vo.Member" %>
    <%@ page import="com.semi.mento.model.vo.Mento" %>
    <%@ page import="com.semi.lecture.model.vo.Lecture" %>
    <%@ page import="java.util.*"%>
    
    <% 
       Mento mt = (Mento)session.getAttribute("loginMento");
       Member m = (Member) session.getAttribute("loginMember");
       String mtNum = (String) request.getAttribute("mtNum");
    %>
<section class="center1">

         <h1 class="center1">강의 만들기</h1>
         <br>
         <br>
         <hr>
      <div class="regdata center1">
         <form action="<%=request.getContextPath()%>/lecture/lectureEnrollEnd.do?mtNum=1" method="POST" enctype="multipart/form-data" >
            <div>수업제목</div>
            <input type="text" id="title" name="className" onclick="dataJson();">
            <br> <br> <br>
            <div>수업카테고리</div>
            <select name="maincategory" id="maincategory">
              <input type="text" name="maincategory">
            </select>
<!--              <select id="good" name="subcategory"> -->
               <input type="text" name="subcategory" placeholder="서브카테고리">
<!--             </select> <br> <br> <br> -->
            <div>수업형태</div>
            <input type="radio" name="classType" id="r2" value="그룹" checked><label
               for="r2">그룹수업</label> 
               <input type="text" name="studentCount"
               placeholder="수업정원을 입력하세요."> <input type="radio"
               name="classType" id="r1" value="1:1"><label for="r1">1:1수업</label>
            <br> <br>
             커버사진등록 <input type="file" name="file1"    accept="image/*" multiple /> <br> <br> <br>
            사진등록(최대3개)
            <table width="400" border="0" cellspacing="0" cellpadding="0">
               <tr>
                  <td colspan="2" align="left" bgcolor="#FFFFFF">
                     <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                           <td colspan="5" bgcolor="#FFFFFF" height="25" align="left">
                              <input name="addButton" type="button" style="cursor: hand"
                              onClick="insRow()" value="추가"> <font color="#FF0000">*</font>추가버튼을
                              클릭해 보세요.
                           </td>
                        </tr>
                        <br>
                        <tr>
                           <td height="25">
                              <table id="addTable" width="400" cellspacing="0"
                                 cellpadding="0" bgcolor="#FFFFFF" border="0">
                                 <tr>
                                    <br>
                                    <td>
                                    <input type="file" name="addImg"></td>
                                    <td align="left"></td>
                                 </tr>
                              </table>
                           </td>
                        </tr>
                     </table>
                  </td>
               </tr>
            </table>
            
            <input type="hidden" value=<%=1 %> name="mtNum">

            <br> <br>
            <div>멘토소개</div>
            <textarea cols="30" rows="10" name="mentoIntroduce"
               placeholder="내용을 입력해주세요."></textarea>
            <br> <br> <br>
            <div>강좌소개</div>
            <textarea cols="30" rows="10" name="classIntroduce"
               placeholder="내용을 입력해주세요."></textarea>
            <br> <br> <br>
            <div>시간당가격</div>
            <input type="text" name="price" id="hourPrice"> 원 <br>
            <br> <br>
            <div>기본수업시간</div>
            <select name="time" id="time">
               <option value="0">선택하세요</option>
               <option value="1">1시간</option>
               <option value="2">2시간</option>
               <option value="3">3시간</option>
               <option value="4">4시간</option>
               <option value="5">5시간</option>
               <option value="6">6시간</option>
            </select> /회 <br> <br> <br>
            <div>총수업(한달기준)</div>
            <input type="text" name="totaltime" id="totalTime"
               placeholder="예) 20회" onkeyup="total()">회 <br> <br>
            <br>
            <div>총가격</div>
            <div class="calc-total">
               <dl>
                  <dt>
                     <font id="calc-price"><span id="price2">0</span></font>원 X <font
                        id="calc-time"><span id="time2">0</span></font>시간 X <font
                        id="calc-totaltime"><span id="totalTime2">0</span></font>회
                  </dt>
                  <dd>
                     총 <font id="calc-result"><span id="totalPrice2">0</span></font>원
                  </dd>
                  <dd>
                     연결수수료 <font id="calc-fee"><span id="fee2">0</font>원
                  </dd>
               </dl>
            </div>
            <br>            
            
            <div>장소</div>
            <input name="local1" id="local1">
              
            <input id="local" name="local">
            <input type="text" name="local2" id="local2"
               placeholder="세부장소를 입력하세요."> <br> <br> <br>
            <div>가능 요일별 시간대(복수선택)</div>
            <br> <input type="checkbox" name="yo" value="월요일">월요일
            </option>
            <input type="checkbox" name="yo" value="화요일">화요일
            </option>
            <input type="checkbox" name="yo" value="수요일">수요일
            </option>
            <input type="checkbox" name="yo" value="목요일">목요일
            </option>
            <input type="checkbox" name="yo" value="금요일">금요일
            </option>
            <input type="checkbox" name="yo" value="토요일">토요일
            </option>
            <input type="checkbox" name="yo" value="일요일">일요일
            </option>

            <br> <br> <input type="text" name="day1"
               placeholder="ex>오후7시~오후9시"> <input type="radio"
               name="week1" id="f1" value="선택" checked><label for="f1"></label>
            <input type="date" name="month1" min='2019-01-01' max='2019-12-31' />
            <input type="radio" name="week1" id="f2" value="협의"><label
               for="f2">협의</label> <br> <input type="text" name="day2"
               placeholder="ex>오후7시~오후9시"> <input type="radio"
               name="week2" id="f3" value="선택" checked><label for="f3"></label>
            <input type="date" name="month2" min='2019-01-01' max='2019-12-31' />
            <input type="radio" name="week2" id="f4" value="협의"><label
               for="f4">협의</label> <br> <br> <br>
            <button type="submit" id="insert1" name="submit1">승인요청</button>
         </form>
      </div>

</section>
<script>

       //장르선택 카테고리
      $(function(){
         $("#maincategory").click(function(){
            $.ajax({
               url : "<%=request.getContextPath()%>/selectSubCategory?scNum=" + $('#maincategory').val(),
               type : "post",
               dataType : "html",
               success:function(data){
                  $("#good").find("option").remove();

                  var datas = data.split(",");
            
                       for(var i = 0; i < datas.length; i++){    
                          if(data==0){
                             $("#good").append("<option value='1'>과목선택</option>");
                          }else{
                       console.log(datas[i]);                        
                          $('<option value="' + (i+1) +'">' + datas[i] + '</option>').appendTo('#good');
                    }
                       }
               }
            });
         });
      });
             
       //기본수업시간선택
      $("#time").on("propertychange change keyup paste input",
            function() {
               var currentVal = $(this).val();
               var opt = document.getElementById("time2");
               opt.innerHTML=currentVal;
                        
      })
      
      //시간당가격입력
      $("#hourPrice").on("propertychange change keyup paste input",
            function() {
               var currentVal = $(this).val();
               var opt = document.getElementById("price2");
               opt.innerHTML=currentVal;
                        
      })
      
      //한달 기준 총수업
      $("#totalTime").on("propertychange change keyup paste input",
            function() {
               var currentVal = $(this).val();
               var opt = document.getElementById("totalTime2");
               opt.innerHTML=currentVal;
                        
      })
      
      //연결수수료
      $("#hourPrice").on("propertychange change keyup paste input",
            function() {
               var currentVal = $(this).val();
               var opt = document.getElementById("fee2");
               opt.innerHTML=currentVal;
                        
      })
      
      //총액계산
      function total() //total()함수 부분

      {
      
        var hourPrice1=document.getElementById("hourPrice"); //<body>부분에서 num1변수를 getElementById를 이용하여 값을 가져온다.
      
        var hourPrice1s=hourPrice1.value; // num1에서 가져온 값을 num1s에 저장해주고,
      
        var hourPrice1b=parseInt(hourPrice1s); // 계산을 하기위해 그 값을 int형으로 바꿔준다.
      
        var totalTime2=document.getElementById("totalTime"); //위 방법과 같음..
      
        var totalTime2s=totalTime2.value;
      
        var totalTime2b=parseInt(totalTime2s);   
      
        var time1=document.getElementById("time"); //역시 op값을 가져오고
      
        var times=time1.value;
        
        var time2b=parseInt(times);
        
        var totalPrice = hourPrice1b * time2b * totalTime2b;
        document.getElementById("totalPrice2").innerHTML=totalPrice;
      
   
      }
       
       //수업타입 라디오버튼
       $(document).ready(function(){
 
       // 라디오버튼 클릭시 이벤트 발생
       $("input:radio[name=classType]").click(function(){
    
           if($("input[name=classType]:checked").val() == "그룹수업"){
               $("input:text[name=studentCount]").attr("disabled",false);
               // radio 버튼의 value 값이 1이라면 활성화
    
           }else if($("input[name=classType]:checked").val() == "1:1수업"){
                 $("input:text[name=studentCount]").attr("disabled",true);
               // radio 버튼의 value 값이 0이라면 비활성화
           }
       });
      });
       
       
       //날짜선택 라디오 버튼11
      $(document).ready(function(){
      // 라디오버튼 클릭시 이벤트 발생
       $("input:radio[name=week1]").click(function(){
    
           if($("input[name=week1]:checked").val() == "선택"){
               $("input:text[name=day1]").attr("disabled",false);
               // radio 버튼의 value 값이 1이라면 활성화
    
           }else if($("input[name=week1]:checked").val() == "협의"){
                 $("input:text[name=day1]").attr("disabled",true);
               // radio 버튼의 value 값이 0이라면 비활성화
           }
       });
      });
       
      //날짜선택 라디오 버튼11
      $(document).ready(function(){
      // 라디오버튼 클릭시 이벤트 발생
       $("input:radio[name=week1]").click(function(){
    
           if($("input[name=week1]:checked").val() == "선택"){
               $("input:date[name=month1]").attr("disabled",false);
               // radio 버튼의 value 값이 1이라면 활성화
    
           }else if($("input[name=week1]:checked").val() == "협의"){
                 $("input:date[name=month1]").attr("disabled",true);
               // radio 버튼의 value 값이 0이라면 비활성화
           }
       });
      });
       
        //날짜선택 라디오 버튼22
      $(document).ready(function(){
      // 라디오버튼 클릭시 이벤트 발생
       $("input:radio[name=week2]").click(function(){
    
           if($("input[name=week2]:checked").val() == "선택"){
               $("input:text[name=day2]").attr("disabled",false);
               // radio 버튼의 value 값이 1이라면 활성화
    
           }else if($("input[name=week2]:checked").val() == "협의"){
                 $("input:text[name=day2]").attr("disabled",true);
               // radio 버튼의 value 값이 0이라면 비활성화
           }
       });
      });
        
      //장르선택 카테고리
         $(function(){
            $("#local1").click(function(){
               $.ajax({
                  url : "<%=request.getContextPath()%>/selectSubLocal?localNum=" + $('#local1').val(),
                  type : "post",
                  dataType : "html",
                  success:function(data){
                     $("#local").find("option").remove();
   
                     var datas = data.split(",");
               
                          for(var i = 0; i < datas.length; i++){    
                             if(data==0){
                                $("#local").append("<option value='1'>지역선택</option>");
                             }else{
                          console.log(datas[i]);                        
                             $('<option value="' + (i+1) +'">' + datas[i] + '</option>').appendTo('#local');
                       }
                          }
                  }
               });
            });
         });
      
               
               var oTbl;
               //Row 추가
               function insRow() {
                 oTbl = document.getElementById("addTable");
                 var oRow = oTbl.insertRow();
                 oRow.onmouseover=function(){oTbl.clickedRowIndex=this.rowIndex}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
                 var oCell = oRow.insertCell();

                 //삽입될 Form Tag
                 var frmTag = "<input type=file name=addImg style=width:350px; height:20px;> ";
                 frmTag += "<input type=button value='삭제' onClick='removeRow()' style='cursor:hand'>";
                 oCell.innerHTML = frmTag;
               }
               //Row 삭제
               function removeRow() {
                 oTbl.deleteRow(oTbl.clickedRowIndex);
               }

               function frmCheck()
               {
                 var frm = document.form;
                 
                 for( var i = 0; i <= frm.elements.length - 1; i++ ){
                    if( frm.elements[i].name == "addImg" )
                    {
                        if( !frm.elements[i].value ){
                                frm.elements[i].focus();
                   return;
                         }
                     }
                  }
                }
            </script>
    


    <%@ include file = "/views/common/footer.jsp" %>