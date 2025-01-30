package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DressesPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import utils.Utils;
import java.time.Duration;

public class Test {

    private final Duration tempo = Duration.ofSeconds(5);
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    DressesPage dressesPage = new DressesPage();
    MyAccountPage myAccountPage = new MyAccountPage();

    @Before
    public void fazerLogin() {
        loginPage.fazerLogin();
        myAccountPage.validarPaginaMyAccount();
    }

    @Given("Estou acessando a home page")
    public void acessarHomePage() {
        homePage.validarTituloHome();
    }

    @And("Acesso a aba Dresses")
    public void acessarAbaDresses() {
        homePage.acessarMenuDresses();
    }

    @And("Adiciono produto no carrinho")
    public void adicionarProdutoNoCarrinho(){
        dressesPage.validarTitulo();
        dressesPage.adicionarProdutoNoCarrinho();
    }

    @Then("Finalizo a compra")
    public void finalizarCompra(){
        Assert.assertEquals("Order - My Shop", Utils.initDriver().getTitle());
        Utils.initDriver().findElement(By.xpath("(//span[contains(.,'Proceed to checkout')])[2]")).click();
        Utils.initDriver().findElement(By.xpath("//button[@name='processAddress']")).click();
        WebElement checkbox = Utils.initDriver().findElement(By.xpath("//input[@id='cgv']"));
        if(!checkbox.isSelected()){
            checkbox.click();
        }
        Utils.clickButton("//button[@name='processCarrier']");
        Utils.esperarElemento(tempo, By.xpath("//a[@class='bankwire']"));
        Utils.initDriver().findElement(By.xpath("//a[@class='bankwire']")).click();
        Utils.initDriver().findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();
        Utils.esperarElemento(tempo, By.xpath("//p[@class='alert alert-success']"));
        WebElement compraFeitaMsg = Utils.initDriver().findElement(By.xpath("//p[@class='alert alert-success']"));
        Assert.assertEquals("Your order on My Shop is complete.", compraFeitaMsg.getText());
    }

    @After
    public void tear(){
        Utils.fecharDriver();
    }
}
