<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
  <head>
    <title>BeansPresenter</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${startertemplate}" rel="stylesheet" />
  </head>
  <body>
   <!-- Page Content -->
  <div class="container">
<nav class="navbar navbar-light bg-light">
  <form class="form-inline" method="GET">
    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
  </form>
</nav>
  <h2>Root Context Beans</h1>
<ul class="list-group">
 <c:forEach var="bean" items="${beans}">
    <li class="list-group-item">${bean}</li>
 </c:forEach>
</ul>
 <h2>Web Beans</h1>
<ul class="list-group">
  <c:forEach var="webBean" items="${webBeans}">
    <li  class="list-group-item list-group-item-action">${webBean}</li>
  </c:forEach>
</ul>
  </div>
 </body>
</html>
