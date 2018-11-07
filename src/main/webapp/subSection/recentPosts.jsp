<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

{
	"validator"		:	"${ currentPage }",
	"pageCount"		:	"${ pageCount }",
	"count"			:	"${count}",
	"articleList"	:	
						[
							<c:forEach var="article" items="${ articleList }" varStatus="status">
								{
									"post_id"				:	"${article.post_id}",
									"post_title"			:	"${article.post_title}",
									"post_summary"			:	"${article.post_summary}",
									"post_regdate"			:	"${article.post_regdate}",
									"post_viewcount"		:	"${article.post_viewcount}",
									"Img_url"				:	"${thumbnailMap.get(article.getImg_id())}"
								}
								<c:if test="${ !status.last }">,</c:if>
							</c:forEach>
						]
}
		
		


































