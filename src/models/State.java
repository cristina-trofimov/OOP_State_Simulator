package models;

import java.util.ArrayList;
import java.util.List;

public class State {

    private String name;
    private List<Transition> transitions;

    public State(String names) {
        this.name = name;
        this.transitions = new ArrayList<>();
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
