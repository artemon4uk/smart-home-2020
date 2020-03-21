package ru.sbt.mipt.oop;

import java.util.Collection;

public class SmartHomeHandler {
    private Collection<EventHandler> eventHandlers;

    public SmartHomeHandler(Collection<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public void startHandle() {
        SensorEvent event = GeneratorEvent.getNextSensorEvent();
        while (event != null) {
            startProcess(event);
            event = GeneratorEvent.getNextSensorEvent();
        }
    }

    public void startProcess(SensorEvent event) {
        System.out.println("Got event: " + event);
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handle(event);
        }
    }
}
