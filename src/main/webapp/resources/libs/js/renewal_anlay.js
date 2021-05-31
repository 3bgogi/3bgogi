jQuery(document).ready(function($) {
	jQuery.ajaxSettings.traditional = true;
	
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
	        setTableHTML+='<table class="table table-hover calendar " style="word-break: keep-all;">';
	        
	        setTableHTML+='<tr style="text-align: center;"><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>';
	        for(var i=0;i<6;i++){
	            setTableHTML+='<tr height="100">';
	            for(var j=0;j<7;j++){
	                setTableHTML+='<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap; min-width: 150px;">';
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
	            $tdDay.eq(i).text(++dayCount+"일");
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
	    	
	    	$.ajax({
			    type       : 'GET',
			    datatype   :'json',
			    data       : {
			    	"formatYear":year,
			    	"formatMonth":month
			    },
			    url        : '/analytics/reserv_product_qty_month.do',
			    success    : function(data){
			    	jsonData = data;
			    	console.log(data);
			    	return data;
			    }
			    
			});
	    	
	    }
	    
	    
	    //스케줄 그리기
	    function drawSche(){
	    	
	    	$.ajax({
			    type       : 'GET',
			    data       : {
			    	"formatYear":year,
			    	"formatMonth":month
			    		
			    },
			    datatype   :'json',
			    url        : '/analytics/reserv_product_qty_month.do',
			    
			    success    : function(data){
			    	var counting = 0;
			    	for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
			    		for(var j = 0; j< data.length; j++){	

	            			if(i == data[j].date_end.split('-')[2]*1){
	            				
	            				
	            				insertHTML = "&nbsp;&nbsp;예약된 상품 개수  : "+data[j].max_num+"<br>";
	            				
	            				insertHTML+='&nbsp;&nbsp;<label class="custom-control custom-checkbox custom-control-inline">'
	                            	+'<input type="checkbox" name="orSendingDeadline" value="'+data[j].date_end+'"' 
	                            	+'class="custom-control-input"><span class="custom-control-label"> 확인하기 </span>'
	                            +'</label>';
	            				
	            				$tdSche.eq(i+1).html(insertHTML);
	            			}
	            		}
			            
			        }
			    },error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
			    
			});
	    }
	 
	    $("#checkProdBtn").click(function(){
			
			var orSendingDeadlineSize = $("input[name=orSendingDeadline]:checked").length;
			
			if(orSendingDeadlineSize == 0){
				alert("조회할 날짜가 체크되어 있지 않습니다"); 
				return false;
			}
			
			if(confirm(orSendingDeadlineSize+" 개의 날짜를 검색하시겠습니까?")){
				
				var searchKeywordList = new Array();

				for(var i=0; i<orSendingDeadlineSize; i++){
					searchKeywordList.push($("input[name=orSendingDeadline]:checked")[i].value);

				}
				
				$.ajax({
					type       : 'POST',
					data       : {
						"searchKeywordList":searchKeywordList
					},
					url        : '/analytics/reserv_product_qty.do',
					success    : function(data){
						console.log(data);
						
						var prodHTML = "";
						if(data.length == 0){
							prodHTML+="<tr><td colspan='2'>검색 결과가 존재하지 않습니다</td></tr>";
						}else{							
							$.each(data, function(){
								prodHTML+=	'<tr data-cf-code="'+this.cf_code+'">'                                        	
								+'<th>'+this.product_name+' ['+this.option_name+'] </th>'
								+'<th>'+this.qty+'개</th>'
								+'</tr>';
							});
						}
						
						$("#checkProd").html(prodHTML);
					}
					
				});
			}
			
		});
	   
	    $("#checkAll").click(function(){
	    	$("input[name=orSendingDeadline").each(function(){
	    		$(this).prop("checked", "checked");
	    	});
	    });
	    
	    
	    $("#checkDel").click(function(){
	    	$("input[name=orSendingDeadline").each(function(){
	    		$(this).prop("checked", "");
	    	});
	    });
	    
	    
}); // AND OF JQUERY