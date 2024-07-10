package com.example.melphrasuggui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfugue.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    // The use of an ObservableList allows us to bind the list data to the ListView control
    private static ObservableList<String> phraseList;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Melodic Phrase Suggester");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static ObservableList<String> getPhrasesFromFile() {
        phraseList = FXCollections.observableArrayList();
        try {
            File myFileObj = new File("SavedMelodicPhrases.txt");
            Scanner myReader = new Scanner(myFileObj);
            while (myReader.hasNextLine()) {
                String phrase = myReader.nextLine();
                phraseList.add(phrase);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Text file does not yet exist; will be created upon exit");
        } finally {
            return phraseList;
        }
    }

    // Writes each line from the ObservableList to the disk file
    public static void writeListToFile() {
        try {
            FileWriter myWriter = new FileWriter("SavedMelodicPhrases.txt");
            for (String phrase : phraseList) {
                myWriter.write(phrase + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String generatePhrase(int numOfMeasures) {
        Phrase phrase = new Phrase(numOfMeasures);
        phrase.buildPhrase();
        String jFugueString = phrase.toJFugueString();
        playPhrase(jFugueString);
        return jFugueString;
    }

    public static void playPhrase(String phraseToPlay) {
        Player player = new Player();
        player.play(phraseToPlay);
    }

    public static void addPhrase(String phraseToAdd) {
        if (phraseList.indexOf(phraseToAdd) == -1) {
            phraseList.add(phraseToAdd);
        }
    }

    public static boolean deletePhrase(String phraseToDelete) {
        for (int i  = 0; i < phraseList.size(); i++) {
            if (phraseList.get(i).equals(phraseToDelete)) {
                phraseList.remove(i);
                return true;
            }
        }
        return false;
    }
}