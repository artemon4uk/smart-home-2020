package ru.sbt.mipt.oop.signalization;

public class AlarmState implements State {
    private final Signalization signalization;
    private final String code;

    public AlarmState(Signalization signalization, String code) {
        this.signalization = signalization;
        this.code = code;
    }

    @Override
    public void activate(String code) {
        //Нельзя перевести в ActiveState, поэтому "ничего не делать".
    }

    @Override
    public void deactivate(String code) {
        if (this.code.equals(code)) {
            signalization.changeState(new PassiveState(signalization));
        } // Иначе остается в AlarmState.
    }

    @Override
    public void activateAlarmMode() {
        //Ничего не делать.
    }
}
