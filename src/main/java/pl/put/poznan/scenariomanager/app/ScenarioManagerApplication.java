package pl.put.poznan.scenariomanager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.IndexScenarioVisitor;


@SpringBootApplication(scanBasePackages = {
        "pl.put.poznan.scenariomanager.rest",
        "pl.put.poznan.scenariomanager.service"
})
public class ScenarioManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenarioManagerApplication.class, args);
    }
}
