package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.List;

public class DressesPage {

    String productImage = "(//img[@alt='Printed Dress'])[2]";
    String productDescription = "short_description_content";
    String sizeSelect = "group_1";
    String ulColor = "color_to_pick_list";
    String colorItem = "//ul[@id='color_to_pick_list']/li";
    String buttonAddToCart = "//button[@class='exclusive']";
    String statusFlag = "availability_statut";
    String buttonProceedToCheckout = "//a[@class='btn btn-default button button-medium']";

    public void validarTitulo() {
        Utils.validarTitulo("Dresses - My Shop");
    }

    public void adicionarProdutoNoCarrinho(){
        Utils.esperarElemento(Utils.tempo, By.xpath(productImage));
        Utils.clickButton(productImage);
        Utils.esperarElemento(Utils.tempo, By.id(productDescription));

        WebElement selectSize = Utils.getElement(By.id(sizeSelect));
        Select select = new Select(selectSize);

        select.selectByVisibleText("M");

        WebElement ul = Utils.getElement(By.id(ulColor));
        List<WebElement> colors = ul.findElements(By.xpath(colorItem));
        WebElement statusProduto = Utils.getElement(By.id(statusFlag));

        //selecionar cor até o item ficar disponível
        for(WebElement itemATual : colors) {
            itemATual.click();

            if (statusProduto.getText().matches("In stock")) break;
        }
        Utils.esperarElementoClicavel(Utils.tempo, By.xpath(buttonAddToCart));
        Utils.clickButton(buttonAddToCart);
        Utils.esperarElemento(Utils.tempo,By.xpath(buttonProceedToCheckout));
        Utils.clickButton(buttonProceedToCheckout);
    }
}
