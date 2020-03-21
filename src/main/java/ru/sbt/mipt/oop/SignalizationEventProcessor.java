package ru.sbt.mipt.oop;

public class SignalizationEventProcessor implements EventHandler {
    private final SmartHome smartHome;

    public SignalizationEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private void handleSignalization(SensorSignalizationEvent event, boolean isActive) {
        smartHome.execute(homeObject -> {
            if (homeObject instanceof Signalization) {
                Signalization signalization = (Signalization) homeObject;
                if (isActive) {
                    signalization.activate(event.getCode());
                } else {
                    signalization.deactivate(event.getCode());
                }
            }
        });
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == SensorEventType.ALARM_ACTIVATE && event instanceof SensorSignalizationEvent) {
            handleSignalization((SensorSignalizationEvent) event, true);
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE && event instanceof SensorSignalizationEvent) {
            handleSignalization((SensorSignalizationEvent) event, false);
        }
    }
}
