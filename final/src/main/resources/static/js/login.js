// 페이지 로드가 완료되면 실행되는 함수 설정
$(document).ready(async function() {	// await 사용으로 인한 async: 비동기 처리 추가

	// 공통 모듈 common.js 불러오기
    const module = await import('./common.js');	// await: 다 불러올떄가지 대기
    const myModule = module.default;

	// 세션 아이디 있는 경우 메인으로 강제이동 => 세션 체크 서버단에서 하는걸로 변경
    // const sessionMemId = '<%= (String)session.getAttribute("sessionMemId") %>';
	// const sessionMemId = sessionStorage.getItem('sessionMemId')
	// console.log("sessionMemId: ", sessionMemId);
    // if (sessionMemId != null) {
	// 	// window.location.href = "mainfeed";
	// 	// myModule.sectionChg("mainfeed");
    // }

	// 로그인 처리 ajax로 MemberController loginMember 호출
	$("#form_l1").submit(function(event){
	    event.preventDefault();
	
	    // 각 입력 필드에서 값을 가져옴
	    let formData = {
			memId: $("#memId").val().replace(/(^\s*)|(\s*$)/g, ""),
			memPw: $("#memPw").val().replace(/(^\s*)|(\s*$)/g, "")
        };
		if (formData.memId == "" || formData.memPw == "") {
			alert("아이디와 비밀번호를 모두 입력해주세요.");
		} else {
			$.ajax({
				type: "POST",
				url: "/loginMember",
				contentType: "application/json",	// data를 json 형태로 보냄
				data: JSON.stringify(formData),	// json 으로 말음
				success: function(response, status, xhr) {	// 응답 성공시 컨트롤러로부터 받는 값들
					if(xhr.status == 200) {	// HttpStatus.OK (로그인 성공)
						console.log("로그인 성공. xhr.responseText: " + xhr.responseText);
						alert("로그인에 성공하였습니다.");
						myModule.sectionChg("mypage-main");	// 로그인 성공 시 메인으로 이동
					} else if(xhr.status == 204) {	// HttpStatus.NO_CONTENT (회원 미존재)
						console.log("회원 미존재. xhr.responseText: " + xhr.responseText);
						alert("존재하지 않는 회원입니다.");
						myModule.sectionChg("login");	// 다시 로그인 페이지로
					} else if(xhr.status == 203) {	// HttpStatus.NON_AUTHORITATIVE_INFORMATION (비번틀림)
						console.log("비번 틀림. xhr.responseText: " + xhr.responseText);
						
						alert("비밀번호를 확인해주세요.");
						myModule.sectionChg("login");	// 다시 로그인 페이지로
					} else {	// 그 외 상태값
						alert("xhr.status: " + xhr.status);
						console.log("그 외 상태값. xhr.responseText: " + xhr.responseText);
						window.location.href = "/error";	// 스프링부트 기본 에러페이지로 이동
					}	
				},
				error: function(xhr, status, error) {	// 응답 오류시 처리
					alert("xhr.status: " + xhr.status);
					console.log("알 수 없는 오류. xhr.responseText: " + xhr.responseText);
					window.location.href = "/error";	// 스프링부트 기본 에러페이지로 이동
				}
			});
		}
	});
});