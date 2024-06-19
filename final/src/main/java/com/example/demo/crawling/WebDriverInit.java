// package com.example.demo.crawling;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import jakarta.annotation.PostConstruct;

// @Component
// public class WebDriverInit {
//     // private String webDriverPath;

//     // @Value("${webdriver-path.chrome}")
//     // public void setWebDriverPath(String webDriverPath) {
//     //     this.webDriverPath = webDriverPath;
//     // }

//     String driverPath = "C:/fullstack/final2/multi_final_prj/final/src/main/resources/driver/chromedriver.exe";

//     @PostConstruct
//     public void init() {
//         System.setProperty("webdriver.chrome.driver", driverPath);
//     }

//     /**
//      * 크롬드라이버 설정 및 리턴
//      * @return WebDriver 인스턴스
//      */
//     public WebDriver getChromeDriver() {
//         ChromeOptions options = new ChromeOptions();
//         options.addArguments("headless"); // 창 안 띄우고 백그라운드 프로세스에서 실행
//         options.setCapability("browserVersion", "126.0.6478.115");
//         options.setCapability("ignoreProtectedModeSettings", true);

//         return new ChromeDriver(options);
//     }

//     /**
//      * WebDriver 리소스를 해제합니다.
//      * @param driver WebDriver 인스턴스
//      */
//     public void quit(WebDriver driver) {
//         if (driver != null) {
//             driver.quit();
//         }
//     }

//     /**
//      * WebDriver 창을 닫습니다.
//      * @param driver WebDriver 인스턴스
//      */
//     public void close(WebDriver driver) {
//         if (driver != null) {
//             driver.close();
//         }
//     }
// }
