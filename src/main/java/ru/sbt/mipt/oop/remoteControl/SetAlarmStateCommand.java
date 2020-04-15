package ru.sbt.mipt.oop.remoteControl;

import ru.sbt.mipt.oop.SmartHome;

public class SetAlarmStateCommand implements Command {
    private final SmartHome smartHome;

    public SetAlarmStateCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().activateAlarmMode();
    }
}
