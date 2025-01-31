package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.rules.TestWatcher;

public class AllureListener extends TestWatcher {

    // Método para adicionar uma captura de screenshot ou outro tipo de anexo
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] captureScreenshot() {
        // Captura o screenshot (você pode usar uma biblioteca como Selenium WebDriver)
        // Aqui você retornaria os bytes do screenshot
        return new byte[0]; // Exemplo fictício
    }

    // Método para adicionar um passo customizado
    public static void addStep(String stepDescription) {
        Allure.step(stepDescription);
    }
}
