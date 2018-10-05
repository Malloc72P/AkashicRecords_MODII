<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="constSet.MainConst" %>
<script src="../js/jsConstData.js"></script>
<!--| ***body StarT*** |-->
<div class="sel-2">

	<c:if test="${count==0}">
		<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
		<tr>
		    <td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
		</table>
	</c:if>
	
	<c:if test="${currentPage==1}">
		<div id="id_div_postListHeader" class="w3-card w3-bar w3-border" >
		<div class="w3-bar-item">
		<h5>${count} 포스트</h5>
		</div>
		<a class="w3-right w3-bar-item w3-button w3-mobile" id="id_a_writePost" href="#">
		<h5>글쓰기</h5>
		</a>
		</div>
	</c:if>

	<c:forEach var="article"  items="${articleList}">
			<div class="post-wrapper w3-container post-body w3-pannel w3-leftbar <%=MainConst.THEME_COLOR_MAIN%>">
				
				<div class="post-content custom-w3-card">
					<div class="post-text-area">
						<div class="post-header w3-xlarge">
							<p class="w3-large post-title" style="padding: 5px 5px 5px 15px;">
								<a href="<%=MainConst.PROJECT_NAME%>/content.do?num=${article.post_id}&pageNum=${currentPage}">
									${article.post_title}
								</a>
							</p>
						</div>
						<div class="post-summary w3-small w3-opacity">
							<p class="w3-middle post-text" style="padding: 5px 5px 5px 15px;">
								${ article.post_summary }
							</p>				
						</div>
						<div class="post-footer-wrapper w3-display-bottomleft">
							<p class="post-text post-footer-date post-footer-text">${ article.post_regdate }</p>
							<p class="post-text post-footer-view post-footer-text">${ article.post_viewcount } 읽음</p>
						</div>
	
					</div>
					<div class="post-img-area" style="text-align: center">
						<div class="post-img imgRanderer"></div>
					</div>
				</div> <!-- akashic-post -->
			</div><!-- post-contentWrapper -->
			<hr>
		</c:forEach>
		<form name="res_form">
			<input type="hidden" id="id_currentPage" name="currentPage" value="${ currentPage }">
			<input type="hidden" id="id_pageCount" name="pageCount" value="${ pageCount }">
			<input type="hidden" id="id_postCount" name="postCount" value="${count}">
		</form>
</div>

<!--| ###body EnD### |-->



































