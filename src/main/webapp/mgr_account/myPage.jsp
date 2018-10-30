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
		input#id_input_myPageProfImgUpload {
		    border: 1px solid #d5d5d5;
		    margin-left: 12px;
		}
		input##id_input_myPageProfImgUpload {
		    border: 1px solid #d5d5d5;
		    margin-left: 0px;
		    position: relative;
		    top: 10px;
		    left: -22px;
		}
		div.myPageThumbWrapper {
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
			myPageUpdater_eventBinder("myPage_btn_submit");
			//######
			
			//######
 			profileImgUploader();
 			
			})//document.ready
			
			function profileImgUploader(){
				$("#id_input_myPageProfImgUpload").click(function(){
					window.open('profileImgUploader.do','_fileupload','width=800, height=600');
				})
			
			}
			function setProfileImg(imgUrl,imgId){
				$("#id_img_myPageThumb").attr("src",imgUrl);
				$("#id_input_myPageImgId").attr("value",imgId);
			}

		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>
 <body>
	<!--| ***body StarT*** |-->

		<div 	class="w3-white w3-card-4 w3-display-middle boxShadow-lite popUp" 
				id="id_div_myPagePanel" 
				style="width: 70%; top: 50% !important; width: 100%; height: 100%; overflow-y: scroll; z-index : 9999;">
			
			<div class="w3-container w3-bar <%=MainConst.THEME_COLOR_MAIN%>">
				<h2 class="w3-bar-item">MyPage...</h2>
				<h2 class="w3-bar-item w3-right w3-button" id="id_h2_myPageCloser"><i class="im im-x-mark"></i></h2>
			</div>
			<div class="w3-container  <%=MainConst.THEME_COLOR_MAIN%>" style="margin-top: -1px;">
				<hr class="w3-black">
			</div>
			
			<form class="w3-container" name="register_form" method="post">
				<label class="" style="font-weight: bold;">프로필 이미지</label>
				<br>
				<div class="regThumbWrapper" >
					<img id="id_img_myPageThumb" src="" style="width: 128px; height: 128px;">
					<input type="hidden" id="id_input_myPageImgId" value="-1">
				</div>
				<input class="w3-btn btnMargin btnBorderBottom profileImgBtn" 
					   type="button" name="reg_btn_submit" 
					   id="id_input_myPageProfImgUpload" value="수정">
				<br>
				<br>
				<label class="" style="font-weight: bold;">E-mail</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="myPage_email" id="myPage_email">
				<br>
				<label class="" style="font-weight: bold;">패스워드를 바꾸지 않는 경우, 3개의 패스워드 입력란에 사용중인 패스워드를 입력해주세요</label>
				<br>
				<label class="" style="font-weight: bold;">Previous Password</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="myPage_password" id="myPage_prevPassword">
				<br>
				<label class="" style="font-weight: bold;">New Password</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="myPage_password" id="myPage_newPassword">
				<br>
				<label class="" style="font-weight: bold;">New Password Check</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="myPage_passwordCheck" id="myPage_newPasswordCheck">
				<br>
				<label class="" style="font-weight: bold;">NickName</label>
				<input class="w3-input w3-border w3-round-large" type="text" name="myPage_nickname" id="myPage_nickname">
				<br>
				<input class="w3-btn <%=MainConst.THEME_COLOR_MAIN%> btnMargin btnBorderBottom" 
					   type="submit" name="myPage_btn_submit" 
					   id="myPage_btn_submit" value="회원가입">
				<input class="w3-btn <%=MainConst.THEME_COLOR_MAIN%> btnBorderBottom" type ="button" name="bt_goBack" id="id_btn_myPageGoBack" value="뒤로가기">
				<br><br>
			</form>
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>