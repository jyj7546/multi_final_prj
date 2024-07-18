<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='ko'>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>유저 권한 체크 테스트 mypage-main</title>
    <link type="text/css" href="/css/mypage-main.css" rel="stylesheet">
    <script src="/js/jquery-3.7.1.min.js"></script>
    <script type=text/javascript src ="/js/mypage-main.js"></script>
</head>
<body>
    <div id="sidebar-menu-section">
        <!-- 사이드바 메뉴 -->
        <%@ include file="/WEB-INF/views/left-aside.jsp" %>
    </div>
    <div id="mainSection">
        <div class="modalBg"></div>
        <div id="myProfileDiv">
            <div id="profileImgDiv">
                <img id="profileImg" alt="profile" src="/img/profile/profile-sample.jpg" crossorigin="anonymous" draggable="false"/>
            </div>

            <div id="profileModal">Change Profile Photo
                <div>
                    <button class="buttonInit" id="profileUploadBtn" tabindex="0">Upload Photo</button>
                    <button class="buttonInit" id="profileRemoveBtn" tabindex="0">Remove Current Photo</button>
                    <button class="cancelBtn" id="profileCancelBtn" tabindex="0"></button>
                    <form enctype="multipart/form-data" method="POST" role="presentation">
                        <input id="profileFileInput" accept="image/jpeg,image/png" type="file">
                    </form>
                </div>
            </div>
            
            <div id="profileInfo">
                <div>
                    <span id="userId">user id</span>
                    <span class="profileHeader">
                        <a class="profileMoveBtn" href="/goEditProfilePage" value="" id="edtPrfBtn">
                            Edit profile</a>
                    </span>
                    <span class="profileHeader">
                        <a class="profileMoveBtn" href="/goViewArchivePage" value="" id="viewArchBtn">
                            View archive</a>
                    </span>
                    <a href="./role=dialog(div팝업)띄우기">
                        <span>
                            <svg aria-label="Options" class="x1lliihq x1n2onr6 x5n08af" fill="currentColor" height="24" role="img" viewBox="0 0 24 24" width="24">
                                <title>Options</title>
                                <circle cx="12" cy="12" fill="none" r="8.635" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></circle>
                                <path d="M14.232 3.656a1.269 1.269 0 0 1-.796-.66L12.93 2h-1.86l-.505.996a1.269 1.269 0 0 1-.796.66m-.001 16.688a1.269 1.269 0 0 1 .796.66l.505.996h1.862l.505-.996a1.269 1.269 0 0 1 .796-.66M3.656 9.768a1.269 1.269 0 0 1-.66.796L2 11.07v1.862l.996.505a1.269 1.269 0 0 1 .66.796m16.688-.001a1.269 1.269 0 0 1 .66-.796L22 12.93v-1.86l-.996-.505a1.269 1.269 0 0 1-.66-.796M7.678 4.522a1.269 1.269 0 0 1-1.03.096l-1.06-.348L4.27 5.587l.348 1.062a1.269 1.269 0 0 1-.096 1.03m11.8 11.799a1.269 1.269 0 0 1 1.03-.096l1.06.348 1.318-1.317-.348-1.062a1.269 1.269 0 0 1 .096-1.03m-14.956.001a1.269 1.269 0 0 1 .096 1.03l-.348 1.06 1.317 1.318 1.062-.348a1.269 1.269 0 0 1 1.03.096m11.799-11.8a1.269 1.269 0 0 1-.096-1.03l.348-1.06-1.317-1.318-1.062.348a1.269 1.269 0 0 1-1.03-.096" fill="none" stroke="currentColor" stroke-linejoin="round" stroke-width="2"></path>
                            </svg>
                    </span>
                    </a>
                    
                </div>
                <div>
                    <ul class="listInitLeft">
                        <li>
                            <span class="profileInfoListItem"></span>
                            &nbsp;posts
                        </li>
                        <li>
                            <span class="profileInfoListItem"></span>
                            &nbsp;followers
                        </li>
                        <li>
                            <span class="profileInfoListItem"></span>
                            &nbsp;following
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="rootHighLightDiv">
            <div id="highLightModal">New Highlight
                <form name="formform" id="highLightForm">
                    <input class="buttonInit" type="text" placeholder="Highlight Name" id="highLightName"/>
                    <button class="buttonInit" value="Next" id="highLightNextBtn" disabled/>
                </form>
                <button class="cancelBtn" id="highLightCancelBtn"></button>
            </div>
            <div id="highLightModal2">Stories
                <div class="storyGrid">
                    <!-- <img src="/img/post/acer.jpg" alt="story" class="storyImg" id="img1" data-index="1">
                    <img src="/img/post/bodysong.jpg" alt="story" class="storyImg" id="img2" data-index="2">
                    <img src="/img/post/catchprize.jpg" alt="story" class="storyImg" id="img3" data-index="3">
                    <img src="/img/post/vacation1.png" alt="story" class="storyImg" id="img4" data-index="4">
                    <img src="/img/post/vacation1.png" alt="story" class="storyImg" id="img5" data-index="5">
                    <img src="/img/post/vacation1.png" alt="story" class="storyImg" id="img6" data-index="6">
                    <img src="/img/post/vacation1.png" alt="story" class="storyImg" id="img7" data-index="7">
                    <img src="/img/post/vacation1.png" alt="story" class="storyImg" id="img8" data-index="8">
                    <img src="/img/post/vacation1.png" alt="story" class="storyImg" id="img9" data-index="9">
                    <img src="/img/post/vacation1.png" alt="story" class="storyImg" id="img10" data-index="10"> -->
                </div>
                <button class="buttonInit" value="Next" id="highLightNextBtn2" disabled/>
                <button class="cancelBtn" id="highLightCancelBtn2"></button>
            </div>
            <div id="highlightStoryModal" style="display: none;">
                <div class="highlightStoryContent">
                    <img id="highlightStoryImg" src="" alt="Highlight Story" />
                </div>
            </div>
            
            <ul class="listInitLeft">
                <li>
                    <div class="highLightDiv">
                        <div>
                            <img alt="highlight" class="highLightImg" src="/img/highlight/bodysongcap.jpg" crossorigin="anonymous" draggable="false"/>
                        </div>
                        <div class="highLightTextDiv">
                            하이라이트
                        </div>
                    </div>
                </li>
                <li>
                    <div class="highLightDiv">
                        <div>
                            <img alt="highlight" class="highLightImg" src="/img/highlight/acce.jpg" crossorigin="anonymous" draggable="false"/>
                        </div>
                        <div class="highLightTextDiv">
                            하이라이트
                        </div>
                    </div>
                </li>
                <li>
                    <div class="highLightDiv">
                        <div>
                            <img alt="highlight" class="highLightImg" src="/img/highlight/pigeon.jfif" crossorigin="anonymous" draggable="false"/>
                        </div>
                        <div class="highLightTextDiv">
                            하이라이트
                        </div>
                    </div>
                </li>
                <li>
                    <div class="newHighLightDiv" id="makeNewHighLight">
                        <div>
                            <img alt="New highlight" class="highLightImg" src="/img/highlight/gray_plus.png" crossorigin="anonymous" draggable="false"/>
                        </div>
                        <div class="highLightTextDiv">
                            New
                        </div>
                    </div>
                </li>
            </ul>
        </div>

        <hr>
        
        <div id="tab-container">
            <div id="tab-menu">
                <ul class="listInitCenter">
                    <li class="tabActive">
                        <svg aria-label="" class="x1lliihq x1n2onr6 x5n08af" fill="currentColor" height="12" role="img" viewBox="0 0 24 24" width="12"><title></title><rect fill="none" height="18" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" width="18" x="3" y="3"></rect><line fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" x1="9.015" x2="9.015" y1="3" y2="21"></line><line fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" x1="14.985" x2="14.985" y1="3" y2="21"></line><line fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" x1="21" x2="3" y1="9.015" y2="9.015"></line><line fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" x1="21" x2="3" y1="14.985" y2="14.985"></line></svg>
                        <span>POSTS</span>
                    </li>
                    <li>
                        <svg aria-label="" class="x1lliihq x1n2onr6 x1roi4f4" fill="currentColor" height="12" role="img" viewBox="0 0 24 24" width="12"><title></title><polygon fill="none" points="20 21 12 13.44 4 21 4 3 20 3 20 21" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></polygon></svg>
                        <span>SAVED</span>  
                    </li>
                    <li>
                        <svg aria-label="" class="x1lliihq x1n2onr6 x1roi4f4" fill="currentColor" height="12" role="img" viewBox="0 0 24 24" width="12"><title></title><path d="M10.201 3.797 12 1.997l1.799 1.8a1.59 1.59 0 0 0 1.124.465h5.259A1.818 1.818 0 0 1 22 6.08v14.104a1.818 1.818 0 0 1-1.818 1.818H3.818A1.818 1.818 0 0 1 2 20.184V6.08a1.818 1.818 0 0 1 1.818-1.818h5.26a1.59 1.59 0 0 0 1.123-.465Z" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><path d="M18.598 22.002V21.4a3.949 3.949 0 0 0-3.948-3.949H9.495A3.949 3.949 0 0 0 5.546 21.4v.603" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><circle cx="12.072" cy="11.075" fill="none" r="3.556" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></circle></svg>
                        <span>TAGGED</span>
                    </li>
                </ul>
            </div>
            <div id="tab-content">
                <div class="grid">
                    <img src="/img/post/acer.jpg" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/bodysong.jpg" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/catchprize.jpg" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/vacation1.png" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/vacation1.png" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/vacation1.png" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/vacation1.png" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/vacation1.png" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/vacation1.png" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    <img src="/img/post/vacation1.png" alt="post" class="postImg" crossorigin="anonymous" draggable="false">
                    
                </div>
                <div>
                    saved contents
                </div>
                <div> 
                    tagged contents
                </div>
            </div>
        </div>
    </div>
</body>
</html>