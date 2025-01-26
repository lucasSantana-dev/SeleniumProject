package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;
import java.time.Duration;
import java.util.List;

public class Test {

    Utils utils = new Utils();

    public WebDriver driver;
    private final Duration tempo = Duration.ofSeconds(5);

    @Before
    public void initialize() {
        WebDriverManager.chromedriver().setup();

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

    @And("Acesso a aba Dresses")
    public void acessarAbaTshirts() {
        WebElement buttonTshirt = driver.findElement(By.xpath("(//a[@title='Dresses'])[2]"));
        buttonTshirt.click();
        Assert.assertEquals("Dresses - My Shop", driver.getTitle());
    }

    @And("Adiciono produto no carrinho")
    public void adicionarProdutoNoCarrinho(){
        utils.esperarElemento(driver, tempo, By.xpath("(//img[@alt='Printed Dress'])[2]"));
        driver.findElement(By.xpath("(//img[@alt='Printed Dress'])[2]")).click();
        utils.esperarElemento(driver, tempo, By.id("short_description_content"));
        WebElement selectSize = driver.findElement(By.id("group_1"));
        Select select = new Select(selectSize);
        select.selectByVisibleText("M");
        WebElement ulCor = driver.findElement(By.id("color_to_pick_list"));
        List<WebElement> cores = ulCor.findElements(By.xpath("//ul[@id='color_to_pick_list']/li"));
        WebElement statusProduto = driver.findElement(By.id("availability_statut"));

        //selecionar cor até o item ficar disponível
        for(WebElement itemATual : cores) {
            itemATual.click();

            if (statusProduto.getText().matches("In stock")) break;
        }
        utils.esperarElemento(driver, tempo,By.xpath("//button/span[text()='Add to cart']"));
        driver.findElement(By.xpath("//button/span[text()='Add to cart']")).click();
        utils.esperarElemento(driver,tempo,By.xpath("//a[@class='btn btn-default button button-medium']"));
        driver.findElement(By.xpath("//a[@class='btn btn-default button button-medium']")).click();
    }

    @Then("Finalizo a compra")
    public void finalizarCompra(){
        Assert.assertEquals("Order - My Shop", driver.getTitle());
        driver.findElement(By.xpath("//a/span[text()='Proceed to checkout']"));
    }

    @After
    public void tear(){
        if(driver != null) {
            driver.quit();
        }
    }
}
