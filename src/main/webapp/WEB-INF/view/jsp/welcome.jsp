<!DOCTYPE html>
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
      font-size: 60px;
      width:1500px;
      height: 550px;
   }
label{
   color:#8f0101;
}
form {
    width:600px;
     height: 400px;
     margin: 200px auto 0 auto;
     text-align: center;
     background:#fcfafaab;
     float: center;
}
button {
    background-color: #d32f2f;
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
      margin: 80px 0 0 0;
}
</style>
</head>
<body>
   <form action="/chatapp/authentication" method="POST">
     <label>Welcome to the chat application!</label>
     <button type="submit" class="btn btn-default btn-lg">Log in to account</button>
   </form>
 </body>
</html>

