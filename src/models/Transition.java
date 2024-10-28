package models;

public class Transition {

    protected State source;
    protected State target;
    protected Event event;
    protected Guard guard;
    protected Action action;

    public Transition(State source, State target, Event event, Guard guard, Action action) {
        this.source = source;
        this.target = target;
        this.event = event;
        this.guard = guard;
        this.action = action;
    }

    public State getSource() {
        return source;
    }

    public void setSource(State source) {
        this.source = source;
    }

    public State getTarget() {
        return target;
    }

    public void setTarget(State target) {
        this.target = target;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Guard getGuard() {
        return guard;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String toString() {
        return source.getName() + " -> " + target.getName();
    }
}
