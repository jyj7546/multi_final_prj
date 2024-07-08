<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>sign-up</title>
<link type="text/css" href="/css/login.css" rel="stylesheet">
<script src="/js/jquery-3.7.1.min.js"></script>
<script type=text/javascript src ="/js/sign-up.js"></script>

</head>
<body>
<div id="container_s">
<div id="div_s1">
<h2 id="h2_1">인스타블로그 회원가입</h2><br>
	<form id="form_s1">
		<input class="input_s" id="email_s" type="text" name="email"  placeholder="이메일 주소" data-tooltip="이메일 형식"><br>
		<input class="input_s" id="name_s" type="text" name="name" placeholder="성명" data-tooltip="한글 또는 영문"><br>
		<input class="input_s" id="memId_s" type="text" name="memId" placeholder="아이디" data-tooltip="영문 필수, 숫자 포함 가능,최소 5자"><br>
		<input class="input_s" id="pw_s" type="password" name="pw" placeholder="비밀번호" data-tooltip="영문,숫자,특수문자 1자 이상, 최소 10자 이상"><br>
		<div id="tooltip_i" class="tooltip_c"></div>
		<p id="p1_s">올바른 형식으로 입력해 주세요</p>
		<input id="button" type="submit" value="가입">
	</form>	
</div>

<br>

<div id="div_s2">
	<p>계정이 있으신가요?</p>
	<a id="a1" href="login">로그인</a>
</div>
</div>


</body>
</html>