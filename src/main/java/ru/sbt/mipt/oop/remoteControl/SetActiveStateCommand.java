package ru.sbt.mipt.oop.remoteControl;

import ru.sbt.mipt.oop.SmartHome;

public class SetActiveStateCommand implements Command {
    private final SmartHome smartHome;
    private final String code;

    public SetActiveStateCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().activate(code);
    }
}
