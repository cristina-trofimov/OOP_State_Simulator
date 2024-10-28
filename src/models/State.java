package models;

import utils.ConsoleColours;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class State {

    private String name;
    private String alias;
    private boolean isInitial;
    private boolean isFinal;
    private State superState;
    private List<State> subStates;
    private Entry onEntryBehavior;
    private Do doBehavior;
    private Exit onExitBehavior;
    private ArrayList<EntryPseudoState> entryPseudoStates;
    private ArrayList<ExitPseudoState> exitPseudoStates;
    private ArrayList<Transition> transitions;


    public State(String name) {
        this.name = name;
        this.alias = name;
        this.isInitial = false;
        this.isFinal = false;
        this.subStates = new ArrayList<>();
        this.transitions = new ArrayList<>();
        this.onEntryBehavior = null;
        this.doBehavior = null;
        this.onExitBehavior = null;
        this.entryPseudoStates = new ArrayList<>();
        this.exitPseudoStates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public void setInitial(boolean initial) {
        isInitial = initial;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public List<State> getSubStates() {
        return subStates;
    }

    public void setSubStates(List<State> subStates) {
        this.subStates = subStates;
    }

    public Entry getOnEntryBehavior() {
        return onEntryBehavior;
    }

    public void setOnEntryBehavior(Entry onEntryBehavior) {
        this.onEntryBehavior = onEntryBehavior;
    }

    public Do getDoBehavior() {
        return doBehavior;
    }

    public void setDoBehavior(Do doBehavior) {
        this.doBehavior = doBehavior;
    }

    public Exit getOnExitBehavior() {
        return onExitBehavior;
    }

    public void setOnExitBehavior(Exit onExitBehavior) {
        this.onExitBehavior = onExitBehavior;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    public State getSuperState() {
        return superState;
    }

    public void setSuperState(State superState) {
        this.superState = superState;
    }

    public ArrayList<EntryPseudoState> getEntryPseudoStates() {
        return entryPseudoStates;
    }

    public void setEntryPseudoStates(ArrayList<EntryPseudoState> entryPseudoStates) {
        this.entryPseudoStates = entryPseudoStates;
    }

    public ArrayList<ExitPseudoState> getExitPseudoStates() {
        return exitPseudoStates;
    }

    public void setExitPseudoStates(ArrayList<ExitPseudoState> exitPseudoStates) {
        this.exitPseudoStates = exitPseudoStates;
    }

    public void addEntryPseudoState(EntryPseudoState entryPseudoState) {
        this.entryPseudoStates.add(entryPseudoState);
    }

    public void addExitPseudoState(ExitPseudoState exitPseudoState) {
        this.exitPseudoStates.add(exitPseudoState);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(ConsoleColours.BLUE).append("State: ").append(ConsoleColours.RESET)
                .append(name).append(" (Alias: ").append(alias).append(")\n");

        sb.append(ConsoleColours.PURPLE).append("Type: ").append(ConsoleColours.RESET)
                .append(isInitial ? "Initial" : isFinal ? "Final" : "Normal").append("\n");

        if (superState != null) {
            sb.append(ConsoleColours.PURPLE).append("Super State: ").append(ConsoleColours.RESET)
                    .append(superState.getName()).append("\n");
        }

        if (!subStates.isEmpty()) {
            sb.append(ConsoleColours.PURPLE).append("Sub States: ").append(ConsoleColours.RESET)
                    .append(subStates.stream().map(State::getName).collect(Collectors.joining(", ")))
                    .append("\n");
        }

        sb.append(ConsoleColours.YELLOW).append("Behaviors:").append(ConsoleColours.RESET).append("\n");
        sb.append("  Entry: ").append(onEntryBehavior != null ? onEntryBehavior : "None").append("\n");
        sb.append("  Do: ").append(doBehavior != null ? doBehavior : "None").append("\n");
        sb.append("  Exit: ").append(onExitBehavior != null ? onExitBehavior : "None").append("\n");

        if (!entryPseudoStates.isEmpty()) {
            sb.append(ConsoleColours.YELLOW).append("Entry Pseudo States: ").append(ConsoleColours.RESET)
                    .append(entryPseudoStates.size()).append("\n");
        }

        if (!exitPseudoStates.isEmpty()) {
            sb.append(ConsoleColours.YELLOW).append("Exit Pseudo States: ").append(ConsoleColours.RESET)
                    .append(exitPseudoStates.size()).append("\n");
        }

        sb.append(ConsoleColours.YELLOW).append("Transitions: ").append(ConsoleColours.RESET)
                .append(transitions.size()).append("\n");
        for (Transition t : transitions) {
            sb.append("  ").append(t).append("\n");
        }

        return sb.toString();
    }
}
