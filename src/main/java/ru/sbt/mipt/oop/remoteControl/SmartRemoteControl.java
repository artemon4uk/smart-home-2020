package ru.sbt.mipt.oop.remoteControl;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class SmartRemoteControl implements RemoteControl {
    private Map<String, Map<String, Command>> commandFromButton = new HashMap<>();

    public SmartRemoteControl(Map<String, Map<String, Command>> commandFromButton) {
        this.commandFromButton = commandFromButton;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (commandFromButton.containsKey(rcId) && commandFromButton.get(rcId).containsKey(buttonCode)) {
            commandFromButton.get(rcId).get(buttonCode).execute();
        }
    }
}
