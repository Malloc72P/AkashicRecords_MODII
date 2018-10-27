<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>writePost</title>


	
	<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		/* table, tr, td, th{
			border : 1px solid black;
		} */
		
		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>
	
	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		
		$(document).ready( function () {
			
		    var table = $('#id_table_adminUserMgr').DataTable({
                ajax		: {
                				url			:	AKASHIC.URL+AKASHIC.PROJECT+"/hello/getUsersMetadata.do"
                			  },
              	columns		:	[
													{ data : "user_email" },
													{ data : "thumbnail_image_id" },
													{ data : "user_nickname" },
													{ data : "validation" },
													{ data : "adminauthority_level" }
												]
		    	
		    });
		} );

		
		function getAdminPage(){
			/*
			 * 패스워드 체크가 필요한 패널을 열기 전에 거쳐가는 패스워드 재확인 패널의 ajax함수입니다
			 * */
			console.log("mainPage.js >>> getAdminPage >>> 함수 호출됨")
				
			$.ajax(
				{ 
					method : "post",
					url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/adminPage.do",
					cache  : false
				}
			)
				.done(function(result){
					console.log("mainPage.js >>> getAdminPage >>> AJAX수신 완료")
					var jsonRes = JSON.parse(result)
					//서버로부터 받은 데이터를 json으로 파싱한다
					console.log("jsonRes : "+jsonRes);
				})//done
		}//function getAdminPage
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
	
 </head>   
 <body>
	<!--| ***body StarT*** |-->

		<div class="w3-white w3-card-4 w3-display-middle boxShadow-lite popUp" 
		style="width: 70%; " id="id_div_adminPagePanel">
			<div class="w3-container w3-bar w3-white" id="id_div_adminPageCloser">
				<h2 class="w3-bar-item">관리자 페이지...</h2>
				<h2 class="w3-bar-item w3-right w3-button"><i class="im im-x-mark"></i></h2>
			</div>
			<div class="w3-container  w3-white" style="margin-top: -1px;">
				<hr class="w3-black">
			</div>
			
			<form class="w3-container" method="post" action="/hello/loginProc.do" >
				<table id="id_table_adminUserMgr" class="adminUserMgr">
				    <thead>
				        <tr>
				            <th>user_email</th>
				            <th>thumbnail_image_id</th>
				            <th>user_nickname</th>
				            <th>validation</th>
				            <th>adminauthority_level</th>
				            
				        </tr>
				    </thead>
				</table>
				       
				<input class="w3-btn w3-white btnBorderBottom"
				       type ="button" id="id_btn_adminPageGoBack" value="뒤로가기">
				<br><br>
			</form>
		</div>

	<!--| ###body EnD### |-->
 </body>
</html>