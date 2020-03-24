package ru.sbt.mipt.oop.signalization;

public class PassiveState implements State {
    private final Signalization signalization;

    public PassiveState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        signalization.changeState(new ActiveState(signalization, code));
    }

    @Override
    public void deactivate(String code) {
        //Ничего не делать.
    }

    @Override
    public void activateAlarmMode() {
        //Так как мы не можем перевести из пассивного состояния в тревожное, то "Ничего не делать".
    }
}
