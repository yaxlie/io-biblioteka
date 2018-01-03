package pl.put.poznan.scenariomanager.data.dto.scenario;

import java.io.Serializable;

public class ScenarioStepDto implements Serializable {

    private String text;
    private ScenarioStepTypeDto type;

    public ScenarioStepDto(String text, ScenarioStepTypeDto type) {

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
