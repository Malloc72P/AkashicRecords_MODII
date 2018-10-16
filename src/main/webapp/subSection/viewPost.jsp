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
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		::-webkit-scrollbar {
		    display: none;
		}
		div.viewPostArticle{
		    min-height: 100%;
		    -webkit-box-shadow: 0px 42px 75px -27px rgba(0,0,0,0.75);
		    -moz-box-shadow: 0px 42px 75px -27px rgba(0,0,0,0.75);
		    box-shadow: 0px 20px 75px -20px rgba(0,0,0,0.75);
		}
		div.viewPostNavBar{
			top: 0px;
			border-bottom : 1px solid #cbcbcb;
			box-shadow: 0px 5px 6px -5px rgba(0,0,0,0.5);
		}
		div.viewPostHeader{
		    width: 80%;
		    font-size: 12px;
		    margin: 0 auto;
		    padding: 50px 0 50px 0;
		}
		.viewPostTitle{
			font-weight: 600;
			font-size: 33px;
		}
		.viewPostWriter{
				
		}
		.viewPostDate{
			opacity: 0.4;
		}
		.viewPostViewCount{
			opacity: 0.4;
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
		
		<div class="w3-white w3-card-4 w3-display-middle boxShadow-lite popUp" 
		style="width: 100%; height: 100%; top:50%; z-index:9999; overflow:auto;" 
		id="id_div_viewPostPanel">
			<div class="w3-container w3-bar w3-white viewPostNavBar">
				<a href="#" class="w3-bar-item" style="text-decoration: none;">
					<h3>Akashic Records Mk.29</h3>
				</a>
				<h2 class="w3-bar-item w3-right w3-button" id="id_div_viewPostCloser">
					<i class="im im-x-mark"></i>
				</h2>
			</div>
			<div class="viewPostArticle" >
				<div class="viewPostHeader">
					<div class="viewPostSeries">
						시리즈 : 일상
					</div>
					<div class="viewPostTitle" id="id_div_viewPostTitle">
						
					</div>
					<div class="viewPostWriter">
						Malloc72P
					</div>
					<div class="viewPostDate" id="id_div_viewPostRegdate">
						
					</div>
					<div class="viewPostViewCount" id="id_div_viewPostViewCount">
						
					</div>
				</div>
				<hr style="width:85%; margin: 0 auto; margin-bottom: 30px;">
				<div id="id_div_viewPostArticle">
					
				</div>
			</div>
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>