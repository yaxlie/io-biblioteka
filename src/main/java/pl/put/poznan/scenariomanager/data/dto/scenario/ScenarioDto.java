package pl.put.poznan.scenariomanager.data.dto.scenario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.util.buf.StringUtils;
import pl.put.poznan.scenariomanager.data.model.scenario.Scenario;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ScenarioDto implements Serializable {

    private String title;
    private List<String> actors;
    private List<ScenarioStepDto> steps;

    @JsonCreator
    public ScenarioDto(@JsonProperty(value = "title", required = true) String title,
                       @JsonProperty(value = "actors") List<String> actors,
                       @JsonProperty(value = "steps", required = true) List<ScenarioStepDto> steps) {

        this.title = title;
        this.actors = actors;
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<ScenarioStepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<ScenarioStepDto> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Scenario\n");
        builder.append("\tTitle: " + title + "\n");
        builder.append("\tActors: " + StringUtils.join(actors, ',') + "\n");
        builder.append("\tSteps:\n");

        for (ScenarioStepDto step : steps)
            builder.append("\t\t" + step.getText() + "\n");

        return builder.toString();
    }
}
