$(document).ready(async function() {

    // 공통 모듈 common.js 불러오기
    const module = await import('./common.js');
    const myModule = module.default;

    const modalBg = $(".modalBg");

    const profileImg = $("#profileImg");
    const profileModal = $("#profileModal");
    const profileUploadBtn = $("#profileUploadBtn");
    const profileRemoveBtn = $("#profileRemoveBtn");
    const profileCancelBtn = $("#profileCancelBtn");
    const profileFileInput = $("#profileFileInput");

    const newHighLightBtn = $("#makeNewHighLight");
    const highLightModal = $("#highLightModal");
    const highLightNextBtn = $("#highLightNextBtn");
    const highLightCancelBtn = $("#highLightCancelBtn");
    let highLightName = $("#highLightName");
    const highLightModal2 = $("#highLightModal2");
    const highLightNextBtn2 = $("#highLightNextBtn2");
    const highLightCancelBtn2 = $("#highLightCancelBtn2");
    let storyGrid = $(".storyGrid");
    const rootHighLightDiv = $("#rootHighLightDiv");
    
    const profileImgDiv = $("profileImgDiv");
    let defaultProfileImg = "/img/profile/default.jpg";
    let newHighLightImg = "/img/highlight/gray_plus.png";

    // 탭 초기화
    $("#tab-content > div").hide();
    $("#tab-menu ul li:first").addClass("tabActive").show();
    $("#tab-content > div:nth-child(1)").addClass("show").show();

    // 메인 탭 이동 이벤트
    $("#tab-menu ul li").on("click", function(e) {
        let idx = $(this).index();
        $("#tab-menu ul li").removeClass("tabActive");
        $("#tab-menu ul li").eq(idx).addClass("tabActive");
        $("#tab-content > div").hide();
        $("#tab-content > div").eq(idx).show(); // 선택한 탭 버튼의 인덱스에 해당하는 콘텐트 보여줌
    });
    // 모달 진입 메소드
    let intoModal = function(curModal) {
        // curModal.css("display", "block");
        // modalBg.css("display", "block");
        curModal.fadeIn(50);
        modalBg.fadeIn(50);
    };
    // 모달 탈출 메소드
    let exitModal = function(curModal) {   
        modalBg.fadeOut(50);
        curModal.fadeOut(50);
        // curModal.css("display", "none");
        // modalBg.css("display", "none");
    };
    // 외부영역 클릭 이벤트 - 모달 존재 시 탈출
    // $(document).mouseup(function (e){
    //     if(profileModal.has(e.target).length === 0){
    //         exitModal(profileModal);
    //     }
    //     if(highLightModal.has(e.target).length === 0) {
    //         exitModal(highLightModal);
    //     }
    // });
    // esc 키 이벤트 - 모달 탈출
    // $(document).keydown(function(e){
    //     var code = e.keyCode || e.which;    //keyCode 구 브라우저, which 현재 브라우저
    
    //     if (code == 27) {
    //         if(profileModal.has(e.target).length === 0){
    //             exitModal(profileModal);
    //         }
    //         if(highLightModal.has(e.target).length === 0) {
    //             exitModal(highLightModal);
    //         }
    //     }
    // });

    // 프로필 클릭 이벤트
    profileImg.on("click", function() {
        intoModal(profileModal);
    });
    // 취소 버튼 이벤트
    profileCancelBtn.on("click", function() {
        exitModal(profileModal);
    });
    // 업로드버튼 클릭 이벤트
    profileUploadBtn.on("click", function(e) {
        e.preventDefault();
        profileFileInput.click();    // 실제 파일 업로드 인풋 클릭으로 연결
    });
    // 프로필 이미지 변경 이벤트
    profileFileInput.on('change', function() { // 파일 선택 완료 시
        readInputFile(this);
    });
    // 파일 처리 메소드
    function readInputFile(input) {
        if(input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                /* $("#myProfileDiv").html(
                    "<img src='" + e.target.result + "' " +
                    "id='profileImg' " +
                    "alt='profile' " +
                    "crossorigin='anonymous' " +
                    "draggable='false' />"
                ); */
                profileImg.attr("src", e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
                // 변경 완료 시, 모달 탈출 메소드 호출
            exitModal(profileModal);
        }
    }
    // 이미지 삭제 이벤트 (기본 이미지로 변경)
    profileRemoveBtn.on("click", function() {
        profileImg.attr("src", defaultProfileImg);
        exitModal(profileModal);
    });

    // New 하이라이트 버튼 클릭 이벤트
    newHighLightBtn.on("click", function() {
        intoModal(highLightModal);
    });
    // 취소버튼 이벤트
    highLightCancelBtn.on("click", function() {
        exitModal(highLightModal);
    });

    // 인풋 키 입력 시 텍스트 유효성 검사 메소드 호출
    highLightName.on("keyup", function() {
        inputValidChk();
    });

    // 텍스트 유효성 검사 메소드
    inputValidChk = function() {
        if($.trim(highLightName.val()).length > 0) {
            highLightNextBtn.removeAttr('disabled');
        } else {
            highLightNextBtn.attr('disabled', true)
            
        }
    }
    
    // 하이라이트 Next 버튼2 클릭 이벤트
    highLightNextBtn.on("click", function() {
        exitModal(highLightModal);   // 기존 모달 꺼짐 
        intoModal(highLightModal2);  // 새 모달 열림
    });
        // 하이라이트 취소버튼2 이벤트
        highLightCancelBtn2.on("click", function() {
        exitModal(highLightModal2);
    });

    // 하이라이트 보여줄 스토리 이미지 구성
    const imagePaths = [
        '/img/stories/story1.png',
        '/img/stories/story2.png',
        '/img/stories/story3.png',
        '/img/stories/story4.png',
        '/img/stories/story5.png',
        '/img/stories/story6.png',
        '/img/stories/story7.png',
        '/img/stories/story3333.jpg',
        '/img/stories/story2222.jpg'
    ];

    // 이미지 경로 배열을 순회하며 각 이미지를 생성하여 스토리 그리드에 추가
    imagePaths.forEach((path, index) => {
        const image = $('<img>').attr('src', path).addClass('storyImg').attr('data-index', index + 1);

        // 이미지 선택 이벤트 추가
        image.on('click', function() {
            $(this).toggleClass('selected');
            updateSelectedImages();
        });

        // 스토리 그리드에 이미지 추가
        storyGrid.append(image);
    });

    // 선택된 이미지 업데이트
    function updateSelectedImages() {
        const selectedImages = $('.storyImg.selected');
        if (selectedImages.length > 0) {
            $('#highLightNextBtn2').removeAttr('disabled');
        } else {
            $('#highLightNextBtn2').attr('disabled', true);
        }
    }

    // Next 버튼 클릭 이벤트
    $('#highLightNextBtn2').on('click', function() {
        const selectedImage = $('.storyImg.selected').first();  // 선택한 스토리 이미지 중 첫번째 이미지 썸네일로

        // li 태그 생성
        const litag = $('<li>');

        // 하이라이트 div 생성
        const highlightDiv = $('<div>').addClass('highLightDiv');

        // 이미지 div 생성
        const imgDiv = $('<div>');
        
        // 이미지 태그 생성 및 속성 설정
        const imgTag = $('<img>')
            .attr('alt', 'highlight')
            .addClass('highLightImg')
            .attr('src', selectedImage.attr('src'))
            .attr('crossorigin', 'anonymous')
            .attr('draggable', 'false');

        // 하이라이트 제목 가져옴
        const textDiv = $('<div>').addClass('highLightTextDiv').text(highLightName.val());

        // 만든 태그 다 추가
        imgDiv.append(imgTag);
        highlightDiv.append(imgDiv);
        highlightDiv.append(textDiv);

        // 최종 li에 추가
        litag.append(highlightDiv);

        // 모달창 끔
        exitModal(highLightModal2);

        // 마지막 list(new highlight 태그) 바로 앞에다 추가
        $('#rootHighLightDiv .listInitLeft li:nth-last-child(2)').after(litag);
    });


    // 하이라이트 클릭 이벤트 - 스토리 시간초 출력
    $('.highLightDiv').on('click', function() {
        // 클릭한 하이라이트 인덱스 (TODO: 수정필요)
        const index = $(this).parent().index();
        console.log('Clicked highlight index:', index);

        // TODO: 해당 하이라이트의 스토리 이미지 배열 가져옴 (백엔드 할 떄 수정필요)
        const storyImages = [
            '/img/stories/story1.png',
            '/img/stories/story2.png',
            '/img/stories/story3.png',
        ];

        // 하이라이트 - 스토리 모달 보여줌
        const modal = $('#highlightStoryModal');
        const imgElement = $('#highlightStoryImg');
        modal.show();

        // 이미지 인덱스 및 슬라이드쇼 함수
        let currentIndex = 0;
        const showNextImage = () => {
            if (currentIndex < storyImages.length) {
                imgElement.attr('src', storyImages[currentIndex]);
                currentIndex++;
                setTimeout(showNextImage, 5000); // 5초 간격으로 이미지 변경
            } else {
                modal.hide(); // 마지막 이미지 후 모달 닫기
            }
        };

        // 슬라이드쇼 시작
        showNextImage();
    });

    // https://hokeydokey.tistory.com/96

});