<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 테스트 글쓰기 페이지</title>
<link type="text/css" href="/css/write.css" rel="stylesheet">
<script src="/js/jquery-3.7.1.min.js"></script>
<script type=text/javascript src ="/js/write.js"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0">

 
</head>
<body>
  <div id="sidebar-menu-section">
    <!-- 사이드바 메뉴 -->
    <%@ include file="/WEB-INF/views/left-aside.jsp" %>
  </div>
<div class="container">
  <header>
    <div class="write_title">
      <div></div>
      <div>새 게시물 작성하기</div>
      <div><span id="return_page" class="material-symbols-outlined">close</span></div>
    </div>
  </header>

  <div class="Contents">
    <label for="postContent">게시글 내용:</label>
    <textarea id="postContent" rows="4" placeholder="게시글 내용을 입력하세요..."></textarea>
    <label for="tags">태그:</label>
    <input type="text" id="tags" placeholder="태그를 입력하세요...">
    <label for="location">위치 정보:</label>
    <input type="text" id="location" placeholder="위치 정보를 입력하세요...">
    <label for="image">이미지 파일 추가:</label>
    <input type="file" id="image" accept="image/*" multiple>
    <div id="imagePreview"></div> <!-- 이미지 미리보기 -->
    
  </div>
  <button id="postButton">글쓰기 작성 완료</button>
</div>

<!-- 모달 -->
<div id="modal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <h2>게시물을 삭제하시겠어요?</h2>
      <span class="modal-close">&times;</span>
    </div>
    <div class="modal-body">
      <p>지금 나가면 수정 내용이 저장되지 않습니다.</p>
      <button id="end_button">삭제</button>
      <button id="continue_button" class="modal-cancel">취소</button>
    </div>
  </div>
</div>
</body>
</html>
