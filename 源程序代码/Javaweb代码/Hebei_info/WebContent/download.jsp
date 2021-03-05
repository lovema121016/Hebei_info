<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.servlet.WriteFile" %>

<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
      String title=request.getParameter("title");
      int index=Integer.valueOf(request.getParameter("index"));
      WriteFile cha=new WriteFile();
      cha.writeone( title, index);
      PrintWriter pWriter=response.getWriter();
      //响应用户
      pWriter.write("<script language='javaScript'>"+"alert('Successful');"+"document.location.href='content.jsp?id="+index+"';"+"</script>");
      pWriter.close();
   %>
</body>
</html>