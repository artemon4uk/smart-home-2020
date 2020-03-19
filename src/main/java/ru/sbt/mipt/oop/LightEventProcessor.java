package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventHandler {
    private final SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private void handleLights(SensorEvent event, boolean isOn) {
        smartHome.execute(homeObject -> {
            if (homeObject instanceof Room) {
                Room room = (Room) homeObject;

                room.execute(roomObject -> {
                    if (roomObject instanceof Light) {
                        Light light = (Light) roomObject;
                        if (light.getId().equals(event.getObjectId())) {
                            light.setState(isOn);
                            System.out.println("Light "
                                    + light.getId()
                                    + " in room "
                                    + room.getName()
                                    + " was turned "
                                    + (isOn ? "on" : "off"));
                        }
                    }
                });
            }
        });
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == LIGHT_ON) {
            handleLights(event, true);
        }

        if (event.getType() == LIGHT_OFF) {
            handleLights(event, false);
        }
    }
}
