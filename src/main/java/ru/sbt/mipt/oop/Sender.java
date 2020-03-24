package ru.sbt.mipt.oop;

public class Sender {
    public void sendCommand(String string, SensorCommand command) {
        System.out.println(string + command);
    }

    public void sendSMS() {
        System.out.println("Sending sms");
    }
}
