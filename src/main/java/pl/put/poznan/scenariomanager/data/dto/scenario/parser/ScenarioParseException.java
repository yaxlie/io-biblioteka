package pl.put.poznan.scenariomanager.data.dto.scenario.parser;

public class ScenarioParseException extends Exception {

    public ScenarioParseException(String message) {
        super(message);
    }

    public ScenarioParseException(String message, Exception inner) {
        super(message, inner);
    }
}
