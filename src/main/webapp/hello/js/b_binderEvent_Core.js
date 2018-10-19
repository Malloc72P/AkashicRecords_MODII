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