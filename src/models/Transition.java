package models;

public class Transition {

    private State source;
    private State target;
    private String event;
    private String guard;
    private String action;

    public Transition(State source, State target, String event, String guard, String action) {
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
