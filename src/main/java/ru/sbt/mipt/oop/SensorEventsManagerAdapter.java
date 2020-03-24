package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

public class SensorEventsManagerAdapter implements com.coolcompany.smarthome.events.EventHandler{

    private final EventHandler eventHandler;
    private final StringToSensorEventTypeAdapter stringToSensorEventTypeAdapter;

    public SensorEventsManagerAdapter(EventHandler eventHandler, StringToSensorEventTypeAdapter stringToSensorEventTypeAdapter) {
        this.eventHandler = eventHandler;
        this.stringToSensorEventTypeAdapter = stringToSensorEventTypeAdapter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType sensorEventType = stringToSensorEventTypeAdapter.get(event.getEventType());
        if (sensorEventType != null) {
            SensorEvent sensorEvent = new SensorEvent(sensorEventType, event.getObjectId());
            eventHandler.handle(sensorEvent);
        }
    }
}
