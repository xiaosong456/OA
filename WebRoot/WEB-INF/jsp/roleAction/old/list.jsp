<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<%-- <s:iterator value="#roleList">
    		<s:property value="id" />
    		<s:property value="name" />
    		<s:property value="description" />
    		<a href="roleAction_delete.action?id=<s:property value="id" />" onclick="return confirm('确定要删除吗?')">删除</a>
    	</s:iterator> --%>
    	
    	<s:iterator value="#roleList">
    		${id },
    		${name },
    		${description },
<%--     	<a href="<%=basePath %>roleAction_delete.action?id=${id }" onclick="return confirm('确定要删除吗?')">删除</a>
 --%>    		
 
 				<%-- <s:a action="roleAction_delete">
    				<s:param name="id" value="%{id}"></s:param>删除
    			</s:a> --%>
    			
    			<s:a action="roleAction_delete?id=%{id}" onclick="return confirm('确定要删除吗?')">删除</s:a>
    			<s:a action="roleAction_editUI?id=%{id}">修改</s:a>
    	</s:iterator>
    		<s:a action="roleAction_addUI">添加</s:a>
    	
  </body>
</html>
