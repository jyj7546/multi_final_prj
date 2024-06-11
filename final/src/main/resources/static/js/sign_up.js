// 페이지 로드가 완료되면 실행되는 함수 설정
$(document).ready(async function() {

	// 공통 모듈 common.js 불러오기
    const module = await import('./common.js');
    const myModule = module.default;

	$("#form_s1").submit(function(event){
	    event.preventDefault();

		// 컨트롤러로 보낼 form 데이터 값 가져옴 
			// => @RequestBody MemberDTO dto에 자동 매핑되게끔 MemberDTO 파일 변수와 변수명을 동일하게 맞춤
			let formData = {
				memId: $("#id_s").val().replace(/(^\s*)|(\s*$)/g, ""),	// 공백 제거 정규식
				memPw: $("#pw_s").val().replace(/(^\s*)|(\s*$)/g, ""),
				memName: $("#name_s").val().replace(/(^\s*)|(\s*$)/g, ""),
				// memBirth:
				memEmail: $("#email_s").val().replace(/(^\s*)|(\s*$)/g, "")
			};
			

		//입력값 유효성 검사를 위한 정규 표현식을 정의
	    // 이메일 형식을 검사하는 정규 표현식
	    // let email_Regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	    // 이름이 한글 또는 영문자로만 구성되었는지 검사하는 정규 표현식
	    // let name_Regex = /^[가-힣a-zA-Z]+$/;
	    // // 아이디가 영문을 반드시 포함하며, 숫자를 선택적으로 포함할 수 있는 5자 이상의 문자열인지 검사
	    // let id_Regex = /^(?=.*[A-Za-z])[A-Za-z\d]{5,}$/;
	    // // 비밀번호가 영문, 숫자, 특수문자 각각 최소 하나 이상 포함하며, 총 10자 이상인지 검증
	    // let pw_Regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{10,}$/;
	    
	    // 각 입력값의 유효성 검사 결과를 저장할 변수를 초기화
	    // let check_email=0
		// let check_name=0
		// let check_id=0
		// let check_pw=0;
	
	    // 입력값들에 대해 정규 표현식을 이용한 유효성 검사를 수행하고 결과를 저장
	    // if(email_Regex.test(formData.memEmail)) {
		// 	check_email = 1;
		// }
		// if(name_Regex.test(formData.memName)) {
		// 	check_name = 1;
		// }









		// 빠른 테스트를 위해 아래 정규식 체크는 임시 주석처리함
		// if(id_Regex.test(formData.memId)) {
		// 	check_id = 1;
		// }
		// if(pw_Regex.test(formData.memPw)) {
		// 	check_pw = 1;
		// }
		
		// if(check_email*check_name*check_id*check_pw == 1){
		// if(check_email*check_name == 1){
			

	        // event.target.submit();
			// form 데이터를 ajax로 컨트롤러(MemberController.java insertMember 메소드)로 전달
			$.ajax({
				type: "POST",
				url: "/insertMember",
				contentType: "application/json",	// data는 JSON형태로 보냄
				data: JSON.stringify(formData),
				success: function(response, status, xhr) {
					// window.location.href = "login.html";
					alert(response);	// 서버로부터 응답 메세지 받아 출력
					if(xhr.status == 201) {
						myModule.sectionChg("login");	// 회원가입 성공 시 로그인 페이지로 이동
					} else {
						alert("예외. xhr.status: " + xhr.status);
						console.log("예외. xhr.responseText: " + xhr.responseText);
					}
				},
				error: function(xhr, status, error) {
					alert("회원가입 실패. xhr.status: " + xhr.status);
					console.log("회원가입 실패. xhr.responseText: " + xhr.responseText);
					window.location.href = "/error";
				}
			});
	    
		
		
			
		
		
		// } else {
			 // 하나 이상의 입력값이 유효하지 않은 경우, 사용자에게 알림
			//  let info="";
			//  let info2="";
			//  if (check_email == 0){
			// 	 info += "유효하지 않은 이메일 형식입니다.\n";
			// 	 info2 += "이메일 ";
			//  }
			//  if (check_name == 0){
			// 	 info += "유효하지 않은 이름 형식입니다.\n";
			// 	 info2 += "성명 ";
			//  }
			//  if (check_id == 0){
			// 	 info += "유효하지 않은 아이디 형식입니다.\n";
			// 	 info2 += "아이디 ";
			//  }
			//  if (check_pw == 0){
			// 	 info += "유효하지 않은 비밀번호 형식입니다.\n";
			// 	 info2 += "비밀번호 ";
			//  }
			//  // 문서 내의 특정 위치에 유효하지 않은 입력 필드를 표시
			// //  document.getElementById('p1_s').innerHTML = "잘못된 형식: " + info2;	
			// $('#p1_s').text("잘못된 형식: " + info2); 
	    // }
	});
	

	


	// .input_s 클래스를 가진 모든 요소를 선택하여 inputs 변수에 저
	const inputs = document.querySelectorAll('.input_s');
	// tooltip_i 아이디를 가진 요소를 선택하여 tooltip 변수에 저장
    const tooltip = document.getElementById('tooltip_i');
    
	// input_s에 포함된 각 input 요소에 대하여 반복 실행
    inputs.forEach(function(input) {
		// input 요소에 마우스가 진입할 때 실행될 이벤트 리스너를 추가
        input.addEventListener('mouseenter', function(e) {
			// 마우스가 진입한 요소의 data-tooltip 속성 값을 읽어 message 변수에 저장
            const message = e.target.getAttribute('data-tooltip');
            // tooltip 요소의 텍스트 내용을 message로 설정
            tooltip.textContent = message;
            // tooltip 요소를 화면에 보이게 합
            tooltip.style.display = 'block';
        });

		// input 요소 위에서 마우스가 움직일 때 실행될 이벤트 리스너를 추가
        input.addEventListener('mousemove', function(e) {
			// 마우스의 현재 x, y 좌표를 구함
            const x = e.clientX;
            const y = e.clientY;
            // tooltip 요소의 위치를 마우스 커서의 바로 오른쪽 아래로 설정
        	// 여기서 10px는 마우스 커서와 툴팁 사이의 간격
            tooltip.style.left = (x + 10) + 'px';
            tooltip.style.top = (y + 10) + 'px';
        });

		// input 요소에서 마우스가 벗어날 때 실행될 이벤트 리스너를 추가
        input.addEventListener('mouseleave', function() {
			// tooltip 요소를 화면에서 숨깁니다.
            tooltip.style.display = 'none';
        });
    });




});