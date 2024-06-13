$(document).ready(async function() {

    // 공통 모듈 common.js 불러오기
    const module = await import('./common.js');
    const myModule = module.default;

    const mainIcon = $("#mainIcon");
    const homeIcon = $("#homeIcon");
    const searchIcon = $("#searchIcon");
    const exploreIcon = $("#exploreIcon");
    const reelsIcon = $("#reelsIcon");
    const dmIcon = $("#dmIcon");
    const notiIcon = $("#notiIcon");
    const createIcon = $("#createIcon");
    const mypageIcon = $("#mypageIcon");
    const moreIcon = $("#moreIcon");
    const mainSection = $("#mainSection");

    // $("#mainSection").load(href); // 스프링부트에선 직접 로드 불가
    // 최초 메인피드 노출

    // 사이드바 메뉴 버튼 클릭 이벤트 처리
    mainIcon.on("click", function() {
        myModule.sectionChg("mainfeed");
    });
    homeIcon.on("click", function() {
        myModule.sectionChg("mainfeed");
    });
    // 마이페이지 이동
    mypageIcon.on("click", function() {
        myModule.sectionChg("mypage-main");
    });
    // 게시글작성 이동
    createIcon.on("click", function() {
        myModule.sectionChg("write");
    });
});