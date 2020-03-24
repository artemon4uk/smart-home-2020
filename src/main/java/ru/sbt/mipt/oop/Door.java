package ru.sbt.mipt.oop;

public class Door implements HomeObject {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setState(boolean on) {
        isOpen = on;
    }
}