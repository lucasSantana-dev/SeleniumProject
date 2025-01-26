package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.time.Duration;

public class Test {

    Utils utils = new Utils();

    public WebDriver driver;
    private final Duration tempo = Duration.ofSeconds(5);

    @Before
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");
        // fazer login
        utils.esperarElemento(driver, tempo, By.xpath("//a[@class='login']"));
        driver.findElement(By.xpath("//a[@class='login']")).click();
        Assert.assertEquals("Login - My Shop", driver.getTitle());
        driver.findElement(By.id("email")).sendKeys("automatedTest@qa.com");
        driver.findElement(By.id("passwd")).sendKeys("12345");
        driver.findElement(By.id("SubmitLogin")).click();
        utils.esperarElemento(driver, tempo, By.xpath("//h1[@class='page-heading']"));
        driver.getTitle();
        Assert.assertEquals("My account - My Shop", driver.getTitle());
        driver.findElement(By.xpath("//img[contains(@alt,'My Shop')]")).click();
    }

    @Given("Estou acessando a home page")
    public void acessarHomePage() {
        utils.esperarElemento(driver, tempo, By.xpath("//img[@class='logo img-responsive']"));
        String tituloPagina = driver.getTitle();
        Assert.assertEquals("My Shop", tituloPagina);
    }

    @And("Acesso a aba T-shirts")
    public void acessarAbaTshirts() {
        WebElement buttonTshirt = driver.findElement(By.xpath("(//a[@title='T-shirts'])[2]"));
        buttonTshirt.click();
        Assert.assertEquals("T-shirts - My Shop", driver.getTitle());
    }

    @And("adiciono produto no carrinho")
    public void adicionarProdutoNoCarrinho(){
        utils.esperarElemento(driver, tempo, By.xpath("//img[contains(@alt,'Faded Short Sleeve T-shirts')]"));
        driver.findElement(By.xpath("//img[contains(@alt,'Faded Short Sleeve T-shirts')]")).click();
        utils.esperarElemento(driver, tempo, By.id("short_description_content"));
        WebElement selectSize = driver.findElement(By.id("group_1"));
        Select select = new Select(selectSize);
        select.selectByVisibleText("L");
        utils.esperarElemento(driver, tempo,By.id("quantity_wanted_p"));
    }

    @After
    public void tear(){
        if(driver != null) {
            driver.quit();
        }
    }
}
