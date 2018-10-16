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
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		body{
		     background: rgb(216, 226, 234);
		}
		*{
			font-family: 'Source Sans Pro', sans-serif;
		}
		.cl_div_imgUp_fullWrapper{
			
		}
		.cl_div_imgUp_main{
			width: 100%;
    		min-height: 407px;
		}
		.cl_input_imageUploadeCore{
		}
		
		/* _______________________________ */
		.dz-preview.dz-processing.dz-success.dz-complete.dz-image-preview {
		    float: left;
		    margin-right: 10px;
		}
		.dz-size {
		    display: none;
		}
		.dz-filename {
		    display: none;
		}
		.dz-success-mark {
		    display: none;
		}
		.dz-error-mark {
		    display: none;
		}
		.cl_div_imgUp_footer{
		    /* position: fixed; */
		    width: 100%;
		    /* bottom: 0px; */
		    /* margin-top: 11px; */
		}
		.cl_div_dropzone{
		    position: relative;
		    margin-top: 30px;
		    /* margin-bottom: 30px; */
		    /* padding-bottom: 70px; */
		    padding: 70px 70px 180px 70px;
		    width: 100%;
		    height: 100%;
		    border: 5px dotted purple;
		    background: white;
		}
		.cl_div_dropzone:after {
		    content: "";
		    display: table;
		    clear: both;
		}
		.cl_div_dropdownHelper{
		    position: relative;
		    text-align: center;
		    top: -53px;
		    /* margin-top: -25px; */
		    /* margin-bottom: 80px; */
		}
		.cl_h4_dropdownHelperText{
		    font-weight: bold;
		    font-size: 15px;
		}
		.cl_div_insertImgBtn{
			text-align: center;
		}

		/* _______________________________ */
		
		
		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>

	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		var imgArr = [];
			$(document).ready(function(){
				
				var myDropzone = new Dropzone(
											    "div#id_div_dropzone",
						                     	{ 
											    	url				:	"../hello/imageUploadProc.do",
											    	parallelUploads	:	1
										    	}
				 							 );
				
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
					imgArr.push( 
									{ 
										img_url		:	jsonRes.img_url,
										img_name	:	jsonRes.img_name,
										img_id		:	jsonRes.img_id
									}
							   );
					//insertImage(jsonRes.img_url);
				})
				
				eventBinder_insertImgIntotheTinyMce("id_div_insertImgIntoTinyMce");
				
			})
			
			
			function insertImage(img_url, img_name, img_id)
			{
				opener.TinyMceInsert('<p><img class="post_viewImg" style="max-width:700px;" src="'+img_url+'" alt="'+img_name+'" title="aka_imgID_'+img_id+'"></p>');
			}
			function eventBinder_insertImgIntotheTinyMce(id_div_insertImgIntoTinyMce){
				$("#"+id_div_insertImgIntoTinyMce).click(function(){
					for( var i = 0 ; i < imgArr.length ; i++ ){
						/* alert("img_url : "+imgArr[i].img_url+"\n"+"img_name : "+imgArr[i].img_name); */
						insertImage( imgArr[i].img_url , imgArr[i].img_name, imgArr[i].img_id );
					}
					self.close();
				});
			}
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>
 <body>
	<!--| ***body StarT*** |-->

		<div class="cl_div_imgUp_fullWrapper">
			<div class="w3-container w3-purple">
				<h2>이미지 업로드</h2>
			</div>
			
			
			<div class="w3-container cl_div_imgUp_main">
				<div    id    = "id_div_dropzone"
						class = "cl_div_dropzone">
					<div class="cl_div_dropdownHelper">
						<h4 class="cl_h4_dropdownHelperText">업로드 하려는 이미지를 여기에 드롭다운해주세요</h4>
						<h4 class="cl_h4_dropdownHelperText">또는 해당 영역을 클릭해서 업로드할 수도 있습니다.</h4>
					</div>
				</div>
			
			
				<!-- <form action="../hello/imageUploadProc.do" class="cl_div_imgUp_main dropzone" id="myDropZone">
					<div class="fallback">
						<input name="file" type="file" multiple />
					</div>
				</form> -->
			</div>
			<div class="cl_div_insertImgBtn">
				<div 	id   = "id_div_insertImgIntoTinyMce"
						class= "w3-button w3-purple">
					<h5>포스트에 이미지 넣기</h5>
				</div>
			</div>
			
			<div style="height: 65px"></div>
			<div class="w3-container w3-purple cl_div_imgUp_footer">
				<h2 style="text-align: right;">powered by AkashicRecords</h2>
			</div>
			
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>