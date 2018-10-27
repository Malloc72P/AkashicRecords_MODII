<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>writePost</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="myStyle/myStyle.css">
	<link rel="stylesheet" href="myStyle/css_preset.css">
	<script src="js/mgr_account_MK2.js"></script>
	<script src="https://cloud.tinymce.com/stable/tinymce.min.js?
				apiKey=n3qgxuzmb0qsl7vkc0n1wvbe4l2dys01jth56fio4zvc62xs"></script>
	<script>
		tinyMCE.init({
		   selector: '#id_input_writePostContent', // This is my <textarea> class
		   setup : function(ed) {
		                  ed.on('change', function(e) {
		                     // This will print out all your content in the tinyMce box
		                     console.log('the content '+ed.getContent());
		                     // Your text from the tinyMce box will now be passed to your  text area ... 
		                     $(".tinymce").text(ed.getContent()); 
		                  });
		            },
		   plugins: "image code fullscreen",
		   toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | image | code | fullscreen | myButton",
		   image_advtab: true,
		   setup: function (editor) {
				editor.addButton('myButton', {
					text: '파일 업로드',
					icon: false,
					onclick: function () {
						window.open('imageUploader.do','_fileupload','width=800, height=600');
					}
				});
			}
		});
	</script>
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
	
		
		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>
	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		
		function TinyMceInsert(s)
		{
			tinymce.execCommand('mceInsertContent',false,s);
		}
		
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>   
 <body>
	<!--| ***body StarT*** |-->

		<div class="w3-white w3-card-4 w3-display-middle boxShadow-lite popUp" 
		style="width: 100%; height: 100%; top:50%;" id="id_div_writePostPanel">
			<div class="w3-container w3-bar w3-white" id="id_div_writePostCloser">
				<h2 class="w3-bar-item">포스트 작성하기...</h2>
				<h2 class="w3-bar-item w3-right w3-button"><i class="im im-x-mark"></i></h2>
			</div>
			<div class="w3-container  w3-white" style="margin-top: -1px;">
				<hr class="w3-black">
			</div>
			
			<form class="w3-container" method="post" action="/hello/loginProc.do" >
				<br>
				<label class="" style="font-weight: bold;">시리즈</label>
				<select id="id_select_writePostSeries"
						class="w3-select w3-border w3-round-large" name="writePost_series" type="text">
					<option value="" disabled selected>포스트가 등록될 시리즈를 선택해 주세요</option>
					<option value="1">일상</option>
					<option value="2">음식</option>
					<option value="3">ICT</option>
					<option value="4">게임</option>
				</select>
				<br>
				<br>
				<label class="" style="font-weight: bold;">제목</label>
				<input  id="id_input_writePostTitle"
						class="w3-input w3-border w3-round-large" type="text">
				<br>
				<label class="" style="font-weight: bold;">내용</label>
				 <textarea id="id_input_writePostContent">Next, use our Get Started docs to setup Tiny!</textarea>
				<br>
				<input id="id_input_submitPost"
					   class="w3-btn w3-white btnMargin btnBorderBottom"
				       type ="submit" value="작성하기">	
				       
				<input class="w3-btn w3-white btnBorderBottom"
				       type ="button" id="id_btn_writePostGoBack" value="뒤로가기">
				<br><br>
			</form>
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>