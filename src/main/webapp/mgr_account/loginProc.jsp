<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	System.out.println("___________________________");
	System.out.println("loginProc.jsp >>> 페이지 호출됨");
	boolean loginChecker = (Boolean)session.getAttribute("loginChecker");
	String email = null;
	if(loginChecker != false){
		email = (String)session.getAttribute("email");
		System.out.println("loginProc>>> "+email);	
	}
	System.out.println("___________________________");
%>
{"loginChecker":"<%=loginChecker %>","email":"<%=email %>"}