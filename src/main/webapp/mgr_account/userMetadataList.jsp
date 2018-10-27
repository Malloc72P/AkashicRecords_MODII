<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	System.out.println("___________________________");
	System.out.println("userMetadataList.jsp >>> 페이지 호출됨");
	System.out.println("___________________________");
	
%>
{
	"data"	:	
				[
				<c:forEach var="item" items="${ metadatas }" varStatus="status">
					{
						"id"					:	"${ status.index }",
						"user_email"			:	"${ item.getUser_email() }",
						"thumbnail_image_id"	:	"${ item.getImg_id() }",
						"user_nickname"			:	"${ item.getUser_nickname() }",
						"validation"			:	"${ item.getValidation() }",
						"adminauthority_level"	:	"${ item.getAdmin_level() }"
					}
					<c:if test="${ !status.last }">,</c:if>
				</c:forEach>
				]
}



