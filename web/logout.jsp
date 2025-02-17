<%-- 
    Document   : logout
    Created on : May 13, 2015, 1:01:08 PM
    Author     : Geofrey Nyabuto
--%>
<%
    response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setDateHeader("Expires", 0);

 session.invalidate();
                response.sendRedirect("index.jsp");

    
%>
<%@page import="java.net.InetAddress"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>log out</title>
    </head>
    <body>
          <%
 
           
          
%>

    </body>
    
   
</html>
