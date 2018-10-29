<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>writePost</title>

	<script src="../hello/js/jsConstData.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		div.userTableWrapper{
		    width: 100%;
		    height: 100%;
		    text-align: center;
		}
		.btnBorderBottom {
		    border-bottom: 1px solid #cbcbcb;
		}
		input.updateInputer{
			width: 100%;
		}
		table.updateTable{
			position	:	relative;
			border	:	1px solid black;
			margin: 0 auto;
		}
		table.updateTable tr, table.updateTable td, table.updateTable th{
			border	:	1px solid black;
			text-align: center;
		}
		div#id_table_adminUserMgr_wrapper{
			 overflow: auto;
		}
		table#id_table_adminUserMgr {
		    width: 100% !important;
		}
		
		/*--| ###CSS-StylesheeT EnD### |--*/
	</StylE>
<c:if test="${ adminChecker }">
	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		
		$(document).ready( function () {
			
		    var myDataTable = $('#id_table_adminUserMgr').DataTable({
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
		    
		    $('#id_table_adminUserMgr tbody').on( 'click', 'tr', function () {
		        if ( $(this).hasClass('selected') ) {
		            $(this).removeClass('selected');
		        }
		        else {
		        	myDataTable.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		        }
		        var rowData = myDataTable.row(".selected").data();
		        console.log("rowData : ",rowData);
		        getSelectedRecord(	rowData.user_email, rowData.thumbnail_image_id, 
		        					rowData.user_nickname,
		        					rowData.adminauthority_level
		        				 );
		    } );
		 
		    $('#button').click( function () {
		    	myDataTable.row('.selected').remove().draw( false );
		    } );
		    
		    updateEventBinder(myDataTable);
		    deleteEventBinder(myDataTable);
		    closeEventBinder();
		    resetEventBinder();
		} );
		
/* 		id="id_btn_delete"
		id="id_btn_update"
		id="id_btn_close" */
		
		
		function updateTableClear(){
			$("#id_input_update_email").val("");
			$("#id_opt_thumb2").val("");
			$("#id_input_update_nickname").val("");
			//$("#id_input_update_validation").val("");
			$("#id_input_update_authority").val("");
		}
		function getSelectedRecord(email,imgid,nickname,authorityLevel){
			$("#id_input_update_email").val(email);
			$("#id_opt_thumb2").val(imgid);
			$("#id_input_update_nickname").val(nickname);
			//$("#id_input_update_validation").val(validation);
			$("#id_input_update_authority").val(authorityLevel);
		}
		function updateEventBinder($myDataTable){
			$("#id_btn_update").click(function(){
				var	email		=	$("#id_input_update_email").val();
				//var	imgid		=	$("#id_input_update_img_id").val();
				var	imgid		=	$("select#id_input_update_img_id > option:selected").val();
				var nickname	=	$("#id_input_update_nickname").val();
				var	validation	=	$("select#id_input_update_validation > option:selected").val();
				var	authority	=	$("#id_input_update_authority").val();
				$.ajax( 
						{
							method : "post",
							url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/updateUserData.do",
							data   : { 
										"email"			:	email,
										"imgid"			:	imgid,
										"nickname"		:	nickname,
										"validation"	:	validation,
										"authority"		:	authority
							         },
						    success : function(result){
						    	var jsonRes = JSON.parse(result)
						    	if(jsonRes.updateChecker == "true"){
						    		alert("성공적으로 수정되었습니다");
						    		updateTableClear();
						    		$myDataTable.ajax.reload();
						    	}
						    	else{
						    		alert("수정에 실패하였습니다")
						    	}
						    }//success
						}//ajax {}
				)//.ajax
			})
		}
		function deleteEventBinder($myDataTable){
			$("#id_btn_delete").click(function(){
				var	email	=	$("#id_input_update_email").val();
				$.ajax( 
						{
							method : "post",
							url    : AKASHIC.URL+AKASHIC.PROJECT+"/hello/deleteUserdata.do",
							data   : { 
										"email"	:	email
							         },
						    success : function(result){
						    	var jsonRes = JSON.parse(result)
						    	if(jsonRes.deleteChecker == "true"){
						    		alert("성공적으로 삭제되었습니다");
						    		updateTableClear();
						    		$myDataTable.ajax.reload();
						    	}
						    	else{
						    		alert("삭제에 실패하였습니다")
						    	}
						    }//success
						}//ajax {}
				)//.ajax
			})
		}
		function closeEventBinder(){
			$("#id_btn_close").click(function(){
				self.close();
			})
		}
		function resetEventBinder(){
			$("#id_btn_clear").click(function(){
				updateTableClear();
			})
		}
		
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
</c:if>	

	
 </head>
 
 <c:if test="${ adminChecker }">
	 <body>
		<!--| ***body StarT*** |-->
	
			<div class="w3-white w3-card-4 w3-display-middle boxShadow-lite popUp userTableWrapper"
				id="id_div_adminPagePanel">
				<div class="w3-container w3-bar w3-white" id="id_div_adminPageCloser">
					<h2 class="w3-bar-item">관리자 페이지...</h2>
					<h2 class="w3-bar-item w3-right w3-button"><i class="im im-x-mark"></i></h2>
				</div>
				<div class="w3-container  w3-white" style="margin-top: -1px;">
					<hr class="w3-black">
				</div>
				
				<form class="w3-container" method="post" action="/hello/loginProc.do" >
					<table id="id_table_adminUserMgr" class="adminUserMgr" style="text-align: center;">
					    <thead>
					        <tr>
					            <th>user_email</th>
					            <th>thumbnail_image_id</th>
					            <th>user_nickname</th>
					            <th>validation</th>
					            <th>authority_level</th>
					        </tr>
					    </thead>
					</table>
					<div style="width: 100%; margin-top: 15px; overflow: auto;">
						<table class="updateTable">
							<thead>
								<tr>
						            <th>thumbnail</th>
						            <th>nickname</th>
						            <th>validation</th>
						            <th>authority</th>
								</tr>
							</thead>
<!-- 							<tbody> -->
								<tr>
									<!-- <td><input class="updateInputer" type="text" id="id_input_update_img_id"></td> -->
									<td>
										<select id="id_input_update_img_id"
											class="updateInputer w3-select w3-border w3-round-large" type="text">
											<option value="" disabled selected>썸네일 이미지를 리셋하려면 1을 선택해주세요</option>
											<option id="id_opt_thumb1" value="1">1</option>
											<option id="id_opt_thumb2" value="1">기존 이미지</option>
										</select>
									</td>
									<td><input class="updateInputer" type="text" id="id_input_update_nickname"></td>
									<!-- <td><input class="updateInputer" type="text" id="id_input_update_validation"></td> -->
									<td>
										<select id="id_input_update_validation"
											class="updateInputer w3-select w3-border w3-round-large" type="text">
											<option value="" disabled selected>활성화 여부를 선택해주세요</option>
											<option id="id_opt_validate1" value="true">활성화</option>
											<option id="id_opt_validate2" value="false">비활성화</option>
										</select>
									</td>
									<td><input class="updateInputer" type="text" id="id_input_update_authority"></td>
<!-- 								</tr> -->
							</tbody>
						</table>
					</div>
					<input class="w3-btn w3-white btnBorderBottom" type ="button" id="id_btn_clear" value="리셋">
					<input class="w3-btn w3-white btnBorderBottom" type ="button" id="id_btn_update" value="수정하기">
					<input class="w3-btn w3-white btnBorderBottom" type ="button" id="id_btn_delete" value="삭제하기">
					<input class="w3-btn w3-white btnBorderBottom" type ="button" id="id_btn_close" value="뒤로가기">
					<br><br>
					<input type="hidden" id="id_input_update_email">
				</form>
			</div>
	
		<!--| ###body EnD### |-->
	 </body>
 </c:if>
 
 <c:if test="${ !adminChecker }">
 	<body>
 		<h5>비정상적인 접근입니다.</h5>
 	</body>
 </c:if>

</html>