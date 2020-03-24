package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        SmartHome smartHome = initSmartHome();

        String jsonString = getJson(smartHome);
        System.out.println(jsonString);

        Path path = Paths.get("output.js");
        printPath(jsonString, path);
    }

    private static SmartHome initSmartHome() {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(false, "4")),
                "hall");
        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }

    private static void printPath(String jsonString, Path path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

    private static String getJson(SmartHome smartHome) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(smartHome);
    }

}
