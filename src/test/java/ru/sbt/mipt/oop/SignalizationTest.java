package ru.sbt.mipt.oop;

import org.junit.Test;

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
    public void checkAlarmMode() {
        Signalization signalization = new Signalization();

        signalization.activateAlarmMode();

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