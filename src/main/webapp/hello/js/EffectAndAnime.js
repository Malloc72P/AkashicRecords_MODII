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
 * AKASHIC-RECORD-SUBSECTION-EFFECTOR
************************************/

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