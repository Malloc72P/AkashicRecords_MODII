<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
${post.getPost_content()}
<form>
	<input type="hidden" name="post_title" value="${ post.getPost_title() }">
	<input type="hidden" name="post_regdate" value="${ post.getPost_regdate() }">
	<input type="hidden" name="post_viewcount" value="${ post.getPost_viewcount() }">
</form>