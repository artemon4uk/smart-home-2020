package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.signalization.Signalization;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        Signalization signalization = new Signalization();
        Sender sender = new Sender();
        SmartHome smartHome = new SmartHome(Reader.getSmartHome().getRooms(), signalization);

        List<EventHandler> eventHandlers = Arrays.asList(new DoorEventProcessor(smartHome),
                                                         new LightEventProcessor(smartHome),
                                                         new HallDoorEventProcessor(smartHome),
                                                         new SignalizationEventProcessor(smartHome));

        SignalizationDecorator signalizationDecorator = new SignalizationDecorator(smartHome, eventHandlers, sender);
        SmartHomeHandler smartHomeHandler = new SmartHomeHandler(signalizationDecorator, new GeneratorEvent());

        smartHomeHandler.startHandle();
    }
}
