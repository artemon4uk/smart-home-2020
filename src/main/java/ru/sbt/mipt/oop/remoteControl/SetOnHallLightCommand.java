package ru.sbt.mipt.oop.remoteControl;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class SetOnHallLightCommand implements Command {
    private final SmartHome smartHome;

    public SetOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeObject -> {
            if (homeObject instanceof Room) {
                Room room = (Room) homeObject;

                if (room.getName().equals("hall")) {
                    room.execute(roomObject -> {
                        if (roomObject instanceof Light) {
                            Light light = (Light) roomObject;
                            light.setState(true);
                        }
                    });
                }

            }
        });
    }
}
