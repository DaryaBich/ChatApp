<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
   <head>
    <title>MyChats</title>
  </head>
  <body>
  <div class="container">
    <h2>Chats</h1>
  <ul class="list-group">
   <c:forEach var="chat" items="${chats}">
      <li class="list-group-item">${chat}</li>
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">choose</button>
   </c:forEach>
  </ul>
  </div>
  </body>
</html>