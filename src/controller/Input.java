package controller;

import org.jpl7.*;

import models.*;
import models.enums.EventTypesEnum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Input {

    private final File file;
    private HashMap<String, State> states;
    private HashMap<Event, Transition> transitions;

    public Input(File file) {
        this.file = file;
        this.states = new HashMap<>();
        this.transitions = new HashMap<>();
    }

    private void parseFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                line = line.strip();
                String command = getKeyword(line);
                String content = extractBetweenParentheses(line, command).strip();
                switch (command) {
                    case "state":
                        parseState(content);
                        break;
                    case "alias":
                        parseAlias(content);
                        break;
                    case "initial":
                        parseInitial(content);
                        break;
                    case "final":
                        parseFinal(content);
                        break;
                    case "transition":
                        parseTransition(content);
                        break;
                    case "entry_pseudostate":
                        parseEntryPseudoState(content);
                        break;
                    case "exit_pseudostate":
                        parseExitPseudoState(content);
                        break;
                    default:
                        // Handle unknown command or empty line
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKeyword(String line) {
        int openParenIndex = line.indexOf('(');
        if (openParenIndex != -1) {
            return line.substring(0, openParenIndex);
        }
        return "";
    }

    private void parseState(String content) {
        for (State state : states.values()) {
            if (state.getName().equals(content)) {
                return;
            }
        }
        State state = new State(content);
        states.put(content, state);
    }

    private void parseAlias(String content) {
        String[] stateAndAlias = content.split(",");

        State state = states.get(stateAndAlias[0]);
        state.setAlias(stateAndAlias[1]);
    }

    private void parseInitial(String content) {
        State state = states.get(content);
        state.setInitial(true);
    }

    private void parseFinal(String content) {
        State state = states.get(content);
        state.setFinal(true);
    }

    private void parseTransition(String content) {
        ArrayList<String> transitionContents = extractTransitionContents(content);

        State source = states.get(transitionContents.get(0));
        State target = states.get(transitionContents.get(1));
        Event event = parseEvent(transitionContents.get(2));
        Guard guard = null;
        Action action = null;
        if (!transitionContents.get(3).equals("null")) {
            guard = new Guard(transitionContents.get(3));
        }
        if (!transitionContents.get(4).equals("null") || !transitionContents.get(4).equals("nil)")) {
            action = parseAction(transitionContents.get(4));
        }

        Transition transition = new Transition(source, target, event, guard, action);
        transitions.put(event, transition);

    }

    private Event parseEvent(String event) {
        String eventContent = extractBetweenParentheses(event, "event");
        String[] eventContents = eventContent.split(",");

        return new Event(EventTypesEnum.valueOf(eventContents[0].toUpperCase()), eventContents[1]);
    }

    private Action parseAction(String action) {
        String actionContent = extractBetweenParentheses(action, "action");
        String[] actionContents = actionContent.split(",");

        return new Action(actionContents[0], actionContents[1]);
    }

    private void parseEntryPseudoState(String content) {
        String[] entryPseudoStateAndState = content.split(",");
        EntryPseudoState entryPseudoState = new EntryPseudoState(entryPseudoStateAndState[0]);
        states.put(entryPseudoState.getName(), entryPseudoState);
        for (State state : states.values()) {
            if (state.getName().equals(entryPseudoStateAndState[1])) {
                state.addEntryPseudoState(entryPseudoState);
                return;
            }
        }
        State state = new State(entryPseudoStateAndState[0]);
        state.addEntryPseudoState(entryPseudoState);
        states.put(state.getName(), state);
    }

    private void parseExitPseudoState(String content) {
        String[] exitPseudoStateAndState = content.split(",");
        ExitPseudoState exitPseudoState = new ExitPseudoState(exitPseudoStateAndState[0]);
        states.put(exitPseudoState.getName(), exitPseudoState);
        for (State state : states.values()) {
            if (state.getName().equals(exitPseudoStateAndState[1])) {
                state.addExitPseudoState(exitPseudoState);
                return;
            }
        }
        State state = new State(exitPseudoStateAndState[0]);
        state.addExitPseudoState(exitPseudoState);
        states.put(state.getName(), state);
    }

    private ArrayList<String> extractTransitionContents(String content) {
        ArrayList<String> transitionContent = new ArrayList<>();

        ArrayList<String> temp = new ArrayList<>(Arrays.asList(content.split(",")));

        for (int i = 0; i < temp.size(); i++) {
            String finalAttribute = temp.get(i).strip();
            if (temp.get(i).contains("(")) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (temp.get(j).contains(")")) {
                        String word = temp.get(j).strip();
                        finalAttribute += "," + temp.get(j).strip();
                        transitionContent.add(finalAttribute);
                        i = j;
                        break;
                    } else {
                        finalAttribute += "," + temp.get(j).strip();
                    }
                    i = j + 1;
                }

            } else {
                transitionContent.add(finalAttribute);
            }
        }
        return transitionContent;


    }

    private String extractBetweenParentheses(String input, String keyword) {
        int lengthOfKeyword = keyword.length();
        int indexClosingParen;
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == ')') {
                indexClosingParen = i;
                return input.substring(lengthOfKeyword + 1, indexClosingParen).strip();
            }
        }
        return "";
    }

    public void run() {
        parseFile();
        for (Transition transition : transitions.values()) {
            states.get(transition.getSource().getName()).getTransitions().add(transition);
        }
    }

    public HashMap<Event, Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(HashMap<Event, Transition> transitions) {
        this.transitions = transitions;
    }

    public File getFile() {
        return file;
    }

    public HashMap<String, State> getStates() {
        return states;
    }

    public void setStates(HashMap<String, State> states) {
        this.states = states;
    }

    public void runProlog() {
        Query consultQuery = new Query("consult", new Term[]{new Atom("src/resources/automaton.pl")});
        consultQuery.hasSolution();
        consultQuery.close();
        Query query = new Query("run");
        query.hasSolution();
        query.close();
    }
}
