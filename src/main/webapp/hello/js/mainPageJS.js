/************************************
 * AKASHIC-RECORD-MAIN PAGE JAVASCRIPT MOD I
************************************/
var fadeSpeed = 250

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









