package com.example.demo.crawling;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class CrawlingTest2 {
    @Value("${mart.url.emart}")
    private String EMART_URL;
    @Value("${mart.url.homeplus}")
    private String HOMEPLUS_URL;
    @Value("${mart.url.lottemart}")
    private String LOTTEMART_URL;

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
        chrome();

        List<String> subtitleList = new ArrayList<>();
        List<String> goodsNameList = new ArrayList<>();
        List<String> priceList = new ArrayList<>();
        List<String> linkList = new ArrayList<>();

        driver.get(EMART_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        // 팝업 체크 후 떠있으면 닫기
        // TODO

        // 세일중 버튼 클릭 > 페이지 이동
        driver.findElement(By.xpath("//*[@id=\"skip_gnb\"]/div/div[2]/ul/li[6]/a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        System.out.println("세일중 페이지 이동 성공");
        // List<WebElement> sectionList = driver.findElements(By.tagName("section"));
        // System.out.println("sectionList 갯수 : " + sectionList.size());
        // for (WebElement section : sectionList) {
        //     String subtitle = section.findElement(By.cssSelector("div > div > strong")).getText();
        //     System.out.println("subtitle : " + subtitle);
        //     if (!subtitle.equals("세일중 전체보기")) {
        //         subtitleList.add(subtitle);
        //     }
        // }

        WebElement ulTag1 = driver.findElement(By.xpath("//*[@id=\"content\"]/section[1]/div[2]/div[1]/ul"));
        List<WebElement> liList1 = ulTag1.findElements(By.tagName("li"));
        System.out.println("liList1 갯수 : " + liList1.size());
        for (WebElement elem : liList1) {
            WebElement divTag1 = elem.findElement(By.cssSelector(".mnemitem_unit"));
            WebElement aTag1 = divTag1.findElement(By.tagName("a"));
            String link = aTag1.getAttribute("href");
            System.out.println("link : " + link);

            String goodsName = "";
            // WebElement brandAndGoodsDivTag = aTag1.findElement(By.className("mnemitem_tit"));
            WebElement brandAndGoodsDivTag = aTag1.findElement(By.cssSelector("div:first-of-type"));
            System.out.println("brandAndGoodsDivTag : " + brandAndGoodsDivTag);
            List<WebElement> brandAndGoodsSpanTag = brandAndGoodsDivTag.findElements(By.tagName("span"));
            System.out.println("brandAndGoodsSpanTag : " + brandAndGoodsSpanTag.toString());
            System.out.println("brandAndGoodsSpanTag.size()" + brandAndGoodsSpanTag.size());

            if (brandAndGoodsSpanTag.size() == 1) {   
                goodsName = brandAndGoodsSpanTag.get(0).getText();
            } else if(brandAndGoodsSpanTag.size() == 2) {
                String brandName = brandAndGoodsSpanTag.get(0).getText();
                goodsName = brandAndGoodsSpanTag.get(1).getText();
                goodsName = brandName + " " + goodsName;
            } else {
                System.out.println("brandAndGoodsSpanTag.size() error !!!!!!!");
            }

            WebElement priceDivTag1 = aTag1.findElement(By.cssSelector(".mnemitem_pricewrap_v2"));
            WebElement priceDivTag2 = priceDivTag1.findElement(By.cssSelector(".mnemitem_price_row"));
            WebElement priceDivTag3 = priceDivTag2.findElement(By.cssSelector(".new_price"));
            String price = priceDivTag3.findElement(By.cssSelector(".ssg_price")).getText();

            goodsNameList.add(goodsName);
            priceList.add(price);
            linkList.add(link);
        }

        // 테스트 출력
        for (String subtitle : subtitleList) {
            System.out.println("subtitle : " + subtitle);
        }
        for (String goodsName : goodsNameList) {
            System.out.println("goodsName : " + goodsName);
        }
        for (String price : priceList) {
            System.out.println("price : " + price);
        }
        for (String link : linkList) {
            System.out.println("link : " + link);
        }

        quitDriver();
    }

    private void quitDriver() {
        driver.quit();
    }
}
