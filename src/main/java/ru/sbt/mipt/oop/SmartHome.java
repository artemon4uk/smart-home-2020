package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private static void handleDoorEvents(SmartHome smartHome, SensorEvent event) {
        if (isDoor(event)) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    DoorEventProcessor doorEventProcessor = new DoorEventProcessor(smartHome);
                    doorEventProcessor.handle(event, room, door);
                }
            }
        }
    }

    static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static void handleLightEvents(SmartHome smartHome, SensorEvent event) {
        if (isLight(event)) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    LightEventProcessor lightEventProcessor = new LightEventProcessor(smartHome);
                    lightEventProcessor.handle(event, room, light);
                }
            }
        }
    }

    public static SmartHome getSmartHome() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
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