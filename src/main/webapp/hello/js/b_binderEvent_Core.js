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


