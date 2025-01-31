package pages;

import utils.Utils;

public class SummaryPage {

    //Elements path
    String buttonProceedToCheckout = "(//span[contains(.,'Proceed to checkout')])[2]";

    public void avancarEtapa(){
        Utils.clickButton(buttonProceedToCheckout);
    }

}
