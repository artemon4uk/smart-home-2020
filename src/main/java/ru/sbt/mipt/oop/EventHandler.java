package ru.sbt.mipt.oop;

public interface EventHandler {
    void handle(SensorEvent event, Room room, HomeObject object);
}
