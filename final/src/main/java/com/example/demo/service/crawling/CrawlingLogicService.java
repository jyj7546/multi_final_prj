package com.example.demo.service.crawling;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CrawlingDTO;
import com.example.demo.enumeration.MartCd;
import com.example.demo.util.MyTimeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 설명: 크롤링 로직 서비스
 * 작성자: 전영준
 * 최초생성: 2024-06-20
 * 수정일자: 
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingLogicService {

    private final WebDriverFactory webDriverFactory;
    private final CrawlingDBService crawlingService;

    private static final int CRAWLING_LIMIT_COUNT = 50; // 상품 갯수 많을 시 50개까지만 제한하여 크롤링
    private static final Duration DURATION_1SEC = Duration.ofMillis(1000);  // 웹 대기 타임아웃

    private static final String emartCode = MartCd.EMART.code();
    private static final String homeplusCode = MartCd.HOMEPLUS.code();
    private static final String lotteCode = MartCd.LOTTEMART.code();
    private static final String emartUrl = MartCd.EMART.url();
    private static final String homeplusUrl = MartCd.HOMEPLUS.url();
    private static final String lotteUrl = MartCd.LOTTEMART.url();

    // TODO: html 셀렉터 By 지정자 한데 모으기 (계속 변동하고 바뀔 가능성 있어서 유지보수 포인트 높음)
    // private static final By MAIN_CONTENT = new By.ByXPath("//*[@id=\"content\"]");

    // TODO: 셀레니움 html 요소 가져오기 전에 먼저 있는지 체크하는 메소드

    public boolean crawling(String martCd) {
        boolean result = false;
        String date = MyTimeUtil.getNowDate("yyyyMMdd");

        log.debug("===============================================");
        log.debug("마트: {}, 일자: {} 크롤링 시작", date, martCd);
        log.debug("===============================================");

        Map<String, Object> param = new HashMap<>();
        param.clear();
        param.put("martCd", martCd);
        param.put("date", date);

        switch (martCd) {
            case "emart":
                result = crawlingEmart(param);
                break;
            case "homeplus":
                result = crawlingHomeplus(date);
                break;
            case "lottemart":
                result =  crawlingLotte(date);
                break;
            default:
                log.error("마트 파라미터 오류");
                break;
        }

        return result;
    }

    /**
     * 이마트 크롤링 로직
     * @return
     */
    public boolean crawlingEmart(Map<String, Object> map) {
        boolean result = false;

        String martCd = "";
        String date = "";

        // 파라미터 valid 체크
        if (map != null && !map.isEmpty()) {
            if(map.containsKey("martCd")) {
                martCd = (String) map.get("martCd");
            }

            if(map.containsKey("date")) {
                date = (String) map.get("date");
            }
        }

        // 파라미터 valid 체크
        if(martCd.equals("")) {
            martCd = MartCd.EMART.code();
        }
        MyTimeUtil.validBatchDate(date);

        // 크롤링 중복 여부 체크
        if(chkAlreadyCrawling(martCd, date)) {
            return result;
        }

        // 출력 테스트용 리스트
        // List<String> subtitleList = new ArrayList<>();
        // List<String> goodsNameList = new ArrayList<>();
        // List<Integer> priceList = new ArrayList<>();
        // List<String> goodsDirectLinkList = new ArrayList<>();

        WebDriver driver = webDriverFactory.createChromeDriver();
        List<CrawlingDTO> crawlingDTOList = new ArrayList<>();

        driver.get(emartUrl);  // url 접속
        driver.manage().timeouts().implicitlyWait(DURATION_1SEC); // 페이지 기다림 타임아웃 1초

        // TODO: 팝업 뜨는 경우 체크 후 닫기 선행

        // 이마트 메인페이지에서 세일중 버튼 클릭 > 페이지 이동

        try {
            driver.findElement(By.xpath("//*[@id=\"skip_gnb\"]/div/div[2]/ul/li[6]/a")).click();
            driver.manage().timeouts().implicitlyWait(DURATION_1SEC);
            log.debug("세일중 페이지 이동 성공");

            // 메인 content 요소로 이동
            WebElement contentDiv = driver.findElement(By.xpath("//*[@id=\"content\"]"));
            
            // 섹션명 가져오기
            List<WebElement> sectionList = contentDiv.findElements(By.tagName("section"));
            log.debug("sectionList 갯수 : {}", sectionList.size());

            int sectionCnt = 0;
            for (WebElement section : sectionList) {
                String subtitle = section.findElement(By.cssSelector("div:nth-of-type(1) > div > strong")).getText();
                log.debug("subtitle ::: {}", subtitle);
                
                // 4번째까지만 가져오기 X
                // 섹션 추가될 가능성이 있으므로 그냥 다 가져오고 세일중 전체보기만 패스
                if (subtitle.equals("세일중 전체보기")) {
                    continue;
                }

                // subtitleList.add(subtitle); // 출력 테스트용 리스트에 담음
                sectionCnt++;

                // 해당 섹션 상품 ul 가져오기
                // WebElement ulTag1 = driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[2]/div[1]/ul"));
                
                WebElement ulTag1 = null;
                // if(section.findElement(By.cssSelector("div:nth-of-type(2) > div:nth-of-type(1) > ul")).isDisplayed()) {
                //     ulTag1 = section.findElement(By.cssSelector("div:nth-of-type(2) > div:nth-of-type(1) > ul"));
                // } else {
                //     // 하나 더, 사은품, 다다익선 섹션은 상품 ul태그가 두번째 div에 있음
                //     ulTag1 = section.findElement(By.cssSelector("div:nth-of-type(2) > div:nth-of-type(2) > ul"));
                // }

                if(sectionCnt == 1 || sectionCnt == 3) {
                    ulTag1 = section.findElement(By.cssSelector("div:nth-of-type(2) > div:nth-of-type(1) > ul"));
                } else {
                    // 하나 더, 사은품, 다다익선 섹션은 상품 ul태그가 두번째 div에 있음
                    ulTag1 = section.findElement(By.cssSelector("div:nth-of-type(2) > div:nth-of-type(2) > ul"));
                }
                
                List<WebElement> liList1 = ulTag1.findElements(By.tagName("li"));
                log.debug("liList1 갯수 ::: {}", liList1.size());

                // TODO: 상품 갯수 너무 많은 경우 처리

                for (WebElement elem : liList1) {   // 해당 섹션 상품 li 갯수만큼 반복
                    WebElement divTag1 = elem.findElement(By.cssSelector(".mnemitem_unit"));
                    WebElement aTag1 = divTag1.findElement(By.tagName("a"));
                    String link = aTag1.getAttribute("href");
                    log.debug("link ::: {}", link);

                    String goodsName = "";

                    // 상품명 가져오기 1
                    // WebElement brandAndGoodsDivTag2 = divTag1.findElement(By.cssSelector("a > div:nth-of-type(1) > span:nth-of-type(2)")); 
                    // 두번째 span 이 있는 경우 해당 요소는 상품명이고 첫번째 span이 브랜드명
                    // if(!ObjectUtils.isEmpty(brandAndGoodsDivTag2)) {
                    //     goodsName = brandAndGoodsDivTag2.getText();
                    //     // 브랜드명
                    //     String brandName = divTag1.findElement(By.cssSelector("a > div:nth-of-type(1) > span:nth-of-type(1)")).getText();
                    //     goodsName = brandName + " " + goodsName;
                    // } else {
                    //     goodsName = divTag1.findElement(By.cssSelector("a > div:nth-of-type(1) > span")).getText();
                    // }
                    // log.debug("goodsName : {}", goodsName);

                    // 상품명 가져오기 2
                    List<WebElement> brandAndGoodsSpanTag = divTag1.findElements(By.cssSelector("a > div > span"));
                    log.debug("brandAndGoodsSpanTag.size() : {}", brandAndGoodsSpanTag.size());
                    if (brandAndGoodsSpanTag.size() == 1) {   
                        goodsName = brandAndGoodsSpanTag.get(0).getText();
                    } else if(brandAndGoodsSpanTag.size() == 2) {
                        String brandName = brandAndGoodsSpanTag.get(0).getText();
                        goodsName = brandAndGoodsSpanTag.get(1).getText();
                        goodsName = brandName + " " + goodsName;
                        log.debug("goodsName ::: {}", goodsName);
                    } else {
                        log.debug("brandAndGoodsSpanTag.size() error. size is : {}", brandAndGoodsSpanTag.size());
                    }

                    // 가격 가져오기
                    // String stringPrice = divTag1.findElement(By.cssSelector("a > div:nth-of-type(2) > div:nth-of-type(1) > div > em")).getText(); // 세일 섹션마다 div 순서 다름
                    String stringPrice = divTag1.findElement(By.cssSelector("a > div:nth-of-type(2) > div.mnemitem_price_row > div.new_price > em")).getText();
                    String newPrice = stringPrice.replace(",", "");
                    int price = Integer.parseInt(newPrice);
                    log.debug("price ::: {}", price);

                    // 출력 테스트용 리스트에 추가
                    // goodsNameList.add(goodsName);
                    // priceList.add(price);
                    // goodsDirectLinkList.add(link);

                    // DTO 생성 후 DTO 리스트로 추가
                    CrawlingDTO dto = new CrawlingDTO();
                    dto.setSubtitle(subtitle);
                    dto.setGoodsName(goodsName);
                    dto.setPrice(price);
                    if(link == null) {
                        link = MartCd.EMART.url();
                    } 
                    dto.setGoodsDirectLink(link);
                    dto.setMartCd(martCd);
                    dto.setCrawlingDate(date);

                    crawlingDTOList.add(dto);
                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            log.error("EEEEEEEEEE");
            log.error("html 태그 요소 없음");
            log.error("EEEEEEEEEE");

            log.error(noSuchElementException.getMessage());
            noSuchElementException.printStackTrace();
        }

        // 테스트 출력
        // for (String subtitle : subtitleList) {
        //     log.debug("subtitle ::: {}", subtitle);
        // }
        // for (String goodsName : goodsNameList) {
        //     log.debug("goodsName ::: {}", goodsName);
        // }
        // for (int price : priceList) {
        //     log.debug("price ::: {}", price);
        // }
        // for (String link : goodsDirectLinkList) {
        //     log.debug("link ::: {}", link);
        // }

        int insertCount = crawlingService.insertCrawlingData(crawlingDTOList);    // 크롤링한 데이터 저장
        log.debug("=============================================");
        log.debug("마트: {}, 일자: {} 크롤링 데이터 {}개 적재 성공", martCd, date, insertCount);
        log.debug("=============================================");
        if(insertCount >= 1) {
            // 1혹은 그 이상이면 데이터 적재 성공
            result = true;
        } 

        webDriverFactory.quitDriver(driver);    // 크롤링 끝 세션 종료 필수

        return result;
    }

    public boolean crawlingHomeplus(String date) {
        boolean result = false;
        String martCd = homeplusCode;

        // 크롤링 중복 여부 체크
        if(chkAlreadyCrawling(martCd, date)) {
            return result;
        }

        // 크롤링 시작 로직 종료
        
        return result;
    }

    public boolean crawlingLotte(String date) {
        boolean result = false;
        String martCd = lotteCode;

        // 크롤링 중복 여부 체크
        if(chkAlreadyCrawling(martCd, date)) {
            return result;
        }

        // 크롤링 시작 로직 종료

        return result;
    }

    /**
     * 금일자 크롤링 중복 여부 체크
     * @param martCd {"emart", "homeplus", "lottemart"}
     * @param date "yyyyMMdd"
     * @return
     */
    public boolean chkAlreadyCrawling(String martCd, String date) {
        boolean result = false;
        // 마트, 크롤링 일자로 조회해서 이미 있으면 update(delete and insert or upsert)
        Map<String, Object> param = new HashMap<>();
        param.clear();
        param.put("martCd", martCd);
        param.put("crawlingDate", date);

        int selectCount = crawlingService.getCrawlingDataCnt(param);
        if(selectCount > 0) {
            log.debug("=============================================");
            log.debug("마트: {}, 일자: {} 크롤링 데이터 {}개 이미 존재", martCd, date, selectCount);
            log.debug("=============================================");
            result = true;
        }

        return result;
    }

}
