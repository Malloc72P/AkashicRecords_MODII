var writePost_bindingEvent_limiter	 = 0
var writeSeries_bindingEvent_limiter = 0

function pwCheck_eventBinder(id_input_submitPWCHK, openThisPanel){
	//openThisPanel은 패스워드체크패널로 검사 후 열고 싶은 패널을 의미합니다
	$("#"+id_input_submitPWCHK).click(function(event){
		alert("pwChecker.jsp >>> pwCheck_eventBinder >>> 함수 호출됨")
		event.preventDefault()
		var pw = $("#"+"id_input_pwchkPW").val()
		console.log("mainPage.js >>> pwCheck_eventBinder >>> pw : "+pw)
		pwCheck_AJAX( pw, openThisPanel )	
	})
	
}

function writePost_eventBinder(){
	if(writePost_bindingEvent_limiter == 0){
		bind_Open_Panel( "id_a_writePost"     , "id_div_pwCheckerPanel" ,"id_div_mainContent")
		$("#id_input_submitPWCHK").unbind("click")
		pwCheck_eventBinder("id_input_submitPWCHK", "id_div_writePostPanel")
//		pwCheck_eventBinder("id_input_submitPWCHK")
//		writePost_bindingEvent_limiter = 1;
	}
	else{
		
	}
}
function writeSeries_eventBinder(){
	if(writeSeries_bindingEvent_limiter == 0){
		bind_Open_Panel( "id_a_writeSeries", "id_div_pwCheckerPanel" ,"id_div_mainContent")
		$("#id_input_submitPWCHK").unbind("click")
		pwCheck_eventBinder("id_input_submitPWCHK", "id_div_writeSeriesPanel")
//		pwCheck_eventBinder("id_input_submitPWCHK")
//		writePost_bindingEvent_limiter = 1;
	}
	else{
		
	}
}
function panelOpener_viewContent(event){
	event.preventDefault();
	alert("mainPage.js >>> panelOpener_viewContent \n >>> event.target : "+event.target)
}

function submitPost_eventBinder(id_input_submitPost , id_input_writePostTitle, id_input_writePostContent, id_select_writePostSeries){
	$("#"+id_input_submitPost).click(function( event ){
		event.preventDefault()
		
		var post_title   = $( "#"+id_input_writePostTitle   ).val()
		tinyMCE.triggerSave();
		var post_content = $( "#"+id_input_writePostContent ).val()
		
		var series_id = $("#"+id_select_writePostSeries +"> option:selected").val()
		
		submitPost(post_title, post_content,series_id)
	})
}

function submitSeries_eventBinder(id_input_submitSeries , id_input_writeSeriesTitle){
	$("#"+id_input_submitSeries).click(function( event ){
		event.preventDefault();
		var seriesTitle = $("#"+id_input_writeSeriesTitle).val();
		console.log("seriesTitle : "+seriesTitle);
		submitSeries(seriesTitle);
	})
}

/******************************************************************
 * JQUERY FUNCTION 
 * 		SUBSECTIN SELECTING FUNCTION BINDER
 * ###DESCRIPTION
 * bind_Select_Function() >>> 메인페이지에서 기능을 고를 수 있게 해주는 함수
 * 각각의 function selector에 click event에 fire되는 CALL-BACK-FUNCTION이 여기에 있다
 * 	CALL-BACK-FUNCTION 안에는 if($(this).attr("id") == "sel-2") {} 조건식이 있는데, 여기에 각 기능에 대해 실행될 코드가 작성되면 된다
 *******************************************************************/
function bind_Select_Function(id_div_funcList , id_div_subSection , id_tempData_in_pageNum){
				//".func-list" , "sub-section" , "id_tempData_pageNum"
	effectOn_selectedFuncSelector( id_div_funcList )
	console.log(" bind_Select_Function ")
	console.log("bind_Select_Function>>> i will send this : "+$("#"+id_tempData_in_pageNum).val())
	getRecentPosts(id_div_subSection ,id_tempData_in_pageNum)
	
	$("#"+id_div_funcList).find("a[href^='#sel-']").each(function(){
		console.log("find a tag")
		$(this).click(function(event){//this는 클릭된 functionSelector이다
			
			console.log("functionSelector clicked <<<")
			
			var $prev_funcSelector = getCurrSelectedFuncObj(id_div_funcList)
			console.log("CALL-BACK >>> "+$prev_funcSelector.attr("id"))
			$prev_funcSelector.removeClass("fader")
			effectOff_selectedFuncSelector(id_div_funcList)
			hide_subSection($prev_funcSelector.attr("id"))
			
			$prev_funcSelector.attr("href","#"+$prev_funcSelector.attr("id"))
			if($(this).attr("id") == "sel-1"){//this는 클릭된 functionSelector이다
				//기능이 바뀌었으니, SUB-SECTION을 전부 지운다
				$("#"+id_div_subSection).empty()
				
				console.log("mainPage.js.bind_Select_Function >>> 프로필 보기 기능 선택됨")
				getProfilePage(id_div_subSection)
			}
			if($(this).attr("id") == "sel-2"){//this는 클릭된 functionSelector이다
				//기능이 바뀌었으니, SUB-SECTION을 전부 지운다
				$("#"+id_div_subSection).empty()
				//만약 최신포스트 셀렉터가 눌렸다면......
				
				console.log("mainPage.js.bind_Select_Function >>> 최신포스트 보기 기능 선택됨")
				getRecentPosts(id_div_subSection ,id_tempData_in_pageNum)
				
			}
			if($(this).attr("id") == "sel-3"){//this는 클릭된 functionSelector이다
				//기능이 바뀌었으니, SUB-SECTION을 전부 지운다
				console.log("mainPage.js.bind_Select_Function >>> 시리즈 보기 기능 선택됨")
				$("#"+id_div_subSection).empty()
				
				getpostListPage(id_div_subSection)
				
			}
			if($(this).attr("id") == "sel-4"){//this는 클릭된 functionSelector이다
				//기능이 바뀌었으니, SUB-SECTION을 전부 지운다
				console.log("mainPage.js.bind_Select_Function >>> 방명록 보기 기능 선택됨")
				$("#"+id_div_subSection).empty()
				
				getGuestBook(id_div_subSection)
			}
			if($(this).attr("id") == "sel-5"){//this는 클릭된 functionSelector이다
				//기능이 바뀌었으니, SUB-SECTION을 전부 지운다
				console.log("mainPage.js.bind_Select_Function >>> 웹프로그램 보기 기능 선택됨")
				$("#"+id_div_subSection).empty()
				
				getWebTools(id_div_subSection)
			}
			
			$(this).attr("href","#sel-selected")
			effectOn_selectedFuncSelector(id_div_funcList)
			$(this).addClass("fader")
			show_subSection($(this).attr("id"))
		})
	})
}//bind_Select_Function

//다음페이지 버튼을 눌렀을때 다음페이지에 속해있는 포스트를 불러오는 기능
function bind_appendPost(id_btn, subSection , id_pageNum){
	$("#"+id_btn).click(function(){
		append_morePosts(subSection , id_pageNum)
	})
}//bind_appendPost