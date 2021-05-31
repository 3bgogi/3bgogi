jQuery(document).ready(function($) {
	
	jQuery.ajaxSettings.traditional = true;
	
	$('.demo').each(function() {
		$(this).minicolors({control : $(this).attr('data-control') || 'hue',
							defaultValue : $(this).attr('data-defaultValue') || '',
							format : $(this).attr('data-format') || 'hex',
							keywords : $(this).attr('data-keywords') || '',
							inline : $(this).attr('data-inline') === 'true',
							letterCase : $(this).attr('data-letterCase') || 'lowercase',
							opacity : $(this).attr('data-opacity'),
							position : $(this).attr('data-position') || 'bottom left',
							swatches : $(this).attr('data-swatches') ? $(this).attr('data-swatches').split('|'): [],
							change : function(value, opacity) {
								if (!value)
									return;
								if (opacity)
									value += ', '+ opacity;
								if (typeof console === 'object') {
									console.log(value);
								}
							},
							theme : 'bootstrap'
				});
			});//색상

    
	$('#projectAlarmDate').datetimepicker({
		format:'Y-m-d H:i',
		lang:'kr'
	});
	
	$('.deleteTag').each(function(){
			$(this).hide();
	});
	
	//처음에 ajax 로 자기 태그 불러오기 값이 -1 일 경우 자신의 pk로 불러옴
	
	/*$(document).on("hover", ".projectTag", function(){
		$(this).find(".deleteTag").show();
		
	}, function(){
		$(this).find(".deleteTag").hide();
		
	});*/
	
	
	//다른 팀원 태그 보기를 누를 경우
	$("#showAnotherTeamProjectTag").click(function(){
		
		if($(this).is(":checked")){
			selectProjectTag(0);
			
		}else{
			selectProjectTag(-1);
			
		}
	});
	
	//업무페이지의 왼쪽 픽스바에 태그 넣기
	function writeProjectTag(projectTagList){
		var searchProjectTagListHTML = "";
		
		$.each(projectTagList, function(idx){
			searchProjectTagListHTML+='<li><a href="#" class="projectTags projectSearchCondition">'+this.ptagTitle+'</a></li>';
		});
		
		$("#searchProjectTagList").html(searchProjectTagListHTML);
	}
	//업무 태그에 가까이 댈 경우 삭제버튼
	$(document).on({
	    mouseenter: function () {
	    	 $(this).find(".deleteTag").show();
	    },
	    mouseleave: function () {
	    	$(this).find(".deleteTag").hide();
	    }
	}, ".projectTag");
	
	//업무 완료 사항 상단 검색 기능
	$("#searchProject").keyup(function(){
		
		var searchProejctVal =$("#searchProject").val();
		
		if(searchProejctVal != null || searchProejctVal  != ''){
			
			$(".msg").each(function(){
				
				if($(this).text().includes(searchProejctVal)){
					$(this).parent().parent().show();
				}else{
					$(this).parent().parent().hide();
				}
				
			});
			
		}else{
			
			$(".msg").each(function(){
				$(this).parent().parent().show();
			});
			
		}
		
	});//업무 완료 사항 상단 검색 기능 끝
	
	$(document).on("click","#projectImportantViewButton", function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			pdImportant = 0;
			openProject();
		}else{
			$(this).addClass("active");
			pdImportant = 1;
			openProject();
		}
	});
	

	//프로젝트 기능 함수 상세 분할
	function projectTitle(proTitle, proDetail, proTitleColor){
		$("#projectId").html(proTitle);
		$("#projectId").css("color", proTitleColor);
		
		if(proDetail != null || proDetail != ""){
			$("#projectDetail").html(proDetail);
			
		}else{
			$("#projectDetail").text("");
		}
		
	}
	
	//설정에서 업무 대상자 추가 및 설정 관련
	function projectTargetInc(proAdmins){
		projectTargetIncHTML = '<select id="proTargetAddDelete" multiple="multiple">';
		
		$.each(proAdmins, function(idx){
			projectTargetIncHTML+='<option value="'+this.adminPk+'" ';
			
			if(this.proInc == true){
				projectTargetIncHTML+=' data-pt-pk="'+this.ptPk+'" selected="selected">';
				
			}else{
				
				projectTargetIncHTML+=' data-pt-pk="0">';
			}
			
			projectTargetIncHTML+=this.adminName+'</option>';
		});
		
		projectTargetIncHTML += '</select>'; 
		
		$("#multipleTargetDIv").html(projectTargetIncHTML);
		
		$('#proTargetAddDelete').multiSelect({
			afterSelect: function(values){
				//업무 대상자 추가할 경우
				var adminPk = values;
			    
				$.ajax({
				    type       : 'GET',
				    async      : false,
				    data       : {
				    	"ptProFk":projectNum,
				    	"ptAdminFk":adminPk
				    },
				    url        : '/project/add_project_target.do',
				    success    : function(data){
				    	
				    	alert(data);
				    	openProject();
				    }
				    
				});
				
			  },
			  afterDeselect: function(values, text){
				  var pks = values;
				  //업무 대상자 삭제할 경우
				  $("#proTargetAddDelete option").each(function(){					  
					  if(pks == $(this).val() ){
						  var ptpk = $(this).data("pt-pk");
						  
						  $.ajax({
							    type       : 'GET',
							    async      : false,
							    data       : {
							    	"ptPk":ptpk,
							    	"proPk":projectNum
							    },
							    url        : '/project/delete_project_target.do',
							    success    : function(data){
							    	
							    	alert(data);
							    	openProject();
							    }
							    
							});
						  
					  }
				  });
			  }
			  
		});

	}
	
	//업무 태그 관련
	function projectTag(projectTagList){
		if(projectTagList){
			var projectTagListHTML = "";
			$.each(projectTagList, function(idx){
				
				projectTagListHTML +='<span class="badge badge-light projectTag">#'+this.ptagTitle+'<span class="deleteTag" style="display:none;" data-ptag-pk="'+this.ptagPk+'">x</span></span>&nbsp;';
				
			});
			
			$("#projectTagList").html(projectTagListHTML);
		}
	}
	
	//설정 관련
	function projectConfig(proTitleColor, proAlarmDate, projectTargetList){
		$("#projectTitleColor").val(proTitleColor);
		$("#projectAlarmDate").val(allFormatDate(proAlarmDate));
		
		var projectTargetListHTML = "";
		var projectTargetOptionListHTML = "";
		
		//업무 대상자 넣기
		$.each(projectTargetList, function(idx){
			
			projectTargetListHTML+='<li><a href="#"><i class="m-r-10 mdi mdi-label text-secondary"></i>'+this.adminName+'</a></li>';
			
			if(this.proIdentify == true){
				
				$("#ptTopAlarmFlag").val(this.ptPk);
				if(this.ptTopAlarmFlag == true){
					
					$("#ptTopAlarmFlag").prop("checked","checked");
				}else{
					$("#ptTopAlarmFlag").prop("checked","");
				} 
				
			}
			
		});
		$("#projectTargetList").html(projectTargetListHTML);
	}
	
	function writeProjectDetailList(projectDetailList){
		
		
		var projectDetailListHTML = "";
		
		if(projectDetailList[0] == null ){

			projectDetailListHTML+='<div class="email-list-item email-list-item--unread project-finished-list" style="text-align:center;"><h4>데이터가 없습니다.</h4></div>';
			
		}else{
			
			$.each(projectDetailList, function(idx){
				
				projectDetailListHTML+='<div class="email-list-item email-list-item--unread project-finished-list">'
					+'<div class="email-list-actions">'
					+'<label class="custom-control custom-checkbox">'
					+'<input class="custom-control-input project_checkboxes" type="checkbox" name="pdPk" value="'+this.pdPk+'">'
					+'<span class="custom-control-label"></span>'
					+'</label>';
				
				//중요사항 체크 여부
				if(this.pdImportant == true){
					projectDetailListHTML+='<a name="pdImportant" class="pdImportant favorite active" data-pd-pk="'+this.pdPk+'" href="#">';
				}else{
					projectDetailListHTML+='<a name="pdImportant" class="pdImportant favorite" data-pd-pk="'+this.pdPk+'" href="#">';
				}
				
				projectDetailListHTML+='<span>'
					+'<i class="fas fa-star"></i>'
					+'</span></a>'
					+'</div>'
					+'<div id="heading'+this.pdPk+'" class="email-list-detail" data-toggle="collapse" data-target="#collapse'+this.pdPk+'" aria-expanded="true" aria-controls="collapse'+this.pdPk+'">'
					+'<span class="date float-right"><span class="icon">';
				
				//파일이 있는지 여부
				if(this.pdFile1){
					
					//이미지 관련 파일이라면
					if(this.pdFileExtType == '.jpg' || this.pdFileExtType == '.gif' || this.pdFileExtType == '.png' || this.pdFileExtType == '.jpeg'){
						projectDetailListHTML+='<i class="fas fa-image"></i>';
						
						//일반 파일이라면
					}else{
						projectDetailListHTML+='<i class="fas fa-paperclip"></i>';
						
					}
				}
				
				projectDetailListHTML+='</span>'+allFormatDate(this.pdFinishTime)+'</span><span class="from"><i class="m-r-10 mdi mdi-label text-secondary"></i>'+this.pdAdmin+'</span>'
				+'<p class="msg">'+this.pdDetail+'</p>'
				+'</div>'
				+'</div>';
				
				//파일이 있다면 아코디언식으로 볼 수 있도록 처리
				if(this.pdFile1){
					
					projectDetailListHTML+='<div id="collapse'+this.pdPk+'" class="collapse" aria-labelledby="heading'+this.pdPk+'" data-parent="#accordion3" style="border: 1px solid #e6e6f2;">';
					
					if(this.pdFileExtType == ".png" || this.pdFileExtType == ".jpg" || this.pdFileExtType == ".gif" || this.pdFileExtType == ".jpeg"){
						projectDetailListHTML+='<img class="" src="/resources/pds_upload/project_file/'+this.pdFile1+'" alt="Card image cap" style="display: block; width: 100%; margin: 0px auto;">';
					}
					
					projectDetailListHTML+='<div class="card-body">'
						+'<p style="text-align: right">'
						+'<a download href="/resources/pds_upload/project_file/'+this.pdFile1+'">'
						+'<i class="fas fa-download "></i> '+this.pdFileRealName+'</a>'
						+'</p>'
						+'</div>'
						+'</div>';
					
				}
				
			});
		}
		
		
		$("#projectDetailList").html(projectDetailListHTML);
	}
	
	//상단 알람 반복 데이터 설정
	function projectAlarmSetting(proAlarmType, proRepeatDay, proRepeatWeek){

		if(proAlarmType == null){
			$("#proAlarmType option").each(function(){
				if($(this).val() == 0){
					$(this).prop("selected", true);
				}
			});
			
		}else{
			$("#proAlarmType option").each(function(){
				if( $(this).val() == proAlarmType ){
					$(this).prop("selected", true);
					
				}
			});
			
		}
		
		if(proAlarmType == null || proAlarmType == 0 || proAlarmType == 1){
			$("#proRepeatDayDiv").hide();
			$("#proRepeatWeekDiv").hide();
			
		}else if(proAlarmType == 2){
			$("#proRepeatWeekDiv").show();
			$("#proRepeatDayDiv").hide();
			$("#proRepeatWeek option").each(function(){
				if($(this).val() == proRepeatWeek){
					$(this).prop("selected", true);
				}
			});
			
			$("#proRepeatWeek").selectpicker('refresh');
			
		}else if(proAlarmType == 3){
			$("#proRepeatDayDiv").show();
			$("#proRepeatWeekDiv").hide();
			$("#proRepeatDay option").each(function(){
				if($(this).val() == proRepeatDay){
					$(this).prop("selected", true);
				}
			});
			
			$("#proRepeatDay").selectpicker('refresh');
			
		}
		
		$("#proAlarmType").selectpicker('refresh');
		
	}
	
	var projectNum = 0;
	var pdDisplayFlag = 1;
	var pdImportant = 0;
	//업무페이지 기능
	
	$(document).on('click', '.showDetailProject', function(){
		projectNum = $(this).find("input[name=proPk]").val();
		openProject();
		
		
		
	});
	
	$(document).on('click', '.projectTitle', function(){
		projectNum = $(this).next().val();
		openProject();
		
	});
	
	//업무 전체 사항 불러오기
	function openProject(){
		
		$.ajax({
		    type       : 'GET',
		    async      : false,
		    data       : {
		    	"proPk":projectNum,
		    	"pdDisplayFlag":pdDisplayFlag,
		    	"pdImportant":pdImportant
		    },
		    url        : '/project/project_detail.do',
		    success    : function(data){
		    	//업무 대상자 가져오기
		    	var targets;
		    	
		    	$.ajax({
				    type       : 'GET',
				    async      : true,
				    data       : {
				    	"proPk":projectNum
				    },
				    datatype:'json',
				    url        : '/project/project_detail_target.do',
				    success    : function(resData){
				    	projectTargetInc(resData);
				    }
				    
				});
		    	
		    	
		    	//업무 개수 가져오기
		    	$.ajax({
				    type       : 'GET',
				    async      : true,
				    data       : {
				    	"proPk":projectNum
				    },
				    datatype:'json',
				    url        : '/project/project_detail_count.do',
				    success    : function(resData){
				    	$("#projectDetailCounting").text(resData+" 개");
				    }
				    
				});
		    	
		    	//업무명, 업무 상세 및 색상
				$("input[name=pdProFk]").val(data.projectDetails.proPk);
				
				projectTitle(data.projectDetails.proTitle,data.projectDetails.proDetail, data.projectDetails.proTitleColor);
				
				//업무 태그
				projectTag(data.projectDetails.projectTagList);
				
				//업무 좌변 픽스 설정 사항
				projectConfig(data.projectDetails.proTitleColor, data.projectDetails.proAlarmDate, data.projectDetails.projectTargetList);
				
				//업무완료사항
				writeProjectDetailList(data.projectDetails.projectDetailList);
				
				$("#pdDetail").focus();
		       
				abledProject(data.projectDetails.proDeleteFlag, data.projectDetails.proFinishedFlag, data.projectOwn);
				
				projectAlarmSetting(data.projectDetails.proAlarmType, data.projectDetails.proRepeatDay, data.projectDetails.proRepeatWeek);
		    }
		}).then(function(){
			$.ajax({
				url:"/project/project_alarm.do",
				method:"POST",
				success:function(data){
					projectTopRewrite(data);
					
				}
			});
		});
		
	}
	
	function abledProject(deleteFlag, finishedFlag, projectOwn){
		
		if(deleteFlag == true || finishedFlag == true){
			$("#ptTopAlarmFlag").prop("disabled", true);
			$("#ptAlarmFlag").prop("disabled", true);
			$("#projectDetailForm").hide();
			$("#projectDetailTopOptions").hide();
			$("#projectTagList").hide();
		}else{
			$("#ptTopAlarmFlag").prop("disabled", false);
			$("#ptAlarmFlag").prop("disabled", false);
			$("#projectDetailForm").show();
			$("#projectDetailTopOptions").show();
			$("#projectTagList").show();
		}
		
		if(projectOwn == true){
			$("#tab-outline-three").show();
		}else{
			$("#tab-outline-three").hide();
			$("#tab-outline-one").click();
		}
			
	}
	
	//업무 완료사항 함수
	$("form[name=projectDetailForm]").submit(function(){

		var param = $("form[name=projectDetailForm]").serialize();
		
		var formData = $("form[name=projectDetailForm]")[0];
		
		var forms = new FormData(formData);
		
		$.ajax({   
			   type: "POST",
			   url: "/project/project_detail.do",
			   processData: false,
			   contentType: false,
			   data: forms,
			   success:function(data){
				   alert(data);
				   openProject(projectNum);
			  }
		});
		
		$("input[name=pdDetail]").val("");
		
		return false;
		
	});
	//상단 고정 상태 변경 : 업무 상세 확인에서
	$("#ptTopAlarmFlag").click(function(){
		var status;
		
		if( $(this).is(":checked") ){
			status = 1;
			
		}else{
			status = 0;
		}
		
		$.ajax({   
			   type: "GET",
			   url: "/project/project_top_alarm.do",
			   data:{
				   "proPk":projectNum,
				   "status":status
			   },
			   success:function(data){
				   
				   if(status == 1){
						
						alert("상단 고정 완료.");
					}else{
						
						alert("상단 삭제 완료.");
					}
				   
				   projectTopRewrite(data);
				   
				   var nowPageUrl = document.location.href;
				   var projectPageCheck = nowPageUrl.indexOf("/project/projects.do");
				   
				   if( projectPageCheck !== -1){
					   callProjectList();
				   }
				   
			  }
		});
		
	});
	
	//상단 고정 상태 변경  : 업무확인 페이지에서
	$(document).on('click', '.changeFixedProjectStatus', function(){
		
		var proPk = $(this).attr("data-pro-pk");
		
		var status;
		
		if($(this).hasClass("active") ===true){
			status = 0;
			$(this).removeClass("active");
		}else{
			status = 1;
			$(this).addClass("active");
		}
		
		
		$.ajax({   
			   type: "GET",
			   url: "/project/project_top_alarm.do",
			   data:{
				   "proPk":proPk,
				   "status":status
			   },
			   success:function(data){
				   
				   if(status == 1){
						
						alert("상단 고정 완료.");
					}else{
						
						alert("상단 삭제 완료.");
					}
				   
				   projectTopRewrite(data);
			    
			  }
		});
	});
	
	//업무 완료사항 중요도 체크 상태 변경 : 업무 상세 체크 모달에서
	$(document).on('click', 'a[name=pdImportant]', function(){
		var pdPk = $(this).attr("data-pd-pk");
		var status = 0;
		
		if($(this).hasClass("active")){
			status = 0;
		}else{
			status = 1;
		}
		
		$.ajax({   
			   type: "GET",
			   url: "/project/project_important_status.do",
			   data:{
				   "ptProFk":projectNum,
				   "pdPk":pdPk,
				   "status":status
			   },
			   success:function(data){
				   openProject();
			    
			  }
		});
		
	});
	
	//완료사항에서 전체체크 상태에 따라 하위 체크박스 상태값 변화
	$(".project_chk_all").click(function(){
		
		if($(this).is(":checked")){
			$(".project_checkboxes").prop("checked","checked");
			
		}else{
			$(".project_checkboxes").prop("checked","");
		}
		
	});
	
	//완료사항에서 전체체크가 되어있을 경우
	//하나라도 체크박스를 풀 때에 전체체크박스의 체크 풀기
	$(document).on("click", ".project_checkboxes", function(){
		
		if(!$(this).is(":checked")){
			
			if($(".project_chk_all").is(":checked")){
				
				$(".project_chk_all").prop("checked","");
			}
			
		}
		
	});
	
	//업무 완료사항 다중 삭제 기능
	$("#projectDetailDeleteButton").click(function(){
		var pdPks = $("input:checkbox[name=pdPk]:checked").length;
		
		var pdPkArray = [];
		
		if(pdPks == 0){
			alert("선택된 업무가 없습니다. ");
			
			return false;
		}
		
		if(confirm("선택된 업무를 삭제하시겠습니까?")){
			
			
			for(var i=0; i < pdPks; i++){
				pdPkArray.push($("input:checkbox[name=pdPk]:checked")[i].value);
			}
			
			
			
			$.ajax({   
				type: "GET",
				url: "/project/project_delete.do",
				data:{
					"ptProFk":projectNum,
					"pdPkArray":pdPkArray
				},
				success:function(data){
					if(data < 0){
						alert("삭제 불가능한 업무 완료사항 개수 "+data+" 개")
					}
					
					openProject();
					
				}
			});
		}
		
		
	});
	
	//업무 제목 명 색상 변경
	$("#projectTitleColor").focusout(function(){
		var proTitleColor = $("#projectTitleColor").val();
		
		if(proTitleColor == null){
			
			return false;
		}
		
		$.ajax({   
			type: "GET",
			url: "/project/project_title_color_change.do",
			data:{
				"proPk":projectNum,
				"proTitleColor":proTitleColor
			},
			success:function(data){
				openProject();
			}
		});
		
	});//업무 제목 명 색상 변경
	
	//업무 알람일 변경
	$("#projectAlarmDate").focusout(function(){
		
		var proAlarmDateString = $(this).val();
		
		if(proAlarmDateString == null){
			
			return false;
		}
		
		$.ajax({   
			type: "GET",
			url: "/project/change_alarm_date.do",
			data:{
				"proPk":projectNum,
				"proAlarmDateString":proAlarmDateString
			},
			success:function(msg){
				
			}
		});
		
	});//업무 알람일 변경
	
	$("#ptAlarmFlag").click(function(){
		if($(this).is(":checked")){
			$("#proAlarmDateDiv").show();
			
		}else{
			$("#proAlarmDateDiv").hide();
			
		}
	});
	
	$("#ptTopAlarmFlag").prop("checked","");
	
	$("form[name=addProjectTagForm]").submit(function(){
		var ptagTitle = $("#ptagTitle").val();
		
		if(ptagTitle == null || ptagTitle.trim() == ''){
			event.preventDefault();
			return false;
			
		}
		
		$.ajax({   
			type: "GET",
			url: "/project/add_project_tag.do",
			data:{
				"ptagProFk":projectNum,
				"ptagTitle":ptagTitle
			},
			success:function(data){
				$("#ptagTitle").val("");
				if(data == -1){
					alert("태그는 5개를 초과할 수 없습니다. ");
				}
				openProject();
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	$(document).on("click", "#projectTagList .deleteTag", function(){
		var ptagPk = $(this).attr("data-ptag-pk");
		
		$.ajax({   
			type: "GET",
			url: "/project/delete_project_tag.do",
			data:{
				"ptagProFk":projectNum,
				"ptagPk":ptagPk
			},
			success:function(data){
				if(data == -1){
					alert("삭제 권한이 없습니다.");
				}
				openProject();
			}
		});
		
	});
	
	//업무 삭제 기능 : 실제로 데이터베이스에서 삭제되지는 않음
	$(document).on("click", ".deleteAllProjectData", function(){
		var proPk = $(this).data("pro-pk");
		
		if(confirm("해당 업무를 삭제하시겠습니까?")){
			var deletePass = prompt("사용자의 비밀번호를 입력해주세요.");
			
			var form = document.createElement("form");
			form.method="post";
			form.action="/project/delete_project.do";
			
			var proPkInput = document.createElement("input");
			proPkInput.type="hidden";
			proPkInput.name="proPk";
			proPkInput.value=proPk;
			
			var passInput = document.createElement("input");
			passInput.type="hidden";
			passInput.name="deletePass";
			passInput.value=deletePass;
			
			$(form).append(proPkInput);
			$(form).append(passInput);
			
			$('body').append(form); 
			  
			form.submit();
		
		}
		
	});
	
	//업무 태그 불러오기 기능
	function selectProjectTag(selectType){
		
		$.ajax({   
			type: "GET",
			url: "/project/project_tag_list.do",
			data:{
				"adminPk":selectType
				
			},
			success:function(data){
				writeProjectTag(data);
				
			}
		});
	}
	
	//업무 태그를 누를 시 active 효과 
	$(document).on("click", ".projectSearchCondition", function(){
		
		if($(this).hasClass("projectTags")){
			if($(this).hasClass("active")){
				$(this).removeClass("active");
				
			}else{
				$(this).addClass("active");
				
			}
		}
		
		callProjectList();
		
	});
	
	//업무 검색 조건
	function callProjectList(){
		
		var todayProject = $("input:checkbox[name='todayProject']").is(":checked");
		var showHide = $("input:checkbox[name='showHide']").is(":checked");
		var finishedProject = $("input:checkbox[name='finishedProject']").is(":checked");
		var showAnotherTeamProject = $("input:checkbox[name='showAnotherTeamProject']").is(":checked");
		var alarmOnlyProject = $("input:checkbox[name='alarmOnlyProject']").is(":checked");
		
		var projectTags = new Array();
		
		$(".projectTags").each(function(){
			if( $(this).hasClass("active")){
				projectTags.push($(this).text());
				
			}
		});	
		
		$.ajax({   
			type: "GET",
			url: "/project/call_project_list.do",
			data:{
				"todayProject":todayProject,
				"showHide":showHide,
				"finishedProject":finishedProject,
				"showAnotherTeamProject":showAnotherTeamProject,
				"alarmOnlyProject":alarmOnlyProject,
				"projectTags":projectTags
			},
			success:function(data){
				writeCallProjectList(data);
				
			}
		});
		
	}//callProjectList
	
	//업무페이지에서 업무 조건 클릭 시 조건에 따라 다시불러오기 기능
	function writeCallProjectList(projectList){
		var projectListHTML = ""; 
		$.each(projectList, function(){
				
			projectListHTML+='<div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-12 table-grid-test">'
	            +'<div class="card">'
	            	+'<input type="hidden" value="'+this.proPk+'" name="proPk">';
					
					if(this.proThumbnailImage != null){
						projectListHTML+='<img class="card-img-top renewal-img-padding" src="/resources/images/project_image/'+this.proThumbnailImage+'"'
							+' alt="'+this.proThumbnailImageRealName+'" align="middle">';
					}

					projectListHTML+='<div class="card-header d-flex">'
	                    +'<h4 data-toggle="modal" data-target="#projectModal" class="mb-0 projectTitle" style="color:'+this.proTitleColor+'; cursor: pointer;">'+this.proTitle+'</h4>'
	                    +'<input type="hidden" name="proPk" value="'+this.proPk+'">'
	                    +'<div class="dropdown ml-auto">'
	                        +'<a class="toolbar" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-dots-vertical"></i>  </a>'
	                        +'<div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">'
	                            +'<a class="dropdown-item deleteAllProjectData" data-pro-pk="'+this.proPk+'" href="#"> 업무 삭제 </a>'
	                            +'<a class="dropdown-item changeFixedProjectStatus';
					
								if(this.projectTargetList[0].ptTopAlarmFlag == true){
									projectListHTML+=' active';
								}
	                            
								projectListHTML+='" data-pro-pk="'+this.proPk+'" href="#"> 상단 알람 </a>'
	                        +'</div>'
	                    +'</div>'
	                +'</div>'
	                +'<div class="card-body">'
	                    +'<p class="card-text">'+this.proDetail+'</p>'
	                +'</div>'
	                +'<div class="card-footer">'
	                	+'<div class="btn-group tag-list">';
								
								$.each(this.projectTagList, function(){
									
									projectListHTML+='<span class="badge badge-light">#'+this.ptagTitle+'</span>&nbsp;';
								});

					projectListHTML+='</div>'
	                +'</div>'
	            +'</div>'
	        +'</div>';
	        
		});
		
		$("#projectListDivs").html(projectListHTML);
		
	}//writeCallProjectList
	
	
	//알람 반복 타입 정할 때
	$("#proAlarmType").change(function(){
		if($(this).val() == 2){
			$("#proRepeatWeekDiv").show();
			$("#proRepeatDayDiv").hide();
			
		}else if($(this).val() == 3){
			$("#proRepeatDayDiv").show();
			$("#proRepeatWeekDiv").hide();
			
		}else{
			$("#proRepeatDayDiv").hide();
			$("#proRepeatWeekDiv").hide();
			
		}
	});
	
	//알람 반복 타입 설정 후 저장할 때
	$("#changeProAlarmTypeButton").click(()=>{
		var proAlarmType = $("#proAlarmType").val();
		var proRepeatWeek = $("#proRepeatWeek").val();
		var proRepeatDay = $("#proRepeatDay").val();
		
		$.ajax({   
			type: "GET",
			url: "/project/setting_alarm.do",
			data:{
				"proPk":projectNum,
				"proAlarmType":proAlarmType,
				"proRepeatWeek":proRepeatWeek,
				"proRepeatDay":proRepeatDay
			},
			success:function(data){
				alert(data);
			}
		});
	});
	
	//알람 해제하기
	$("#projectAlarmClearButton").click(function(){
		
		$.ajax({   
			type: "GET",
			url: "/project/clear_alarm.do",
			data:{
				"proPk":projectNum
			},
			success:function(data){
				alert(data);
			}
		});
		
	});
	
	
	//업무 최종 완료 처리하기
	$("#finishProject").click(function(){
		if(confirm('업무 완료를 할 경우 완료사항을 더 이상 등록할 수 없습니다. 최종 완료 처리를 하시겠습니까?')){
			
			$.ajax({   
				type: "GET",
				url: "/project/project_done.do",
				data:{
					"proPk":projectNum
				},
				success:function(data){
					alert(data);
				}
			});
			
		}
	});
	//업무 페이지 기능 끝
	
	

}); // AND OF JQUERY