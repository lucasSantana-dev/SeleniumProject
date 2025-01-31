package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Utils {

    private static WebDriver driver;
    public static Duration tempo = Duration.ofSeconds(5);

    public static WebDriver initDriver(){
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriverWait wait(Duration time) {
        return new WebDriverWait(driver, time);
    }

    public static void esperarElemento(Duration tempoSec, By by) {
        wait(tempoSec).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void esperarElementoClicavel(Duration tempoSec, By by){
        wait(tempoSec).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement getElement(By by){
        return initDriver().findElement(by);
    }

    public static void clickButton(String path){
        Utils.getElement(By.xpath(path)).click();
    }

    public static void sendKeys(String path, String keys){
        WebElement input = initDriver().findElement(By.xpath(path));
        input.sendKeys(keys);
    }

    public static void validarTitulo(String expectedTitle){
        String tituloPagina = Utils.initDriver().getTitle();
        Assert.assertEquals(expectedTitle, tituloPagina);
    }


    public static void fecharDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}