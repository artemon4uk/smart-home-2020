package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventHandler {
    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private void handleDoors(SensorEvent event, boolean isOpen) {
        smartHome.execute(homeObject -> {
            if (homeObject instanceof Room) {
                Room room = (Room) homeObject;

                room.execute(roomObject -> {
                    if (roomObject instanceof Door) {
                        Door door = (Door) roomObject;
                        if (door.getId().equals(event.getObjectId())) {
                            door.setState(isOpen);
                            System.out.println("Door "
                                    + door.getId()
                                    + " in room "
                                    + room.getName()
                                    + " was "
                                    + (isOpen ? "opened" : "closed"));
                        }
                    }
                });
            }
        });
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == DOOR_OPEN) {
            handleDoors(event, true);
        }

        if (event.getType() == DOOR_CLOSED) {
            handleDoors(event, false);
        }
    }
}
