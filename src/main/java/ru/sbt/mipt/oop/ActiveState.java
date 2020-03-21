package ru.sbt.mipt.oop;

public class ActiveState extends State {

    public ActiveState(Signalization signalization) {
        super(signalization);
    }

    @Override
    public void activate(String code) {
        //Ничего не делать.
    }

    @Override
    public void deactivate(String code) {
        if (signalization.checkEqualsCode(code)) {
            signalization.changeState(new PassiveState(signalization));
        } else {
            activateAlarmMode();
        }
    }

    @Override
    public void activateAlarmMode() {
        signalization.changeState(new AlarmState(signalization));
    }
}
