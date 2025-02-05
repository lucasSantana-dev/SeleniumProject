package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.List;

public class DressesPage {

    public static String productPrice;

    String productImage = "(//img[@alt='Printed Dress'])[2]";
    String productDescription = "short_description_content";
    String sizeSelect = "group_1";
    String ulColor = "color_to_pick_list";
    String colorItem = "//ul[@id='color_to_pick_list']/li";
    String buttonAddToCart = "//span[contains(.,'Add to cart')]";
    String statusFlag = "availability_statut";
    String buttonProceedToCheckout = "//a[@class='btn btn-default button button-medium']";
    String productPriceSpan = "//span[@class='price']";
    String modalProductName = "//span[@class='product-name']";
    String modalProductPrice = "//span[@id='layer_cart_product_price']";
    String modalTotalProductsPrice = "//span[@class='ajax_block_products_total']";

    public void validarTitulo() { Utils.validarTitulo("Dresses - My Shop"); }

    public void selecionarItemAteFicarDisponivel(){
        WebElement ul = Utils.getElement(By.id(ulColor));
        List<WebElement> colors = ul.findElements(By.xpath(colorItem));
        WebElement statusProduto = Utils.getElement(By.id(statusFlag));

        for(WebElement itemATual : colors) {
            itemATual.click();

            if (statusProduto.getText().matches("In stock")) break;
        }
    }

    public void selecionarTamanho(){
        WebElement selectSize = Utils.getElement(By.id(sizeSelect));
        Select select = new Select(selectSize);

        select.selectByVisibleText("M");
    }

    public void adicionarProdutoNoCarrinho(){
        Utils.esperarElementoClicavel(Utils.tempo, By.xpath(productImage));
        Utils.clickButton(productImage);
        Utils.esperarElemento(Utils.tempo, By.id(productDescription));
        productPrice = getPrice();

        selecionarTamanho();
        selecionarItemAteFicarDisponivel();

        Utils.esperarElementoClicavel(Utils.tempo, By.xpath(buttonAddToCart));
        Utils.clickWithJS(buttonAddToCart);
        validateModalCart();
        Utils.clickButton(buttonProceedToCheckout);
    }

    public void validateModalCart(){
        Utils.esperarElemento(Utils.tempo,By.xpath(buttonProceedToCheckout));

        String actualModalProductName = Utils.getElement(By.xpath(modalProductName)).getText();
        String actualModalProductPrice = Utils.getElement(By.xpath(modalProductPrice)).getText();
        String actualModalTotalProductsPrice = Utils.getElement(By.xpath(modalTotalProductsPrice)).getText();

        Assert.assertEquals("Printed Dress", actualModalProductName);
        Assert.assertEquals(productPrice, actualModalProductPrice);
        Assert.assertEquals(productPrice, actualModalTotalProductsPrice);
    }

    public String getPrice(){
        Utils.esperarElemento(Utils.tempo, By.xpath(productPriceSpan));
        return Utils.getElement(By.xpath(productPriceSpan)).getText();
    }
}
