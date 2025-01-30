package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.time.Duration;

public class HomePage {

    Duration tempo = Duration.ofSeconds(5);

    //elements path
    String menuDressesLink = "(//a[@title='Dresses'])[2]";

    public void validarTituloHome() {
        Utils.esperarElemento(tempo, By.xpath(menuDressesLink));
        Utils.validarTitulo("My Shop");
    }

    public void acessarMenuDresses() {
        WebElement buttonDresses = Utils.getElement(By.xpath(menuDressesLink));
        buttonDresses.click();
    }
}
