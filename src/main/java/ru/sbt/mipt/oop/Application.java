package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = SmartHome.getSmartHome();
        SensorEvent event = GeneratorEvent.getNextSensorEvent();
        SmartHomeHandler.startHandle(smartHome, event);
    }
}
