package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import utils.Utils;

public class SummaryPage {

    //Elements path
    String buttonProceedToCheckout = "(//span[contains(.,'Proceed to checkout')])[2]";
    String unitPrice = "//li[@class='price']";

    public void avancarEtapa(){
        Utils.clickButton(buttonProceedToCheckout);
    }

    public void validateInfos(String productPrice) {
        Utils.esperarElemento(Utils.tempo, By.xpath(unitPrice));
        String unitPriceText = Utils.getElement(By.xpath(unitPrice)).getText();
        Assert.assertEquals(productPrice, unitPriceText);
    }

}
