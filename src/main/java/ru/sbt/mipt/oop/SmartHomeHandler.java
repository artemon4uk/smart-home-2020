package ru.sbt.mipt.oop;

public class SmartHomeHandler {

    public static void startHandle(SmartHome smartHome, SensorEvent event) {
        while (event != null) {
            smartHome.startProcess(event);
            event = GeneratorEvent.getNextSensorEvent();
        }
    }
}
