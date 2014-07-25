<%@page import="java.sql.ResultSet"%>
<%@page import="com.itrium.borey.client.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    int user = (int) userObj;
    String key = (String) request.getParameter("key");
    if (key == null) {
        return;
    }
    Event[] events = Controllers.getEvents(key, response.getWriter());
    if (events == null) {
        %>
            <h3>No events.</h3>
          
        <%
        return;
    }
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hystory of events</title>
</head>
<body>

<h3>Hystory of events</h3>

<p>
Controller ID: <strong><%= key %></strong>
</p>

<a href="status.jsp">Back</a>

<p>
    <table>
     
<%	for (int i = 0; i < events.length; i++) {%>
      <tr>
        <td><%= events[i].time %></td>
        <td><%= events[i].msg %></td>
      </tr>
<%} %>     
 
    </table>
</p>

</body>
</html>