package org.example;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AuthTest {

    public static AuthPage authPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        authPage = new AuthPage(driver);
        profilePage = new ProfilePage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage")); }



    @Test
    public void loginTest() {
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин
        authPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        authPage.clickLoginBtn();
        //вводим пароль
        authPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        authPage.clickLoginBtn();
        //получаем отображаемый логин
        String user = profilePage.getUserName();
        //и сравниваем его с логином из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("login"), user); }

    @AfterClass
    public static void tearDown() {
        profilePage.entryMenu();
        profilePage.userLogout();
        driver.quit();
    }
}

