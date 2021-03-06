<%@ taglib 
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="constSet.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>Skynet765's Akashic Records</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="myStyle/myStyle.css">
	<link rel="stylesheet" href="myStyle/main_mediaSet.css">
	<link rel="stylesheet" href="myStyle/css_preset.css">
	<link rel="stylesheet" href="myStyle/subSection.css">
	<link rel="stylesheet" href="https://cdn.iconmonstr.com/1.3.0/css/iconmonstr-iconic-font.min.css">
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.2.0/anime.js"></script>
	<script src="js/jsConstData.js"></script>
  	<script src="js/mainPageJS.js"></script>
  	
  	<script src="js/a_ajaxRequest.js"></script>
  	
  	<script src="js/v_effectAndAnime.js"></script>
  	
  	<script src="js/b_binderEvent_Core.js"></script>
  	<script src="js/b_binderEvent_SubSection.js"></script>
  	
  	<script src="js/p_Panels.js"></script>
  	
  	
  	<script src="js/mgr_account_MK2.js"></script>
  	<script src="js/animeBinder.js"></script>
  	
	<StylE>
		/*--| ***CSS-StylesheeT StarT*** |--*/
		*{
			font-family: 'Source Sans Pro', sans-serif;
		}
		body{
			background: white;
			display: none;
		}
		/*--| ###CSS-StylesheeT EnD### |--*/
		
	</StylE>
	<ScripT>
		/*--| ***JavascripT StarT*** |--*/
		$(document).ready(function(){
			//로그인 패널 이벤트 바인더
			bind_Open_Panel("id_a_openLogin"      , "id_div_loginPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_h2_loginCloser"  , "id_div_loginPanel" , "id_div_mainContent")
			bind_Close_Panel("id_btn_loginGoBack" , "id_div_loginPanel" ,"id_div_mainContent")
			
			//리지스터 패널 이벤트 바인더
			bind_Open_Panel("id_a_openRegister"      , "id_div_registerPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_h2_registerCloser"  , "id_div_registerPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_btn_registerGoBack" , "id_div_registerPanel" ,"id_div_mainContent")
			
			//어드민 페이지 이벤트 바인더
			adminPageOpener_eventBinder();
			
			//마이페이지 페이지 이벤트 바인더
			myPageOpener_eventBinder()
			bind_Close_Panel("id_h2_myPageCloser"  , "id_div_myPagePanel" ,"id_div_mainContent")
			bind_Close_Panel("id_btn_myPageGoBack"  , "id_div_myPagePanel" ,"id_div_mainContent")
			
			//포스트 작성 패널 이벤트 바인더
			bind_Close_Panel("id_div_writePostCloser"    , "id_div_writePostPanel"  ,"id_div_mainContent")
			bind_Close_Panel("id_btn_writePostGoBack"    , "id_div_writePostPanel"  ,"id_div_mainContent")
			submitPost_eventBinder("id_input_submitPost" , "id_input_writePostTitle", "id_input_writePostContent", "id_select_writePostSeries")
			
			//시리즈 작성 패널 이벤트 바인더
			bind_Close_Panel("id_div_writeSeriesCloser"  , "id_div_writeSeriesPanel"  ,"id_div_mainContent")
			bind_Close_Panel("id_btn_writeSeriesGoBack"  , "id_div_writeSeriesPanel"  ,"id_div_mainContent")
			submitSeries_eventBinder("id_input_submitSeries" , "id_input_writeSeriesTitle")
			
			//방명록 작성 패널 이벤트 바인더
			bind_Close_Panel("id_div_GbCloser"       , "id_div_writeGbPanel"  ,"id_div_mainContent")
			bind_Close_Panel("id_btn_writeGbGoBack"  , "id_div_writeGbPanel"  ,"id_div_mainContent")
			submitGbMsg_eventBinder("id_input_submitGb" , "id_input_writeGbMsg")
			
			//방명록 답글 패널 이벤트 바인더
			bind_Close_Panel("id_div_GbReplyCloser"       , "id_div_writeGbReplyPanel"  ,"id_div_mainContent")
			bind_Close_Panel("id_btn_writeGbReplyGoBack"  , "id_div_writeGbReplyPanel"  ,"id_div_mainContent")
			submitGb_ReplyMsg_eventBinder("id_input_submitGbReply" , "id_input_writeGbReplyMsg", "id_i_gbReplyBtn")
			
			//패스워드체크 패널 이벤트 바인더
			bind_Close_Panel("id_btn_pwchkGoBack" , "id_div_pwCheckerPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_div_pwchkCloser" , "id_div_pwCheckerPanel" ,"id_div_mainContent")
			
			//패스워드체크 패널 이벤트 바인더
			bind_Close_Panel("id_btn_admin_pwchkGoBack" , "id_div_admin_pwCheckerPanel" ,"id_div_mainContent")
			bind_Close_Panel("id_div_admin_pwchkCloser" , "id_div_admin_pwCheckerPanel" ,"id_div_mainContent")
			
			//로그아웃 기능 이벤트 바인더
			logout_eventBinder("id_a_logout")
			
			//페이지 로드 이펙트 바인더
			$("body").fadeIn(500)
			var post_anime = bind_post_Anime("sub-section" , "post-wrapper")
			
			//FUNCTION 셀렉팅 애니메이션 바인더
			bind_Select_Function("id_div_funcList" , "id_div_subSection" , "id_tempData_in_pageNum")
			
			//포스트 작성 기능 바인딩
			
			
			/* binder_clickRecentPosts($("#sel-2") , $("#id_div_subSection")) */
			
			
		})
		
		/*--| ###JavascripT EnD### |--*/
	</ScripT>
 </head>
 <body>
	<!--| ***body StarT*** |-->
		<!-- 아카식 사이드바 -->
			<div id="akashicSideBar" class="main-sidebar w3-sidebar w3-bar-block <%=MainConst.THEME_COLOR_MAIN%> w3-card w3-animate-right" style="right: 0px; display: none;"> <!-- 아카식 사이드바 -->
				<c:choose>
				
					<c:when test="${email != null}">
						<div id="id_div_loggedIn_sidebar">
							<h3 class="w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<h4 id="id_div_sidebar_email" class="w3-bar-item">${ user_email }</h4>
							<h4>
								<a  id="id_a_logout" class="w3-bar-item w3-button">logout</a>
							</h4>
							<c:if test="${ adminCheck }">
								<h4>
									<a  id="id_a_adminPageOpener" class="w3-bar-item w3-button">AdminPage</a>
								</h4>
							</c:if>
							<h4><a  id='id_a_myPageOpener' class='w3-bar-item w3-button'>MyPage</a></h4>
							
						</div>
						<div id="id_div_notLoggedIn_sidebar" style="display:none;">
							<h3 class="chk-side-on w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<a class="w3-bar-item w3-button" id="id_a_openLogin">Login</a>
							<a class="w3-bar-item w3-button" id="id_a_openRegister" >Register</a>
						</div>
					</c:when>
					
					<c:otherwise>
						<div id="id_div_loggedIn_sidebar" style="display:none;">
							<h3 class="w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<h4 class="w3-bar-item" id="id_div_sidebar_email">${ user_email }</h4>
							<h4>
								<a  class="w3-bar-item w3-button" id="id_a_logout">logout</a>
							</h4>
						</div>
						<div id="id_div_notLoggedIn_sidebar">
							<h3 class="chk-side-on w3-bar-item w3-button" onclick="w3_close()">Close</h3>
							<a class="w3-bar-item w3-button" id="id_a_openLogin">Login</a>
							<a class="w3-bar-item w3-button" id="id_a_openRegister" >Register</a>
						</div>
					</c:otherwise>
				</c:choose>			
			</div>
	<!-- 아카식 사이드바 -->
	<!-- 아카식 네비바-->
		<div class="main-navibar w3-bar <%=MainConst.THEME_COLOR_MAIN%>"><!-- 상단바 -->
			<a href="#" class="w3-bar-item" style="text-decoration: none;">
				<h3>Akashic Records Mk.52</h3>
			</a>
			
			<div class="chk-side-on m_mgr_account">
			<c:choose>
				<c:when test="${email != null}">
					<button id="id_btn_emailOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button" onclick="w3_open()">
						<h3 id="id_h3_openerContent" class="chk-side-on w3-bar-item">${ user_email }</h3>
					</button>
					<button id="id_btn_keyOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button c_sidebar_toggle_key" style="display:none;"  onclick="w3_open()">
						<h3 class="chk-side-on" ><i class="chk-side-on im im-key"></i></h3>
					</button>
				</c:when>
				<c:otherwise>
					<button id="id_btn_emailOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button" style="display:none;" onclick="w3_open()">
						<h3 id="id_h3_openerContent" class="chk-side-on w3-bar-item">${ user_email }</h3>
					</button>
					<button id="id_btn_keyOpener_sidebar" class="chk-side-on w3-bar-item w3-right w3-button c_sidebar_toggle_key" onclick="w3_open()">
						<h3 class="chk-side-on" ><i class="chk-side-on im im-key"></i></h3>
					</button>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		
<!-- 아카식 네비바 -->

<!-- 아카식 메인컨텐츠 -->
	<div id="id_div_mainContent" class="cl_div_mainContent boxShadow-lite">


		
		<!-- 아카식 배너 이미지 -->
		<div class="imgRanderer banner-img w3-sepia">
			<div class="profile-img imgRanderer w3-normal" style="display:none;">
				<h4 class="profile-name">Guest</h4>
			</div>
		</div>
		<!-- 아카식 배너 이미지 -->
		
		
		
		<!-- 아카식 컨텐츠 래퍼 -->
		<div class="main-section">
		
		<!-- 아카식 FUNCTION-SELECTOR -->
		<div id="id_div_funcList" class="func-list w3-card w3-border w3-bar">
				<!-- func-element-1 -->
				<a id="sel-1"  class="w3-bar-item w3-button func-element1 w3-mobile" href="#sel-1" style="width:25%;" ><h4>프로필</h4></a>
				
				<!-- func-element-2 -->
				<a id="sel-2"  class="w3-bar-item w3-button func-element2 w3-mobile" href="#sel-selected" style="width:25%;" ><h4>최신포스트</h4></a>
				
				<!-- func-element-3 -->
				<a id="sel-3"  class="w3-bar-item w3-button func-element3 w3-mobile" href="#sel-3" style="width:25%;" ><h4>시리즈</h4></a>
				
				<!-- func-element-4 -->
				<a id="sel-4"  class="w3-bar-item w3-button func-element4 w3-mobile" href="#sel-4" style="width:25%;" ><h4>방명록</h4></a>
							
		</div>
		<!-- 아카식 FUNCTION-SELECTOR -->
		
		
		<!-- 아카식 SUB-SECTION -->
		
		<div id="id_div_subSection" class="sub-section" style="margin-bottom: 80px;">
			
		</div>
		
		<form id="id_form_tempData" name="form_tempData">
			<input id="id_tempData_in_pageNum"  type="hidden" value="1"><!-- recentPost의 pageNum -->
			<input id="id_tempData_gb_pageNum"  type="hidden" value="1"><!-- guestBook의 pageNum -->
		</form>
		
		<!-- 아카식 SUB-SECTION -->
		
		
		</div><!-- wrapper-content -->
		
		

	</div><!-- id_div_mainContent -->
<!-- 아카식 메인컨텐츠 -->

	<!--| ###body EnD### |-->
		<div style="margin-bottom: 193px;"></div> <!-- 아카식 FOOTER -->
		<div class="w3-bar <%=MainConst.THEME_COLOR_MAIN%>" style="text-align: right; width: 100%;font-weight: bold; border-top: 1px solid #cbcbcb; bottom: 0px;">
			<h3 class="w3-bar-item w3-right">Akashic Records</h3>
		</div>

		
	
	
		<!-- popPannel_login -->
			<jsp:include page="mgr_account/login.jsp"></jsp:include>
		<!-- popPannel_register -->
			<jsp:include page="mgr_account/register.jsp"></jsp:include>
		<!-- popPannel_register -->
			<jsp:include page="mgr_account/myPage.jsp"></jsp:include>
			
		<!-- popPannel_pwChecker -->
			<jsp:include page="subSection/pwChecker.jsp"></jsp:include>
		<!-- popPannel_pwChecker -->
			<jsp:include page="subSection/pwChecker_admin.jsp"></jsp:include>
		<!-- popPannel_postWrite -->
			<jsp:include page="subSection/writePost.jsp"></jsp:include>
		<!-- popPannel_viewContent -->
		<jsp:include page="subSection/viewPost.jsp"></jsp:include>
		<!-- popPannel_seriesWrite -->
		<jsp:include page="subSection/writeSeries.jsp"></jsp:include>
		<!-- popPannel_GbWrite -->
		<jsp:include page="subSection/writeGB.jsp"></jsp:include>
		<!-- popPannel_GbReplyWrite -->
		<jsp:include page="subSection/writeGB_Reply.jsp"></jsp:include>
							
			
 </body>
</html>