package models;


import models.enums.EventTypesEnum;

public class Event {

    private EventTypesEnum type;
    private String name;

    public Event(EventTypesEnum type, String name) {
        this.type = type;
        this.name = name;
    }

    public EventTypesEnum getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
