<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="web.board.BoardDBBean"%>
<%@ page import="web.board.BoardDataBean" %>
<%
	
	//request.getParameter(주소줄에있는 데이터 값을 가져오는 함수)
	String sub = request.getParameter("subject");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String content = request.getParameter("content");
	String password = request.getParameter("password");

	System.out.println("subject = "+sub);
	System.out.println("name = "+name);
	System.out.println("email = "+email);
	System.out.println("content = "+content);
	System.out.println("password = "+password);
	
	
	BoardDBBean bdb = BoardDBBean.getInstance();
// 	bdb.insertArticle(dataBean);
	bdb.insertDetailArticle(name, sub, email, content, password);
	
// 	out.println("writepro.jsp");    
	BoardDataBean dataBean = new BoardDataBean();
	dataBean.setContent(content);
	dataBean.setWriter(name);
	dataBean.setEmail(email);
	dataBean.setPasswd(password);
	dataBean.setSubject(sub);
	
	bdb.insertArticle(dataBean);
	
	
// 	dataBean.setSubject(sub);
// 	out.println("sub = "+sub);
// 	out.println(dataBean.toString());
%>
