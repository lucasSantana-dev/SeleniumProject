package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

public class ShippingPage {

    //Elements path
    String buttonCheckbox = "//input[@id='cgv']";
    String buttonAvancarEtapa = "//button[@name='processCarrier']";

    public void avancarEtapa(){
        Utils.clickButton(buttonAvancarEtapa);
    }

    public void clicarCheckBox(){
        WebElement checkbox = Utils.getElement(By.xpath(buttonCheckbox));
        if(!checkbox.isSelected()){
            checkbox.click();
        }
    }
}
