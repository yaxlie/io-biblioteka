package pl.put.poznan.scenariomanager.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.scenariomanager.data.model.scenario.visitor.impl.IndexScenarioVisitor;


@SpringBootApplication(scanBasePackages = {
        "pl.put.poznan.scenariomanager.rest",
        "pl.put.poznan.scenariomanager.service"
})
public class ScenarioManagerApplication {

    private static final Logger log = LoggerFactory.getLogger(ScenarioManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScenarioManagerApplication.class, args);

        log.info("Application started!!!");
        log.info("Listening at port 8099...");
    }
}
