package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.signalization.Signalization;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {

    private Collection<Room> rooms;
    private Signalization signalization;


    public SmartHome() {
        rooms = new ArrayList<>();
        this.signalization = new Signalization();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        this.signalization = null;
    }

    public SmartHome(Collection<Room> rooms, Signalization signalization) {
        this.rooms = rooms;
        this.signalization = signalization;
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

    public Signalization getSignalization() {
        return signalization;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);

        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
