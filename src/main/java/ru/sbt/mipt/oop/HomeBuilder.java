package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.signalization.BuildConfiguration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BuildConfiguration.class);

        String jsonString = applicationContext.getBean(String.class, "getJson");
        System.out.println(jsonString);

        Path path = applicationContext.getBean(Path.class);
        printPath(jsonString, path);
    }

    private static void printPath(String jsonString, Path path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}
