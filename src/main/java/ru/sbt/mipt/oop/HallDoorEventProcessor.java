package ru.sbt.mipt.oop;

public class HallDoorEventProcessor {
    SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleHallDoor(Room room) {
        if (room.getName().equals("hall")) {
            LightEventProcessor lightEventProcessor = new LightEventProcessor(smartHome);
            lightEventProcessor.setOffAllLight(smartHome);
        }
    }
}
