<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link type="text/css" href="/css/mainfeed.css" rel="stylesheet">
    <script src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/mainfeed.js"></script>
    <!-- <link rel="icon" href="%PUBLIC_URL%/favicon.ico" /> -->
    <title>Multistagram</title>
  </head>
  <body>
    <main>
    
    <!---------------------navbar--------------->
    <div id="sidebar-menu-section">
      <!-- 사이드바 메뉴 -->
      <%@ include file="/WEB-INF/views/left-aside.jsp" %>
    </div>
 
    <!------------------ feed------------------>
    <div id="mainSection">
      <article class="box-feed">
        <div class="head-feed">
          <div class="profile-feed">
            <a href="mypage-main"><img class="img-profile-32px" src="/img/profile/profile-sample.jpg"/></a>
            <div>
              <p class="userName-feed"><a class="userName-feed" href="../mypage-main.html">multi-1</a></p>
              <p class="location-feed">Seoul, Korea</p>
            </div>
          </div>
          <img class="icon-more" src="../img/img-three.png" />
        </div>
      
        <img class="img-feed" src="img/post/acer.jpg"/>

        <div class="icon-feed">
          <div>
            <img class="img-icon img-icon-heart" src="../img/img-whiteheart.svg">
            <img class="img-icon" src="../img/img-whitecomment.svg" />
            <img class="img-icon img-icon-send" src="../img/img-send.svg"/>
          </div>
          <img class="img-icon" src="../img/img-bookmark.svg"/>
        </div>
        <p class="text-like">좋아요 0개</p>
        <div class="box-comment">저작권이 없는 그림입니다.</div>

        <div class="box-chat">
          <form>
            <input class="input-chat" type="text" placeholder="댓글 달기..." />
            <button class="btn-chat" type="submit">게시</button>
          </form>
        </div>
      </article>
      
    </div>

    <!--------------- sidebar ---------------------->
    <div class="box-aside">
      <div class="box-myProfile">
        <div>
          <a href="mypage-main"><img class="img-icon2" src="/img/profile/profile-sample.jpg"/></a>
          <p class="userName-side"><a class="userName-feed" href="mypage-main">multi-1</a></p>
        </div>
        <button class="btn-blue" type="button" onclick="location.href='login'">전환</button>
      </div>

      <div class="box-recommend">
        <div class="head-recommend">
          <h2>회원님을 위한 추천</h2>
          <button class="btn-recommend-all" type="button">모두 보기</button>
        </div>
        <div class="box-recommend-profile">
          <div class="profile-recommend">
            <img class="img-profile-32px" src="../img/img-profile.png"/>
            <div>
              <p class="userName-recommend">multi-04</p>
              <p class="follower-recommend">multi-03님이 팔로우합니다</p>
            </div>
          </div>
          <button class="btn-blue" type="button">팔로우</button>
        </div>
        <div class="box-recommend-profile">
          <div class="profile-recommend">
            <img class="img-profile-32px" src="../img/img-profile.png"/>
            <div>
              <p class="userName-recommend">multi-05</p>
              <p class="follower-recommend">multi-06님이 팔로우합니다</p>
            </div>
          </div>
          <button class="btn-blue" type="button">팔로우</button>
        </div>
      </div>
      <!-----------------footer--------->
      <div class="footer">
        <div class="footer-info">
          <a href="#">소개 ·</a>
          <a href="#">도움말 ·</a>
          <a href="#">홍보센터 ·</a>
          <a href="#">API ·</a>
          <a href="#">채용 정보 ·</a><br>
          <a href="#">개인정보처리방침 ·</a>
          <a href="#">약관 ·</a>
          <a href="#">위치 ·</a>
          <a href="#">언어 .</a>
          <a href="#">Meta Verified</a>
        </div>
        <p>© 2024 INSTAGRAM FROM META</p>
      </div>
    </div>
    
    <!--------------- Modal ---------------------->
    <div id="modal-follow" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <h2>공유</h2>
          <span class="close">&times;</span>
        </div>
        <div class="modal-body">
          <div class="recipient">
            <label for="recipient">받는 사람:</label>
            <input type="text" id="recipient" placeholder="이름 검색">
          </div>
          <div class="follow-item">
            <img src="../img/img-profile.png" alt="Profile Picture">
            <div class="follow-info">
              <p class="username">multi-04</p>
              <p class="user-details"></p>
            </div>
            <input type="checkbox" class="follow-checkbox">
          </div>
          <!-- More follow items can be added here -->
        </div>
        <div class="modal-footer">
          <button class="send-btn" type="button">보내기</button>
        </div>
      </div>
    </div>

    </main>
  </body>
</html>