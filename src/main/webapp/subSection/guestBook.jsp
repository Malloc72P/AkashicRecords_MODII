<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="constSet.MainConst" %>

<head>
	<style type="text/css">
		.guestBookFullWrapper{
			margin-top: 50px;
		}
		.adminMsgWrapper{
		    display: block;
		    width: 100%;
		    top: -56px;
    		position: relative;
		}
		.adminMsgWrapper:after{
			display: block;
			content: '';
			clear: both;
			margin-bottom: 40px;
		}
		.userMsgWrapper{
		    display: block;
		    width: 100%;
		}
		.userMsgWrapper:after{
			display: block;
			content: '';
			clear: both;
			margin-bottom: 40px;
		}
		.msgCoupler{
		    /* margin-bottom: 50px; */
		    content: '';
		    display: block;
		}
		.adminFullWrapper{ 
		    display: flex;
		    position: relative;
		    float: right;
		    margin-right: 12px;
		}
		.adminFullWrapper:after {
		    content: '';
		    display: block;
		    clear: both;
		}
		.adminBalloon {
            display: block;
		    position: relative;
		    background: #eae36d;
	        padding: 10px 24px 10px 24px;
		    margin-top: 10px;
		    float: right;
		    margin-right: 22px;
		    border-radius: 5px;
		    margin-bottom: 10px;
		    max-width: 420px;
		}
		.adminBalloon:after {
		    content: '';
		    position: absolute;
		    border-top: 14px solid #eae36d;
		    border-right: 14px solid transparent;
		    border-left: 14px solid #eae36d;
		    border-bottom: 14px solid transparent;
		    top: 5px;
		    right: -22px;
		    clear: both;
		}
		
		.adminName{
		    position: absolute;
		    right: 60px;
		    top: -30px;
		}
		.guestBookAdminRegDate{
	        position: absolute;
		    left: 0px;
		    bottom: -8px;
		    opacity: 0.60;
		}
		.userFullWrapper{ 
		    display: flow-root;
		    position: relative;
		    float: left;
		    margin-left: 12px;
		    margin-bottom: 40px;
		}
		.userFullWrapper:after {
		    content: '';
		    display: block;
		    clear: both;
		}
		.userBalloon {
		    display: block;
		    position: relative;
		    background: #ccc;
		    padding: 10px 24px 10px 24px;
		    margin-top: 10px;
		    float: left;
		    margin-left: 71px;
		    border-radius: 5px;
		    margin-bottom: 10px;
		    max-width: 420px;
		}
		.userBalloon:after {
		    content: '';
		    position: absolute;
		    border-top: 14px solid #ccc;
		    border-right: 14px solid #ccc;
		    border-left: 14px solid transparent;
		    border-bottom: 14px solid transparent;
		    top: 5px;
		    left: -22px;
		    clear: both;
		}
		.adminProfileImgWrapper{
			width: 48px;
		    height: 48px;
		}
		.userProfileImgWrapper{
			width: 48px;
		    height: 48px;
		}
		.adminProfileImg{
		    width: 48px;
		    height: 48px;
		    border-radius: 50%;
	        top: -12px;
		    left: 6px;
		    position: relative;
		}
		.userProfileImg{
		    width: 48px;
		    height: 48px;
		    border-radius: 50%;
	        top: -87px;
		    left: -5px;
		    position: relative;
		}
		.userName{
		    position: absolute;
		    left: 57px;
		    top: -30px;
		}
		.guestBookUserRegDate{
		    position: absolute;
		    right : 0px;
		    bottom: -8px;
		    opacity: 0.60;
		}
		
		.modify-img{
			background-image: url(../hello/img/profile_default.png);
		    width: 12px;
		    height: 12px;
		    border-radius: 50%;
		    border: 1px solid grey;
		}		
		.guestBookProfileName{
			font-weight: 500;
		}
		.guestBook-text{
			
		}
		.guestBookUserReply{
			display: inline-block;
		    position: absolute;
	        right: -23px;
		    bottom: -17px;
		}
		.guestBookUserReply_icon{
			-webkit-transition-duration: 0.4s; /* Safari */
    		transition-duration: 0.4s;
    		cursor: pointer;
			font-size: 18px;
			opacity: 0.6;
		}
		.guestBookUserReply_icon:hover{
		
			opacity: 1;
		}

	</style>
</head>

<!--| ***body StarT*** |-->
<div class="sel-4">

	<c:if test="${ currentPage == 1 }">
		<div id="id_div_guestBookHeader" class="w3-card w3-bar" style="margin-bottom: 40px;" >
			<div class="w3-bar-item">
				<h5>${ count } 메세지</h5>
			</div>
			<a class="w3-right w3-bar-item w3-button w3-mobile" id="id_a_writeGuestBook" href="#">
				<h5>방명록 작성</h5>
			</a>
		</div>
	</c:if>
	
	<div class="guestBookFullWrapper" id="id_div_guestBookFullWrapper">
		<c:forEach var="guestMsg" items="${ guestMsgList }">
			<div class="msgCoupler">
				<div class="userMsgWrapper">
					<div class="userFullWrapper">
						<div class="userBalloon">
							<p class="guestBook-text w3-large">${ guestMsg.getGb_content() }</p>
						</div>
						<div class="userName guestBookProfileName"><h5>${ guestMsg.getGb_writer_email() }</h5></div>
						<div class="userProfileImgWrapper">
							<!-- ################################### -->
							<img class="userProfileImg" src="../${ guestProfImgMap.get( guestMsg.getGb_writer_email() ) }">
						</div>
						<div class="guestBookUserRegDate"><p class="w3-small">${ guestMsg_timeSet.get( guestMsg.getGb_id() ) }</p></div>
						<div	id="id_div_gbReplyBtn" 
								class="guestBookUserReply">
							<i 	id="id_i_gbReplyBtn"
								class="im im-plus-circle guestBookUserReply_icon" title="${ guestMsg.getGb_id() }"></i>
						</div>
					</div>
				</div><!-- msgWrapper -->
				
				<c:if test="${ guestMsg.getGb_from_admin_id() != -1 }">
					<div class="adminMsgWrapper">
						<div class="adminFullWrapper">
							<div class="adminBalloon">
								<p class="guestBook_text w3-large">${ guestReplyMap.get( guestMsg.getGb_id() ).getGb_content() }</p>
							</div>
							<div class="adminName guestBookProfileName"><h5>${ guestReplyMap.get( guestMsg.getGb_id() ).getGb_writer_email() }</h5></div>
							<div class="adminProfileImgWrapper">
								<img class="adminProfileImg" src="../${ adminProfImgMap.get( guestReplyMap.get( guestMsg.getGb_id() ).getGb_writer_email() ) }">
							</div>
							<div class="guestBookAdminRegDate"><p class="w3-small">${ adminMsg_timeSet.get( guestReplyMap.get( guestMsg.getGb_id() ).getGb_id() ) }</p></div>
						</div>
					</div><!-- msgWrapper -->
				</c:if>
				

						
			</div><!-- msgCoupler -->
		</c:forEach>
		<input type="hidden" id="id_gb_postCount" 	name="postCount" 	value="${count}">
		<input type="hidden" id="id_gb_pageCount" 	name="pageCount" 	value="${ pageCount }">
		<input type="hidden" id="id_gb_currentPage" name="currentPage" 	value="${ currentPage }">

</div>
<!--| ###body EnD### |-->