package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.Utils;

import java.time.Duration;

public class HomePage {

    Duration tempo = Duration.ofSeconds(5);
    //elementos
    String loginButton = "//a[@class='login']";
    String emailInput = "//input[@id='email']";
    String passwdInput = "//input[@id='passwd']";
    String submitLoginButton = "//button[@id='SubmitLogin']";
    String logo = "//img[contains(@alt,'My Shop')]";


    public void fazerLogin() {
        Utils.initDriver().get("http://www.automationpractice.pl/index.php");
        Utils.esperarElemento(tempo, By.xpath(loginButton));
        Utils.clickButton(loginButton);
        Assert.assertEquals("Login - My Shop", Utils.initDriver().getTitle());
        Utils.sendKeys(emailInput, "automatedTest@qa.com");
        Utils.sendKeys(passwdInput, "12345");
        Utils.clickButton(submitLoginButton);
        Utils.esperarElemento(tempo, By.xpath("//h1[text()='My account']"));
        Assert.assertEquals("My account - My Shop", Utils.initDriver().getTitle());
        Utils.clickButton(logo);
    }
}
