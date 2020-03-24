package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String... args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        SmartHomeHandler smartHomeHandler = applicationContext.getBean(SmartHomeHandler.class);
        smartHomeHandler.startHandle();
    }
}
