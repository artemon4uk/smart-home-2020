package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessorTest {
    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");
    private Door door2 = new Door(true, "2");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);
    private Light light2 = new Light("2", false);

    SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "bedroom"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "kitchen"),
            new Room(Arrays.asList(light2), Arrays.asList(door2), "hall")
    ));

    private EventHandler doorEventHandler = new DoorEventProcessor(smartHome);

    @Test
    public void handleDoorClosedEvent() {
        doorEventHandler.handle(new SensorEvent(DOOR_CLOSED, "0"));
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertFalse(door0.isOpen());
        assertFalse(door1.isOpen());
        assertTrue(door2.isOpen());
    }

    @Test
    public void handleDoorOpenedEvent() {
        doorEventHandler.handle(new SensorEvent(DOOR_OPEN, "1"));
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertTrue(door0.isOpen());
        assertTrue(door1.isOpen());
        assertTrue(door2.isOpen());
    }

    @Test
    public void handleDoorEventWhenThereIsNoID() {
        doorEventHandler.handle(new SensorEvent(DOOR_CLOSED, "10"));
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertTrue(door0.isOpen());
        assertFalse(door1.isOpen());
        assertTrue(door2.isOpen());
    }
}


