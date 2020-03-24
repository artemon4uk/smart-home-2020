package ru.sbt.mipt.oop.signalization;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Signalization implements Actionable {

    private State state;

    public Signalization() {
        this.state = new PassiveState(this);    
    }

    public State getState() {
        return state;
    }

    protected void changeState(State state) {
        this.state = state;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void activateAlarmMode() {
        state.activateAlarmMode();
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
