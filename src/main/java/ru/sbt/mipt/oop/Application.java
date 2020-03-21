package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = Reader.getSmartHome();
        SmartHomeHandler smartHomeHandler = new SmartHomeHandler(Arrays.asList(
                new Decorator(smartHome, new DoorEventProcessor(smartHome)),
                new Decorator(smartHome, new LightEventProcessor(smartHome)),
                new Decorator(smartHome, new HallDoorEventProcessor(smartHome)),
                new Decorator(smartHome, new SignalizationEventProcessor(smartHome))
        ));

        smartHomeHandler.startHandle();
    }
}
