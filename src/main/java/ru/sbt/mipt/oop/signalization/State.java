package ru.sbt.mipt.oop.signalization;

public interface State {

    void activate(String code);

    void deactivate(String code);

    void activateAlarmMode();
}
