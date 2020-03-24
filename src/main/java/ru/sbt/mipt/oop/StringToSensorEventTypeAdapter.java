package ru.sbt.mipt.oop;

import java.util.HashMap;
import java.util.Map;

public class StringToSensorEventTypeAdapter {

    private Map<String, SensorEventType> stringToTypeAdapter = new HashMap<String, SensorEventType>();

    public StringToSensorEventTypeAdapter() {
        stringToTypeAdapter.put("LightIsOn", SensorEventType.LIGHT_ON);
        stringToTypeAdapter.put("LightIsOff", SensorEventType.LIGHT_OFF);
        stringToTypeAdapter.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        stringToTypeAdapter.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        stringToTypeAdapter.put("DoorIsLocked", null);
        stringToTypeAdapter.put("DoorIsUnlocked", null);
    }

    public SensorEventType get(String key) {
        return stringToTypeAdapter.get(key);
    }
}
