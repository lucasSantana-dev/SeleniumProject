package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;
import utils.Utils;
import java.time.Duration;
import java.util.List;

public class Test {

    private final Duration tempo = Duration.ofSeconds(5);
    HomePage homePage = new HomePage();

    @Before
    public void fazerLogin() {
        homePage.fazerLogin();
    }

    @Given("Estou acessando a home page")
    public void acessarHomePage() {
        Utils.esperarElemento(tempo, By.xpath("//img[@class='logo img-responsive']"));
        String tituloPagina = Utils.initDriver().getTitle();
        Assert.assertEquals("My Shop", tituloPagina);
    }

    @And("Acesso a aba Dresses")
    public void acessarAbaTshirts() {
        WebElement buttonTshirt = Utils.initDriver().findElement(By.xpath("(//a[@title='Dresses'])[2]"));
        buttonTshirt.click();
        Assert.assertEquals("Dresses - My Shop", Utils.initDriver().getTitle());
    }

    @And("Adiciono produto no carrinho")
    public void adicionarProdutoNoCarrinho(){
        Utils.esperarElemento(tempo, By.xpath("(//img[@alt='Printed Dress'])[2]"));
        Utils.initDriver().findElement(By.xpath("(//img[@alt='Printed Dress'])[2]")).click();
        Utils.esperarElemento(tempo, By.id("short_description_content"));
        WebElement selectSize = Utils.initDriver().findElement(By.id("group_1"));
        Select select = new Select(selectSize);
        select.selectByVisibleText("M");
        WebElement ulCor = Utils.initDriver().findElement(By.id("color_to_pick_list"));
        List<WebElement> cores = ulCor.findElements(By.xpath("//ul[@id='color_to_pick_list']/li"));
        WebElement statusProduto = Utils.initDriver().findElement(By.id("availability_statut"));

        //selecionar cor até o item ficar disponível
        for(WebElement itemATual : cores) {
            itemATual.click();

            if (statusProduto.getText().matches("In stock")) break;
        }
        Utils.esperarElementoClicavel(tempo, By.xpath("//button[@class='exclusive']"));
        Utils.initDriver().findElement(By.xpath("//button[@class='exclusive']")).click();
        Utils.esperarElemento(tempo,By.xpath("//a[@class='btn btn-default button button-medium']"));
        Utils.initDriver().findElement(By.xpath("//a[@class='btn btn-default button button-medium']")).click();
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
