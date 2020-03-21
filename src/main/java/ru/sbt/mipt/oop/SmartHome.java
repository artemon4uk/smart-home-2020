package ru.sbt.mipt.oop;

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
//        this.signalization = new Signalization();
    }

    public SmartHome(Collection<Room> rooms, Signalization signalization) {
        this.rooms = rooms;
        this.signalization = signalization;
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
