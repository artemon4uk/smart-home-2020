package ru.sbt.mipt.oop.signalization;

public class ActiveState implements State {
    private final Signalization signalization;
    private final String code;

    public ActiveState(Signalization signalization, String code) {
        this.signalization = signalization;
        this.code = code;
    }

    @Override
    public void activate(String code) {
        //Ничего не делать.
    }

    @Override
    public void deactivate(String code) {
        if (this.code.equals(code)) {
            signalization.changeState(new PassiveState(signalization));
        } else {
            activateAlarmMode();
        }
    }

    @Override
    public void activateAlarmMode() {
        signalization.changeState(new AlarmState(signalization, code));
    }
}
