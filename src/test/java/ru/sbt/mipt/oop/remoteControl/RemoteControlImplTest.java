package ru.sbt.mipt.oop.remoteControl;

import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.signalization.ActiveState;
import ru.sbt.mipt.oop.signalization.AlarmState;
import ru.sbt.mipt.oop.signalization.Signalization;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Map;

public class RemoteControlImplTest {
    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");
    private Door hallDoor = new Door(true, "2");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);
    private Light hallLight = new Light("2", false);

    SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "bedroom"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "kitchen"),
            new Room(Arrays.asList(hallLight), Arrays.asList(hallDoor), "hall")
    ), new Signalization());

    @Test
    public void onButtonPressed() {
        String code = "123";
        RemoteControlImpl remoteControl = new RemoteControlImpl(Map.of("id1", Map.of(
                "A", new CloseHallDoorCommand(smartHome),
                "B", new SetActiveStateCommand(smartHome, code),
                "C", new SetAlarmStateCommand(smartHome),
                "1", new SetOffAllLightsCommand(smartHome),
                "2", new SetOnAllLightsCommand(smartHome),
                "3", new SetOnHallLightCommand(smartHome)
        )));

        remoteControl.onButtonPressed("A", "id1");
        assertFalse(hallDoor.isOpen());

        remoteControl.onButtonPressed("B", "id1");
        assertTrue(smartHome.getSignalization().getState() instanceof ActiveState);

        remoteControl.onButtonPressed("C", "id1");
        assertTrue(smartHome.getSignalization().getState() instanceof AlarmState);
        smartHome.getSignalization().deactivate(code);

        remoteControl.onButtonPressed("1", "id1");
        assertFalse(light0.isOn());
        assertFalse(light1.isOn());
        assertFalse(hallLight.isOn());

        remoteControl.onButtonPressed("2", "id1");
        assertTrue(light0.isOn());
        assertTrue(light1.isOn());
        assertTrue(hallLight.isOn());

        hallLight.setState(false);
        remoteControl.onButtonPressed("3", "id1");
        assertTrue(hallLight.isOn());
    }
}