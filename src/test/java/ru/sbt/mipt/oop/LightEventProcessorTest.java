package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventProcessorTest {
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

    private EventHandler lightEventHandler = new LightEventProcessor(smartHome);
    @Test
    public void handleLightClosedEvent() {
        lightEventHandler.handle(new SensorEvent(LIGHT_OFF, "1"));
        assertFalse(light0.isOn());
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());

        assertTrue(door0.isOpen());
        assertFalse(door1.isOpen());
        assertTrue(door2.isOpen());
    }

    @Test
    public void handleLightOpenedEvent() {
        lightEventHandler.handle(new SensorEvent(LIGHT_ON, "0"));
        assertTrue(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertTrue(door0.isOpen());
        assertFalse(door1.isOpen());
        assertTrue(door2.isOpen());
    }

    @Test
    public void handleLightEventWhenThereIsNoID() {
        lightEventHandler.handle(new SensorEvent(DOOR_CLOSED, "10"));
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertFalse(light2.isOn());

        assertTrue(door0.isOpen());
        assertFalse(door1.isOpen());
        assertTrue(door2.isOpen());
    }
}