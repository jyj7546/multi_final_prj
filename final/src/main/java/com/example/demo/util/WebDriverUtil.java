// package com.example.demo.util;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;
// import org.springframework.util.ObjectUtils;

// import jakarta.annotation.PostConstruct;

// @Component
// public class WebDriverUtil {

//     private static String WEB_DRIVER_PATH;
//     private static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 프로퍼티 키

//     @Value("${webdriver-path.chrome}")
//     private String webdriverPath;

//     @PostConstruct
//     private void init() {
//         WEB_DRIVER_PATH = webdriverPath;
//     }

//     /**
//      * 크롬드라이버 설정 및 리턴
//      * @return
//      */
//     public static WebDriver getChromeDriver() {
//         if (ObjectUtils.isEmpty(System.getProperty(WEB_DRIVER_ID))) {
//             System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//         }

//         ChromeOptions options = new ChromeOptions();
//         options.addArguments("--lang=ko");
//         options.addArguments("--no-sandbox");
//         options.addArguments("--disable-dev-shm-usage");
//         options.addArguments("--disable-gpu");
//         options.addArguments("--start-maximized");
//         options.addArguments("--disable-popup-blocking");
//         options.addArguments("headless"); // 창 안 띄우고 백그라운드 프로세스에서 실행
//         options.setCapability("ignoreProtectedModeSettings", true);

//         return new ChromeDriver(options);
//     }

//     public static void quit(WebDriver driver) {
//         if (driver != null) {
//             driver.quit();
//         }
//     }

//     public static void close(WebDriver driver) {
//         if (driver != null) {
//             driver.close();
//         }
//     }

// }