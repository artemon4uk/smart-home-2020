package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.signalization.ActiveState;
import ru.sbt.mipt.oop.signalization.AlarmState;
import ru.sbt.mipt.oop.signalization.PassiveState;
import ru.sbt.mipt.oop.signalization.Signalization;

import static org.junit.Assert.assertTrue;

public class SignalizationTest {

    @Test
    public void checkInitialState() {
        Signalization signalization = new Signalization();
        assertTrue(signalization.getState() instanceof PassiveState);
    }

    @Test
    public void checkActivation() {
        Signalization signalization = new Signalization();
        String code = "qwe";

        signalization.activate(code);

        assertTrue(signalization.getState() instanceof ActiveState);
    }

    @Test
    public void checkDeactivation() {
        Signalization signalization = new Signalization();
        String code = "qwe";

        signalization.activate(code);
        signalization.deactivate(code);

        assertTrue(signalization.getState() instanceof PassiveState);
    }

    @Test
    public void wrongCodeToAlarmMode() {
        Signalization signalization = new Signalization();
        String code = "qwe";
        String wrongCode = "123";

        signalization.activate(code);
        signalization.deactivate(wrongCode);

        assertTrue(signalization.getState() instanceof AlarmState);
    }

    @Test
    public void deactivationAfterAlarm() {
        Signalization signalization = new Signalization();
        String code = "qwe";
        String wrongCode = "123";

        signalization.activate(code);
        signalization.deactivate(wrongCode);
        assertTrue(signalization.getState() instanceof AlarmState);

        signalization.deactivate(code);
        assertTrue(signalization.getState() instanceof PassiveState);
    }

    @Test
    public void workWellAfterLoopActivationAndDeactivation() {
        Signalization signalization = new Signalization();
        String code = "qwe";
        String anotherCode = "123";


        signalization.activate(code);
        signalization.deactivate(code);

        assertTrue(signalization.getState() instanceof PassiveState);

        signalization.activate(anotherCode);
        assertTrue(signalization.getState() instanceof ActiveState);

        signalization.deactivate(anotherCode);
        assertTrue(signalization.getState() instanceof PassiveState);
    }
}