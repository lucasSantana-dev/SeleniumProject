package pages;

import org.openqa.selenium.By;
import utils.Utils;

public class MyAccountPage {

    //elements path
    String myAccountTitle = "//h1[text()='My account']";
    String logo = "//img[contains(@alt,'My Shop')]";

    public void validarPaginaMyAccount() {
            Utils.esperarElemento(Utils.tempo, By.xpath(myAccountTitle));
            Utils.validarTitulo("My account - My Shop");
            Utils.clickButton(logo);
        }
}
