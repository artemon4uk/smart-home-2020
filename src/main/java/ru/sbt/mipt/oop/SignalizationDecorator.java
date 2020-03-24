package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.signalization.ActiveState;
import ru.sbt.mipt.oop.signalization.AlarmState;

import java.util.Collection;

public class SignalizationDecorator implements EventHandler {
    private SmartHome smartHome;
    private Collection<EventHandler> eventHandlers;
    private final Sender sender;

    public SignalizationDecorator(SmartHome smartHome, Collection<EventHandler> eventHandlers, Sender sender) {
        this.smartHome = smartHome;
        this.eventHandlers = eventHandlers;
        this.sender = sender;
    }

    @Override
    public void handle(SensorEvent event) {
        if (smartHome.getSignalization().getState() instanceof ActiveState) {
            smartHome.getSignalization().activateAlarmMode();
        }
        if (smartHome.getSignalization().getState() instanceof AlarmState) {
            sender.sendSMS();
        } else {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handle(event);
            }
        }
    }
}
