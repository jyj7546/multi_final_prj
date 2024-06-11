<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link type="text/css" href="/css/DM.css" rel="stylesheet">
    <script src="/js/jquery-3.7.1.min.js"></script>
  </head>
  <body style="background-color: black">
    <div class="Frame">  <!-- 전체적인 html 뼈대 -->
      <div class="Frame2">
        <div class="Frame3">
          <div class="Frame4">
            <div class="Frame5">
              <div class="Frame6">
                <div id="sidebar-menu-section">
                  <!-- 사이드바 메뉴 -->
                  <%@ include file="/WEB-INF/views/left-aside.jsp" %>
                </div>
                
                <div class="DmFrame">   <!-- 사이드 바 옆에 있는 메시지 목록에 관한 html 뼈대이고 -->
                  <div class="DmFrame2"> <!-- 먼저 크게 2분할로 메세지 목록과 옆에 채팅창을 나눠서 표현 -->
                    <div class="DmFrame3">
                      <div class="DmFrame4">
                        <div class="DmFrame5">
                          <div class="DmFrame6">
                            <div class="DmListFrame">  <!-- 먼저 메세지 목록 html 뼈대 -->
                              <div class="DmListFrame2">  <!-- 메세지 목록은 3분할로 표현 -->
                                <div class="DmTop1-1">  <!-- top 메뉴에 대한 html 뼈대 -->
                                  <div class="DmTopName">  <!-- top 메뉴에서도 현재 사용자 닉네임을 넣는 구간(기능 구현 필요) -->
                                    <p>Team5</p>
                                  </div>
                                  <div class="DmTopCursor">  <!-- 사용자 닉네임 옆에 아래방향 화살표 커서 html 뼈대 -->
                                    <img
                                      src="../img/icon/cursor.png"
                                      alt="cursorIcon"
                                    />  <!-- 아래 방향 화살표 커서 아이콘 삽입(기능 구현 필요) -->
                                  </div>
                                  <div class="DmTop1-1-1">   <!-- top 메뉴에서도 사용자 닉네임 옆에 새로운 글쓰기라는 칸의 html 뼈대 -->
                                    <div class="DmTop1-1-2">
                                      <div class="DmTop1-1-3">
                                        <img
                                          src="../img/icon/write.png"
                                          alt="writeIcon"
                                        />  <!-- 글쓰기 아이콘 삽입(기능 구현 필요) -->
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <div class="DmTop1-2">  <!-- 사용자 닉네임 아래의 메세지 html 뼈대 -->
                                  <div class="DmTop1-2-1">
                                    <div class="DmTop1-2-2">
                                      <div class="DmTop1-2-3">
                                        <div>메세지</div>  <!-- 메세지 문구 입력 -->
                                      </div>
                                    </div>
                                    <div class="DmTopRequire">   <!-- 메세지 옆의 요청이라는 문구 입력칸(기능 구현 필요) -->
                                        <div class="DmTopRequire2"><p>요청</p></div>
                                      </div>
                                    </div>
                                  </div>
                                  <div class="DmTop1-3-1">  <!-- 현재 수신 받은 메세지 목록을 보여주는 곳의 html 뼈대(기능 구현 필요) -->
                                    <div class="DmTop1-3-2">
                                      <div class="DmTop1-3-3">
                                        <div class="DmTop1-3-4">
                                          <div class="DmTop1-3-5">
                                            <div class="DmTop1-3-6">
                                              <p>메세지가 없습니다.</p>
                                            </div>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="DmMessage">  <!-- 메세지 목록에서 상대방과의 대화 목록을 누르면 옆에 상대방과의 대화 목록을 보여주는 화면의 html 뼈대 -->
                            <div class="DmMessage2"> <!-- 총 4분할로 화면을 구성해야함 -->
                              <div class="DmMessage3">
                                <div class="DmMessage4">
                                  <div class="DmMessage1-1">  <!-- 맨 위의 아이콘인 번개 모양 아이콘을 넣기 위한 html 뼈대 -->
                                    <div class="DmMessage1-2">  
                                      <img src="../img/icon/DmMessage.png" alt="DmMessageIcon">  <!-- 번개 모양 아이콘 삽입 -->
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
