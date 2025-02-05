package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import pages.*;
import utils.Utils;

public class Test {

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    DressesPage dressesPage = new DressesPage();
    MyAccountPage myAccountPage = new MyAccountPage();
    AdressesPage adressesPage = new AdressesPage();
    SummaryPage summaryPage = new SummaryPage();
    ShippingPage shippingPage = new ShippingPage();
    PaymentPage paymentPage = new PaymentPage();

    String productPrice;

    @Before
    public void fazerLogin() {
        Allure.step("Abrindo a página de login");
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
        summaryPage.avancarEtapa();
        adressesPage.avancarEtapa();
        shippingPage.clicarCheckBox();
        shippingPage.avancarEtapa();
        paymentPage.selecionarFormaDePagamento();
    }

    @After
    public void tear(){
        Utils.fecharDriver();
    }
}
