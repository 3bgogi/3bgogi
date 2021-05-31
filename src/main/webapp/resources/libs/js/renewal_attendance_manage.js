jQuery(document).ready(function($) {
	 var today = null;
	    var year = null;
	    var month = null;
	    var firstDay = null;
	    var lastDay = null;
	    var $tdDay = null;
	    var $tdSche = null;
	    var jsonData = null;

	    drawCalendar();
	    initDate();
	    drawDays();
	    drawSche();
	    $("#movePrevMonth").on("click", function(){movePrevMonth();});
	    $("#moveNextMonth").on("click", function(){moveNextMonth();});
	    
	    //Calendar 그리기
	    function drawCalendar(){
	        var setTableHTML = "";
	        setTableHTML+='<table class="calendar" style="width:100%;">';
	        setTableHTML+='<tr style="text-align: center;"><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>';
	        for(var i=0;i<6;i++){
	            setTableHTML+='<tr height="100">';
	            for(var j=0;j<7;j++){
	                setTableHTML+='<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
	                setTableHTML+='    <div class="cal-day"></div>';
	                setTableHTML+='    <div class="cal-schedule"></div>';
	                setTableHTML+='</td>';
	            }
	            setTableHTML+='</tr>';
	        }
	        setTableHTML+='</table>';
	        $("#cal_tab").html(setTableHTML);
	    }
	    
	    //날짜 초기화
	    function initDate(){
	        $tdDay = $("td div.cal-day")
	        $tdSche = $("td div.cal-schedule")
	        dayCount = 0;
	        today = new Date();
	        year = today.getFullYear();
	        month = today.getMonth()+1;
	        if(month < 10){month = "0"+month;}
	        firstDay = new Date(year,month-1,1);
	        lastDay = new Date(year,month,0);
	    }
	    
	    //calendar 날짜표시
	    function drawDays(){
	        $("#cal_top_year").text(year);
	        $("#cal_top_month").text(month);
	        for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
	            $tdDay.eq(i).text(++dayCount);
	        }
	        for(var i=0;i<42;i+=7){
	            $tdDay.eq(i).css("color","red");
	        }
	        for(var i=6;i<42;i+=7){
	            $tdDay.eq(i).css("color","blue");
	        }
	    }
	    
	    //calendar 월 이동
	    function movePrevMonth(){
	        month--;
	        if(month<=0){
	            month=12;
	            year--;
	        }
	        if(month<10){
	            month=String("0"+month);
	        }
	        getNewInfo();
	        }
	    
	    function moveNextMonth(){
	        month++;
	        if(month>12){
	            month=1;
	            year++;
	        }
	        if(month<10){
	            month=String("0"+month);
	        }
	        getNewInfo();
	    }
	    
	    //정보갱신
	    function getNewInfo(){
	        for(var i=0;i<42;i++){
	            $tdDay.eq(i).text("");
	            $tdSche.eq(i).text("");
	        }
	        dayCount=0;
	        firstDay = new Date(year,month-1,1);
	        lastDay = new Date(year,month,0);
	        drawDays();
	        drawSche();
	    }
	    
	    //2019-08-27 추가본
	    
	    //데이터 등록
	    function setData(){
	    	var adminPk = $("input[name=adminFk]").val();
	    	
	    	$.ajax({
			    type       : 'GET',
			    data       : {
			    	"formatYear":year,
			    	"formatMonth":month,
			    	"adminPk":adminPk
			    },
			    url        : '/admin/team/ajax_admin_breaks.do',
			    success    : function(data){
			    	jsonData = data;
			    	console.log(data);
			    	return data;
			    }
			    
			});
	    	
	        
	    }
	    
	    //스케줄 그리기
	    function drawSche(){
	    	var adminPk = $("input[name=adminFk]").val();
	    	
	    	$.ajax({
			    type       : 'GET',
			    data       : {
			    	"formatYear":year,
			    	"formatMonth":month,
			    	"adminPk":adminPk
			    },
			    url        : '/admin/team/ajax_admin_breaks.do',
			    success    : function(data){
			    	var counting = 0;
			    	var totalHours = 0;
			    	var totalMinutes = 0;
			    	for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
			            var txt = null;
			            
			            txt=data[counting];
			            
			            if(txt){
			            	dateMatch = firstDay.getDay() + counting;
			            	if(txt.dcDate > new Date()){
			            		
			            	}else{
			            		//휴무 타입이 없을 경우
			            		if(txt.adType == 0){			            		
			            			
			            			// 출근 기록이 없을 경우
			            			if(txt.aaWorkStart == null){
			            				$tdSche.eq(dateMatch).html("근태 기록이 없습니다");
			            				
			            			//출근 기록이 존재할 경우
			            			}else{
			            				//$tdSche.eq(dateMatch).html(""+"출근시각 : "+formatDate_HH_MM(txt.aaWorkStart)+"<br>퇴근시각 : "+formatDate_HH_MM(txt.aaWorkEnd)+"<br>근무 시간 : "+txt.workTime+"<br> 휴게시간 : "+txt.aaBreakTime+"분<br><button name='insertTest' class='btn btn-primary btn-xs' value="+txt.aaPk+">수정하기</button>");
			            				$tdSche.eq(dateMatch).html(""+"출근시각 : "+formatDate_HH_MM(txt.aaWorkStart)+"<br>퇴근시각 : "+formatDate_HH_MM(txt.aaWorkEnd)+"<br>근무 시간 : "+txt.workTime+"<br> 휴게시간 : "+txt.aaBreakTime+"분");
			            				var endDate = new Date(txt.aaWorkEnd);
			            				var StartDate = new Date(txt.aaWorkStart);
			            				totalHours += endDate.getHours() - StartDate.getHours();
			            				totalMinutes += endDate.getMinutes() - StartDate.getMinutes();
			            			}
			            			
			            		//기간 휴무일이 있을 경우
			            		}else if(txt.adType == 1){
			            			$tdSche.eq(dateMatch).html(txt.adTitle);
			            		//고정 휴무일이 있을 경우
			            		}else if(txt.adType == 2){
			            			//고정 휴무일에 출근 기록이 있을 경우
			            			if(txt.aaWorkStart != null){
			            				$tdSche.eq(dateMatch).html("휴무일 출근 <br> "+"출근시각 : "+formatDate_HH_MM(txt.aaWorkStart)+"<br>퇴근시각 : "+formatDate_HH_MM(txt.aaWorkEnd)+"<br>근무 시간 : "+txt.workTime);
			            				var endDate = new Date(txt.aaWorkEnd);
			            				var StartDate = new Date(txt.aaWorkStart);
			            				totalHours += endDate.getHours() - StartDate.getHours();
			            				totalMinutes += endDate.getMinutes() - StartDate.getMinutes();
			            			//아닐 경우
			            			}else{
			            				$tdSche.eq(dateMatch).html(txt.adTitle);
			            				
			            			}
			            		}
			            		
			            	}
		                    
		                    counting++;
			            }
			            
			        }
			    	console.log("시간 : "+totalHours+", 분 : "+totalMinutes);
			    }
			    
			});
	    }
	 
	

}); // AND OF JQUERY