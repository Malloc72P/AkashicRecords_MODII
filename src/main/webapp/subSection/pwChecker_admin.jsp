<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>passwordCheck</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="myStyle/myStyle.css">
	<link rel="stylesheet" href="myStyle/css_preset.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/mgr_account_MK2.js"></script>
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
		style="width: 70%;"
		id="id_div_admin_pwCheckerPanel">
			<div class="w3-container w3-bar w3-white" id="id_div_admin_pwchkCloser">
				<h2 class="w3-bar-item">본인확인</h2>
				<h2 class="w3-bar-item w3-right w3-button"><i class="im im-x-mark"></i></h2>
			</div>
			<div class="w3-container  w3-white" style="margin-top: -1px;">
				<hr class="w3-black">
			</div>
			
			<form class="w3-container" method="post" >
				<br>
				<label class="" style="font-weight: bold;">패스워드</label>
				<input class="w3-input w3-border w3-round-large" type="password"
					   name="name_input_submitPWCHK" id="id_input_admin_pwchkPW">
				<br>
				<input class="w3-btn w3-white btnMargin btnBorderBottom" id="id_input_admin_submitPWCHK"
				       type ="submit" value="입력">	
				       
				<input class="w3-btn w3-white btnBorderBottom"
				       type ="button" name="bt_goBack" id="id_btn_admin_pwchkGoBack"value="뒤로가기">
				<br><br>
			</form>
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>