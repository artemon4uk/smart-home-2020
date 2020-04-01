package ru.sbt.mipt.oop.remoteControl;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private Map<String, Map<String, Command>> rcIdToButtonCodeToCommand = new HashMap<>();

    public RemoteControlImpl(Map<String, Map<String, Command>> rcIdToButtonCodeToCommand) {
        this.rcIdToButtonCodeToCommand = rcIdToButtonCodeToCommand;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        rcIdToButtonCodeToCommand.get(rcId).get(buttonCode).execute();
    }
}
