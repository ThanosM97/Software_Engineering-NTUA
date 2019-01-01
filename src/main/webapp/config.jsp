<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="gr.ntua.ece.stingy.conf.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>JSP</title>
    </head>

    <body>

        <%
        Configuration conf = Configuration.getInstance();
        %>

        <h2>
        	Configuration of <%= conf.getContextPath()%>
    	</h2>
    	<div>
    	    <table border="12">
    		<%
    		for(String key : conf.propertyNames()) {
    		%>
    			<tr>
    				<td><%= key %></td>
    				<td><%= conf.getProperty(key) %></td>
				</tr>
    		<% } %>
    		</table>
    	</div>

    </body>
</html>
