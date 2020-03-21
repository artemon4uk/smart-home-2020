package ru.sbt.mipt.oop;

public class PassiveState extends State {

    public PassiveState(Signalization signalization) {
        super(signalization);
    }

    @Override
    public void activate(String code) {
        signalization.changeCode(code);
        signalization.changeState(new ActiveState(signalization));
    }

    @Override
    public void deactivate(String code) {
        //Ничего не делать.
    }

    @Override
    public void activateAlarmMode() {
        signalization.changeState(new AlarmState(signalization));
    }
}
