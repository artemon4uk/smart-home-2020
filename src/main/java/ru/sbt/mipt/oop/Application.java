package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = SmartHome.getSmartHome();
        SensorEvent event = GeneratorEvent.getNextSensorEvent();
        SmartHomeHandler.startHandle(smartHome, event);
    }
}
