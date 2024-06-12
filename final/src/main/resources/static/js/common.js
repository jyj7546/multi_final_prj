/**
 * 프론트엔드 공통 기능 자바스크립트 함수
 */
const myModule = {
    /**
     * 메뉴 클릭 시 해당하는 뷰 jsp를 리턴하는 컨트롤러 호출
     * @param {"mainfeed", "mypage-main", "login", "sign-up", "write", "DM"} menuName 
     */
    sectionChg: function(menuName) {
        console.log("sectionChg start");
        console.log("menuName : " + menuName);

        // location.href="/goto/"+menuName;    // 클라이언트 요청만 함
        $.ajax({
            type: "GET",
            url: "/" + menuName,
            success: function(response) {
                // 서버로부터 받은 response를 처리
                if(response.message) {
                    alert(response.message);
                }
                // 새로운 페이지로 이동
                location.href="/"+menuName;
            },
            error: function(xhr, status, error) {
                console.log("Error occurred: " + error);
                location.href="/error";
            }
        });
    },

    /**
     * 클라이언트단에서 세션 체크
     * ===> 일단 미사용...서버단에서 메뉴 이동 호출시 체크하는걸로
     */
    sessionChk: function() {
        $.ajax({
            type: "POST",
            url: "/getSessionid",
            contentType: "application/json",
            // data: JSON.stringify(formData),
            success: function(response, status, xhr) {
                console.log("xhr.responseText: " + xhr.responseText);
                if(xhr.status == 200) {
                    if(response == "") {    // null리턴이 아닌 이유 수정필요 일단 임시로 이렇게
                        console.log("sessionid: ", response);
                        myModule.sectionChg("login");
                    } else {
                        console.log("sessionid: ", response);
                        // myModule.sectionChg("mypage-main");
                    }
                    
                }
            },
            error: function(xhr, status, error) {
                console.log("get session 실패 xhr.responseText: " + xhr.responseText);
                // window.location.href = "login";
                window.location.href = "/error";
            }
        });

        

        // let sessionid = "<%= session.getAttribute('sessionid') %>";
        // if (sessionid) {
        //     console.log("sessionid: ", sessionid);
        // } else {
        //     window.location.href = "login";
        // }
    }
}




export default myModule