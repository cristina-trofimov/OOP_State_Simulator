package models;

import utils.ConsoleColours;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation {

    private ArrayList<State> states;
    private ArrayList<Transition> transitions;

    public Simulation(ArrayList<State> states, ArrayList<Transition> transitions) {
        this.states = states;
        this.transitions = transitions;
    }

    public void run( ) {
        State initialState = getInitialState();
        State currentState = initialState;


        System.out.println(ConsoleColours.CYAN + "=== State Machine Execution ===" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.GREEN + "Initial State: " + initialState.getName() + ConsoleColours.RESET);

        int step = 1;
        while (!currentState.isFinal()) {
            for (Transition transition : transitions) {
                if (transition.getSource() == currentState) {
                    System.out.printf(ConsoleColours.YELLOW + "Step %d:" + ConsoleColours.RESET + "\n", step);
                    System.out.printf("  %-20s -> %-20s\n", currentState.getName(), transition.getTarget().getName());
                    System.out.printf("  Event: %-20s\n\n", transition.getEvent().getName());

                    currentState = transition.getTarget();
                    step++;
                    break;
                }
            }
        }

        System.out.println(ConsoleColours.GREEN + "Final State: " + currentState.getName() + ConsoleColours.RESET);
        System.out.println(ConsoleColours.CYAN + "=== Execution Complete ===" + ConsoleColours.RESET);
    }

    public String toString() {
        String response = "";
        response += "States: \n";

        for (State state : states) {
            response += state + "\n";
        }
        response += "Transitions: \n";
        for (Transition transition : transitions) {
            response += transition + "\n";
        }

        return response;
    }

    public State getInitialState() {
        for (State state : states) {
            if (state.isInitial() && state.getSuperState() == null) {
                return state;
            }
        }
        return null;
    }


}
