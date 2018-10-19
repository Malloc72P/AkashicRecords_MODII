/************************************
 * AKASHIC-RECORD-MAIN PAGE JAVASCRIPT MOD I
************************************/
var fadeSpeed = 250
var writePost_bindingEvent_limiter = 0

$("html").click(function(e){
	if( !$(e.target).hasClass("main-sidebar") && !$(e.target).hasClass("chk-side-on") ){
		if( $("div#akashicSideBar[display='block']") ){
			w3_close()
		}
	}
})//("html").click(function(e)

function w3_open() {
	$("div#akashicSideBar").css("display","block")
}//w3_open()
	
function w3_close() {
	$("div#akashicSideBar").css("display","none")
}//w3_close()

/************************************
 * AKASHIC-RECORD-ANIMATOR
************************************/

function panel_fadeIn(id_panel){
	$("#"+id_panel).fadeIn(fadeSpeed , function(){
		console.log("panel animation completed")
	})
}
function panel_fadeOut(id_panel){
	$("#"+id_panel).fadeOut(fadeSpeed , function(){
		console.log("panel animation completed")
	})
}
function blurOn_Tag(id_blurOnThis){
	$("#"+id_blurOnThis).addClass("w3-opacity-max")
}
function bluroff_Tag(id_blurOnThis){
	$("#"+id_blurOnThis).removeClass("w3-opacity-max")
}

/************************************
 * AKASHIC-RECORD-EFFECTOR
************************************/
function effectOn_selectedFuncSelector(id_div_funcList){
	var $tgt = getCurrSelectedFuncObj(id_div_funcList)
	$tgt.addClass("w3-teal")
}
function panelOpener(id_panel , id_background){
	panel_fadeIn(id_panel)
	if($("#"+id_background) != null){
		blurOn_Tag(id_background)
	}
}
function panelCloser(id_panel , id_background){
	panel_fadeOut(id_panel)
	if($("#"+id_background) != null){
		bluroff_Tag(id_background)
	}
}

/************************************
 * AKASHIC-RECORD-EventBinder FUNCTION
************************************/
//패널 여는 이벤트를 바인딩하는 함수
function bind_Open_Panel(id_trigger, id_panel , id_background){
	$("#"+id_trigger).click(function(event){
		event.preventDefault()
		panelOpener(id_panel , id_background)
		/*panel_fadeIn(id_panel)
		if($("#"+id_background) != null){
			blurOn_Tag(id_background)
		}*/
	})
}
//패널 닫는 이벤트를 바인딩하는 함수
function bind_Close_Panel(id_trigger, id_panel ,id_background){
	$("#"+id_trigger).click(function(event){
		event.preventDefault()
		panelCloser(id_panel ,id_background)
		/*panel_fadeOut(id_panel)
		if($("#"+id_background) != null){
			bluroff_Tag(id_background)
		}*/
	})
}

function pwCheck_eventBinder(id_input_submitPWCHK){
	$("#"+id_input_submitPWCHK).click(function(event){
		alert("pwChecker.jsp >>> pwCheck_eventBinder >>> 함수 호출됨")
		event.preventDefault()
		var pw = $("#"+"id_input_pwchkPW").val()
		console.log("mainPage.js >>> pwCheck_eventBinder >>> pw : "+pw)
		pwCheck_AJAX( "id_div_pwCheckerPanel", pw )	
	})
	
}
function writePost_eventBinder(){
	if(writePost_bindingEvent_limiter == 0){
		bind_Open_Panel( "id_a_writePost"     , "id_div_pwCheckerPanel" ,"id_div_mainContent")
		bind_Close_Panel("id_btn_pwchkGoBack" , "id_div_pwCheckerPanel" ,"id_div_mainContent")
		bind_Close_Panel("id_div_pwchkCloser" , "id_div_pwCheckerPanel" ,"id_div_mainContent")
		
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

function effectOff_selectedFuncSelector(id_div_funcList){
	var $tgt = getCurrSelectedFuncObj(id_div_funcList)
	$tgt.removeClass("w3-teal")
}
//현재 선택된 펑션셀렉터가 뭔지 리턴함.(태그객체 반환)
function getCurrSelectedFuncObj(id_div_funcList){
	console.log("getCurrSelectedFuncObjObj >>> "+id_div_funcList)
	console.log("getCurrSelectedFuncObjObj >>>"+ $("#"+id_div_funcList).find("a[href='#sel-selected']") )
	return $("#"+id_div_funcList).find("a[href='#sel-selected']")
}

//fader효과를 주면서 서브섹션을 등장시키는 함수
function show_subSection(tgt){
	// 여기서 tgt 는
	console.log("show_subSection >>> "+tgt)
	$(".sub-section").find($("div"+"."+tgt)).addClass("fader")
	$(".sub-section").find($("div"+"."+tgt)).show()
}
//서브섹션을 감추고, 그리고 페이드 효과를 빼앗는 함수
function  hide_subSection(tgt){
	console.log("hide_subSection >>> "+tgt)
	$(".sub-section").find($("div"+"."+tgt)).removeClass("fader")
	$(".sub-section").find($("div"+"."+tgt)).hide()
}

/******************************************************************
 * AJAX-JQUERY FUNCTION
 *******************************************************************/
//getRecentPosts함수는 최초로 포스트를 집어오는 함수이다
//따라서 여기서 pageNum을 1로 초기화해준다
function getRecentPosts(id_div_subSection , id_pageNum){
	$("#"+id_pageNum).val("1")
	var pageNum = $("#"+id_pageNum).val()
	console.log("getRecentPosts >>> "+pageNum)
	$.ajax(
		{ 
			method : "post",
			url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/recentPosts.do",
			data   : { "pageNum":pageNum },
			cache  : false,
			async  : true,
			success: function(result){
				var htmlRES = $.parseHTML( result )// ajax로 받아온 페이지를 html로 파싱한다
				
				//다 지우고 나면, AJAX로 받아온 페이지를 SUB-SECTION에 출력한다
				$("#"+id_div_subSection).append(htmlRES)
				
				//그리고, AJAX로 받아온 페이지엔 CURRENT_PAGE와 PAGECOUNT값을 가지고 있는 히든태그가 있고 다음과 같이 가져올 수 있다
				//파싱된 HTML객체라서 다음과 같이 DOM을 이용해서 값을 찾을 수 있다.
				//추가로, postheader에 쓰일 post갯수를 담고있는 postCount도 있다
				var currentPage = $(htmlRES).find("#id_currentPage").attr("value");
				var pageCount   = $(htmlRES).find("#id_pageCount").attr("value");
				var postCount   = $(htmlRES).find("#id_postCount").attr("value");
				
				console.log("id_currentPage >>> "+currentPage)
				console.log("id_pageCount >>> "  +pageCount  )
				console.log("id_postCount >>> "  +postCount  )
				
				//포스트리스트의 각 포스트의 제목(포스트를 여는 오프너)에 뷰컨텐츠 오프너 함수를 바인딩한다
				$(htmlRES).find("a").each(function(){
					console.log("포스트리스트_타이틀_이벤트바인더"+this);
					$(this).click(function(event){
						console.log("event.target : "+event.target);
						var targetUrl	=	event.target;
						
						getViewPage(targetUrl);
						
						event.preventDefault();
					});
				})
				
				//포스트리스트의 헤더에 이벤트를 바인딩한다
				writePost_eventBinder()
//				bind_Open_Panel( "id_a_writePost"     , "id_div_pwCheckerPanel" ,"id_div_mainContent")
//				bind_Close_Panel("id_btn_pwchkGoBack" , "id_div_pwCheckerPanel" ,"id_div_mainContent")
//				bind_Close_Panel("id_div_pwchkCloser" , "id_div_pwCheckerPanel" ,"id_div_mainContent")
//				
//				pwCheck_eventBinder("id_input_submitPWCHK")
				/*
				 * CASE1 : 만약 현재페이지가 전체 페이지수보다 작다면, 다음 페이지가 있다는 뜻이다.
				 * 		그러므로, 다음페이지 버튼을 출력한다
				 * CASE1 : 만약 현재페이지가 전체 페이지수와 같다면, 현재 페이지가 마지막 페이지가 된다.
				 * 		그러므로, 다음페이지 버튼을 출력하지 않는다.
				 * CASE3 : 있어어는 안되는 케이스로, 오류상황이다. 
				 * */
				if( currentPage < pageCount ){
					console.log("if( currentPage < pageCount )")
					var strTemp = '<div id="id_post_nextPage" class="w3-card w3-middle w3-button" style="width: 100%; ">';
					   strTemp += '<h4>다음페이지</h4>';
					   strTemp += '</div>';
					   $("#"+id_div_subSection).append(strTemp)
					   
					   bind_appendPost("id_post_nextPage", id_div_subSection , id_pageNum)
				}
				else if( currentPage == pageCount ){
					console.log("else if( currentPage == pageCount )")
				}
				else{
					console.log("else")
					console.log("포스트를 가져오는 과정에서 에러가 발생했습니다.")
				}
				
				show_subSection("sel-2")
			}
		}
	)
}//function submitAjax
function append_morePosts(id_div_subSection , id_pageNum){
	console.log("append_morePosts >>> id_pageNum : "+id_pageNum)
	 var pageNum = parseInt( $("#"+id_pageNum).attr("value") )
	 pageNum = pageNum + 1
	 $("#"+id_pageNum).attr("value" , pageNum )
	 console.log("append_morePosts >>> pageNum : "+$("#"+id_pageNum).attr("value"))
	$.ajax(
			{ 
				method : "post",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/recentPosts.do",
				data   : { "pageNum":pageNum },
				cache  : false,
				async  : true,
				success: function(result){
					var htmlRES = $.parseHTML( result )
					$("#"+id_div_subSection).append(htmlRES)
					
					//AJAX로 받아온 페이지엔 CURRENT_PAGE와 PAGECOUNT값을 가지고 있는 히든태그가 있고 다음과 같이 가져올 수 있다
					//파싱된 HTML객체라서 다음과 같이 DOM을 이용해서 값을 찾을 수 있다.
					var currentPage = $(htmlRES).find("#id_currentPage").attr("value");
					var pageCount   = $(htmlRES).find("#id_pageCount").attr("value");
					
					console.log("id_currentPage >>> "+currentPage)
					console.log("id_pageCount >>> "  +pageCount  )
					/*
					 * 글쓰기 버튼 누를 경우, 패스워드 확인하는 창 띄워주기
					 * */
					
					/*
					 * CASE1 : 만약 현재페이지가 전체 페이지수보다 작다면, 다음 페이지가 있다는 뜻이다.
					 * 		그러므로, 다음페이지 버튼을 출력한다
					 * CASE1 : 만약 현재페이지가 전체 페이지수와 같다면, 현재 페이지가 마지막 페이지가 된다.
					 * 		그러므로, 다음페이지 버튼을 출력하지 않는다.
					 * CASE3 : 있어어는 안되는 케이스로, 오류상황이다. 
					 * */
					if( currentPage < pageCount ){
						console.log("if( currentPage < pageCount )")
						$("#id_post_nextPage").appendTo( $("#"+id_div_subSection) )
					  
					}
					else if( currentPage == pageCount ){
						console.log("else if( currentPage == pageCount )")
						//더이상 추가페이지가 없으므로 버튼을 숨긴다
						$("#id_post_nextPage").hide()
					}
					else{
						console.log("else")
						console.log("포스트를 가져오는 과정에서 에러가 발생했습니다.")
					}
					
					
					
					console.log("hidden tag : "+$("#id_tempData_pageNum").attr("value"))
					show_subSection("sel-2")
				}
			}
		)
}
/******************************************************************
 * AJAX-JQUERY FUNCTION
 * 		GET SUBSECTION PAGE 
 *******************************************************************/
function getProfilePage( id_div_subSection ){
	console.log("mainPage.js.getProfilePage >>> 함수 호출됨")
	$.ajax(
			{ 
				method : "post",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/profile.do",
				cache  : false,
				success: function(result){
					var htmlRES = $.parseHTML( result )
					$("#"+id_div_subSection).append(htmlRES)
					//AJAX로 받아온 페이지엔 CURRENT_PAGE와 PAGECOUNT값을 가지고 있는 히든태그가 있고 다음과 같이 가져올 수 있다
					//파싱된 HTML객체라서 다음과 같이 DOM을 이용해서 값을 찾을 수 있다.
					
					show_subSection("sel-1")
				}
			}
		)
}
function getpostListPage( id_div_subSection ){
	console.log("mainPage.js.getpostListPage >>> 함수 호출됨")
	$.ajax(
			{ 
				method : "post",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/postList.do",
				cache  : false,
				success: function(result){
					var htmlRES = $.parseHTML( result )
					$("#"+id_div_subSection).append(htmlRES)
					//AJAX로 받아온 페이지엔 CURRENT_PAGE와 PAGECOUNT값을 가지고 있는 히든태그가 있고 다음과 같이 가져올 수 있다
					//파싱된 HTML객체라서 다음과 같이 DOM을 이용해서 값을 찾을 수 있다.
					
					show_subSection("sel-3")
				}
			}
		)
}
function getGuestBook( id_div_subSection ){
	console.log("mainPage.js.GuestBook >>> 함수 호출됨")
	$.ajax(
			{ 
				method : "post",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/guestBook.do",
				cache  : false,
				success: function(result){
					var htmlRES = $.parseHTML( result )
					$("#"+id_div_subSection).append(htmlRES)
					//AJAX로 받아온 페이지엔 CURRENT_PAGE와 PAGECOUNT값을 가지고 있는 히든태그가 있고 다음과 같이 가져올 수 있다
					//파싱된 HTML객체라서 다음과 같이 DOM을 이용해서 값을 찾을 수 있다.
					
					show_subSection("sel-4")
				}
			}
		)
}
function getWritePost( id_div_subSection ){
	console.log("mainPage.js.getWritePost >>> 함수 호출됨")
	$.ajax(
			{ 
				method : "post",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/writePost.do",
				cache  : false,
				success: function(result){
					var htmlRES = $.parseHTML( result )
					$("#"+id_div_subSection).append(htmlRES)
					//AJAX로 받아온 페이지엔 CURRENT_PAGE와 PAGECOUNT값을 가지고 있는 히든태그가 있고 다음과 같이 가져올 수 있다
					//파싱된 HTML객체라서 다음과 같이 DOM을 이용해서 값을 찾을 수 있다.
					
					show_subSection("sel-4")
				}
			}
		)
}
function getWebTools( id_div_subSection ){
	console.log("mainPage.js.getWebTools >>> 함수 호출됨")
	$.ajax(
			{ 
				method : "post",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/webTools.do",
				cache  : false,
				success: function(result){
					var htmlRES = $.parseHTML( result )
					
					$("#"+id_div_subSection).append(htmlRES)
					//AJAX로 받아온 페이지엔 CURRENT_PAGE와 PAGECOUNT값을 가지고 있는 히든태그가 있고 다음과 같이 가져올 수 있다
					//파싱된 HTML객체라서 다음과 같이 DOM을 이용해서 값을 찾을 수 있다.
					
					show_subSection("sel-5")
				}
			}
		)
}
function getViewPage( requestUrl ){
	console.log("mainPage.js.getViewPage >>> 함수 호출됨")
	var htmlRES	=	null;
	$.ajax(
			{ 
				method : "post",
				url    : requestUrl,
				cache  : false,
				success: function(result){
					htmlRES = $.parseHTML( result )
					var post_title		=	$(htmlRES).find("input[name='post_title']").val();
					var post_regdate	=	$(htmlRES).find("input[name='post_regdate']").val();
					var post_viewcount	=	$(htmlRES).find("input[name='post_viewcount']").val();
					var post_writer		=	$(htmlRES).find("input[name='post_writer']").val();
					var post_series		=	$(htmlRES).find("input[name='post_series']").val();
					
					console.log("post_title : "+post_title);
					console.log("post_regdate : "+post_regdate);
					console.log("post_viewcount : "+post_viewcount);
					console.log("post_writer : "+post_writer);
					console.log("post_series : "+post_series);
					
					console.log("getViewPage >>> htmlRES : "+htmlRES);
					
					$("#id_div_viewPostTitle").empty();
					$("#id_div_viewPostTitle").append(post_title);
					
					$("#id_div_viewPostRegdate").empty();
					$("#id_div_viewPostRegdate").append("작성일 : "+post_regdate);
					
					$("#id_div_viewPostViewCount").empty();
					$("#id_div_viewPostViewCount").append("조회수 : "+post_viewcount);
					
					$("#id_div_viewPostWriter").empty();
					$("#id_div_viewPostWriter").append(post_writer);
					
					$("#id_div_viewPostSeries").empty();
					$("#id_div_viewPostSeries").append("시리즈 : "+post_series);
					
					$("#id_div_viewPostArticle").empty();
					$("#id_div_viewPostArticle").append(htmlRES);
					
					
					panelOpener("id_div_viewPostPanel", "id_div_mainContent");
					
					bind_Close_Panel("id_div_viewPostCloser" , "id_div_viewPostPanel" ,"id_div_mainContent");
					
				}
			}
		);
}
/******************************************************************
 * AJAX-JQUERY FUNCTION
 * 		SUBSECTION SUPPORT FUNCTION
 *******************************************************************/
function pwCheck_AJAX(openThisPanel, user_password){
	/*
	 * 패스워드 체크가 필요한 패널을 열기 전에 거쳐가는 패스워드 재확인 패널의 ajax함수입니다
	 * openThisPanel은 이 패스워드 체크로 재확이
	 * */
	console.log("mainPage.js >>> pwCheck_AJAX >>> password : "+user_password)
	$.ajax(
		{ 
			method : "post",
			url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/pwCheckProc.do",
			data   : { "user_password":user_password },
			cache  : false
		}
	)
		.done(function(result){
			console.log("mainPage.js >>> pwCheck_AJAX >>> AJAX수신 완료")
			var jsonRes = JSON.parse(result)
			//서버로부터 받은 데이터를 json으로 파싱한다
			if(jsonRes.validator=="true"){//로그인 성공시
				//$("#"+"id_div_pwCheckerPanel").hide()
				/*panel_fadeOut("id_div_pwCheckerPanel")
				bluroff_Tag("id_div_mainContent")*/
				panelCloser(openThisPanel, "id_div_mainContent")
				panelOpener(openThisPanel, "id_div_mainContent")
				
			}
			else{//로그인 실패시
				alert("패스워드가 일치하지 않습니다")
				panelCloser(openThisPanel, "id_div_mainContent")
			}
		})//done
}//function submitAjax

function submitPost(post_title, post_content, series_id){
	console.log("mainPage.js >>> submitPost >>> 함수 호출됨")
	
	$.ajax( 
			{
				method : "post",
				url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/writePostProc.do",
				data   : { 
							"post_title":post_title,
							"post_content":post_content,
							"series_id":series_id
				         },
			    success : function(result){
			    	var jsonRes = JSON.parse(result)
			    	if(jsonRes.insertChecker == "true"){
			    		alert("성공적으로 저장되었습니다")
			    		panelCloser("id_div_writePostPanel", "id_div_mainContent")
			    	}
			    	else{
			    		alert("저장에 실패하였습니다")
			    	}
			    }//success
			}//ajax {}
	)//.ajax
}//submitPost


function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}









