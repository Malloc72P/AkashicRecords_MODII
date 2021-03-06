<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="constSet.MainConst" %>
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>register to Akashic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="myStyle/myStyle.css">
	<link rel="stylesheet" href="myStyle/css_preset.css">
	<script src="js/jsConstData.js"></script>
	
	
	<script type="text/javascript" src="js/mgr_account_MK2.js"></script>
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		img.imageTest{
			max-width	:	100%;
		}
		div.imageTestWrapper{
			width: 100%;
		}
		.profileImgBtn{
			
		}
		input#id_reg_btn_profileImgUploader {
		    border: 1px solid #d5d5d5;
		    margin-left: 0px;
		    position: relative;
		    top: 10px;
		    left: -22px;
		    border: 1px solid #d5d5d5;
		    margin-left: 12px;
		}
		div.regThumbWrapper {
		    width: 128px;
		    height: 128px;
		    overflow: hidden;
		    border: 1px solid #cfcfcf;
		    border-radius: 50%;
		    display: inline-block;
		}

		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>

	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		$(document).ready(function(){
			register_eventBinder("reg_btn_submit");
			//######
			
			//######
 			profileImgUploader();
 			
			})//document.ready
			
			function profileImgUploader(){
				$("#id_reg_btn_profileImgUploader").click(function(){
					window.open('profileImgUploader.do','_fileupload','width=800, height=600');
				})
			
			}
			function Reg_setProfileImg(imgUrl,imgId){
				$("#id_img_regThumb").attr("src",imgUrl);
				$("#id_input_regImgId").attr("value",imgId);
			}

		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>
 <body>
	<!--| ***body StarT*** |-->

		<div 	class="w3-white w3-card-4 w3-display-middle boxShadow-lite popUp" 
				id="id_div_registerPanel" 
				style="width: 70%; top: 50% !important; width: 100%; height: 100%; overflow-y: scroll; z-index : 9999;">
			
			<div class="w3-container w3-bar <%=MainConst.THEME_COLOR_MAIN%>">
				<h2 class="w3-bar-item">Register...</h2>
				<h2 class="w3-bar-item w3-right w3-button" id="id_h2_registerCloser"><i class="im im-x-mark"></i></h2>
			</div>
			<div class="w3-container  <%=MainConst.THEME_COLOR_MAIN%>" style="margin-top: -1px;">
				<hr class="w3-black">
			</div>
			
			<form class="w3-container" name="register_form" method="post">
				<label class="" style="font-weight: bold;">프로필 이미지</label>
				<br>
				<div class="regThumbWrapper" >
					<img id="id_img_regThumb" src="" style="width: 128px; height: 128px;">
					<input type="hidden" id="id_input_regImgId" value="-1">
				</div>
				<input class="w3-btn btnMargin btnBorderBottom profileImgBtn" 
					   type="button" name="reg_btn_submit" 
					   id="id_reg_btn_profileImgUploader" value="수정">
				<br>
				<br>
				<label class="" style="font-weight: bold;">E-mail</label>
				<label class="<%=MainConst.THEME_COLOR_ERR%> errMsg" id="reg_email_err_1" style="font-weight: bold;">___이메일을 입력하십시오</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="reg_email" id="reg_email">
				<br>
				<label class="" style="font-weight: bold;">Password</label>
				<label class="<%=MainConst.THEME_COLOR_ERR%> errMsg" id="reg_password_err_1" style="font-weight: bold;">___패스워드를 입력하십시오</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="reg_password" id="reg_password">
				<br>
				<label class="" style="font-weight: bold;">Password Check</label>
				<label class="<%=MainConst.THEME_COLOR_ERR%> errMsg" id="reg_passwordCheck_err_1" style="font-weight: bold;">___패스워드를 재확인해주십시오</label>
				<label class="<%=MainConst.THEME_COLOR_ERR%> errMsg" id="reg_passwordCheck_err_2" style="font-weight: bold;">___패스워드가 일치하지 않습니다</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="reg_passwordCheck" id="reg_passwordCheck">
				<br>
				<label class="" style="font-weight: bold;">NickName</label>
				<label class="<%=MainConst.THEME_COLOR_ERR%> errMsg" id="reg_nickname_err_1" style="font-weight: bold;">___닉네임을 입력하십시오</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="reg_nickname" id="reg_nickname">
				<br>
				<input class="w3-btn <%=MainConst.THEME_COLOR_MAIN%> btnMargin btnBorderBottom" 
					   type="submit" name="reg_btn_submit" 
					   id="reg_btn_submit" value="회원가입">
				<input class="w3-btn <%=MainConst.THEME_COLOR_MAIN%> btnBorderBottom" type ="button" name="bt_goBack" id="id_btn_registerGoBack" value="뒤로가기">
				<br><br>
			</form>
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>