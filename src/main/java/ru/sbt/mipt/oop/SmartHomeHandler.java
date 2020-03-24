package ru.sbt.mipt.oop;

public class SmartHomeHandler {
    private EventHandler eventHandler;
    private GeneratorEvent generatorEvent;

    public SmartHomeHandler(EventHandler eventHandler, GeneratorEvent generatorEvent) {
        this.eventHandler = eventHandler;
        this.generatorEvent = generatorEvent;
    }

    public void startHandle() {
        SensorEvent event = generatorEvent.getNextSensorEvent();
        while (event != null) {
            startProcess(event);
            event = generatorEvent.getNextSensorEvent();
        }
    }

    public void startProcess(SensorEvent event) {
        System.out.println("Got event: " + event);
        eventHandler.handle(event);
    }
}
