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
    font-size: 30px;
    height: 700px;

   }
   label{
   color:#8f0101;}
form {
    width:500px;
     height: 680px;
     margin: 60px auto 0 auto;
     text-align: center;
     background:#ffffff8e;
}
input[type=text], input[type=password] {
     width: 300px;
     height:60px;
     font-size: 30px;
     margin-bottom: 25px;
     border-radius: 10px;
     padding-left: 80px;
     border-color: red;
}
button {
    background-color: #d32f2f;
      border: none;
      padding: 15px;
      width: 400px;
      border-radius: 3px;
      box-shadow: 1px 5px 30px -5px rgba(0, 0, 0, 0.6);
      color: #fff;
      margin-top: 60px;
      cursor: pointer;
      border-radius: 10px;
      font-size: 20px;
      font-family: cursive;
}
</style>
</head>
<body>
   <form action="/chatapp/createaccount" method="POST">
     <label>Input Name
       <input type="text" name="name"><br/>
     </label>
     <label>Input Login
        <input type="text" name="login"><br/>
     </label>
     <label>Input Password
       <input type="password" name="password1"><br/>
     </label>
     <label>Repeat Password
       <input type="password" name="password2"><br/>
     </label>
     <button type="submit">Create</button>
   </form>
 </body>
</html>

