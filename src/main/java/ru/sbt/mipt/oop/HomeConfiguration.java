package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.remoteControl.*;
import ru.sbt.mipt.oop.signalization.Signalization;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class HomeConfiguration {
    @Bean
    SmartHome smartHome() throws IOException {
        return new SmartHome(Reader.getSmartHome().getRooms(), new Signalization());
    }

    @Bean
    Sender sender() {
        return new Sender();
    }

    @Bean
    Map<String, SensorEventType> stringToType() {
        return Map.of(
                "LightIsOn", SensorEventType.LIGHT_ON,
                "LightIsOff", SensorEventType.LIGHT_OFF,
                "DoorIsOpen", SensorEventType.DOOR_OPEN,
                "DoorIsClosed", SensorEventType.DOOR_CLOSED
        );
    }

    @Bean
    String codeForActivateSignalization() {
        return "default";
    }

    @Bean
    EventHandler doorEventProcessor(SmartHome smartHome) {
        return new DoorEventProcessor(smartHome);
    }

    @Bean
    EventHandler lightEventProcessor(SmartHome smartHome) {
        return new LightEventProcessor(smartHome);
    }

    @Bean
    EventHandler hallDoorEventProcessor(SmartHome smartHome) {
        return new HallDoorEventProcessor(smartHome);
    }

    @Bean
    EventHandler signalizationEventProcessor(SmartHome smartHome) {
        return new SignalizationEventProcessor(smartHome);
    }

    @Bean
    List<EventHandler> eventHandlers(SmartHome smartHome) {
        return Arrays.asList(doorEventProcessor(smartHome),
                lightEventProcessor(smartHome),
                hallDoorEventProcessor(smartHome),
                signalizationEventProcessor(smartHome));
    }

    @Bean
    SignalizationDecorator signalizationDecorator(SmartHome smartHome, List<EventHandler> eventHandlers) {
        return new SignalizationDecorator(smartHome, eventHandlers, sender());
    }

    @Bean
    SensorEventsManagerAdapter sensorEventsManagerAdapter(EventHandler eventHandler) {
        return new SensorEventsManagerAdapter(eventHandler, stringToType());
    }

    @Bean
    CloseHallDoorCommand closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    SetActiveStateCommand setActiveStateCommand(SmartHome smartHome) {
        return new SetActiveStateCommand(smartHome, codeForActivateSignalization());
    }

    @Bean
    SetAlarmStateCommand setAlarmStateCommand(SmartHome smartHome) {
        return new SetAlarmStateCommand(smartHome);
    }

    @Bean
    SetOffAllLightsCommand setOffAllLightsCommand(SmartHome smartHome) {
        return new SetOffAllLightsCommand(smartHome);
    }

    @Bean
    SetOnAllLightsCommand setOnAllLightsCommand(SmartHome smartHome) {
        return new SetOnAllLightsCommand(smartHome);
    }

    @Bean
    SetOnHallLightCommand setOnHallLightCommand(SmartHome smartHome) {
        return new SetOnHallLightCommand(smartHome);
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(SmartHome smartHome) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl(smartHome), "id1");
        return remoteControlRegistry;
    }

    @Bean
    SmartRemoteControl remoteControl(SmartHome smartHome) {
        return new SmartRemoteControl(Map.of("id1", Map.of(
                "A", closeHallDoorCommand(smartHome),
                "B", setActiveStateCommand(smartHome),
                "C", setAlarmStateCommand(smartHome),
                "1", setOffAllLightsCommand(smartHome),
                "2", setOnAllLightsCommand(smartHome),
                "3", setOnHallLightCommand(smartHome)
        )));
    }

    @Bean
    SensorEventsManager sensorEventsManager(SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(
                sensorEventsManagerAdapter(
                        signalizationDecorator(smartHome, eventHandlers(smartHome))
                )
        );
        return sensorEventsManager;
    }
}
