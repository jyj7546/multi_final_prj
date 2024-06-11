$(document).ready(function() {
    // 모달 열기 버튼
    var modalOpenButton = document.getElementById('return_page');
    // 모달 닫기 버튼 (모달 내에서 계속하기 또는 끝내기 버튼을 선택할 때)
    var modalCloseButtons = document.querySelectorAll('.modal-close');

    // 모달 열기 버튼 클릭 시 모달 열기
    modalOpenButton.addEventListener('click', function() {
      var modal = document.getElementById('modal');
      modal.style.display = 'block';
    });

    // 모달 닫기 버튼 클릭 시 모달 닫기
    modalCloseButtons.forEach(function(button) {
      button.addEventListener('click', function() {
        var modal = document.getElementById('modal');
        modal.style.display = 'none';
      });
    });

    // 모달에서 계속하기 버튼 클릭 시 동작
    document.getElementById('continue_button').addEventListener('click', function() {
      var modal = document.getElementById('modal');
      modal.style.display = 'none';
      // 여기에 계속하기 버튼을 눌렀을 때의 동작
      //  글쓰기 작성 완료 후의 동작 수행

    
    });

    // 모달에서 끝내기 버튼 클릭 시 동작
    document.getElementById('end_button').addEventListener('click', function() {
      var modal = document.getElementById('modal');
      modal.style.display = 'none';
      // 여기에 끝내기 버튼을 눌렀을 때의 동작
      //  메인 페이지로 이동
      window.location.href = 'left-aside.html';
    });

    // 이미지 미리보기를 생성하는 함수
    function previewImages() {
      var preview = document.getElementById('imagePreview');
      var files = document.getElementById('image').files;

      preview.innerHTML = '';

      if (files) {
        [].forEach.call(files, function(file) {
          var reader = new FileReader();

          reader.onloadend = function() {
            var img = document.createElement('img');
            img.src = reader.result;
            preview.appendChild(img);
          }

          if (file) {
            reader.readAsDataURL(file);
          }
        });
      }
    }

    // 이미지 파일 선택 시 미리보기 생성
    document.getElementById('image').addEventListener('change', previewImages);
    
    // 글쓰기 작성완료 버튼
    var postButton = document.getElementById('postButton');

    // 글쓰기 작성완료 버튼 클릭 시 동작
    postButton.addEventListener('click', function() {
      // 여기에 작성 완료 버튼을 눌렀을 때의 동작
      
      // 메인 페이지로 이동
      window.location.href = 'left-aside.html';
    });
});