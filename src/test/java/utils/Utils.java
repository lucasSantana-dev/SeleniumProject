package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {

    public void esperarElemento(WebDriver driverNav, Duration tempoSec, By by) {
        WebDriverWait wait = new WebDriverWait(driverNav, tempoSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //public void moverComJs(WebElement elemento){
    //   ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView(true);", elemento);
    //}
}