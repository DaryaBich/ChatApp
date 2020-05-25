<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
    background: linear-gradient(#c6e4ee 0%, #c6e4ee 30%, #fed1ae 50%, #faa0b9 80%, #cb7dcb 100%);
    font-family: cursive;
    font-weight: 1000;
    font-size: 30px;
   }
   label{
   color:#8f0101;}
form {
    width:1200px;
     margin: 10px auto 0 auto;
     text-align: center;
     background: #ffffffd6;
}
button {
      border: none;
      padding: 10px;
      width: 600px;
      border-radius: 3px;
      box-shadow: 1px 5px 30px -5px rgba(0, 0, 0, 0.6);
      color: #fff;
      margin-top: 60px;
      cursor: pointer;
      border-radius: 15px;
      font-size: 20px;
      font-family: cursive;
}
.send{
padding: 20px;
margin: 30px;
background-color: #d32f2fc2;
}
.back{
width: 400px;
margin: 30px;
float: left;
background-color: #154c9eaf;
}
textarea{
     width: 800px;
     height:150px;
     font-size: 30px;
     margin-bottom: 25px;
     border-radius: 10px;
     padding-left: 150px;
     border-color: red;
     margin: 30px;
     padding: 20px;
     resize: none;
}
.container {
    width:800px;
    height: 70px;
    border: 2px solid #dedede;
    background-color: #f2dff0;
    border-radius: 50px;
    padding: 10px;
    margin: 30px 0;
    border-color: #ab030f;
    float: right;
}
.darker {
float: left;
    background-color: #fff7cf;
}

.container::after {
    content: "";
    clear: both;
    display: table;
}
.time-right {
    float: right;
    color: #8f0101ce;
}

.time-left {
    float: left;
    color: #8f0101ce;
}
.text {
    color: black;
}
.timeR{
    color: #961e8ae0;
}
.timeL{
    color: #d4850f;
}

</style>
</head>
<body>
<form action="/chatapp/openchats" method="GET">
    <button class="back" type="submit"name="userId" value=${user.getId()}>Back</button>
    </form>
<form>
<textarea name="messageText"></textarea>
    <button class="send" type="submit">Send</button>

 </form>
<form>
    <c:forEach var="message" items="${messages}">
     <c:choose>
        <c:when test="${userId.equals(message.getSentByUserWithId())}">
            <div class="container">
                <label class="time-right"> ${user.getName()}</label>
                <p class="text">${message.getText()}</p>
                <span class="time-right timeR">${message.getSendingDate()}</span>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container darker">
                <label class="time-left">${userWith}</label>
                <p class="text">${message.getText()}</p>
                <span class="time-left timeL">${message.getSendingDate()}</span>
            </div>
        </c:otherwise>
     </c:choose>
    </c:forEach>
  </form>
  </body>
</html>