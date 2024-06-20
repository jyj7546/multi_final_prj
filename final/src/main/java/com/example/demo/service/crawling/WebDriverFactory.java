package com.example.demo.service.crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 설명: 크롤링하기 위한 드라이버 생성 클래스
 * 작성자: 전영준
 * 최초생성: 2024-06-20
 * 수정일자: 
 */
@Component
public class WebDriverFactory {

    /**
     * 크롬 드라이버 생성 후 리턴
     * @return
     */
    public WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();    // Chrome WebDriver 자동 설치

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless"); // 창 안 띄우고 백그라운드 프로세스에서 실행
        options.addArguments("--disable-gpu"); // gpu 가속 비활성화 (PC 느려짐 문제)
        // options.setCapability("browserVersion", "126.0.6478.115");   // 내 로컬 크롬 버전 강제 지정무시 
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }

    /**
     * 생성한 드라이버 종료1
     * 열려 있는 웹브라우저 닫고 관련 프로세스(웹드라이버 세션) 종료
     * WebDriver 객체를 완전히 정리
     * @param driver
     */
    public void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * 생성한 드라이버 종료2
     * 활성화된 웹브라우저만 닫고 관련 프로세스(웹드라이버 세션) 유지
     * @param driver
     */
    public void close(WebDriver driver) {
        if (driver != null) {
            driver.close();
        }
    }

}
