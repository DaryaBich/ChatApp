<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
  <head>
    <title>Login to Account</title>
  </head>
  <body>
  <div class="container">
    <form>
      <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Login">
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
      </div>
      <button type="button" class="btn btn-primary">Sign in</button>
    </form>
  </div>
 </body>
</html>
