package ru.sbt.mipt.oop;

public class HallDoorEventProcessor implements EventHandler {
    private final SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED && isHallDoor(event)) {
            setOffAllLight();
        }
    }

    private boolean isHallDoor(SensorEvent event) {
        final boolean[] isHallDoor = {false};
        smartHome.execute(homeObject -> {
            if (homeObject instanceof Room) {
                Room room = (Room) homeObject;

                if (room.getName().equals("hall")) {
                    room.execute(roomObject -> {
                        if (roomObject instanceof Door) {
                            Door door = (Door) roomObject;

                            if (door.getId().equals(event.getObjectId())) {
                                isHallDoor[0] = true;
                                door.setState(false);
                            }
                        }
                    });
                }
            }
        });
        return isHallDoor[0];
    }

    private void setOffAllLight() {
        smartHome.execute(homeObject -> {
           if (homeObject instanceof Light) {
               Light light = (Light) homeObject;
               light.setState(false);

               Sender sender = new Sender();
               SensorCommand sensorCommand = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
               sender.sendCommand("Pretent we're sending command ", sensorCommand);
           }
        });
    }
}
