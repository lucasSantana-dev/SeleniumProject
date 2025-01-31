package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/bdd.feature", // Caminho dos arquivos .feature
        glue = "steps",           // Pacote com as definições dos steps
        plugin = {                               // Plugins opcionais para saída
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"// Gera relatório HTML
        },
        monochrome = true                     // Deixa a saída mais limpa
)
public class TestRunner {
}