
	function regexFunction( eventTitle, eventTarget){
	
		$(document).on(eventTitle, eventTarget, function(){
			
			var regexp = /^[0-9]*$/
	
				v = $(this).val();
	
				if( !regexp.test(v) ) {
	
					alert("숫자만 입력하세요");
	
					$(this).val("");
	
				}
				
		});
	
	}
	//기타 기능
	function returnWeek(date){
		var week = ['일', '월', '화', '수', '목', '금', '토'];
		
		var dayOfWeek = week[new Date(date).getDay()];
		
		return dayOfWeek;
	}

	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [year, month, day].join('-');
	}
	
	function allFormatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	    
	    var hours = '' + d.getHours();
	    var minutes = '' + d.getMinutes();
	    
	    if(hours < 10) hours="0"+hours;
	    if(minutes < 10) minutes="0"+minutes;
	
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [year, month, day].join('-') + " "+hours+":"+minutes+"";
	}
	
	function formatDate_all_hh_mm_ss(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	    
	    var hours = '' + d.getHours();
	    var minutes = '' + d.getMinutes();
	    var sec = ''+ d.getSeconds();
	    
	    if(hours < 10) hours="0"+hours;
	    if(minutes < 10) minutes="0"+minutes;
	    if(sec < 10) sec="0"+sec;
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [year, month, day].join('-') + " "+hours+":"+minutes+":"+sec;
	}
	
	function formatDate_HH_MM(date) {
	    var d = new Date(date);
	    var hours = '' + d.getHours();
	    var minutes = '' + d.getMinutes();
	    
	    if(hours < 10) hours="0"+hours;
	    if(minutes < 10) minutes="0"+minutes;
	    
	    return hours+":"+minutes+"";
	}
	
	function comma(num){
		
	    var len, point, str; 
	    
	    if(num==null || num == 0){
	    	str = '0';
	    	return str;
	    }
	    num = num + ""; 
	    point = num.length % 3 ;
	    len = num.length; 
	   
	    str = num.substring(0, point); 
	    while (point < len) { 
	        if (str != "") str += ","; 
	        str += num.substring(point, point + 3); 
	        point += 3; 
	    } 
	     
	    return str;
	 
	}
	
	function paginationRebuild(totalRecord, currentPage, recordCountPerPage ){
		let blockSize = 10;
		
		let totalPage = parseInt(Math.ceil((totalRecord/recordCountPerPage)));
		
		
		let firstPage = currentPage-((currentPage-1)%blockSize);
		let lastPage = firstPage+(blockSize-1);
		let firstRecordIndex = (currentPage - 1) * recordCountPerPage;
		let lastRecordIndex = currentPage * recordCountPerPage;
		
		paginationHTML = "";
		
		paginationHTML +='<ul class="pagination" style="display: table; margin-left: auto; margin-right: auto;">';
		
			
			if(firstPage > 1){
				paginationHTML += '<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc('+( firstPage - 1 )+')">«</a></li>';
			}
			for(var i = firstPage; i <= lastPage; i++){
				
				paginationHTML+='<li class="page-item'
					
					if(currentPage == i ){
						paginationHTML+=' active';
					}
				
				paginationHTML+=' "  style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc('+i+')">'+i+'</a></li>';
				
			}
			
			if(lastPage < totalPage){
				paginationHTML +='<li class="page-item" style="float:left; padding-top:5px;"><a class="page-link" href="javascript:void(0)" onclick="pageFunc('+( lastPage + 1 )+')">»</a></li>';
				
			}
			
		paginationHTML+='</ul>';
		
		$("#pageNavigation").html(paginationHTML);
	
	}
	