<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
    background: linear-gradient(#c6e4ee 0%, #c6e4ee 40%, #fed1ae 60%, #faa0b9 70%, #cb7dcb 80%, #757ecb 100%);
    font-family: cursive;
    font-weight: 1000;
      font-size: 30px;
   }
   label{
   color:#8f0101;}
form {
    width:400px;
     height: 200px;
     margin: 200px auto 0 auto;
     text-align: center;
     color=white
}
button {
      border: none;
      padding: 30px;
      width: 300px;
      border-radius: 3px;
      box-shadow: 1px 5px 30px -5px rgba(0, 0, 0, 0.6);
      color: #fff;
      margin-top: 60px;
      cursor: pointer;
      border-radius: 10px;
      font-size: 20px;
      font-family: cursive;
}
.send{
background-color: #d32f2f;
}
.back{
background-color: #154c9e;
}
</style>
</head>
<body>
 <label> user: ${user.getName()}</label>
<form>
  <button class="send" type="submit">Send</button>
  </form>
  <form action="/chatapp/openchats" method="GET">
    <button class="back" type="submit"name="userId" value=${user.getId()}>Back</button>
    </form>
  </body>
</html>