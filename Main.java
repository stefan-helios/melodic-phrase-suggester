package com.example.melphrasuggui;

// This class isn't necessary for running the program in the IDE, but for some reason, it is necessary
// for packaging the JavaFX dependencies in the JAR successfully. One source said that the entry point
// has to be a class that does *not* extend javafx.application.Application.
// Note that various entries in the POM.xml file refer to this as the main class.
public class Main {
    public static void main(final String[] args){
        HelloApplication.main(args);
    }
}
