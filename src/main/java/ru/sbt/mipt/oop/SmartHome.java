package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    private static boolean isDoor(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

    private static boolean isLight(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }

    private static void doorEvent(SmartHome smartHome, SensorEvent event, Room room, Door door) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                door.setState(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setState(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                if (room.getName().equals("hall")) {
                    setOffAllLight(smartHome);
                }
            }
        }
    }

    private static void lightEvent(SensorEvent event, Room room, Light light) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                light.setState(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setState(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }

    private static void setOffAllLight(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setState(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private static void handleDoorEvents(SmartHome smartHome, SensorEvent event) {
        if (isDoor(event)) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    doorEvent(smartHome, event, room, door);
                }
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static void handleLightEvents(SmartHome smartHome, SensorEvent event) {
        if (isLight(event)) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    lightEvent(event, room, light);
                }
            }
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void startProcess(SensorEvent event) {
        System.out.println("Got event: " + event);
        handleLightEvents(this, event);
        handleDoorEvents(this, event);
    }
}
