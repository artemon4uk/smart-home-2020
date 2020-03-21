package ru.sbt.mipt.oop;

public class AlarmState extends State {

    public AlarmState(Signalization signalization) {
        super(signalization);
    }

    @Override
    public void activate(String code) {
        //Нельзя перевести в ActiveState, поэтому "ничего не делать".
    }

    @Override
    public void deactivate(String code) {
        if (signalization.checkEqualsCode(code)) {
            signalization.changeState(new PassiveState(signalization));
        } // Иначе остается в AlarmState.
    }

    @Override
    public void activateAlarmMode() {
        //Ничего не делать.
    }
}
