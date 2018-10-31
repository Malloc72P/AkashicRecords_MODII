<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>html-css-js document</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="../hello/js/dropzone.js"></script>
	
	
	<link rel="stylesheet" href="https://unpkg.com/cropperjs/dist/cropper.css">
	
  	<script src="https://unpkg.com/cropperjs/dist/cropper.js"></script>
	<script src="js/Cropper/jquery-cropper.min.js"></script>
	
	<script src="../hello/js/dropzone.js"></script>
	
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		img.imageTest{
			max-width	:	100%;
		}
		div.imageTestWrapper{
			width: 100%;
		}
		.imgWrapper {
		    height: 400px;
		    overflow: hidden;
		}
		
		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>

	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		$(document).ready(function(){
			//######
			var $image = $('#id_img_imageTest');
			var options = {
					  aspectRatio	: 	1 / 1,
					  responsive	:	true,
					  dragMode	:	"move",
					  viewMode	:	3,
					  crop: function(event) {
					    console.log(event.detail.x);
					    console.log(event.detail.y);
					    console.log(event.detail.width);
					    console.log(event.detail.height);
					    console.log(event.detail.rotate);
					    console.log(event.detail.scaleX);
					    console.log(event.detail.scaleY);
					  }
					};
 			$image.cropper(options); 
			//######
 			/* var myDropzone = new Dropzone(
				    "input#id_uploadImg",
                 	{ 
				    	url				:	"../hello/imageUploadProc.do",
				    	parallelUploads	:	1
			    	}
			 );//myDropZone
			 myDropzone.on('success',function(file, response){
					//alert("image has been uploaded");
					console.log("imageUploader.on( success ) >>> 함수 호출됨");
					console.log("imageUploader.on( success ) >>> file : "+file);
					console.log("imageUploader.on( success ) >>> file : "+response);
					var jsonRes = JSON.parse(response);
					console.log("imageUploader.on( success ) >>> jsonRes : "+jsonRes);
					console.log("imageUploader.on( success ) >>> jsonRes.insertChecker : "+jsonRes.insertChecker);
					console.log("imageUploader.on( success ) >>> jsonRes.img_url : "+jsonRes.img_url);
					console.log("imageUploader.on( success ) >>> jsonRes.img_name : "+jsonRes.img_name);
					console.log("imageUploader.on( success ) >>> jsonRes.img_id : "+jsonRes.img_id);
					console.log("testing",$("id_img_imageTest").attr("src"));
					$("#id_img_imageTest").attr("src",jsonRes.img_url);
			})//myDropzone.on('success',function(file, response) */
			
			
					
			var URL = window.URL || window.webkitURL;
			var inputImage = document.getElementById('id_uploadImg');
			//$image.cropper
			//$().cropper('method', argument1, , argument2, ..., argumentN);
			inputImage.onchange = function(){
				alert("inputImage.onchange...!");
		        var files = inputImage.files;
		        var file  = files[0];
		        var uploadedImageURL = URL.createObjectURL(file);
		        $("#id_img_imageTest").attr("src",uploadedImageURL);
		        
		        $("#id_img_imageTest").cropper("destroy");
		        alert($("#id_img_imageTest").attr("src"));
		        $image.cropper(options);
		        inputImage.value = null;
			} 
	        
			submitImg_EventBinder();
		})//document.ready
		
		function uploadImg_EventBinder(){
			$("#id_uploadImg").click(function(){
				
			})
		}
		function submitImg_EventBinder(){
			$("#id_submitProfileImg").click(function(){
				$("#id_img_imageTest").cropper("getCroppedCanvas").toBlob( (blob)=>{
					console.log("blob : ",blob);
					console.log("blob.type",blob.type);
					
					var strArray = blob.type.split("/");
					console.log("strArray",strArray);
					console.log("strArray[1]",strArray[1]);
										
					const formData = new FormData();
					formData.append('file', blob);
					formData.append('fileType', strArray[1]);
					
					$.ajax('../hello/profileImgUploaderProc.do', {
					    method: "POST",
					    data: formData,
					    processData: false,
					    contentType: false,
					    success(result) {
					    	resultJSON = JSON.parse(result);
 					    	console.log("resultJSON",resultJSON);
 					    	if(opener.Reg_setProfileImg){
 					    		opener.Reg_setProfileImg(resultJSON.img_url, resultJSON.img_id);	
 					    	}
 					    	if(opener.myPage_setProfileImg){
 					    		opener.myPage_setProfileImg(resultJSON.img_url, resultJSON.img_id);
 					    	}
					      	self.close();
					    },
					    error() {
					      alert("전송실패, 다시 시도해주세요");
					    },
					  });
				} )
			})
		}
		
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>
 <body>
	<!--| ***body StarT*** |-->
	<div class="imgWrapper">
		<img class="imageTest" id="id_img_imageTest" src="img/warmind2.jpg">
	</div>
	
	<input class="w3-btn btnMargin btnBorderBottom profileImgBtn" 
		   type="file" name="id_uploadImg" 
		   id="id_uploadImg" value="기술실증">
    <input class="w3-btn btnMargin btnBorderBottom profileImgBtn" 
					   type="button" name="reg_btn_submit" 
					   id="id_submitProfileImg" value="확인">
	
	<!--| ###body EnD### |-->
 </body>
</html>