<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
${post.getPost_content()}
<form>
	<input type="hidden" id="post_title" value="${ post.getPost_title() }">
	<input type="hidden" id="post_regdate" value="${ post.getPost_regdate() }">
	<input type="hidden" id="post_viewcount" value="${ post.getPost_viewcount() }">
	<input type="hidden" id="post_writer" value="${ writer }">
	<input type="hidden" id="post_series" value="${ series }">
	<input type="hidden" id="errorChecker" value="${ errorChecker }">
	
</form>