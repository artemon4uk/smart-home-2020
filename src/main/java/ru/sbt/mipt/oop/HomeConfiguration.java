package ru.sbt.mipt.oop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.signalization.Signalization;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class HomeConfiguration {
    @Bean
    SmartHome smartHome() throws IOException {
        return new SmartHome(Reader.getSmartHome().getRooms(), new Signalization());
    }

    @Bean
    SmartHomeHandler smartHomeHandler(SmartHome smartHome) {
        List<EventHandler> eventHandlers = Arrays.asList(new DoorEventProcessor(smartHome),
                new LightEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome),
                new SignalizationEventProcessor(smartHome));

        SignalizationDecorator signalizationDecorator = new SignalizationDecorator(smartHome, eventHandlers, new Sender());
        return new SmartHomeHandler(signalizationDecorator, new GeneratorEvent());
    }
}
