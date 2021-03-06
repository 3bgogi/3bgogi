<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->
            <div class="footer" style="display: none;">
                
            </div>
            <!-- ============================================================== -->
            <!-- end footer -->
            <!-- ============================================================== -->
        </div>
    </div>
    
    <!-- ============================================================== -->
    <!-- end main wrapper -->
    <!-- ============================================================== -->
    <!-- bootstap bundle js -->
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <!-- slimscroll js -->
    <script src="${pageContext.request.contextPath}/resources/vendor/slimscroll/jquery.slimscroll.js"></script>
    <!-- main js -->
    <script src="${pageContext.request.contextPath}/resources/libs/js/main-js.js"></script>
    <!-- chart chartist js -->
    <script src="${pageContext.request.contextPath}/resources/vendor/charts/chartist-bundle/chartist.min.js"></script>
    <!-- color picker js -->
    <script src="${pageContext.request.contextPath}/resources/vendor/charts/sparkline/jquery.sparkline.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-colorpicker/jquery-asColor/dist/jquery-asColor.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-colorpicker/jquery-asGradient/dist/jquery-asGradient.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-colorpicker/jquery-asColorPicker/dist/jquery-asColorPicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-colorpicker/%40claviska/jquery-minicolors/jquery.minicolors.min.js"></script>
	<div class="modal fade" id="projectModal" tabindex="-1" role="dialog" aria-labelledby="projectModalLabel" aria-hidden="true">
		<div class="modal-dialog renewal-modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header renewal-modal-header">
					<a href="#" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</a>
				</div>
				<div class="modal-body renewal-modal-dody">
					<div class="container-fluid">
					
						<aside class="page-aside renewal-page-aside">
		                    <div class="aside-content renewal-aside-content">
		                        <div class="aside-header renewal-page-aside-header">
		                            <span class="title renewal-page-modal-title" id="projectId" style="color:#ff6161;">
		                            	????????? ?????? ?????????
		                            </span>
		                            <p class="description renewal-page-description" id="projectDetail">?????? ??? ???????????? ??? ????????? ??????????????????</p>
		                            <div class="btn-group tag-list" id="projectTagList">
										<span class="badge badge-light projectTag">#??????????????? <span class="deleteTag">x</span></span>&nbsp;
										<span class="badge badge-light projectTag">#????????? <span class="deleteTag">x</span></span>&nbsp;
										<span class="badge badge-light projectTag">#?????? <span class="deleteTag">x</span></span>&nbsp;
										<span class="badge badge-light projectTag">#?????? <span class="deleteTag">x</span></span>
									</div>
		                        </div>
								
								<div class="form-group">
									<label class="custom-control custom-checkbox">
										<input id="ptTopAlarmFlag" type="checkbox" class="custom-control-input">
										<span class="custom-control-label">?????? ?????? ??????</span>
									</label>
									<label class="custom-control custom-checkbox">
										<input id="ptAlarmFlag" type="checkbox" class="custom-control-input">
										<span class="custom-control-label">????????? ??????</span>
									</label>
									<!-- <label class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input">
										<span class="custom-control-label">?????? ?????? ??????</span>
									</label> -->
								</div>
								<div class="form-group" id="proAlarmDateDiv" style="display: none;">
									<label for="proAlarmtDate"> ?????? ????????? ?????? </label>
									<input type="text" id="projectAlarmDate" class="form-control">
								</div>
								<div class="aside-nav collapse">
		                            <ul class="nav">
		                                <li><a class="nav-link active" id="tab-outline-one" data-toggle="tab" href="#outline-one" role="tab" aria-controls="home" aria-selected="true"><span class="icon"><i class="fas fa-fw fa-inbox"></i></span>????????????<span class="badge badge-primary float-right" id="projectDetailCounting">5 ???</span></a></li>
		                                <!-- <li><a class="nav-link" id="tab-outline-two" data-toggle="tab" href="#outline-two" role="tab" aria-controls="profile" aria-selected="false"><span class="icon"><i class="fas fa-fw  fa-envelope"></i></span>?????? <span class="badge badge-primary float-right">8 ???</span></a></li> -->
		                                <li><a class="nav-link" id="tab-outline-three" data-toggle="tab" href="#outline-three" role="tab" aria-controls="contact" aria-selected="false"><span class="icon"><i class="fas fa-cog"></i></span>?????? ????????????</a></li>
		                            </ul><span class="title">?????? ?????????</span>
		                            <ul class="nav nav-pills nav-stacked" id="projectTargetList">
		                                <li><a href="#"><i class="m-r-10 mdi mdi-label text-secondary"></i>?????????</a></li>
		                                <li><a href="#"><i class="m-r-10 mdi mdi-label text-secondary"></i>?????????</a></li>
		                                <li><a href="#"> <i class="m-r-10 mdi mdi-label text-secondary"></i>?????????</a></li>
		                            </ul>
		                        </div>
		                    </div>
		                </aside>
		                
		                <!-- ?????? ???????????? ?????? -->
		                <div class="main-content container-fluid p-0 tab-content" style="float:left; width:77%; margin-left: 0; border-left: 1px solid #e6e6f2; min-height: 0;">
		                	<!-- ?????? ?????? -->
							<div class="tab-pane fade show active" id="outline-one" role="tabpanel" aria-labelledby="tab-outline-one">
								<div class="email-filters" style="border-top: none;">
									<div class="email-filters-left" id="projectDetailTopOptions">
										<label class="custom-control custom-checkbox be-select-all">
											<input class="custom-control-input project_chk_all" type="checkbox" name="chk_all">
											<span class="custom-control-label"></span>
										</label>
										<div class="btn-group">
											<button class="btn btn-light" id="projectDetailDeleteButton" type="button">??????</button>&nbsp;
											<button id="projectImportantViewButton" class="btn btn-light" type="button">?????? ????????? ??????</button>&nbsp;
										</div>
										<div class="btn-group">
											<button class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button">
												???????????? 
												<span class="caret"></span>
											</button>
											<div class="dropdown-menu dropdown-menu-right" role="menu">
												<a class="dropdown-item" href="#"> ????????? ??????</a> <a
													class="dropdown-item" href="#"> ????????? ??????</a> <a
													class="dropdown-item" href="#"> ?????? ?????? ?????? </a> <a
													class="dropdown-item" href="#"> ?????? ?????? ?????? </a>
												<div class="dropdown-divider"></div>
												<a class="dropdown-item" href="#"> ????????? ??????</a> 
												<a class="dropdown-item" href="#"> ????????? ??????</a>
											</div>&nbsp;
											<button class="btn btn-light" type="button">?????? ?????????</button>&nbsp;
											<button class="btn btn-light" type="button">?????????</button>
										</div>
									</div>
									<div class="email-filters-right">
										<div class="email-search">
												<div class="input-group input-search">
													<input class="form-control" id="searchProject" type="text" placeholder="?????? ?????? ??????">
													<span class="input-group-btn">
														<button class="btn btn-secondary" type="button">
															<i class="fas fa-search"></i>
														</button>
													</span>
												</div>
											</div>
									</div>
								</div>
								<div class="email-list renewal-list-content" id="projectDetailList">

								</div>
								<div class="email-list-item email-list-item-unread"
									style="border-bottom: none;">
									<form id="projectDetailForm" name="projectDetailForm" class="chat-form">
										<input type="hidden" name="pdProFk" value="">
										<input type="text" class="form-control" id="pdDetail" name="pdDetail" style="height: 43px;">
										<div class="chat-form-buttons">
											<button type="submit" class="btn btn-link">
												<i class="far fa-smile"></i>
											</button>
											<div class="custom-file custom-file-naked">
												<input type="file" style="width:100%;" class="custom-file-input" id="pdDetailFiles" name="pdDetailFiles">
												<label class="custom-file-label" for="customFile"> <i
													class="fas fa-paperclip"></i>
												</label>
											</div>
										</div>
									</form>
								</div>
							</div>
							<!-- ?????? ?????? ??? -->
							<!-- ?????? ?????? ??????-->
		                    <div class="tab-pane fade" id="outline-two" role="tabpanel" aria-labelledby="tab-outline-two">
			                    <div class="chat-module renewal-page-chat-module">
									<div class="chat-module-top">
										<div class="chat-module-body renewal-chat-module-body">
											<div class="media chat-item  border-top">
												<div class="media-body" >
													<div class="chat-item-title">
														<span class="chat-item-author"> ????????? </span> <span>2019-03-27</span>
													</div>
													<div class="chat-item-body">
														<p>????????? ????????? ????????? ?????? ???????????? ??????????????? ???????????????</p>
													</div>
												</div>
											</div>
											<div class="media chat-item border-top">
												<div class="media-body">
													<div class="chat-item-title">
														<span class="chat-item-author"> ?????? ??? ??????</span> <span>2019-03-28</span>
													</div>
													<div class="chat-item-body">
														<p>??? ????????? ????????? ????????????????????? ???????????????</p>
													</div>
												</div>
											</div>
											<div class="media chat-item border-top">
												<div class="media-body">
													<div class="chat-item-title">
														<span class="chat-item-author"> ????????? </span> <span>2019-03-28</span>
													</div>
													<div class="chat-item-body">
														<p>????????????... ?????? ?????? ?????? ??????????????? ??? ???????????????;</p>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="email-list-item email-list-item-unread">
									<div class="chat-module-bottom"
										style="border-top: none; background: none;">
										<form class="chat-form">
											<textarea class="form-control" placeholder="?????? ??????"
												rows="1"></textarea>
											<div class="chat-form-buttons">
												<button type="button" class="btn btn-link">
													<i class="fas fa-check"></i>
												</button>
											</div>
										</form>
									</div>
									</div>
								</div>
		                    </div>
		                    <!-- ?????? ?????? ??? -->
		                    <!-- ?????? ?????? ?????? ?????? -->
		                    <div class="tab-pane fade" id="outline-three" role="tabpanel" aria-labelledby="tab-outline-three">
		                    	<div class="email-list renewal-list-content" style="height: 750px;">
			                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			                    		<div class="card">
			                    			<h3 class="card-header"> ?????? ???????????? ???????????? </h3>
			                    		</div>
			                    	</div>
			                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			                    	<div class="form-group">
			                    		<form name="addProjectTagForm">			                    		
											<label for="hue-demo"> ?????? ?????? ?????? </label>
											<div class="input-group">								
												<input type="text" class="form-control" id="ptagTitle">
												<div class="input-group-append">
													<button type="button" class="btn btn-primary">??????</button>
												</div>
											</div> 
			                    		</form>
									</div>
									<div class="form-group">
										<label for="hue-demo"> ????????? ?????? ?????? </label> 
										<input type="text" id="projectTitleColor" class="form-control demo" data-control="hue" value="#ff6161">
									</div>
									</div>
			                    	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			                            <div class="card">
			                                <h5 class="card-header">?????? ????????? ?????? ??? ??????</h5>
			                                <div class="card-body" id="multipleTargetDIv">
			                                    <select id="proTargetAddDelete" multiple='multiple'>
			                                       
			                                    </select>
			                                </div>
			                            </div>
			                        </div>
			                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			                            <div class="card">
			                                <h5 class="card-header">?????? ?????? ?????? ??????</h5>
			                                <div class="card-body">
			                                    <div class="form-group">
													<label for="proAlarmType"> ?????? ?????? ?????? </label>
													<select class="selectpicker" id="proAlarmType" data-width="100%">
														<option value="0" data-content="<span class='badge badge-dark'>??????</span>">??????</option>
														<option value="1" data-content="<span class='badge badge-danger'>?????? ??????</span>">?????? ??????</option>
														<option value="2" data-content="<span class='badge badge-primary'>????????? ??????</span>">????????? ??????</option>
														<option value="3" data-content="<span class='badge badge-success'>????????? ??????</span>">????????? ??????</option>
													</select>
												</div>
			                                </div>
			                                <!-- ?????? ?????? -->
			                                <div class="card-body" id="proRepeatWeekDiv">
			                                    <div class="form-group">
													<label for="proRepeatWeek"> ?????? ?????? </label> 
													<select class="selectpicker" id="proRepeatWeek" data-width="100%">
														<option value="0">?????????</option>
														<option value="1">?????????</option>
														<option value="2">?????????</option>
														<option value="3">?????????</option>
														<option value="4">?????????</option>
														<option value="5">?????????</option>
														<option value="6">?????????</option>
													</select>
												</div>
			                                </div>
			                                <div class="card-body" id="proRepeatDayDiv">
			                                    <div class="form-group">
													<label for="proRepeatDay"> ?????? ?????? </label> <select class="selectpicker" id="proRepeatDay" data-width="100%">
														<c:forEach var="i" begin="1" end="31" step="1">
															<option value="${i }"> ${i } ???</option>
														</c:forEach>
													</select>
												</div>
			                                </div>
			                                <div class="card-body">
			                                    <div class="form-group" style="text-align: right;">
			                                    	<button type="button" id="changeProAlarmTypeButton" class="btn btn-primary"> ?????? ?????? ?????? </button>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">	                        	
				                        <button type="button" id="finishProject" class="form-control btn btn-danger"> ?????? ???????????? </button>
			                        </div>
		                    	</div>
		                    </div>						
		                </div>
		                
		                
					</div>
				</div>
				<div class="modal-footer renewal-page-modal-footer">	
					<button class="btn btn-secondary" type="button" id="projectAlarmClearButton"> ???????????? </button>
					<a href="#" class="btn btn-secondary" data-dismiss="modal" style="text-align: right;">??????</a>
				</div>
								
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-select/js/bootstrap-select.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/datepicker/jquery.datetimepicker.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/multi-select/js/jquery.multi-select.js"></script>
    <script src="${pageContext.request.contextPath}/resources/libs/js/renewal_project.js"></script>
	<script type="text/javascript">
		$(function() {
			fileDropDown();
			
			$.datetimepicker.setLocale('kr');
			
			//$('#optgroup').multiSelect({ selectableOptgroup: true });
			
			$.ajax({
				url:"<c:url value='/delivery/sending_req.do'/>",
				method:"GET",
				async:false,
				success:function(data){
					
					
					if(data.length == 0){
						$("#sendSeqAlarm").hide();
					}else{
						$("#sendSeqAlarm").show();
						
						sendingReqHTML = "";
						$.each(data, function(){
							sendingReqHTML+='<div class="list-group-item list-group-item-action" >'
		                        +'<div class="notification-info">'
		                            +'<div class="notification-list-user-block" style="padding-left: 10px;">'
		                            	+'<span class="notification-list-user-name"> '+this.srAdminName+'('+this.srAdminId+')</span>'
		                            		+'???????????? <a href="/orders/search/customer_orders.do?dateType=or_sending_deadline&datePeriod=1&searchType=invoiceNum&searchKeyword='+this.srInvoiceNum+'">'+this.srInvoiceNum+'</a> ?????? ('+this.srReason+' )'
		                                +'<div class="notification-date"> '+this.srRegdate+'</div>'
		                            +'</div>'
		                        +'</div>'
		                    +'</div>'; 
						});
						
						$("#sendingReqHTML").html(sendingReqHTML);
						
					}
					
				}
			});
			
			$("#chatSendBtn").click(function(){
				event.stopPropagation();
				send();
				
			});
			$("#ccSendBtn").click(function(){
				event.stopPropagation();
				ccSend();
				
			});
			$("#inputMessage").keydown(function(key) {

				if (key.keyCode == 13) {
					event.stopPropagation();
					send();
				}
			});
			$("#ccInputMessage").keydown(function(key) {

				if (key.keyCode == 13) {
					event.stopPropagation();
					ccSend();
				}
			});

		});
		
		window.paceOptions = {
				target : "#progress-bar",
				startOnPageLoad : false
		};
		
			$(document).ajaxStart(function() {
				Pace.start();
			}).ajaxStop(function() {
				Pace.stop();
			});
			
			$('#orMergeSeleted').multiSelect();
		
			$("#openSocket").click();
			
			var ws;
	        var messages=document.getElementById("messageWindow");
	        
	        function openSocket(){
	            if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
	                writeResponse("?????? ?????? ??????");
	                return;
	            }

	            //????????? ?????? ????????? ??????
	            ws=new WebSocket("ws://192.168.0.66:8081/broadcasting.do");
	            
	            ws.onopen=function(event){
	                if(event.data===undefined) return;
	                
	                writeResponse(event.data);
	            };
	            
	            ws.onmessage=function(event){
	                writeResponse(event.data);
	            };
	            
	            ws.onclose=function(event){
	                writeResponse("????????? ??????????????????. ??????????????? ????????????");
	                
	            }
	        }
	        
	        function send(){
	            var text=document.getElementById("inputMessage").value+","+document.getElementById("sender").value;
	            if(document.getElementById("inputMessage").value == ''){
	            	
	            	alert("????????? ????????? ????????????");
	            	document.getElementById("inputMessage").focus();
	            	
	            	return;
	            	
	            	
	            }
	            
	            ws.send("filenameEnded*"+text);
	            
	            text="";
	            document.getElementById("inputMessage").value="";
	            
	            messages.scrollTop = messages.scrollHeight;
	            
	            
	        }
	        
	        function closeSocket(){
	            ws.close();
	        }
	        function writeResponse(text){
	            messages.innerHTML+=text+"\n";
	        }

	        // ?????????
			$("#openSocket").click();
			$("#ccOpenSocket").click();
			
			var ccWs;
	        var ccMessages=document.getElementById("ccMessageWindow");
	        
	        function ccOpenSocket(){
	        	
	            if(ccWs!==undefined && ccWs.readyState!==WebSocket.CLOSED){
	            	
	                return;
	            }

	            //????????? ?????? ????????? ??????
	            ccWs=new WebSocket("ws://192.168.0.66:8081/ccsocket.do");
	            
	            ccWs.onopen=function(event){
	                if(event.data===undefined) return;
	                
	                ccWriteResponse(event.data);
	            };
	            
	            ccWs.onmessage=function(event){
	            	ccWriteResponse(event.data);
	            };
	            
	            ccWs.onclose=function(event){
	            	ccWriteResponse("????????? ??????????????????. ??????????????? ????????????");
	                
	            }
	        }
	        
	        function ccSend(){
	            var text=document.getElementById("ccInputMessage").value+","+document.getElementById("sender").value;
	            if(document.getElementById("ccInputMessage").value == ''){
	            	
	            	alert("????????? ????????? ????????????");
	            	document.getElementById("ccInputMessage").focus();
	            	
	            	return;
	            	
	            }
	            
	            
	            ccWs.send(text);
	            text="";
	            document.getElementById("ccInputMessage").value="";
	            
	            ccMessages.scrollTop = ccMessages.scrollHeight;

	        }
	        function ccWriteResponse(text){
	        	ccMessages.innerHTML+=text+"\n";
	        }
	        
	        function fileDropDown(){
	            var dropZone = $("#messageWindow");
	            dropZone.on('dragleave',function(e){
	                e.stopPropagation();
	                e.preventDefault();
	                $(e.target).css({
	                	"background-color": "#e9ecef"
	                });
	            });
	            dropZone.on('dragover',function(e){
	                e.stopPropagation();
	                e.preventDefault();
	                $(e.target).css({
	                	"background-color": "#FFA2A2"
	                });
	            });
	            
	            
	            dropZone.on('drop',function(e){
	                e.preventDefault();
	                // ???????????? ?????? css
	                dropZone.css('background-color','#e9ecef');
	                
	                e.dataTransfer = e.originalEvent.dataTransfer; //2
	                var files = e.target.files || e.dataTransfer.files;
	             
	                if (files.length > 1) {
	                    alert('????????? ?????????.');
	                    return;
	                }
	                
	                var file = files[0];
	                ws.binaryType = "arraybuffer";
	                
	                
	                ws.send('filename:'+file.name);
	                
	             	var reader = new FileReader();
	             	var rawData = new ArrayBuffer();
	             	
	             	reader.loadend = function(){
	             		
	             	}
	             	
	             	reader.onload = function(e){
	             		rawData = e.target.result;
	             		ws.send(rawData);
	             		ws.send('end*');
	             	}
	             	
	             	reader.readAsArrayBuffer(file);
	                
	                
	            });
	        }

	</script>
</body>

</html>