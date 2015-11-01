<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<tiles:insertAttribute name="metaTags"/>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<tiles:insertAttribute name="siteHeaderInclude"/>
</head>
<body>
   	<div id="wrapper">
		<tiles:insertAttribute name="sidebar"/>
		<tiles:insertAttribute name="body"/>
	</div>
	<!-- /#wrapper -->
	<tiles:insertAttribute name="siteFooterInclude"/>
</body>
</html>
