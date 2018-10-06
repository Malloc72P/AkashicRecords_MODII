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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="../hello/js/dropzone.js"></script>
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		.cl_div_imgUp_fullWrapper{
			
		}
		.cl_div_imgUp_main{
			width : 100%;
			height: 300px;
		}
		.cl_input_imageUploadeCore{
		}
		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>

	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
			$(document).ready(function(){
				
			})
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>
 <body>
	<!--| ***body StarT*** |-->

		<div class="cl_div_imgUp_fullWrapper">
			<div class="w3-container w3-teal">
				<h2>이미지 업로드</h2>
			</div>
			
			<div class="w3-container cl_div_imgUp_main">
				<form action="../hello/imageUploadProc.do" class="cl_div_imgUp_main dropzone" id="my-awesome-dropzone">
					<div class="fallback">
						<input name="file" type="file" multiple />
					</div>
				</form>
			</div>
			
			<div class="w3-container w3-teal">
				<h2 style="text-align: right;">powered by AkashicRecords</h2>
			</div>
			
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>