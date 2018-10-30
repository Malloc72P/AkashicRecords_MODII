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
function adminPageOpener_eventBinder(){
	$("#id_a_adminPageOpener").click(function(event){
		event.preventDefault()
		
		panelOpener("id_div_admin_pwCheckerPanel" , "id_div_mainContent")
		$("#id_input_admin_submitPWCHK").unbind("click");
		
		//_________________________________________________________________
		$("#id_input_admin_submitPWCHK").click(function(){
			var url 	=	"adminPage.do";
			var title	=	"adminPage";
			var pwForm	=	document.getElementById("id_form_pwCheckAdmin");
			console.log("pwForm : ",pwForm);
			window.open("",title,"width=1080, height=600");
			
			pwForm.target	=	title;
			pwForm.action	=	url;
			pwForm.method	=	"post";
			pwForm.submit();
			panelCloser("id_div_admin_pwCheckerPanel", "id_div_mainContent");
		})
		//_________________________________________________________________
		
		//id_input_admin_submitPWCHK
		
		//pwCheck_admin_eventBinder("id_input_admin_submitPWCHK", "id_div_adminPagePanel");
	})
}
function myPageOpener_eventBinder(){
	$("#id_a_myPageOpener").unbind("click");
	bind_Open_Panel( "id_a_myPageOpener", "id_div_pwCheckerPanel" ,"id_div_mainContent");
	$("#id_a_myPageOpener").click(function(){
		$("#id_input_submitPWCHK").unbind("click");
		$("#id_input_submitPWCHK").click(function(){
			event.preventDefault();
			var pw = $("#"+"id_input_pwchkPW").val();
			console.log("mainPage.js >>> pwCheck_eventBinder >>> pw : "+pw);
			//pwCheck_AJAX( pw, openThisPanel, "id_div_pwCheckerPanel" )
			myPage_AJAX( pw, "id_div_myPagePanel", "id_div_pwCheckerPanel" );
		});
	})
	//pwCheck_eventBinder("id_input_submitPWCHK", "id_div_myPagePanel");
}
function appendMyPageBtn(){
	$("#id_div_loggedIn_sidebar").append("<h4><a  id='id_a_myPageOpener' class='w3-bar-item w3-button'>MyPage</a></h4>");
	myPageOpener_eventBinder();
}
function removeMyPageBtn(){
	$("#id_a_myPageOpener").closest("h4").remove();
}

