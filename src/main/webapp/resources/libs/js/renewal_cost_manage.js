jQuery(document).ready(function($) {
	
	jQuery.ajaxSettings.traditional = true;
	
	$.ajax({
	    type       : 'GET',
	    url        : '/products/all_cost_detail_join_cost_code_list.do',
	    success    : function(data){
	    	var costDetailListResult = insertCostDetailList(data);
	    	var insertName = $("select[name='costsVOList[0].cdFk']");
	    	insertName.html(costDetailListResult);
	    	insertName.selectpicker('refresh');

	    }
	});
	
	//costCode 뒤에 id값 부여
	var costCodeCounting = 1;
	
	//원재료 추가하기 버튼 누를 경우
	$("button[name=addCostDetail]").click(function(){
		var costDetaildataSet = "";
		
		costDetaildataSet+='<div class="form-group row">'                                        	
	        +'<div class="col-8 col-sm-6 col-lg-8">'
	            +'<select class="form-control"  data-live-search="true" name="costsVOList['+costCodeCounting+'].cdFk" data-size="8">'
	            	+'<option value="0"> 분류를 선택해주세요. </option>'
	            +'</select>'
	        +'</div>'
	        +'<div class="col-2 col-sm-3 col-lg-2">'
	            +'<input type="text" disabled="disabled" class="form-control" id="test3" name="costsVOList['+costCodeCounting+'].costMeasureCal">'
	        +'</div>'
	        +'<div class="col-2 col-sm-3 col-lg-2">'
	            +'<button type="button" class="btn btn-warning btn-block" name="deleteCostDetail"> <i class="fas fa-trash"></i> 삭제 </button>'
	        +'</div>'
		+'</div>';
		
		$("#addCostDetailDiv").append(costDetaildataSet);
		
		var insertName = $("select[name='costsVOList["+costCodeCounting+"].cdFk']");
		
		$.ajax({
		    type       : 'GET',
		    url        : '/products/all_cost_detail_join_cost_code_list.do',
		    success    : function(data){
		    	var costDetailListResult = insertCostDetailList(data);
		    	insertName.html(costDetailListResult);
		    	insertName.selectpicker('refresh');

		    }
		});
		
		costCodeCounting++;
	
	});

	
	function insertCostDetailList(costDetailList){
		var costDetailListHTML = '<option value="0">제품을 선택해주세요</option>';
		
		$.each(costDetailList, function(){
			costDetailListHTML+='<option value="'+this.cdPk+'" data-unit-type="'+this.cdMeasure+'">[분류 : '+this.costCodeVOList[0].ccCodeType+' ]  '+this.cdName+', 단위[ '+this.cdMeasure+' ],  손실률 포함 원가 : '+comma(this.cdCost+(this.cdCost*this.cdLossRate/100))+' 원</option>';
		});
		
		return costDetailListHTML;
	}

	
	//삭제버튼 누를 시 
	$(document).on('click', 'button[name=deleteCostDetail]', function(){
		$(this).parent().parent().remove();
	});
	

	
	$(document).on('change', 'select[name$=cdFk]', function(){
		var measureType = $(this).children("option:selected").data("unit-type");
		
		if(measureType == 'kg'){
			$(this).parent().parent().next().find("input").prop("placeholder","g 단위를 입력해주세요.");
			$(this).parent().parent().next().find("input").prop("disabled","");
			
		}else if(measureType == 'ea'){
			$(this).parent().parent().next().find("input").prop("placeholder","개수를 입력해주세요.");
			$(this).parent().parent().next().find("input").prop("disabled","");
			
		}else if(measureType == 'liter'){
			$(this).parent().parent().next().find("input").prop("placeholder","ml 단위를 입력해주세요.");
			$(this).parent().parent().next().find("input").prop("disabled","");
			
		}else{
			$(this).parent().parent().next().find("input").prop("placeholder","");
			$(this).parent().parent().next().find("input").prop("disabled","disabled");
			
		}
	});
	

}); // AND OF JQUERY