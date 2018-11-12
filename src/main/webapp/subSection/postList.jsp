<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

{
	"seriesCount"	:	${ seriesCount },
	"seriesList"	:	
		[
			<c:forEach var="series" items="${ seriesList }" varStatus="status">
				{
					"seriesId"			:	"${ series.getSeries_id() }",
					"title"				:	"${ series.getSeries_Title() }",
					"postCount"			:	"${ series.getSeries_postcount() }",
					"viewCount"			:	"${ series.getSeries_viewcount() }",
					"regDate"			:	"${ series.getSeries_regdate() }",
					"imgUrl"			:	"${ imgMap.get(series.getImg_id()) }"
				}
				<c:if test="${ !status.last }">,</c:if>
			</c:forEach>
		]
}


	