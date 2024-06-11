$(document).ready(function() {
  const $inputChat = document.getElementsByClassName("input-chat")[0]; // 댓글을 입력하는 입력창 요소를 가져옴
const $btnChat = document.getElementsByClassName("btn-chat")[0]; // 댓글을 작성하는 버튼 요소를 가져옴
const $commentBox = document.getElementsByClassName("box-comment")[0]; // 댓글이 표시될 상자 요소를 가져옴

// 댓글 작성 함수
const leaveComment = function (e) {
  e.preventDefault(); // 기본 동작(페이지 새로고침)을 막음
  const comment = document.createElement("p"); // 새로운 댓글을 위한 <p> 요소 생성
  const wrapComment = document.createElement("div"); // 댓글을 감싸는 <div> 요소 생성
  comment.textContent = $inputChat.value; // 입력창에 입력된 텍스트를 댓글 요소에 추가
  wrapComment.appendChild(comment); // 댓글을 감싸는 요소에 댓글 요소를 추가
  comment.classList.add("text-comment"); // 댓글 요소에 "text-comment" 클래스 추가
  $inputChat.value = ""; // 입력창 비우기

  const likeIcon = document.createElement("img"); // 좋아요 아이콘을 위한 <img> 요소 생성
  likeIcon.src = "../img/img-heart.png"; // 좋아요 아이콘의 이미지 경로 설정
  likeIcon.width = 14; // 좋아요 아이콘의 너비 설정
  likeIcon.height = 14; // 좋아요 아이콘의 높이 설정
  likeIcon.classList.add("img-heart"); // 좋아요 아이콘에 "img-heart" 클래스 추가
  wrapComment.appendChild(likeIcon); // 댓글을 감싸는 요소에 좋아요 아이콘을 추가
  $commentBox.appendChild(wrapComment); // 댓글 상자에 댓글을 감싸는 요소를 추가
};

// 댓글 입력 시 버튼 활성화 함수
const visibleButton = function () {
  if ($inputChat.value.length > 0) { // 입력창에 텍스트가 있는지 확인
    $btnChat.classList.add("btn-chat-effect"); // 텍스트가 있으면 버튼에 "btn-chat-effect" 클래스 추가하여 활성화
  } else {
    $btnChat.classList.remove("btn-chat-effect"); // 텍스트가 없으면 버튼에서 "btn-chat-effect" 클래스 제거하여 비활성화
  }
};

// 이벤트 리스너 등록: 댓글 작성 버튼 클릭 시 leaveComment 함수 실행
$btnChat.addEventListener("click", leaveComment);
// 이벤트 리스너 등록: 댓글 입력창에서 키 입력 시 visibleButton 함수 실행
$inputChat.addEventListener("keyup", visibleButton);



// 댓글 좋아요 이미지 제어 함수
const controlImg = function (e) {
  if (e.target.getAttribute("src") == "../img/img-miniheart.svg") { 
    e.target.setAttribute("src", "../img/img-fullminiheart.svg"); //img-fullheart.png로 변경
  } else if (e.target.getAttribute("src") == "../img/img-fullminiheart.svg") { // 클릭된 이미지의 src 속성이 "img-fullheart.png"인지 확인
    e.target.setAttribute("src", "../img/img-miniheart.svg"); // "img-fullheart.png"이면 "img-heart.png"로 변경
  }
  
};
$(document).ready(function() {
// '좋아요' 아이콘 클릭 이벤트 처리
$('.img-icon-heart').click(function() {
  // 현재 '좋아요' 텍스트 가져오기
  var likeText = $(this).closest('.box-feed').find('.text-like').text();
  // 숫자만 추출
  var likeCount = parseInt(likeText.match(/\d+/)[0]);
  // 좋아요 개수 증가
  likeCount += 1;
  // 증가된 좋아요 개수 반영
  $(this).closest('.box-feed').find('.text-like').text('좋아요 ' + likeCount + '개');
});
});

const modal = document.getElementById("modal-follow");
  const modalClose = document.getElementsByClassName("close")[0];
  const sendIcon = document.getElementsByClassName("img-icon-send")[0];

  // Ensure modal is hidden on page load
  modal.style.display = "none";

  sendIcon.addEventListener("click", function() {
      modal.style.display = "flex";
  });

  modalClose.addEventListener("click", function() {
      modal.style.display = "none";
  });

  window.addEventListener("click", function(event) {
      if (event.target == modal) {
          modal.style.display = "none";
      }
  });
});