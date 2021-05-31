jQuery(document).ready(function($) {
	
	jQuery.ajaxSettings.traditional = true;
	
	
	$("form[name=productListSearchForm]").submit(function(){
		
		var cfFk = $("select[name=cfFk]").val();
		var productName = $("input[name=productName]").val();
		
		$.ajax({
		    type       : 'GET',
		    url        : '/order/matching/product_list_result.do',
		    data       : {
		    	"cfFk":cfFk,
		    	"productName":productName
		    },
		    success    : function(data){
		    	productListWrite(data);
		    }
		});
		
		
		event.preventDefault();
		return false;
	});
	
	function productListWrite(data){
		var productListHTML = "";
		
		$.each(data, function(){
			productListHTML+='<div class="product-thumbnail">'
				+'<div class="product-content">'
					+'<div class="row">'
						+'<div class="col-lg-4 col-xl-4 col-md-4">'
							+'<div class="product-content-head">'
							+'<h5>'+this.productName+'</h5>'
							+'<div class="product-rating d-inline-block">';
								if(this.productFlag == true){
									productListHTML+='<a href="#" class="btn btn-rounded btn-success">사용중</a>';
								}else{
									productListHTML+='<a href="#" class="btn btn-rounded btn-danger">미사용중</a>';
								}
			productListHTML+='</div>'
							
						+'</div>'
						+'</div>'
						+'<div class="col-lg-8 col-xl-8 col-md-8"><div class="card mb-0">';
			
							if(this.optionVOList.length == 0){
								productListHTML+='<div class="card-header">'
									+'<h5 class="mb-0">등록된 옵션이 없습니다.</h5>'
									+'</div>';
								
							}else{
								$.each(this.optionVOList, function(){									
									productListHTML+='<div class="card-header" style="cursor: pointer;">'
										+'<h5 class="mb-0">'+this.optionName+'</h5>'
										+'<input type="hidden" value="'+this.optionPk+'">'
										+'</div>';
								});
								
							}
							
			productListHTML+='</div></div>'
					+'</div>'
					+'<div class="product-btn">';
						
						if(this.productFlag == true){							
							if($("input[name=editFlag]").val() == 'true'){
								productListHTML+='<button type="button" value="'+this.productPk+'" class="btn btn-success editProductMathing"> 해당 상품으로 재매칭</button>';
								
								
							}else{
								productListHTML+='<button type="button" value="'+this.productPk+'" class="btn btn-primary updateMatchingData"> 해당 상품으로 등록</button>';
							}
							
							
						}
						
			productListHTML+='</div>'
				+'</div>'
			+'</div>';
		});
		
		$("#productSearchList").html(productListHTML);
	}
	
	$(document).on("click", ".editProductMathing", function(){
		if(confirm("해당 상품으로 재매칭을 하시겠습니까?")){			
			var productMatchingform = document.createElement("form");
			productMatchingform.id="productMatchingFormData";
			
			var productFkInput = document.createElement("input");
			productFkInput.type="hidden";
			productFkInput.name="productFk";
			productFkInput.value=$(this).val();
			
			var pmPkInput = document.createElement("input");
			pmPkInput.type="hidden";
			pmPkInput.name="pmPk";
			pmPkInput.value=$("input[name=pmPk]").val();
			
			$(productMatchingform).append(productFkInput);
			$(productMatchingform).append(pmPkInput);
			
			$('body').append(productMatchingform); 
			
			var params = $("#productMatchingFormData").serialize();
			
			$.ajax({
			    type       : 'post',
			    url        : '/order/matching/edit_product_matching.do',
			    data       : params,
			    success    : function(data){			    		
			    	alert("상품 재매칭 완료");
			    	opener.location.reload();

			    	window.close();
			    }
			});
		}else{
			alert("취소됐습니다.");
			
		}
		
	});
	
	$(document).on("click", ".updateMatchingData", function(){
		if(confirm("해당 상품으로 매칭을 등록하시겠습니까?")){			
			var productMatchingform = document.createElement("form");
			productMatchingform.id="productMatchingFormData";
			
			var productFkInput = document.createElement("input");
			productFkInput.type="hidden";
			productFkInput.name="productFk";
			productFkInput.value=$(this).val();
			
			var pmStoreProductNameInput = document.createElement("input");
			pmStoreProductNameInput.type="hidden";
			pmStoreProductNameInput.name="pmStoreProductName";
			pmStoreProductNameInput.value=$("input[name=orProductName]").val();
			
			$(productMatchingform).append(productFkInput);
			$(productMatchingform).append(pmStoreProductNameInput);
			
			$('body').append(productMatchingform); 
			
			var params = $("#productMatchingFormData").serialize();
			
			
			alert("상품 매칭을 시작합니다.");
			$.ajax({
			    type       : 'post',
			    url        : '/order/matching/product_matching.do',
			    data       : params,
			    success    : function(data){			    		
			    	alert("상품 매칭 완료");
			    	opener.location.reload();

			    	window.close();
			    }
			});
		}else{
			alert("취소됐습니다.");
			
		}
		
	});

/*	$(document).on('submit', '#productMatchingForm', function(){
		
		var params = jQuery(this).serialize();
			
			$.ajax({
			    type       : 'post',
			    url        : '/order/matching/product_matching.do',
			    data       : params,
			    success    : function(data){
			    	alert(data);
			    }
			});
			
			event.preventDefault();
			return false;
			
	});*/
	
	//옵션 매칭시에 쓰이는 폼
	$("form[name=productOptionListSearchForm]").submit(function(){
		
		var cfFk = $("select[name=cfFk]").val();
		var productName = $("input[name=productName]").val();
		
		$.ajax({
		    type       : 'GET',
		    url        : '/order/matching/product_list_result.do',
		    data       : {
		    	"cfFk":cfFk,
		    	"productName":productName
		    },
		    success    : function(data){
		    	productOptionListWrite(data);
		    }
		});
		
		event.preventDefault();
		return false;
	});
	
	//상품 변경시
	$("form[name=changeProductOptionListSearchForm]").submit(function(){
		
		var cfFk = $("select[name=cfFk]").val();
		var productName = $("input[name=productName]").val();
		
		$.ajax({
		    type       : 'GET',
		    url        : '/order/matching/product_list_result.do',
		    data       : {
		    	"cfFk":cfFk,
		    	"productName":productName
		    },
		    success    : function(data){
		    	changeProductOptionListWrite(data);
		    }
		});
		
		event.preventDefault();
		return false;
	});
	
	function productOptionListWrite(data){
		var productOptionListHTML = "";
		
		$.each(data, function(){
			var productNames = this.productName;
			productOptionListHTML+='<div class="product-thumbnail">'
				+'<div class="product-content">'
					+'<div class="row">'
						+'<div class="col-lg-4 col-xl-4 col-md-4">'
							+'<div class="product-content-head">'
							+'<h5>'+this.productName+'</h5>'
							+'<div class="product-rating d-inline-block">';
								if(this.productFlag == true){
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-success">사용중</a>';
								}else{
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-danger">미사용중</a>';
								}
			productOptionListHTML+='</div>'
						+'</div>'
						+'</div>'
						+'<div class="col-lg-8 col-xl-8 col-md-8"><div class="card mb-0">';
			
							if(this.optionVOList.length == 0){
								productOptionListHTML+='<div class="card-header">'
									+'<h5 class="mb-0">등록된 옵션이 없습니다.</h5>'
									+'</div>';
								
							}else{
								$.each(this.optionVOList, function(){									
									productOptionListHTML+='<div class="card-header" style="cursor: pointer;">'
											+'<div class="row">'
												+'<div class="col-xl-12 col-lg-12 col-sm-12 col-12">'
													+'<h5 class="mb-0">'+this.optionName+'</h5>'
												+'</div>'
												+'<div class="col-xl-6 col-lg-6 col-sm-12 col-12">'
													+'<select class="form-control form-control-sm" name="optionAmount">';
														for(var i = 1; i < 101; i++){
															productOptionListHTML+='<option value="'+i+'"> 주문 수량 X '+i+' 개 </option>'
														}
				              productOptionListHTML+='</select>'
			                                    +'</div>'
			                                    +'<div class="col-xl-6 col-lg-6 col-sm-12 col-12">';
				              					if($("input[name=editFlag]").val() == 'true'){
				              						productOptionListHTML+='<button name="moreAddOptionMatching" value="'+this.optionPk+'" data-product-name="'+productNames+'" data-option-name="'+this.optionName+'" class="btn btn-success btn-block"> 옵션 매칭 추가 </button>';
				              						
				              					}else{
				              						productOptionListHTML+='<button name="addOption" value="'+this.optionPk+'" data-product-name="'+productNames+'" data-option-name="'+this.optionName+'" class="btn btn-primary btn-block">등록</button>';
				              						
				              					}
				              productOptionListHTML+='</div>'
											+'</div>'
											+'<div>'
											+'</div>'
										+'</div>';
								});
								
							}
							
			productOptionListHTML+='</div></div>'
					+'</div>'
				+'</div>'
			+'</div>';
		});
		
		$("#productOptionSearchList").html(productOptionListHTML);
		
		
	}
	
	function changeProductOptionListWrite(data){
		var productOptionListHTML = "";
		
		$.each(data, function(){
			productOptionListHTML+='<div class="product-thumbnail">'
				+'<div class="product-content">'
					+'<div class="row">'
						+'<div class="col-lg-4 col-xl-4 col-md-4">'
							+'<div class="product-content-head">'
							+'<h5>'+this.productName+'</h5>'
							+'<div class="product-rating d-inline-block">';
								if(this.productFlag == true){
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-success">사용중</a>';
								}else{
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-danger">미사용중</a>';
								}
			productOptionListHTML+='</div>'
						+'</div>'
						+'</div>'
						+'<div class="col-lg-8 col-xl-8 col-md-8"><div class="card mb-0">';
			
							if(this.optionVOList.length == 0){
								productOptionListHTML+='<div class="card-header">'
									+'<h5 class="mb-0">등록된 옵션이 없습니다.</h5>'
									+'</div>';
								
							}else{
								var productPks = this.productPk;
								var productNames = this.productName;
								$.each(this.optionVOList, function(){									
									productOptionListHTML+='<div class="card-header" style="cursor: pointer;">'
											+'<div class="row">'
												+'<div class="col-xl-6 col-lg-6 col-sm-12 col-6">'
													+'<h5 class="mb-0">'+this.optionName+'</h5>'
												+'</div>'
												+'<div class="col-xl-4 col-lg-4 col-sm-12 col-4">'
													+'<select class="form-control form-control-sm" name="optionAmount" >';
														for(var i = 1; i < 101; i++){
															productOptionListHTML+='<option value="'+i+'"> 변경할 개수 : '+i+' 개 </option>';
														}
				              productOptionListHTML+='</select>'
			                                    +'</div>'
			                                    +'<div class="col-xl-2 col-lg-2 col-sm-12 col-2">'
			                                    	+'<button name="changeProductOption" value="'+this.optionPk+'" data-product-name="'+productNames+'" data-product-pk="'+productPks+'" data-option-name="'+this.optionName+'" class="btn btn-primary btn-block"> 변경하기 </button>'
			                                    +'</div>'
											+'</div>'
											+'<div>'
											+'</div>'
										+'</div>';
								});
								
							}
							
			productOptionListHTML+='</div></div>'
					+'</div>'
				+'</div>'
			+'</div>';
		});
		
		$("#productOptionSearchList").html(productOptionListHTML);
		
		
	}
	
	//상품 변경 시 이벤트
	$(document).on('click', 'button[name=changeProductOption]', function(){
		$("#changeProductAndOptionForm").html("");
		var orProduct = $(this).data("product-name");
		var orProductOption = $(this).data("option-name");
		var orPk = $("#orPk").val();
		var orAmount = $(this).parent().prev().find("select[name=optionAmount]").val();
		
		var orProductInput = document.createElement("input");
		orProductInput.name="orProduct";
		orProductInput.type="hidden";
		orProductInput.value=orProduct;
		
		var orProductOptionInput = document.createElement("input");
		orProductOptionInput.name="orProductOption";
		orProductOptionInput.type="hidden";
		orProductOptionInput.value=orProductOption;
		
		var orPkInput = document.createElement("input");
		orPkInput.name="orPk";
		orPkInput.type="hidden";
		orPkInput.value=orPk;
		
		var orAmountInput = document.createElement("input");
		orAmountInput.name="orAmount";
		orAmountInput.type="hidden";
		orAmountInput.value=orAmount;
		
		$("#changeProductAndOptionForm").append(orProductInput);
		$("#changeProductAndOptionForm").append(orProductOptionInput);
		$("#changeProductAndOptionForm").append(orPkInput);
		$("#changeProductAndOptionForm").append(orAmountInput);
		
		var changeProductAndOptionFormParams = jQuery("#changeProductAndOptionForm").serialize();
		
		$.ajax({
		    type       : 'POST',
		    url        : '/orders/config/change_product_option.do',
		    data       : changeProductAndOptionFormParams,
		    success    : function(data){
		    	if(data == true){
		    		alert("옵션 등록 성공");
		    		
		    	}else{
		    		alert("옵션 등록 실패");
		    	}
		    }
		});
	});
	
	var optionNameMatchingCounting = 0;

	$(document).on('click', 'button[name=addOption]', function(){
		var optionPk = $(this).val();
		var optionName = $(this).data("option-name");
		var productName = $(this).data("product-name");
		var omOptionAmount = $(this).parent().prev().find('select[name=optionAmount]').val();
		var omStoreOptionName= $("input[name=orProductOption]").val();
		var pmFk= $("input[name=pmFk]").val();
		
		var divForm = document.createElement("div");
		divForm.id="divFormData"+optionNameMatchingCounting;
		divForm.className="row";
		divForm.style.padding="4px";
		
		var divInForm = document.createElement("div");
		divInForm.className="col-xl-8 col-lg-8 col-md-8 col-sm-12 col-12";

		var optionPkInput = document.createElement("input");
		optionPkInput.type="hidden";
		optionPkInput.name="optionMatchingVOList["+optionNameMatchingCounting+"].optionFk";
		optionPkInput.value=optionPk;
		
		var omOptionAmountInput = document.createElement("input");
		omOptionAmountInput.type="hidden";
		omOptionAmountInput.name="optionMatchingVOList["+optionNameMatchingCounting+"].omOptionAmount";
		omOptionAmountInput.value=omOptionAmount;
		
		var omStoreOptionNameInput = document.createElement("input");
		omStoreOptionNameInput.type="hidden";
		omStoreOptionNameInput.name="optionMatchingVOList["+optionNameMatchingCounting+"].omStoreOptionName";
		omStoreOptionNameInput.value=omStoreOptionName;
		
		var pmFkInput = document.createElement("input");
		pmFkInput.type="hidden";
		pmFkInput.name="optionMatchingVOList["+optionNameMatchingCounting+"].pmFk";
		pmFkInput.value=pmFk;
		
		var optionText = productName+" "+optionName+" 주문수량 X "+omOptionAmount+" 개 ";
		
		var optionDetailShot = document.createElement("h4");
		optionDetailShot.className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12";
		optionDetailShot.style.padding="0px";
		optionDetailShot.innerHTML=optionText;
		
		var labels = document.createElement("label");
		labels.className="be-checkbox custom-control custom-checkbox";
		
		var omSupplyFlag = document.createElement("input");
		omSupplyFlag.type="checkbox";
		omSupplyFlag.className="custom-control-input";
		omSupplyFlag.name="optionMatchingVOList["+optionNameMatchingCounting+"].omSupplyFlag";
		omSupplyFlag.value="0";
		
		var spans = document.createElement("span");
		spans.className="custom-control-label";
		spans.innerHTML="공급가 여부";
		
		labels.append(omSupplyFlag);
		labels.append(spans);
		
		
		var deleteButton = document.createElement("button");
		deleteButton.type="button";
		deleteButton.innerHTML="삭제";
		deleteButton.className="btn btn-primary col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12";
		deleteButton.name="deleteOption"+optionNameMatchingCounting;
		
		$(divInForm).append(optionPkInput);
		$(divInForm).append(omOptionAmountInput);
		$(divInForm).append(omStoreOptionNameInput);
		$(divInForm).append(pmFkInput);
		$(divInForm).append(optionDetailShot);
		$(divInForm).append(labels);
		$(divForm).append(divInForm);
		$(divForm).append(deleteButton);
		
		$("#productOptionInsertForm").append(divForm);
		
		optionNameMatchingCounting++;
		
		$("#productName").focus();

	});
	
	
	$(document).on('click', 'button[name=moreAddOptionMatching]', function(){
		if(!confirm("해당 옵션을 추가하시겠습니까?")){
			
			return false;
		}
		
		var optionPk = $(this).val();
		var omOptionAmount = $(this).parent().prev().find('select[name=optionAmount]').val();
		var omStoreOptionName= $("input[name=orProductOption]").val();
		var pmFk= $("input[name=pmFk]").val();
		
		
		
		var optionMatchingform = document.createElement("form");
		optionMatchingform.id="optionMatchingform";
		
		var optionPkInput = document.createElement("input");
		optionPkInput.type="hidden";
		optionPkInput.name="optionFk";
		optionPkInput.value=optionPk;
		
		var omOptionAmountInput = document.createElement("input");
		omOptionAmountInput.type="hidden";
		omOptionAmountInput.name="omOptionAmount";
		omOptionAmountInput.value=omOptionAmount;
		
		var omStoreOptionNameInput = document.createElement("input");
		omStoreOptionNameInput.type="hidden";
		omStoreOptionNameInput.name="omStoreOptionName";
		omStoreOptionNameInput.value=omStoreOptionName;
		
		var pmFkInput = document.createElement("input");
		pmFkInput.type="hidden";
		pmFkInput.name="pmFk";
		pmFkInput.value=pmFk;
		
		$(optionMatchingform).append(optionPkInput);
		$(optionMatchingform).append(omOptionAmountInput);
		$(optionMatchingform).append(omStoreOptionNameInput);
		$(optionMatchingform).append(pmFkInput);
		
		$('body').append(optionMatchingform); 
		
		var params = $("#optionMatchingform").serialize();
		
		$.ajax({
		    type       : 'post',
		    url        : '/order/matching/add_option_matching.do',
		    data       : params,
		    success    : function(data){			    		
		    	if(data > 0){
		    		alert("옵션 추가 완료");
		    		location.reload();
		    		
		    	}else if(data == 0){
		    		alert("옵션 추가 실패");
		    		
		    	}else{
		    		alert("해당 옵션값이 이미 존재합니다");
		    		location.reload();
		    		
		    	}

		    }
		});


	});
	
	$("button[name=optionMatchingDeleteBtn").click(function(){
		if(!confirm("해당 옵션을 삭제하시겠습니까?")){
			
			return false;
		}
		
		var optionMatchingform = document.createElement("form");
		optionMatchingform.id="optionMatchingform";
		
		var omPktInput = document.createElement("input");
		omPktInput.type="hidden";
		omPktInput.name="omPk";
		omPktInput.value=$(this).val();
		
		$(optionMatchingform).append(omPktInput);
		
		$('body').append(optionMatchingform); 
		
		var params = $("#optionMatchingform").serialize();
		
		$.ajax({
		    type       : 'post',
		    url        : '/order/matching/delete_option_matching.do',
		    data       : params,
		    success    : function(data){			    		
		    	alert("옵션 삭제 완료");
		    	alert(data);
		    	location.reload();

		    }
		});
		
	});
	
	
	$(document).on("change", "input[name$='omSupplyFlag']",function(){
		if($(this).is(":checked")){
			$(this).val("1");
		}else{
			$(this).val("0");
		}
	});
	
	$("#matchingOptionButton").click(function(){
		
		var params = jQuery("#productOptionInsertForm").serialize();
		
		$.ajax({
		    type       : 'POST',
		    url        : '/order/matching/option_matching.do',
		    data       : params,
		    success    : function(data){
		    	if(data == true){
		    		alert("옵션 등록 성공");
		    		
		    	}else{
		    		alert("옵션 등록 실패");
		    	}
		    }
		});
		
		opener.location.reload();
		self.close();
		
	});
	
	
	
	
	
	
	//상품 추가쪽 이벤트
	
	
	//상품 추가시
	$("form[name=addProductOptionListSearchForm]").submit(function(){
		
		var cfFk = $("select[name=cfFk]").val();
		var productName = $("input[name=productName]").val();
		
		$.ajax({
		    type       : 'GET',
		    url        : '/order/matching/product_list_result.do',
		    data       : {
		    	"cfFk":cfFk,
		    	"productName":productName
		    },
		    success    : function(data){
		    	addProductOptionListWrite(data);
		    }
		});
		
		event.preventDefault();
		return false;
	});
	
	$("form[name=addOrderProductListSearchForm]").submit(function(){
		
		var cfFk = $("select[name=cfFk]").val();
		var productName = $("input[name=productName]").val();
		
		$.ajax({
		    type       : 'GET',
		    url        : '/order/matching/product_list_result.do',
		    data       : {
		    	"cfFk":cfFk,
		    	"productName":productName
		    },
		    success    : function(data){
		    	addOrderProductListWrite(data);
		    }
		});
		
		event.preventDefault();
		return false;
	});
	
	function addProductOptionListWrite(data){
		var productOptionListHTML = "";
		
		$.each(data, function(){
			productOptionListHTML+='<div class="product-thumbnail">'
				+'<div class="product-content">'
					+'<div class="row">'
						+'<div class="col-lg-3 col-xl-3 col-md-3">'
							+'<div class="product-content-head">'
							+'<h5>'+this.productName+'</h5>'
							+'<div class="product-rating d-inline-block">';
								if(this.productFlag == true){
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-success">사용중</a>';
								}else{
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-danger">미사용중</a>';
								}
			productOptionListHTML+='</div>'
						+'</div>'
						+'</div>'
						+'<div class="col-lg-9 col-xl-9 col-md-9"><div class="card mb-0">';
			
							if(this.optionVOList.length == 0){
								productOptionListHTML+='<div class="card-header">'
									+'<h5 class="mb-0">등록된 옵션이 없습니다.</h5>'
									+'</div>';
								
							}else{
								var productPks = this.productPk;
								var productNames = this.productName;
								$.each(this.optionVOList, function(){									
									productOptionListHTML+='<div class="card-header" style="cursor: pointer;">'
											+'<div class="row">'
												+'<div class="col-xl-5 col-lg-5 col-sm-5 col-5">'
													+'<h5 class="mb-0">'+this.optionName+'</h5>'
												+'</div>'
												+'<div class="col-xl-3 col-lg-3 col-sm-12 col-3">'
													+'<select class="form-control form-control-sm" name="orAmount" >';
														for(var i = 1; i < 101; i++){
															productOptionListHTML+='<option value="'+i+'"> 추가할 개수 : '+i+' 개 </option>';
														}
				              productOptionListHTML+='</select>'
			                                    +'</div>'
			                                    +'<div class="col-xl-2 col-lg-2 col-sm-12 col-2">'
			                                    	+'<input class="form-control" type="text" id="orTotalPrice" name="orTotalPrice"  value="0" placeholder="판매 가격 입력" />'
			                                    +'</div>'
			                                    +'<div class="col-xl-2 col-lg-2 col-sm-12 col-2">'
			                                    	+'<button name="addProductOption" value="'+this.optionPk+'" data-product-name="'+productNames+'" data-product-pk="'+productPks+'" data-option-name="'+this.optionName+'" class="btn btn-primary btn-block"> 상품 추가 </button>'
			                                    +'</div>'
											+'</div>'
											+'<div>'
											+'</div>'
										+'</div>';
								});
								
							}
							
			productOptionListHTML+='</div></div>'
					+'</div>'
				+'</div>'
			+'</div>';
		});
		
		$("#productOptionSearchList").html(productOptionListHTML);

	}
	
	//상품 추가 버튼 클릭시 이벤트
	$(document).on('click', 'button[name=addProductOption]', function(){
		$("#addProductAndOptionForm").html("");
		var orProduct = $(this).data("product-name");
		var orProductOption = $(this).data("option-name");
		var orTotalPrice = $("input[name=orTotalPrice]").val();
		
		var orAmount = $(this).parent().prev().prev().find("select[name=orAmount]").val();
		
		var orSerialSpecialNumberListSize = $("input[name=orSerialSpecialNumbers]").length;
		var orSerialSpecialNumberList = new Array(orSerialSpecialNumberListSize);
			
		for(var i=0; i<orSerialSpecialNumberListSize; i++){
			orSerialSpecialNumberList[i]=$("input[name=orSerialSpecialNumbers]")[i].value;
		}

		var orProductInput = document.createElement("input");
		orProductInput.name="orProduct";
		orProductInput.type="hidden";
		orProductInput.value=orProduct;
		
		var orProductOptionInput = document.createElement("input");
		orProductOptionInput.name="orProductOption";
		orProductOptionInput.type="hidden";
		orProductOptionInput.value=orProductOption;
		
		var orTotalPriceInput = document.createElement("input");
		orTotalPriceInput.name="orTotalPrice";
		orTotalPriceInput.type="hidden";
		orTotalPriceInput.value=orTotalPrice;
		
		var orAmountInput = document.createElement("input");
		orAmountInput.name="orAmount";
		orAmountInput.type="hidden";
		orAmountInput.value=orAmount;
		
		for(var i=0; i < orSerialSpecialNumberList.length; i++){
			var orSerialSpecialNumberInput = document.createElement("input");
			orSerialSpecialNumberInput.name="orSerialSpecialNumbers";
			orSerialSpecialNumberInput.type="hidden";
			orSerialSpecialNumberInput.value=orSerialSpecialNumberList[i];
			$("#addProductAndOptionForm").append(orSerialSpecialNumberInput);
		}
		
		$("#addProductAndOptionForm").append(orProductInput);
		$("#addProductAndOptionForm").append(orProductOptionInput);
		$("#addProductAndOptionForm").append(orTotalPriceInput);
		$("#addProductAndOptionForm").append(orAmountInput);
		
		var addProductAndOptionFormParams = jQuery("#addProductAndOptionForm").serialize();
		
		$.ajax({
		    type       : 'POST',
		    url        : '/orders/config/add_product_option.do',
		    data       : addProductAndOptionFormParams,
		    success    : function(data){
		    	if(data == true){
		    		alert("상품 추가 완료");
		    		opener.location.reload();
		    		self.close();
		    	}else{
		    		alert("상품 추가 실패");
		    	}
		    }
		});
	});
	
	// regexFunction('keyup', 'input[name=orTotalPrice]');
	
	
	// var addCounting = 0;
	
	$(document).on('click', 'button[name*="deleteOption"]', function(){
		$(this).parent().remove();
		
		
	});
	
	$(document).on('click', 'button[name=addOrderProduct]', function(){
		var addCounting = $(opener.document).find("#productCounting").val();
		
		
		$("#addProductAndOptionForm").html("");
		var orProduct = $(this).data("product-name");
		var orProductOption = $(this).data("option-name");
		var orTotalPrice = $(this).parent().prev().find("input[name=orTotalPrice]").val();
		
		if(orTotalPrice == ''){
			orTotalPrice=0;
		}
		
		var orAmount = $(this).parent().prev().prev().find("input[name=orAmount]").val();
		
		if(orAmount == '' || orAmount == 0){
			alert("상품의 개수를 입력해주세요");
			
			return false;
		}
		
		var appendForm = '<li class="list-group-item d-flex justify-content-between">'
			+'<input type="hidden" name="orAmountList['+addCounting+']" value="'+orAmount+'">'
			+'<input type="hidden" name="orProductList['+addCounting+']" value="'+orProduct+'">'
			+'<input type="hidden" class="orderTotalPrice" name="orTotalPriceList['+addCounting+']" value="'+orTotalPrice+'">'
			+'<input type="hidden" name="orProductOptionList['+addCounting+']" value="'+orProductOption+'">'
			
		+'<div>'
			+'<h6 class="my-0"> '+orProduct+' </h6>'
			+'<small class="text-muted"> '+orProductOption+' '+orAmount+'개 ( '+comma(orTotalPrice)+' 원)</small>'
		+'</div> <span class="text-muted"><button class="btn btn-danger btn-sm deleteCreateOrderProduct"> 삭제 </button></span>'
	+'</li>';
		
		$(opener.document).find("#createOrderProductList").append(appendForm); 
		
		var filter = "win16|win32|win64|mac";

		 

		if(navigator.platform){

    		if(0 > filter.indexOf(navigator.platform.toLowerCase())){

    		alert(orProduct+" "+orProductOption+" "+orAmount+" 개 추가 완료");

    		}

		}
		
		addCounting++;
		$(opener.document).find("#productCounting").val(addCounting);
		var orderTotalPrice = Number(0);
		
		$(opener.document).find(".orderTotalPrice").each(function(){
			orderTotalPrice+=Number($(this).val());
			
		});
		
		
		$(opener.document).find("#orderTotalPrice").text(comma(orderTotalPrice)+" 원");
		
		// opener.document.getElementById("createOrderProductList").html(appendForm);
	});
	
	function addOrderProductListWrite(data){
		var productOptionListHTML = "";
		
		$.each(data, function(){
			productOptionListHTML+='<div class="product-thumbnail">'
				+'<div class="product-content">'
					+'<div class="row">'
						+'<div class="col-lg-3 col-xl-3 col-md-3">'
							+'<div class="product-content-head">'
							+'<h5>'+this.productName+'</h5>'
							+'<div class="product-rating d-inline-block">';
								if(this.productFlag == true){
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-success">사용중</a>';
								}else{
									productOptionListHTML+='<a href="#" class="btn btn-rounded btn-danger">미사용중</a>';
								}
			productOptionListHTML+='</div>'
						+'</div>'
						+'</div>'
						+'<div class="col-lg-9 col-xl-9 col-md-9"><div class="card mb-0">';
			
							if(this.optionVOList.length == 0){
								productOptionListHTML+='<div class="card-header">'
									+'<h5 class="mb-0">등록된 옵션이 없습니다.</h5>'
									+'</div>';
								
							}else{
								var productPks = this.productPk;
								var productNames = this.productName;
								$.each(this.optionVOList, function(){									
									productOptionListHTML+='<div class="card-header" style="cursor: pointer;">'
											+'<div class="row">'
												+'<div class="col-xl-5 col-lg-5 col-sm-12 col-12">'
													+'<h5 class="mb-0">'+this.optionName+'</h5>'
												+'</div>'
												+'<div class="col-xl-3 col-lg-3 col-sm-12 col-12 mb-1">'
												+'<input class="form-control" type="number" id="orAmount" name="orAmount" placeholder="수량 입력" />'
			                                    +'</div>'
			                                    +'<div class="col-xl-2 col-lg-2 col-sm-12 col-12 mb-1">'
			                                    	+'<input class="form-control" type="text" id="orTotalPrice" name="orTotalPrice" placeholder="판매 가격 입력" />'
			                                    +'</div>'
			                                    +'<div class="col-xl-2 col-lg-2 col-sm-12 col-12">'
			                                    	+'<button name="addOrderProduct" value="'+this.optionPk+'" data-product-name="'+productNames+'" data-product-pk="'+productPks+'" data-option-name="'+this.optionName+'" class="btn btn-primary btn-block"> 상품 추가 </button>'
			                                    +'</div>'
											+'</div>'
											+'<div>'
											+'</div>'
										+'</div>';
								});
								
							}
							
			productOptionListHTML+='</div></div>'
					+'</div>'
				+'</div>'
			+'</div>';
		});
		
		$("#productOptionSearchList").html(productOptionListHTML);

	}
	// 상품 추가 끝
	
	function comma(num){
		
	    var len, point, str; 
	    
	    if(num==null){
	    	str = 'No Data';
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
	
}); // AND OF JQUERY