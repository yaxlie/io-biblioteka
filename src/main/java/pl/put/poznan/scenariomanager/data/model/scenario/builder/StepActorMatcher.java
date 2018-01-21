package pl.put.poznan.scenariomanager.data.model.scenario.builder;

import java.util.ArrayList;
import java.util.List;

public class StepActorMatcher {



    private List<String> actors;

    public StepActorMatcher() {
        actors = null;
    }

    public StepActorMatcher(List<String> actors) {
        this.actors = new ArrayList<>(actors);
    }

    public String matchActor(String step) {

        String match = null;

        if (actors != null) {

            for (String actor : actors) {

                if (actorMatches(actor, step) && (match == null || match.length() < actor.length()))
                    match = actor;
            }

        } else {
            throw new IllegalStateException("Actors has not been set");
        }

        if (match == null)
            throw new IllegalArgumentException("Given actor in step \"" + step + "\" does not exists");

        return match;
    }

    private boolean actorMatches(String actor, String step) {

        actor = actor.toLowerCase();
        step = step.toLowerCase();

        if (step.startsWith(actor)) {

            // Step starts with actor, but there are other characters right after the actor, instead of whitespace character
            if (step.length() > actor.length() && !Character.isWhitespace(step.charAt(actor.length())))
                return false;

            return true;
        }

        return false;
    }
}
