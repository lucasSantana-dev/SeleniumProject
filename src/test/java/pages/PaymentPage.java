package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

public class PaymentPage {

    //Elements path
    String buttonBankwire = "//a[@class='bankwire']";
    String buttonConfirmOrder = "//button[@class='button btn btn-default button-medium']";
    String alertSuccess = "//p[@class='alert alert-success']";

    public void selecionarFormaDePagamento(){
        Utils.esperarElemento(Utils.tempo, By.xpath(buttonBankwire));
        Utils.clickButton(buttonBankwire);
        Utils.esperarElemento(Utils.tempo, By.xpath(buttonConfirmOrder));
        Utils.clickButton(buttonConfirmOrder);
        Utils.esperarElemento(Utils.tempo, By.xpath(alertSuccess));

        WebElement compraFeitaMsg = Utils.getElement(By.xpath(alertSuccess));
        Assert.assertEquals("Your order on My Shop is complete.", compraFeitaMsg.getText());
    }
}
