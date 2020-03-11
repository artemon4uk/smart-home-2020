package ru.sbt.mipt.oop;

import java.util.ArrayList;

public class Iterator implements IteratorInterface {
    private SmartHome smartHome;
    private HomeObject currentObject;
    private int currentRoomPosition;
    private int currentLightPosition;
    private int currentDoorPosition;

    public Iterator(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.currentRoomPosition = 0;
        this.currentDoorPosition = 0;
        this.currentLightPosition = 0;
    }

    public boolean hasMoreDoors(Room room) {
        return currentDoorPosition != room.getDoors().size() - 1;
    }

    public boolean hasMoreLights(Room room) {
        return currentLightPosition != room.getLights().size() - 1;
    }

    @Override
    public boolean hasMore() {
        ArrayList<Room> rooms = (ArrayList<Room>) smartHome.getRooms();
        if (currentRoomPosition == rooms.size() - 1) {
            Room room = rooms.get(currentRoomPosition);
            return hasMoreDoors(room) || hasMoreLights(room);
        }
        return true;
    }

    public Light getNextLight() {
        ArrayList<Room> rooms = (ArrayList<Room>) smartHome.getRooms();
        if (hasMoreLights(rooms.get(currentRoomPosition))) {
            currentLightPosition++;
            ArrayList<Light> lights = (ArrayList<Light>) rooms.get(currentRoomPosition).getLights();
            return lights.get(currentLightPosition);
        }
        return null;
    }

    public Door getNextDoor() {
        ArrayList<Room> rooms = (ArrayList<Room>) smartHome.getRooms();
        if (hasMoreDoors(rooms.get(currentRoomPosition))) {
            currentDoorPosition++;
            ArrayList<Door> doors = (ArrayList<Door>) rooms.get(currentRoomPosition).getDoors();
            return doors.get(currentLightPosition);
        }
        return null;
    }

    @Override
    public HomeObject getNext() {
        if (hasMore()) {
            ArrayList<Room> rooms = (ArrayList<Room>) smartHome.getRooms();

            if (hasMoreLights(rooms.get(currentRoomPosition))) {
                return getNextLight();
            } else {
                if (hasMoreDoors(rooms.get(currentRoomPosition))) {
                    return getNextDoor();
                } else {
                    if (currentRoomPosition != rooms.size() - 1) {
                        currentRoomPosition++;
                        currentLightPosition = 0;
                        currentDoorPosition = 0;
                        return getNext();
                    }
                }
            }
        }
        return null;
    }
}
