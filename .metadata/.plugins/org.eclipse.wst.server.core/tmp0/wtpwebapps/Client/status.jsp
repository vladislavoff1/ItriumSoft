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
    String email = (String) session.getAttribute("email");

    String requestUrl = "http://localhost:8080/Client/GetControllers";

    ControllerParams[] statuses = Controllers.getControllers(user, response.getWriter());
    if (statuses == null) {
        %>
            <h3>Statuses error</h3>
          
        <%
        return;
    }
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Information about your controllers.</title>
</head>
<body>

<h3>Information about your controllers.</h3>

<p>
User: <strong> <%= email %></strong> <br>
<a href="LogOut">Log out</a>
</p>

<% 
    for(int i = 0; i < statuses.length; i++) {
        String id = statuses[i].key;
        String time = statuses[i].time;
        String status = statuses[i].status;
        String color = "";

        switch (status) {
            case "NORMAL":
                color = "green";
                break;
            case "DISARM":
                color = "black";
                break;
            case "BREAK":
                color = "red";
                break;
            case "ALARM":
                color = "red";
                break;
            case "PART":
                color = "yellow";
                break;
        }

%>  
        <p>
        Controller ID: <strong id="<%= "id" + i  %>"><%= id %></strong><br>
        Last connection: <strong id="<%= "time" + i  %>"><%= time %></strong><br>
        Status: <strong id="<%= "status" + i  %>"><font color="<%= color %>"><%= status %></font></strong><br>
        <a href="hystory.jsp?key=<%= id %>">Hystory</a>
        </p>
<%
    }
%>

<p>
    <a href="addcontroller.jsp">Add controller</a>
</p>

<% 
    // TODO: Locate JavaScript in file.
%>
<script>

var xmlhttp;
function loadXMLDoc(url, cfunc) {
    if (window.XMLHttpRequest) {
      xmlhttp = new XMLHttpRequest();
    } else {
      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = cfunc;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}


var myVar = setInterval(timer, 1000);

function timer() {
    if (xmlhttp != undefined && xmlhttp.readyState != 4) {
        return;
    }

    loadXMLDoc("<%= requestUrl %>", update);
}

var updateParams = ["id", "time", "status"];

function update() {
    if (xmlhttp.readyState != 4 || xmlhttp.status != 200) {
        return;
    }
    var statuses = parseRequest(xmlhttp.responseText);
    for (var i = 0; i < "<%= statuses.length %>"; i++) {
        for (var j = 0; j < updateParams.length; j++) {
            switch (updateParams[j]) {
                case "status":

                    var color = "";
                    var status = statuses[updateParams[j] + i];
                    switch (status) {
                        case "NORMAL":
                            color = "green";
                            break;
                        case "DISARM":
                            color = "black";
                            break;
                        case "BREAK":
                            color = "red";
                            break;
                        case "ALARM":
                            color = "red";
                            break;
                        case "PART":
                            color = "yellow";
                            break;
                    }
                    document.getElementById(updateParams[j] + i).innerHTML = '<font color="' + color + '">' + status + '</font>';
                    break;
                default:
                    document.getElementById(updateParams[j] + i).innerHTML = statuses[updateParams[j] + i];
                    break;
            }
        }
    }
}

function parseRequest(str) {
    var res = [];
    var preRes = str.split("&");
    for (var i = 0; i < preRes.length; i++) {
        temp = preRes[i].split("=");
        res[temp[0]] = temp[1];
    }
    return res;
}

</script>

</body>
</html>