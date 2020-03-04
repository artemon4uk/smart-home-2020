package ru.sbt.mipt.oop;

public class Light implements HomeObject {
    private final String id;
    private boolean isOn;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setState(boolean on) {
        isOn = on;
    }
}
