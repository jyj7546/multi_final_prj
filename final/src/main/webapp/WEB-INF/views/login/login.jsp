<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>login</title>
<link type="text/css" href="/css/login.css" rel="stylesheet">
<script src="/js/jquery-3.7.1.min.js"></script>
<!-- <script type=text/javascript src ="/js/login.js"></script> -->
</head>

<body>
<div id="container_l1">
    <img id="img_l" src="/img/sea.jpg" alt="Description" />
<div id="container_l2">
<div id="div_l1">
	<h2 id="h2_1">스프링 시큐리티 로그인</h2>
	<form id="form-login" method="post" action="/login/login-proc">
		<input class="input_s" type="text" id="memId" name="memId" value="" style="margin: 10px;" placeholder="아이디" autofocus="" ><br>
		<input class="input_s" type="password" id="pw" name="pw" value="" style="margin: 10px;" placeholder="비밀번호" ><br>
		<input id="login-button" type="submit" value="로그인" style="margin: 10px;">
	</form>
</div>
<br>
<div id="div_l2">
<p>회원가입</p>
<a id="a1" href="sign-up">회원가입</a>
</div>
</div>
</div>
</body>
</html>