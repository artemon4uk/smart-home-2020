package ru.sbt.mipt.oop;

public class Decorator implements EventHandler {
    private SmartHome smartHome;
    private EventHandler eventHandler;

    public Decorator(SmartHome smartHome, EventHandler eventHandler) {
        this.smartHome = smartHome;
        this.eventHandler = eventHandler;
    }

    @Override
    public void handle(SensorEvent event) {
        if (smartHome.getSignalization().getState() instanceof ActiveState) {
            smartHome.getSignalization().activateAlarmMode();
        }
        if (smartHome.getSignalization().getState() instanceof AlarmState) {
            Sender sender = new Sender();
            sender.sendSMS();
        } else {
            eventHandler.handle(event);
        }
    }
}
