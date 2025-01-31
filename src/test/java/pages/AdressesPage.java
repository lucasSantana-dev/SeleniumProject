package pages;

import utils.Utils;

public class AdressesPage {

    //Elements path
    String buttonProceedToCheckout = "//button[@name='processAddress']";

    public void avancarEtapa(){
        Utils.clickButton(buttonProceedToCheckout);
    }
}
