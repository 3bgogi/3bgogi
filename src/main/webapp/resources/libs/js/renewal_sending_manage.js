jQuery(document).ready(function($) {
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
	
	    return [year, month, day].join('-');
	}
	
	
	$.ajax({
		type       : 'GET',
		url        : '/delivery/non_picking_count.do',
		success    : function(data){		
			$("#nonePickingCount").text(data);
			
		}
		
	});
	
	$("#sendingReqReason").click(function(){

		var sendingReqForm = document.createElement("form");
		sendingReqForm.method="POST";
		sendingReqForm.id="sendingReqFormId";

		invoiceNum = $("#invoiceValue").val();
		
		if(invoiceNum == ''){
			alert("입력된 송장이 없습니다");
			return false;
		}
		
		var srInvoiceNum = document.createElement("input");
		srInvoiceNum.name="srInvoiceNum";
		srInvoiceNum.type="hidden";
		srInvoiceNum.value=invoiceNum;
		
		sendingReqForm.append(srInvoiceNum);
		
		
		var srReason = document.createElement("input");
		srReason.name="srReason";
		srReason.type="hidden";
		srReason.value=$("input[name=srReason]:checked").val();
		
		sendingReqForm.append(srReason);
		
		var sendingReqFormData = jQuery(sendingReqForm).serialize();
		
		$.ajax({
			type       : 'POST',
			data       : sendingReqFormData,
			url        : '/delivery/sending_req.do',
			success    : function(data){		
				if(data == 1){
					alert("요청 완료");
					location.reload();
					
				}else{
					alert("중복된 요청사항이 있습니다");
					location.reload();
					
				}
								
			}
			
		});
	});
	
	$("#sendingReqBtn").click(function(){	
		
		invoiceNum = $("#invoiceValue").val();
		
		if(invoiceNum == ''){
			alert("입력된 송장이 없습니다");
			return false;
		}
		
		$("#reasonBtn").click();

	});
	
	$("#barcodeInit").click(function(){
		location.reload();
	});
	
	var oneMore = new Audio();
	var chekingProduct = new Audio();
	var emptyProduct = new Audio();
	var finished = new Audio();
	var nonExistsInvoiceNum = new Audio();
	var nonExistsProduct = new Audio();
	var productOneFinished = new Audio();
	var checkingProductOneMore = new Audio();
	var startSending = new Audio();
	var afterSending = new Audio();
	
	oneMore.src = "/resources/libs/sending_sound/one_more.wav";
	chekingProduct.src = "/resources/libs/sending_sound/cheking_product.wav";
	emptyProduct.src = "/resources/libs/sending_sound/empty_products.wav";
	finished.src = "/resources/libs/sending_sound/finished.wav";
	nonExistsInvoiceNum.src = "/resources/libs/sending_sound/non_exists_invoice_num.wav";
	nonExistsProduct.src = "/resources/libs/sending_sound/non_exists_product.wav";
	productOneFinished.src = "/resources/libs/sending_sound/product_one_finished.mp3";
	checkingProductOneMore.src = "/resources/libs/sending_sound/cheking_product_one_more.wav";
	startSending.src = "/resources/libs/sending_sound/start_sending.wav";
	afterSending.src = "/resources/libs/sending_sound/after_sending_order.wav";
	
	oneMore.volume = 1;
	chekingProduct.volume = 1;
	emptyProduct.volume = 1;
	finished.volume = 1;
	nonExistsInvoiceNum.volume = 1;
	nonExistsProduct.volume = 1;
	productOneFinished.volume = 1;
	checkingProductOneMore.volume = 1;
	startSending.volume = 1;
	afterSending.volume = 1;
	
	var totalLength;
	var numberCounting;
	var completeProduct;
	var orDeliveryInvoiceNumber; 
	
	$(document).on("submit", "#sendingForm",(function(){
		
		orDeliveryInvoiceNumber = $("#orDeliveryInvoiceNumber").val();
		orDeliveryInvoiceNumber = orDeliveryInvoiceNumber.replace(" ","");
		
		if(orDeliveryInvoiceNumber == null || orDeliveryInvoiceNumber == ""){
			alert("값이 없습니다");
			$("#orDeliveryInvoiceNumber").focus();
			
			event.preventDefault();
			return false;
		}
		
		if($("#invoiceValue").val() == ""){
			
			$.ajax({
			    type       : 'POST',
			    async	   : false,
			    url        : '/delivery/result.do',
			    data : {			    	
			    "orDeliveryInvoiceNumber" : orDeliveryInvoiceNumber
			    },
			    success    : function(data){
			    	if(data.length == 0){
			    		nonExistsInvoiceNum.play();
			    		$("#orDeliveryInvoiceNumber").val("");
						$("#orDeliveryInvoiceNumber").focus();
						
			    	}else{
			    		if(formatDate(new Date(data[0].orSendingDeadline)) > formatDate(new Date())){
			    			
			    			alert("예약된 송장입니다");
			    			
			    			completeProduct = 0;
			    			$("#orDeliveryInvoiceNumber").val("");
							$("#orDeliveryInvoiceNumber").focus();
							
							
			    		}else{			    			
			    			if(data[0].orSendingDay == null){
			    				
			    				sendingProductListWrite(data);
			    				$("#invoiceValue").val(orDeliveryInvoiceNumber);
			    				totalLength = $("td[data-cancled='N']").length;
			    				
			    				if(totalLength == 0){
			    					$("#sendingProductList").html("");
									$("#invoiceValue").val("");
			    					$("#sendingTargetOrder").html("");
									$("#finishedOrder").html("");
									alert("해당 송장은 주문 취소되었습니다");
									
			    				}else{
			    					startSending.play();
			    					
			    				}
			    				
			    				completeProduct = 0;
			    				$("#orDeliveryInvoiceNumber").val("");
			    				$("#orDeliveryInvoiceNumber").focus();
			    				
			    				
			    			}else{
			    				
			    				afterSending.play();
			    				completeProduct = 0;
			    				$("#orDeliveryInvoiceNumber").val("");
			    				$("#orDeliveryInvoiceNumber").focus();
			    				

			    			}
			    		}
			    		
			    		
			    	}
			    	
			    }
			});
		}else{
			numberCounting = 1;
			forSearch = 0;
			searchCounting = 0;
			noneProdCounting = 0;

			$("#sendingProductList td[name=productLists]").each(function(idx){

				numberCounting+=1;
				
				//var barcodeNum = $(this).data("barcodenum");
				barcodeNum = $(this).attr("data-barcodenum");
				barcodeNum2 = $(this).attr("data-barcodenum2");
				if(!barcodeNum || !barcodeNum2){
					
				}else{		
					if( (barcodeNum == orDeliveryInvoiceNumber || barcodeNum2 == orDeliveryInvoiceNumber) && $(this).data("cancled") != "Y"){
						
						if( $(this).data("finished") == "N" &&  ( $(this).data("barcodenum") || $(this).data("barcodenum2")) != "N"){

							
							searchCounting++;
							//$(this).text( (Number($(this).data("quantity") )+Number(1))+"개" );
							//$(this).data("quantity",(Number($(this).data("quantity") )+Number(1)));
							
							$(this).text( (Number($(this).attr("data-quantity") )+Number(1))+"개" );
							$(this).attr("data-quantity",(Number($(this).attr("data-quantity") )+Number(1)));
							
							
							$("#orDeliveryInvoiceNumber").val("");
							$("#orDeliveryInvoiceNumber").focus();
							
							//if($(this).data("quantity") == $(this).data("amount")){
							
							if($(this).attr("data-quantity") == $(this).attr("data-amount")){
								
								completeProduct+=1;
								productOneFinished.play();
								if(oneMore.currentTime > 0) oneMore.currentTime = 0;
								if(productOneFinished.currentTime > 0) productOneFinished.currentTime = 0;
								
								$(this).attr("data-barcodenum","N");
								$(this).attr("data-barcodenum2","N");
								
								$(this).attr("data-finished","Y");
								
								$("#finishedOrder").append($(this).parent().clone());
								
								$(this).parent().css("display","none");
								
								
								
								if(totalLength == completeProduct){
									finished.play();
									if(oneMore.currentTime > 0) oneMore.currentTime = 0;
									if(finished.currentTime > 0) finished.currentTime = 0;
									
									$("#sendingProductList").html("");
									$("#invoiceValue").val("");
									
									var params = jQuery("#sendingTargetOrder").serialize();
									
									$.ajax({
									    type       : 'POST',
									    async	   : false,
									    url        : '/delivery/sending_result.do',
									    data	   : params,
									    success    : function(data){
									    	
									    	$.ajax({
									    		type       : 'GET',
									    		async	   : false,
									    		url        : '/delivery/non_picking_count.do',
									    		success    : function(data){		
									    			$("#nonePickingCount").text(data);
									    			
									    		}
									    		
									    	});
									    	
									    }
									});
									
									$("#sendingTargetOrder").html("");
									$("#finishedOrder").html("");
									
								}
								
							}else{
								oneMore.play();
								if(oneMore.currentTime > 0) oneMore.currentTime = 0;
								
								// return false;
							}
							
							return false;
							
						}else{
							
							checkingProductOneMore.play();
							$("#orDeliveryInvoiceNumber").val("");
							$("#orDeliveryInvoiceNumber").focus();
						}
						
					}else if( (barcodeNum == orDeliveryInvoiceNumber || barcodeNum2 == orDeliveryInvoiceNumber) && $(this).data("cancled") == "Y"){
						forSearch++;
					}else if((barcodeNum == orDeliveryInvoiceNumber || barcodeNum2 == orDeliveryInvoiceNumber) && $(this).data("cancled") == "N"){

						noneProdCounting++;
					}else{

						forSearch++;
					}
					
				}
				
			});

			if((forSearch == totalLength && searchCounting == 0) || noneProdCounting == totalLength){
				nonExistsProduct.play();
				$("#orDeliveryInvoiceNumber").val("");
				$("#orDeliveryInvoiceNumber").focus();
			}
			
		}
		
		event.preventDefault();
		return false;
		
	}));
	
	function sendingProductListWrite(data){
		productList = ""; 
		
		$.each(data, function(){
			productList
				+="<tr style='font-size: 12px;'>"
					+"<td width='70%;' style='";
			if(!this.orUserColumn1){
				productList+=" color:red;";
			}
			if(this.orCancledFlag == true){
				productList+=" text-decoration:line-through; color:red;";
			}
			
			productList+="'>"+this.orProduct;
			
			if(!this.orUserColumn1){
				productList+="(바코드 없음)";
			}
			if(this.orCancledFlag == true){
				productList+=" - 취소된 상품 - ";
			}
			if(this.orExcelDivFlag == true){
				productList+=" - 대량 엑셀 파일 - ";
			}
			
			if(this.orCancledFlag == true || this.orExcelDivFlag == true){
				productList+="</td>"
					+"<td width='15%;'>"+this.orAmount+"개</td>"
					+"<td width='15%;' name='productLists' data-barcodenum='";
				
				if(!this.orUserColumn1){
					productList+="N";
				}else{
					productList+=this.orUserColumn1;
				}
				
				productList+="' data-barcodenum2='";
				
				
				if(!this.orUserColumn2){
					productList+="N";
				}else{
					productList+=this.orUserColumn2;
					
				}
				
				productList+="' data-amount='"+this.orAmount+"' data-finished='N' data-cancled='Y' data-quantity='"+this.orAmount+"'>";
				
				if(this.orCancledFlag == true){
					productList+=" 취소 ";
				}else if(this.orExcelDivFlag == true){
					productList+=" 엑셀 주소 파일 ";
				}
				productList+="</td>"
				+"</tr>";
				
				orPkInput = document.createElement("input");
				orPkInput.name="orPk";
				orPkInput.type="hidden";
				orPkInput.value=this.orPk;
				$("#sendingTargetOrder").append(orPkInput);
				
			}else{
				productList+="</td>"
					+"<td width='15%;'>"+this.orAmount+"개</td>"
					+"<td width='15%;' name='productLists' data-barcodenum='";
				
				if(!this.orUserColumn1){
					productList+="N";
				}else{
					productList+=this.orUserColumn1
				}
				
				productList+="' data-barcodenum2='";
				
				
				if(!this.orUserColumn2){
					productList+="N";
					
				}else{
					productList+=this.orUserColumn2;
					
				}
				
				productList+="' data-amount='"+this.orAmount+"' data-finished='N' data-cancled='N' data-quantity='0'>0개</td>"
				+"</tr>";
				
				orPkInput = document.createElement("input");
				orPkInput.name="orPk";
				orPkInput.type="hidden";
				orPkInput.value=this.orPk;
				$("#sendingTargetOrder").append(orPkInput);
				
			}
			
		});
	
		$("#sendingProductList").html(productList);
	}
}); // AND OF JQUERY