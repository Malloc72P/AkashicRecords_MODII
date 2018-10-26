/**
 * PANEL 처리 함수
 */

function refreshSubSection(getSubSectionfunc ){
	$("#"+"id_div_subSection").empty();
	getSubSectionfunc( "id_div_subSection" );
}