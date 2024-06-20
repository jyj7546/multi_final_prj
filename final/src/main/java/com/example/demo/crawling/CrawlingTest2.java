package com.example.demo.crawling;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.dto.CrawlingDTO;

import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class CrawlingTest2 {
    @Value("${mart.url.emart}")
    private String EMART_URL;
    @Value("${mart.url.homeplus}")
    private String HOMEPLUS_URL;
    @Value("${mart.url.lottemart}")
    private String LOTTEMART_URL;

    @Value("${code.mart.emart}")
    private String EMART_CODE;
    @Value("${code.mart.homeplus}")
    private String HOOMEPLUSE_CODE;
    @Value("${code.mart.lottemart}")
    private String LOTTEMART_CODE;

    @Autowired
    CrawlingService crawlingService;

    private static final int CRAWLING_LIMIT_COUNT = 50;

    private WebDriver driver;
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    // public static String WEB_DRIVER_PATH = "C:/fullstack/final2/multi_final_prj/final/src/main/resources/driver/chromedriver.exe";

    private void chrome() {
        // Chrome WebDriver 자동 설치
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("headless"); // 창 안 띄우고 백그라운드 프로세스에서 실행
        // options.setCapability("browserVersion", "126.0.6478.115");
        // options.setCapability("ignoreProtectedModeSettings", true);

        // WebDriver 생성
        driver = new ChromeDriver(options);
    }

    public void crawlEmart() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        chrome();

        List<String> subtitleList = new ArrayList<>();
        List<String> goodsNameList = new ArrayList<>();
        List<Integer> priceList = new ArrayList<>();
        List<String> goodsDirectLinkList = new ArrayList<>();
        
        List<CrawlingDTO> crawlingDTOList = new ArrayList<>();

        driver.get(EMART_URL);  // url 접속
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000)); // 페이지 기다림 타임아웃 1초

        // TODO: 팝업 뜨는 경우 체크 후 닫기 선행

        // 이마트 메인페이지에서 세일중 버튼 클릭 > 페이지 이동

        try {
            driver.findElement(By.xpath("//*[@id=\"skip_gnb\"]/div/div[2]/ul/li[6]/a")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
            System.out.println("세일중 페이지 이동 성공");

            // 메인 content 요소로 이동
            WebElement contentDiv = driver.findElement(By.xpath("//*[@id=\"content\"]"));
            
            // 섹션명 가져오기
            List<WebElement> sectionList = contentDiv.findElements(By.tagName("section"));
            System.out.println("sectionList 갯수 : " + sectionList.size());

            int sectionCnt = 0;
            for (WebElement section : sectionList) {
                String subtitle = section.findElement(By.cssSelector("div:nth-of-type(1) > div > strong")).getText();
                System.out.println("subtitle ::: " + subtitle);
                
                // 4번째까지만 가져오기 X
                // 섹션 추가될 가능성이 있으므로 그냥 다 가져오고 세일중 전체보기만 패스
                if (subtitle.equals("세일중 전체보기")) {
                    continue;
                }

                subtitleList.add(subtitle);
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
                System.out.println("liList1 갯수 ::: " + liList1.size());

                // TODO: 상품 갯수 너무 많은 경우 처리

                for (WebElement elem : liList1) {   // 해당 섹션 상품 li 갯수만큼 반복
                    WebElement divTag1 = elem.findElement(By.cssSelector(".mnemitem_unit"));
                    WebElement aTag1 = divTag1.findElement(By.tagName("a"));
                    String link = aTag1.getAttribute("href");
                    System.out.println("link ::: " + link);

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
                    // System.out.println("goodsName : " + goodsName);

                    // 상품명 가져오기 2
                    List<WebElement> brandAndGoodsSpanTag = divTag1.findElements(By.cssSelector("a > div > span"));
                    System.out.println("brandAndGoodsSpanTag.size() : " + brandAndGoodsSpanTag.size());
                    if (brandAndGoodsSpanTag.size() == 1) {   
                        goodsName = brandAndGoodsSpanTag.get(0).getText();
                    } else if(brandAndGoodsSpanTag.size() == 2) {
                        String brandName = brandAndGoodsSpanTag.get(0).getText();
                        goodsName = brandAndGoodsSpanTag.get(1).getText();
                        goodsName = brandName + " " + goodsName;
                        System.out.println("goodsName ::: " + goodsName);
                    } else {
                        System.out.println("brandAndGoodsSpanTag.size() error. size is : " + brandAndGoodsSpanTag.size());
                    }

                    // 가격 가져오기
                    // String stringPrice = divTag1.findElement(By.cssSelector("a > div:nth-of-type(2) > div:nth-of-type(1) > div > em")).getText(); // 세일 섹션마다 div 순서 다름
                    String stringPrice = divTag1.findElement(By.cssSelector("a > div:nth-of-type(2) > div.mnemitem_price_row > div.new_price > em")).getText();
                    String newPrice = stringPrice.replace(",", "");
                    int price = Integer.parseInt(newPrice);
                    System.out.println("price ::: " + price);

                    // 리스트에 추가
                    goodsNameList.add(goodsName);
                    priceList.add(price);
                    goodsDirectLinkList.add(link);

                    // DTO 생성 후 DTO 리스트로 추가
                    CrawlingDTO dto = new CrawlingDTO();
                    dto.setSubtitle(subtitle);
                    dto.setGoodsName(goodsName);
                    dto.setPrice(price);
                    if(link == null) {
                        link = EMART_URL;
                    } 
                    dto.setGoodsDirectLink(link);
                    dto.setMartCd(EMART_CODE);
                    dto.setCrawlingDate(formatedNow);

                    crawlingDTOList.add(dto);
                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("============== ============== ==============");
            System.out.println("============== html 태그 요소 없음 ==============");
            System.out.println("============== ============== ==============");
            noSuchElementException.getMessage();
            noSuchElementException.getStackTrace();
        }

        // 테스트 출력
        for (String subtitle : subtitleList) {
            System.out.println("subtitle ::: " + subtitle);
        }
        for (String goodsName : goodsNameList) {
            System.out.println("goodsName ::: " + goodsName);
        }
        for (int price : priceList) {
            System.out.println("price ::: " + price);
        }
        for (String link : goodsDirectLinkList) {
            System.out.println("link ::: " + link);
        }

        
        crawlingService.insertCrawlingData(crawlingDTOList);

        quitDriver();
    }

    private void quitDriver() {
        driver.quit();
    }
}
