<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

{
	"currentPage"		:	"${ currentPage }",
	"pageCount"			:	"${ pageCount }",
	"count"				:	"${count}",
	"msgList"			:	
						[
							<c:forEach var="guestMsg" items="${ guestMsgList }" varStatus="status">
								{
									"user_content"		:	"${ guestMsg.getGb_content() }",
									"user_writerEmail"	:	"${ guestMsg.getGb_writer_email() }",
									"user_imgUrl"		:	"${ guestProfImgMap.get( guestMsg.getGb_writer_email() ) }",
									"user_regDate"		:	"${ guestMsg_timeSet.get( guestMsg.getGb_id() ) }",
									"user_msgId"		:	"${ guestMsg.getGb_id() }"
									
									<c:if test="${ guestMsg.getGb_from_admin_id() != -1 }">
										,"user_replyData"	:	{
											"admin_content"		:	"${ guestReplyMap.get( guestMsg.getGb_id() ).getGb_content() }",
											"admin_writerEmail"	:	"${ guestReplyMap.get( guestMsg.getGb_id() ).getGb_writer_email() }",
											"admin_imgUrl"		:	"${ adminProfImgMap.get( guestReplyMap.get( guestMsg.getGb_id() ).getGb_writer_email() ) } ",
											"admin_regDate"		:	"${ adminMsg_timeSet.get( guestReplyMap.get( guestMsg.getGb_id() ).getGb_id() ) }"
										}
									</c:if>
								}
								<c:if test="${ !status.last }">,</c:if>
							</c:forEach>
						]
}