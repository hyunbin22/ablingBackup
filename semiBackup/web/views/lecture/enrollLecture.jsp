<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "/views/common/header.jsp" %>
    <%@ page import="com.semi.member.model.vo.Member" %>
    <%@ page import="com.semi.mento.model.vo.Mento" %>
    <%@ page import="java.util.*, com.semi.category.model.vo.Category, com.semi.subcategory.model.vo.SubCategory, com.semi.local.model.vo.Local, com.semi.sublocal.model.vo.SubLocal"%>
    
    <% 
    	Mento mt = (Mento)session.getAttribute("loginMento");
    	Member m = (Member) session.getAttribute("loginMember");
    	String mtNum = (String) request.getAttribute("mtNum");
    	List<Category> cList=(List)request.getAttribute("category");
    	List<SubCategory> scList=(List)request.getAttribute("scList");
    	List<Local> lList=(List)request.getAttribute("local");
    	List<SubLocal> slList = (List)request.getAttribute("slList");
    %>
<section class="center1">
	<div class="wrap">
		<div class="bar">
			<br>
			<table class = "MYLIST">
                                <tr>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/member/memberMyPage.do?mId=<%=m.getmId()%>'" id = "listBtn">마이페이지</button></td>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/member/mypageModify.do?mId=<%=m.getmId()%>'" id = "listBtn">내정보수정</button></td>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/member/studyList.do?mNum=<%=m.getmNum()%>'" id = "listBtn">신청한강의</button></td>
                                    <td><button onclick="location.href='LIKELIST.html'" id = "listBtn">즐겨찾기목록</button></td>
                                    <%if(mt != null && m.getmNum() == mt.getmNum()) { %>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/mento/enrollLecture.do?mtNum=<%=mt.getMtNum()%>'" id = "listBtn">강의만들기</button></td>
                                    <%} else { %>
                                    <td><button onclick="location.href='<%=request.getContextPath()%>/mento/mentoRegister.do?mId=<%=m.getmId()%>'" id = "listBtn">멘토신청하기</button></td>    
                                	<%} %>
                                	
                                </tr>
                        </table>
			<br>
			<br>
			<h1 class="center1">강의 만들기</h1>
			<br>
			<br>
			<hr>
		</div>
		<div class="regdata center1">
			<form action="<%=request.getContextPath()%>/lectureEnrollEnd">
				<div>수업제목</div>
				<input type="text" id="title" name="className" onclick="dataJson();">
				<br> <br> <br>
				<div>수업카테고리</div>
				<select name="maincategory" id="maincategory">
					<option value="0">카테고리선택</option>
					<%
						for (Category c : cList) {
					%>
					<option value="<%=c.getScNum()%>"><%=c.getScName()%></option>
					<%
						}
					%>
				</select> <select id="good" name="subcategory">
					<option>과목선택</option>
				</select> <br> <br> <br>
				<div>수업형태</div>
				<input type="radio" name="classType" id="r2" value="그룹수업" checked><label
					for="r2">그룹수업</label> <input type="text" name="studentCount"
					placeholder="수업정원을 입력하세요."> <input type="radio"
					name="classType" id="r1" value="1:1수업"><label for="r1">1:1수업</label>
				<br> <br> 커버사진등록 <input type="file" name="file1"
					accept="image/*" multiple /> <br> <br> <br>
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
												<td><input type="file" name="addImg"></td>
												<td align="left"></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>


				<br> <br> <br>
				<div>멘토소개</div>
				<textarea id="" cols="30" rows="10" name="mentoIntroduce"
					placeholder="내용을 입력해주세요."></textarea>
				<br> <br> <br>
				<div>강좌소개</div>
				<textarea id="" cols="30" rows="10" name="classIntroduce"
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
				<select name="local1" id="local1">
					<option value = '0'>지역</option>
					<%
						for (Local l : lList) {
					%>
					<option value="<%=l.getLocalNum()%>"><%=l.getLocalCity()%></option>
					<%
						}
					%>
				</select> <select id="subLocal" name="local">
					<option id="si">선택</option>
				</select> <br> <br>
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
                                            
                          $('<option value="' + (i+1) +'">' + datas[i] + '</option>').appendTo('#good');
                          
                    }
                       }
               }
            });
         });
      });
       
		//서브카테고리 value
        function checkValue(){
			console.log($('#good').val());
		}
		
		
		
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


              /*  //지역선택 카테고리
               function categoryChange2(ee) {
                  var local_a = [ "춘천시", "원주시", "강릉시", "동해시", "태백시",
                        "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군",
                        "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군" ];
                  var local_b = [ "수원시","성남시","의정부시", "안양시",
                        "부천시", "광명시", "평택시", "동두천시", "안산시","고양시","과천시", "구리시", "남양주시", "오산시", "시흥시",
                        "군포시", "의왕시", "하남시", "용인시", "파주시", "이천시", "안성시",
                        "김포시", "화성시", "광주시", "양주시", "포천시", "여주시",
                        "연천군", "가평군", "양평군" ];
                  var local_c = [ "창원시",  "진주시",
                        "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시",
                        "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군",
                        "산청군", "함양군", "거창군", "합천군" ];
                  var local_d = [ "포항시", "경주시",
                        "김천시", "안동시", "구미시", "영주시", "영천시", "상주시",
                        "문경시", "경산시", "군위군", "의성군", "청송군", "영양군",
                        "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군",
                        "봉화군", "울진군", "울릉군" ];
                  var local_e = [ "동구", "서구", "남구", "북구", "광산구" ];
                  var local_f = [ "중구", "동구", "서구", "남구", "북구", "수성구",
                        "달서구", "달성군" ];
                  var local_g = [ "동구", "중구", "서구", "유성구", "대덕구" ];
                  var local_h = [ "종로구", "중구", "용산구", "성동구", "광진구",
                        "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구",
                        "은평구", "서대문구", "마포구", "양천구", "강서구", "구로구",
                        "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구",
                        "송파구", "강동구" ];      
                  var local_j = [ "중구", "남구", "동구", "북구", "울주군" ];
                  var local_k = [ "중구", "동구", "미추홀구", "연수구", "남동구",
                        "부평구", "계양구", "서구", "강화군", "옹진군" ];
                  var local_l = [ "목포시", "여수시", "순천시", "나주시", "광양시",
                        "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군",
                        "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군",
                        "영광군", "장성군", "완도군", "진도군", "신안군" ];
                  var local_m = [ "전주시", "군산시",
                        "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군",
                        "무주군", "장수군", "임실군", "순창군", "고창군", "부안군" ];
                  var local_n = [ "제주시", "서귀포시" ];
                  var local_o = [ "중구", "서구", "동구", "영도구", "부산진구", "동래구",
                        "남구", "북구", "해운대구", "사하구", "금정구", "강서구", "연제구",
                        "수영구", "사상구", "기장군" ];
                  var local_p = [ "천안시", "공주시",
                        "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시",
                        "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군" ];
                  var local_q = [ "청주시", "충주시", "제천시", "보은군", "옥천군", "영동군",
                        "증평군", "진천군", "괴산군", "음성군", "단양군" ];

                  var target = document.getElementById("local");

                  if (ee.value == "강원") {
                     var dd = local_a;
                  } else if (ee.value == "경기") {
                     var dd = local_b;
                  } else if (ee.value == "경남") {
                     var dd = local_c;
                  } else if (ee.value == "경북") {
                     var dd = local_d;
                  } else if (ee.value == "광주") {
                     var dd = local_e;
                  } else if (ee.value == "대구") {
                     var dd = local_f;
                  } else if (ee.value == "대전") {
                     var dd = local_g;
                  } else if (ee.value == "서울") {
                     var dd = local_h;
                  } else if (ee.value == "울산") {
                     var dd = local_j;
                  } else if (ee.value == "인천") {
                     var dd = local_k;
                  } else if (ee.value == "전남") {
                     var dd = local_l;
                  } else if (ee.value == "전북") {
                     var dd = local_m;
                  } else if (ee.value == "제주") {
                     var dd = local_n;
                  } else if (ee.value == "부산") {
                     var dd = local_o;
                  } else if (ee.value == "충남") {
                     var dd = local_p;
                  } else if (ee.value == "충북") {
                     var dd = local_q;
                  }

                  target.options.length = 0;

                  for (x in dd) {
                     var opt = document.createElement("option");
                     opt.value = dd[x];
                     opt.innerHTML = dd[x];
                     target.appendChild(opt);
                  }
               } */
               
             //서브 지역 카테고리
               $(function(){
                  $("#local1").click(function(){
                     $.ajax({
                        url : "<%=request.getContextPath()%>/selectSubLocal?localNum=" + $('#local1').val(),
                        type : "post",
                        dataType : "html",
                        success:function(data){
                           $("#subLocal").find("option").remove();
							console.log(data);
                           var datas = data.split(",");
                     
                                for(var i = 0; i < datas.length; i++){    
                                   if(data==0){
                                      $("#subLocal").append("<option value='1'>시/구/군 선택</option>");
                                   }else{
                                                     
                                   $('<option value="' + (i+1) +'">' + datas[i] + '</option>').appendTo('#subLocal');
                                   
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