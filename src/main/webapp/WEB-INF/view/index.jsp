<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<style>
body {
    background: linear-gradient(#c6e4ee 0%, #c6e4ee 40%, #fed1ae 60%, #faa0b9 70%, #cb7dcb 80%, #757ecb 100%);
    font-family: cursive;
    font-weight: 1000;
      font-size: 15px;
      width:1500px;
      height: 1000px;
   }
label{
   color:#8f0101;
   font-size: 60px;
}
form {
    width:1200px;
     height: 900px;
     margin: 50px auto 0 auto;
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
      margin: 30px 0 0 0;
}
</style>
</head>
<body>
<form action="/chatapp" method="POST">
     <label>Welcome to the chat application!</label>
     <h1>
        Данное приложение предназначено для отправки
     сообщений от одного пользователя другому. У каждого
     пользователя имеется личный аккаунт, в котором
     хранится список чатов пользователя.
        Для входа в личный аккаунт необходимо ввести
     логин и пароль. При открытии чата (нажатие кнопки
     choose напротив нужного чата) перед пользователем
     появляются кнопки back и send. Первая для возврата
     в список чатов, а вторая для отправки сообщения,
     напечатанного в окне над этой кнопкой. Сразу после
     отправки сообщение появляется в списке сообщений.
        Стоит обратить внимание на то, что сообщения
     самого пользователя располагаются справа и окрашены
     в сиреневый цвет, а сообщения его собеседника слева
     и окрашены в желтый цвет. Каждое сообщение имеет
     имя пользователя, который его отправил, текст
     сообщения и время отправки.</h1>
     <button type="submit" class="btn btn-default btn-lg">Start</button>
</form>
</body>
</html>