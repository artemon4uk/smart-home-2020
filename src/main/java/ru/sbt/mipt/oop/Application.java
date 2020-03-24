package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = Reader.getSmartHome();
        SmartHomeHandler smartHomeHandler = new SmartHomeHandler(Arrays.asList(
                new DoorEventProcessor(smartHome),
                new LightEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome)
        ));

        smartHomeHandler.startHandle();
    }
}
