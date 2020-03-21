package ru.sbt.mipt.oop;

public class Signalization implements Actionable {

    private State state;
    private String code;

    public Signalization() {
        this.state = new PassiveState(this);    
    }

    public State getState() {
        return state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void changeCode(String code) {
        this.code = code;
    }

    public boolean checkEqualsCode(String newCode) {
        return code.equals(newCode);
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
