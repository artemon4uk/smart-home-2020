package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String... args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        SensorEventsManager sensorEventsManager = applicationContext.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
