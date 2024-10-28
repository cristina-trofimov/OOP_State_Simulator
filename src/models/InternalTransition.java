package models;

public class InternalTransition extends Transition {
    public InternalTransition(State source, Event event, Guard guard, Action action) {
        super(source, source, event, guard, action);
    }
}
