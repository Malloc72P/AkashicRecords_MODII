<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>writePost</title>




	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
	
		
		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>
	
	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		
		
		
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
	
 </head>   
 <body>
	<!--| ***body StarT*** |-->

		<div class="w3-white w3-card-4 w3-display-middle boxShadow-lite popUp" 
		style="width: 70%; " id="id_div_writeSeriesPanel">
			<div class="w3-container w3-bar w3-white" id="id_div_writeSeriesCloser">
				<h2 class="w3-bar-item">시리즈 작성하기...</h2>
				<h2 class="w3-bar-item w3-right w3-button"><i class="im im-x-mark"></i></h2>
			</div>
			<div class="w3-container  w3-white" style="margin-top: -1px;">
				<hr class="w3-black">
			</div>
			
			<form class="w3-container" method="post" action="/hello/loginProc.do" >
				<br>
				<label class="" style="font-weight: bold;">시리즈 제목</label>
				<input  id="id_input_writeSeriesTitle"
						class="w3-input w3-border w3-round-large" type="text">
				<br>
				<input id="id_input_submitSeries"
					   class="w3-btn w3-white btnMargin btnBorderBottom"
				       type ="submit" value="작성하기">	
				       
				<input class="w3-btn w3-white btnBorderBottom"
				       type ="button" id="id_btn_writeSeriesGoBack" value="뒤로가기">
				<br><br>
			</form>
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>