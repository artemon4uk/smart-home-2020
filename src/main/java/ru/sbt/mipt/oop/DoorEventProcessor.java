package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventHandler {
    SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event, Room room, HomeObject door) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                door.setState(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setState(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(smartHome);
                hallDoorEventProcessor.handleHallDoor(room);
            }
        }
    }
}
