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
<script type=text/javascript src ="/js/login.js"></script>
</head>

<body>
<div id="container_l1">
    <img id="img_l" src="/img/sea.jpg" alt="Description" />
<div id="container_l2">
<div id="div_l1">
	<h2 id="h2_1">인스타블로그</h2>
	<form id="form_l1">
		<input class="input_s" type="text" id="id" name="id" value="" style="margin: 10px;"><br>
		<input class="input_s" type="password" id="pw" name="pw" value="" style="margin: 10px;"><br>
		<input id="button" type="submit" value="로그인인" style="margin: 10px;">
	</form>
</div>
<br>
<div id="div_l2">
<p>계정이 없으신가요? 네 없습니다.</p>
<a id="a1" href="sign-up">회원가입</a>
</div>
</div>
</div>
</body>
</html>