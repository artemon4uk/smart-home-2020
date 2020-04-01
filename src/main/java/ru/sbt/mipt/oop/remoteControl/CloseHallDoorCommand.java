package ru.sbt.mipt.oop.remoteControl;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class CloseHallDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeObject -> {
            if (homeObject instanceof Room) {
                Room room = (Room) homeObject;

                if (room.getName().equals("hall")) {
                    room.execute(roomObject -> {
                        if (roomObject instanceof Door) {
                            Door door = (Door) roomObject;
                            door.setState(false);
                        }
                    });
                }

            }
        });
    }
}
