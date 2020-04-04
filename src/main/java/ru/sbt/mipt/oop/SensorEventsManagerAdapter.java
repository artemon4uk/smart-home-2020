package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.Map;

public class SensorEventsManagerAdapter implements com.coolcompany.smarthome.events.EventHandler{

    private final EventHandler eventHandler;
    private final Map<String, SensorEventType> stringToType;

    public SensorEventsManagerAdapter(EventHandler eventHandler, Map<String, SensorEventType> stringToType) {
        this.eventHandler = eventHandler;
        this.stringToType = stringToType;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType sensorEventType = stringToType.get(event.getEventType());
        if (sensorEventType != null) {
            SensorEvent sensorEvent = new SensorEvent(sensorEventType, event.getObjectId());
            eventHandler.handle(sensorEvent);
        }
    }
}
