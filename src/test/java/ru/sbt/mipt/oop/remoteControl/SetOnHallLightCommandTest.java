package ru.sbt.mipt.oop.remoteControl;

import org.junit.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import static org.junit.Assert.*;

import java.util.Arrays;

public class SetOnHallLightCommandTest {
    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");
    private Door door2 = new Door(true, "2");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);
    private Light hallLight = new Light("2", false);

    SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "bedroom"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "kitchen"),
            new Room(Arrays.asList(hallLight), Arrays.asList(door2), "hall")
    ));

    @Test
    public void execute() {
        Command command = new SetOnHallLightCommand(smartHome);
        hallLight.setState(false);

        command.execute();

        assertTrue(hallLight.isOn());
    }
}