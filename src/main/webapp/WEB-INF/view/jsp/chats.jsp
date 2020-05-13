<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
    background: linear-gradient(to top right, powderblue, #ffebeb);
    font-family: cursive;
    font-weight: 1000;
      font-size: 23px;
   }
label{
   color:#8f0101;
   font-size: 60px;
}

form {
   width:400px;
   height: 500px;
}

button {
    background-color: #154c9e;
      border: none;
      padding: 10px;
      width: 200px;
      box-shadow: 1px 5px 30px -5px rgba(0, 0, 0, 0.6);
      color: #fff;
      margin-top: 30px;
      cursor: pointer;
      border-radius: 10px;
      font-size: 20px;
}

table {
    width: 600px;
    border-collapse: collapse;
    background: white;
}

tr, th, td {
    border: 2px solid grey;
    padding: 5px 5px;
    border-style: groove;
    border-width: 3px;
    border-color:#b01919;
}

caption {
    padding: 14px 0;
    font-size: 30px;
    text-align: left;
}

.center {
    text-align: center;
}
</style>
</head>
<body>
<form action="/chatapp/check" method="POST">
  <div class="container">
  <label> user: ${userName}</label>
    <table class="table table-bordered" align=center>
    <caption></caption>
     <tr>
       <th>With whom</th>
       <th></th>
     </tr>
     <c:forEach var="chat" items="${chatName}">
           <tr class = "center">
           <td>${chat}</td>
           <td><button class="btn btn-outline-success my-2 my-sm-0" type="submit">choose</button></td>
           </tr>
        </c:forEach>
    </table>
  </div>
  </form>
  </body>
</html>