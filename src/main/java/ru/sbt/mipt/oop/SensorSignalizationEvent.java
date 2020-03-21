package ru.sbt.mipt.oop;

public class SensorSignalizationEvent extends SensorEvent {
    private final String code;

    public SensorSignalizationEvent(SensorEventType type, String objectId, String code) {
        super(type, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
