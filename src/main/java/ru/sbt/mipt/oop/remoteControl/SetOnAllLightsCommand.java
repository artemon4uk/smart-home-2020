package ru.sbt.mipt.oop.remoteControl;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class SetOnAllLightsCommand implements Command {
    private final SmartHome smartHome;

    public SetOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeObject -> {
            if (homeObject instanceof Light) {
                Light light = (Light) homeObject;
                light.setState(true);
            }
        });
    }
}
