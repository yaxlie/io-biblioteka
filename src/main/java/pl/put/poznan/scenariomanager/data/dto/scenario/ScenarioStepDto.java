package pl.put.poznan.scenariomanager.data.dto.scenario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ScenarioStepDto implements Serializable {

    private String text;
    private ScenarioStepTypeDto type;

    @JsonCreator
    public ScenarioStepDto(@JsonProperty(value = "text", required = true) String text,
                           @JsonProperty(value = "type", required = true) ScenarioStepTypeDto type) {

        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ScenarioStepTypeDto getType() {
        return type;
    }

    public void setType(ScenarioStepTypeDto type) {
        this.type = type;
    }
}
