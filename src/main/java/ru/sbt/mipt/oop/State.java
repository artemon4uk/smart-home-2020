package ru.sbt.mipt.oop;

public abstract class State {
    protected Signalization signalization;

    public State(Signalization signalization) {
        this.signalization = signalization;
    }

    abstract public void activate(String code);

    abstract public void deactivate(String code);

    abstract public void activateAlarmMode();
}
